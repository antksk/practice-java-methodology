package com.hancof.methodology.part4;

import com.hancof.methodology.part4.capstone.before.OrderAggregate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

/**
 * 캡스톤 완성 판정.
 *
 * after 패키지가 비어 있는 동안에는 완성 기준 테스트가 SKIP 된다.
 * 클래스를 추가하는 순간부터 "모든 필드 final" 규칙이 자동 검사된다.
 */
class CapstoneCompletionTest {

    private static final String AFTER_PACKAGE = "com.hancof.methodology.part4.capstone.after";

    @Test
    @DisplayName("[기준선] before.OrderAggregate 계산 결과 — after는 같은 값을 내야 한다")
    void beforeBaseline() {
        OrderAggregate order = new OrderAggregate();
        order.setCustomer("김하늘", "VIP");
        order.addLine("키보드", 45_000, 2);
        order.addLine("마우스", 15_000, 1);

        assertThat(order.getTotal()).isEqualTo(94_500L); // 105,000 - VIP 10%
    }

    @Test
    @DisplayName("[완성 기준] after 패키지의 모든 필드는 final (상태 변경 메서드 0개)")
    void afterPackageIsImmutable() throws Exception {
        List<Class<?>> classes = loadAfterClasses();
        assumeFalse(classes.isEmpty(), "after 패키지가 아직 비어 있음 — 캡스톤 진행 후 자동 활성화");

        for (Class<?> type : classes) {
            for (Field field : type.getDeclaredFields()) {
                if (field.isSynthetic()) {
                    continue;
                }
                assertThat(Modifier.isFinal(field.getModifiers()))
                        .as("%s.%s 는 final 이어야 한다 (불변 모델 규칙)", type.getSimpleName(), field.getName())
                        .isTrue();
            }
        }
    }

    private List<Class<?>> loadAfterClasses() throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        String path = AFTER_PACKAGE.replace('.', '/');
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null || !"file".equals(url.getProtocol())) {
            return classes;
        }
        File[] files = new File(url.toURI()).listFiles((dir, name) -> name.endsWith(".class"));
        if (files == null) {
            return classes;
        }
        for (File file : files) {
            String simple = file.getName().substring(0, file.getName().length() - ".class".length());
            if (simple.equals("package-info") || simple.contains("$")) {
                continue;
            }
            classes.add(Class.forName(AFTER_PACKAGE + "." + simple));
        }
        return classes;
    }
}
