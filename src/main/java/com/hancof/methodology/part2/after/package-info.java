/**
 * [After · 실습 결과물 자리] Part 2 — 캡슐화된 주문 도메인을 여기에 새로 설계한다.
 *
 * <p>목표:
 * <ul>
 *   <li>Order: 생성 시 불변식 강제(빈 라인 금지), setter 없음, 합계·요약은 행위 메서드로</li>
 *   <li>Money: 금액을 원시 long 대신 값 객체로 (음수 금지)</li>
 *   <li>OrderReportService의 getter 체인 로직을 도메인 행위로 흡수</li>
 * </ul>
 *
 * <p>비교 기준: before.OrderReportService.summaryLine과 같은 결과를 내되,
 * 서비스에는 위임 한 줄만 남긴다.
 */
package com.hancof.methodology.part2.after;
