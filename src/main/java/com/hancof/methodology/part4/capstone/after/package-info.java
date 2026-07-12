/**
 * [After · 캡스톤 결과물 자리] Part 4 — 주문 도메인을 OOP 모델 + FP 파이프라인으로 재구성한다.
 *
 * <p>구성 요소:
 * <ul>
 *   <li>record 데이터 모델: Order / OrderLine / Money / Customer</li>
 *   <li>First-Class Collection: OrderLines (라인 컬렉션 + 집계 행위)</li>
 *   <li>할인: 함수 값(예: {@code UnaryOperator<Money>}) 또는 순수 정적 함수</li>
 * </ul>
 *
 * <p>완성 기준 — {@code CapstoneCompletionTest}가 자동 검사:
 * 이 패키지의 모든 클래스 필드는 final이어야 한다 (상태 변경 메서드 0개).
 * 계산 결과는 before.OrderAggregate와 동일해야 한다.
 */
package com.hancof.methodology.part4.capstone.after;
