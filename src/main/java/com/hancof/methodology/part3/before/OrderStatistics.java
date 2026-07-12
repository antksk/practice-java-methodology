package com.hancof.methodology.part3.before;

import com.hancof.methodology.part1.OrderLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [Before] 명령형 루프 3개 — 임시 변수와 중간 컬렉션을 계속 덮어쓴다.
 *
 * 실습 ① (레슨 3-8): 세 메서드를 part3.after에서 Stream 파이프라인으로 다시 써라.
 *   - 재할당 변수 0개, 중간 컬렉션 직접 조작 0회가 목표
 *   - expensiveProductNames는 filter/map, totalQuantity는 mapToInt/sum,
 *     quantityByProduct는 Collectors.groupingBy + summingInt 후보
 */
public class OrderStatistics {

    /** 단가가 threshold 이상인 상품명 목록. */
    public List<String> expensiveProductNames(List<OrderLine> lines, long threshold) {
        List<String> names = new ArrayList<>();
        for (OrderLine line : lines) {
            if (line.unitPrice() >= threshold) {
                names.add(line.productName());
            }
        }
        return names;
    }

    /** 전체 주문 수량 합. */
    public int totalQuantity(List<OrderLine> lines) {
        int total = 0;
        for (OrderLine line : lines) {
            total = total + line.quantity();
        }
        return total;
    }

    /** 상품명별 수량 합계. */
    public Map<String, Integer> quantityByProduct(List<OrderLine> lines) {
        Map<String, Integer> result = new HashMap<>();
        for (OrderLine line : lines) {
            Integer current = result.get(line.productName());
            if (current == null) {
                result.put(line.productName(), line.quantity());
            } else {
                result.put(line.productName(), current + line.quantity());
            }
        }
        return result;
    }
}
