package com.hancof.methodology.part3.before;

/**
 * [Before] 부작용이 섞인 할인 계산 — 계산 도중 로그를 남기고 인스턴스 상태를 바꾼다.
 *
 * 관찰 포인트:
 *   - 같은 입력으로 두 번 호출하면 lastAppliedRate가 달라질 수 있는가?
 *   - 이 메서드를 테스트하려면 무엇까지 검증해야 하는가?
 *
 * 실습 ② (레슨 3-9): part3.after에서 "순수한 계산"과 "부작용(로그·상태)"을 분리하라.
 *   - 할인율 결정·금액 계산은 순수 함수로
 *   - 로그는 호출자 몫으로 (계산 결과를 받아서 남긴다)
 */
public class DiscountCalculator {

    private int callCount = 0;
    private int lastAppliedRate = 0;

    /** 등급·금액에 따라 할인 적용가를 돌려준다. VIP 10%, 10만원 이상 5%, 중복 시 큰 쪽. */
    public long apply(String grade, long amount) {
        callCount++; // 부작용 1: 인스턴스 상태 변경

        int rate = 0;
        if ("VIP".equals(grade)) {
            rate = 10;
        }
        if (amount >= 100_000 && rate < 5) {
            rate = 5;
        }

        lastAppliedRate = rate; // 부작용 2: 계산 결과를 상태로 저장
        System.out.println("[discount] call#" + callCount + " grade=" + grade + " rate=" + rate); // 부작용 3: I/O

        return amount - (amount * rate / 100);
    }

    public int lastAppliedRate() {
        return lastAppliedRate;
    }
}
