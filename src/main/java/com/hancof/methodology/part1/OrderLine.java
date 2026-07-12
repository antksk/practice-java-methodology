package com.hancof.methodology.part1;

/**
 * Part 1 공통 주문 라인. 세 버전(절차형/OOP/FP)이 같은 입력을 받도록 고정한다.
 * 수량 곱은 각 버전이 스스로 계산한다.
 */
public record OrderLine(String productName, long unitPrice, int quantity) {
}
