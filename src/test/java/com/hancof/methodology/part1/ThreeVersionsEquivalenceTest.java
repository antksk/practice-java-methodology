package com.hancof.methodology.part1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Part 1 관찰 과제의 전제: 세 버전은 "같은 일"을 한다.
 * 다른 것은 코드가 아니라 사고 방식이다.
 */
class ThreeVersionsEquivalenceTest {

    private final List<OrderLine> lines = List.of(
            new OrderLine("키보드", 45_000, 2),
            new OrderLine("마우스", 15_000, 1),
            new OrderLine("모니터암", 60_000, 1)
    );

    @Test
    @DisplayName("절차형·OOP·FP 세 버전은 같은 합계를 낸다 (10만원 이상 5% 할인)")
    void allVersionsAgree() {
        long procedural = ProceduralOrderTotal.calculate(lines);
        long oop = new OopOrderTotal(lines).total();
        long fp = FpOrderTotal.calculate(lines);

        assertThat(procedural).isEqualTo(156_750L); // 165,000 - 5%
        assertThat(oop).isEqualTo(procedural);
        assertThat(fp).isEqualTo(procedural);
    }

    @Test
    @DisplayName("10만원 미만이면 할인 없음")
    void noDiscountUnderThreshold() {
        List<OrderLine> small = List.of(new OrderLine("케이블", 9_000, 3));

        assertThat(ProceduralOrderTotal.calculate(small)).isEqualTo(27_000L);
        assertThat(new OopOrderTotal(small).total()).isEqualTo(27_000L);
        assertThat(FpOrderTotal.calculate(small)).isEqualTo(27_000L);
    }
}
