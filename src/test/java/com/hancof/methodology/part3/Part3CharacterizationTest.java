package com.hancof.methodology.part3;

import com.hancof.methodology.part1.OrderLine;
import com.hancof.methodology.part3.before.DiscountCalculator;
import com.hancof.methodology.part3.before.OrderStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * [Characterization] Part 3 before의 계산 결과를 고정한다.
 * Stream 전환·순수 함수 분리 후에도 이 값은 유지되어야 한다.
 */
class Part3CharacterizationTest {

    private final List<OrderLine> lines = List.of(
            new OrderLine("키보드", 45_000, 2),
            new OrderLine("마우스", 15_000, 1),
            new OrderLine("키보드", 45_000, 1),
            new OrderLine("케이블", 9_000, 5)
    );

    private final OrderStatistics statistics = new OrderStatistics();

    @Test
    @DisplayName("단가 1만원 이상 상품명 (중복 포함, 입력 순서 유지)")
    void expensiveProductNames() {
        assertThat(statistics.expensiveProductNames(lines, 10_000))
                .containsExactly("키보드", "마우스", "키보드");
    }

    @Test
    @DisplayName("전체 수량 합")
    void totalQuantity() {
        assertThat(statistics.totalQuantity(lines)).isEqualTo(9);
    }

    @Test
    @DisplayName("상품명별 수량 합계")
    void quantityByProduct() {
        assertThat(statistics.quantityByProduct(lines))
                .isEqualTo(Map.of("키보드", 3, "마우스", 1, "케이블", 5));
    }

    @Test
    @DisplayName("할인: VIP 10% > 10만원 5% — 큰 쪽 하나만 적용")
    void discountRules() {
        assertThat(new DiscountCalculator().apply("VIP", 200_000)).isEqualTo(180_000L);
        assertThat(new DiscountCalculator().apply("BASIC", 200_000)).isEqualTo(190_000L);
        assertThat(new DiscountCalculator().apply("BASIC", 50_000)).isEqualTo(50_000L);
    }
}
