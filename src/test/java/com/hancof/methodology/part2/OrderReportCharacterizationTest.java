package com.hancof.methodology.part2;

import com.hancof.methodology.part2.before.Customer;
import com.hancof.methodology.part2.before.Order;
import com.hancof.methodology.part2.before.OrderLine;
import com.hancof.methodology.part2.before.OrderReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * [Characterization] before 코드의 현재 동작을 고정한다.
 * 실습 ①·②의 after 설계는 이 결과와 같은 값을 내야 한다.
 */
class OrderReportCharacterizationTest {

    private final OrderReportService service = new OrderReportService();

    @Test
    @DisplayName("VIP 주문 요약: 등급 라벨 + 합계 + 상태")
    void vipSummary() {
        Order order = order("김하늘", "VIP", "PLACED");

        assertThat(service.summaryLine(order))
                .isEqualTo("[VIP] 김하늘 주문 합계 105000원 (PLACED)");
    }

    @Test
    @DisplayName("일반 등급 주문 요약")
    void basicSummary() {
        Order order = order("이바다", "BASIC", "PAID");

        assertThat(service.summaryLine(order))
                .isEqualTo("[일반] 이바다 주문 합계 105000원 (PAID)");
    }

    private Order order(String name, String grade, String status) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setGrade(grade);

        OrderLine line1 = new OrderLine();
        line1.setProductName("키보드");
        line1.setUnitPrice(45_000);
        line1.setQuantity(2);

        OrderLine line2 = new OrderLine();
        line2.setProductName("마우스");
        line2.setUnitPrice(15_000);
        line2.setQuantity(1);

        Order order = new Order();
        order.setCustomer(customer);
        order.setLines(List.of(line1, line2));
        order.setStatus(status);
        return order;
    }
}
