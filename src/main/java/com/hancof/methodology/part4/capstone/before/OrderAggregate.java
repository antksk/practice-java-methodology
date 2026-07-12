package com.hancof.methodology.part4.capstone.before;

import java.util.ArrayList;
import java.util.List;

/**
 * [Before · 캡스톤] Part 2~3의 문제가 전부 모인 가변 주문 도메인.
 *
 * 캡스톤 (레슨 4-8): part4.capstone.after에 다음 조합으로 재구성하라.
 *   - 데이터 모델: record (Order, OrderLine, Money, Customer)
 *   - 라인 컬렉션: First-Class Collection (OrderLines)
 *   - 할인·집계 로직: Stream 파이프라인 + 순수 함수
 *
 * 완성 기준 (CapstoneCompletionTest가 검사):
 *   - after 패키지의 모든 필드는 final (상태 변경 메서드 0개, 생성 제외)
 *   - 기존 before와 같은 계산 결과
 */
public class OrderAggregate {

    private String customerName;
    private String customerGrade;
    private List<Object[]> lines = new ArrayList<>(); // [productName, unitPrice, quantity]
    private long total;
    private boolean calculated;

    public void setCustomer(String name, String grade) {
        this.customerName = name;
        this.customerGrade = grade;
    }

    public void addLine(String productName, long unitPrice, int quantity) {
        lines.add(new Object[]{productName, unitPrice, quantity});
        this.calculated = false;
    }

    /** 합계를 계산해 내부 상태에 저장한다. VIP 10%, 10만원 이상 5%, 중복 시 큰 쪽. */
    public void calculate() {
        long gross = 0;
        for (Object[] line : lines) {
            gross += (long) line[1] * (int) line[2];
        }

        int rate = 0;
        if ("VIP".equals(customerGrade)) {
            rate = 10;
        }
        if (gross >= 100_000 && rate < 5) {
            rate = 5;
        }

        this.total = gross - (gross * rate / 100);
        this.calculated = true;
    }

    public long getTotal() {
        if (!calculated) {
            calculate(); // 숨은 상태 의존 — getter가 계산을 유발한다
        }
        return total;
    }

    public String getCustomerName() {
        return customerName;
    }
}
