package com.hancof.methodology.part2.before;

/**
 * [Before] getter 체인으로 주문 데이터를 "물어봐서" 서비스가 직접 계산한다.
 *
 * 관찰 포인트:
 *   - VIP 판정·합계 계산 로직이 주문이 아니라 서비스에 산다 (Ask)
 *   - Customer 필드 구조가 바뀌면 이 서비스도 같이 바뀐다
 *
 * 실습 ② 목표: 이 로직을 Order/Customer의 행위로 옮겨 getter 체인을 없애라 (Tell).
 */
public class OrderReportService {

    public String summaryLine(Order order) {
        long total = 0;
        for (OrderLine line : order.getLines()) {
            total += line.getUnitPrice() * line.getQuantity();
        }

        String label;
        if (order.getCustomer() != null
                && order.getCustomer().getGrade() != null
                && order.getCustomer().getGrade().equals("VIP")) {
            label = "[VIP] " + order.getCustomer().getName();
        } else {
            label = "[일반] " + (order.getCustomer() == null ? "손님" : order.getCustomer().getName());
        }

        return label + " 주문 합계 " + total + "원 (" + order.getStatus() + ")";
    }
}
