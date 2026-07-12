package com.hancof.methodology.part2.before;

import java.util.ArrayList;
import java.util.List;

/**
 * [Before] 자바빈 스타일 주문 — 전부 getter/setter, 불변식 없음.
 *
 * 실습 ① (레슨 2-7): part2.after에 캡슐화된 Order/Money를 새로 설계하라.
 *   - 생성 시점에 불변식 강제 (라인 없는 주문 금지, 음수 금액 금지)
 *   - setter 제거, 상태 변경은 의미 있는 행위 메서드로만
 * 실습 ② (레슨 2-8): OrderReportService의 getter 체인을 행위 위임으로 제거하라.
 */
public class Order {

    private Customer customer;
    private List<OrderLine> lines = new ArrayList<>();
    private String status;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
