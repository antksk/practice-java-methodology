# java-methodology-practice

**자바로 배우는 개발 방법론 (OOP + FP + Java 21)** 강좌의 실습 코드 저장소.

> 커리큘럼 원본: ita vault `05_CONTENT/30_LECTURE/draft_자바로_배우는_개발방법론.md` — Curriculum 섹션의 실습 1~4와 1:1 대응.

## 요구 환경

- Java 21 (Temurin 권장)
- 빌드: `./gradlew test` — 처음 상태에서 전부 green이어야 정상

## 구조

```
src/main/java/com/hancof/methodology/
├── part1/                     실습 1 (관찰) — 같은 요구사항의 절차형/OOP/FP 3버전
├── part2/before/              실습 2 대상 — 자바빈 Order + getter 체인 서비스
├── part2/after/               ← 실습 2 결과물 자리 (package-info에 목표 명세)
├── part3/before/              실습 3 대상 — 명령형 루프 3개 + 부작용 섞인 할인 계산
├── part3/after/               ← 실습 3 결과물 자리
├── part4/capstone/before/     캡스톤 대상 — 가변 주문 도메인 (문제 총집합)
└── part4/capstone/after/      ← 캡스톤 결과물 자리
```

## 실습 진행 방법

| 실습 | 레슨 | 할 일 | 판정 |
|---|---|---|---|
| 1. 관찰 | 1-5 | `part1` 3버전을 읽고 상태 변경 지점 표시·비교 | `ThreeVersionsEquivalenceTest` (전제 확인) |
| 2. 모델링 | 2-7, 2-8 | `part2/after`에 캡슐화 Order/Money 설계, getter 체인 제거 | `OrderReportCharacterizationTest`와 같은 결과 |
| 3. 전환 | 3-8, 3-9 | `part3/after`에 Stream 전환 + 순수 함수 분리 | `Part3CharacterizationTest`와 같은 결과 |
| 4. 캡스톤 | 4-8 | `part4/capstone/after`에 record + FCC + 파이프라인 재구성 | `CapstoneCompletionTest` — after에 클래스가 생기면 "모든 필드 final" 규칙 자동 검사 |

각 `after` 패키지의 `package-info.java`에 상세 목표가 있다.

## 규칙

- `before` 코드는 수정하지 않는다 (강의 대본·characterization 테스트의 기준).
- characterization 테스트가 고정한 계산 결과는 리팩토링 후에도 같아야 한다.
- 캡스톤 완성 기준: after 패키지 상태 변경 메서드 0개(모든 필드 final) + 전체 테스트 green.
