package com.hancof.methodology.part1;

import java.util.List;
import java.util.function.LongUnaryOperator;

/**
 * [버전 3] FP — 합계는 데이터의 "흐름"으로, 할인은 "함수 값"으로 표현한다.
 *
 * 관찰 과제: 이 버전에서 재할당되는 변수가 하나라도 있는가?
 * 할인 규칙을 다른 함수로 갈아끼우려면 무엇이 필요한가?
 */
public class FpOrderTotal {

    /** 10만원 이상이면 5% 할인. 규칙 자체가 값(함수)이다. */
    static final LongUnaryOperator OVER_100K_5PERCENT =
            gross -> gross >= 100_000 ? gross - (gross * 5 / 100) : gross;

    public static long calculate(List<OrderLine> lines) {
        return calculate(lines, OVER_100K_5PERCENT);
    }

    public static long calculate(List<OrderLine> lines, LongUnaryOperator discountRule) {
        long gross = lines.stream()
                .mapToLong(line -> line.unitPrice() * line.quantity())
                .sum();
        return discountRule.applyAsLong(gross);
    }

    private FpOrderTotal() {
    }
}
