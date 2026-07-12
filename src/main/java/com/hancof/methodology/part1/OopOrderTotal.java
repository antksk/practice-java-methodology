package com.hancof.methodology.part1;

import java.util.List;

/**
 * [버전 2] OOP — 주문이 데이터(라인들)와 행위(합계 계산)를 함께 가진다.
 *
 * 관찰 과제: 절차형과 비교해 "누가" 계산하는지가 어떻게 달라졌는가?
 * 할인 규칙이 바뀌면 어떤 코드가 바뀌는가?
 */
public class OopOrderTotal {

    private final List<OrderLine> lines;

    public OopOrderTotal(List<OrderLine> lines) {
        this.lines = List.copyOf(lines);
    }

    /** 10만원 이상이면 5% 할인된 합계. */
    public long total() {
        long gross = grossTotal();
        return gross >= 100_000 ? gross - (gross * 5 / 100) : gross;
    }

    private long grossTotal() {
        long sum = 0;
        for (OrderLine line : lines) {
            sum += line.unitPrice() * line.quantity();
        }
        return sum;
    }
}
