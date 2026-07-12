package com.hancof.methodology.part1;

import java.util.List;

/**
 * [버전 1] 절차형 — 데이터와 로직이 분리되고, 지역 상태를 계속 덮어쓴다.
 *
 * 관찰 과제: 상태가 "변경"되는 지점에 전부 주석으로 표시해 보라.
 * (total, i, discounted 가 몇 번 바뀌는가?)
 */
public class ProceduralOrderTotal {

    /** 10만원 이상이면 5% 할인된 합계를 돌려준다. */
    public static long calculate(List<OrderLine> lines) {
        long total = 0;
        for (int i = 0; i < lines.size(); i++) {
            OrderLine line = lines.get(i);
            total += line.unitPrice() * line.quantity();
        }
        long discounted = total;
        if (total >= 100_000) {
            discounted = total - (total * 5 / 100);
        }
        return discounted;
    }

    private ProceduralOrderTotal() {
    }
}
