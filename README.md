<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-6.14.15-blue">
  <img alt="node" src="https://img.shields.io/badge/node-14.18.2-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-admin">
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
<br>

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

<br>

## 🐞 Bug Report

버그를 발견한다면, [Issues](https://github.com/next-step/atdd-subway-service/issues) 에 등록해주세요 :)

<br>

## 📝 License

This project is [MIT](https://github.com/next-step/atdd-subway-service/blob/master/LICENSE.md) licensed.

<br> 

## 🚀 1단계 - 인수 테스트 기반 리팩터링

### 인수 테스트 기반 리팩터링
1. Domain으로 옮길 로직을 찾기
   * 스프링 빈을 사용하는 객체와 의존하는 로직을 제외하고 그 외 로직을 도메인으로 이동시킨다.
   * 객체지향 생활체조를 참고한다.
2. Domain의 단위 테스트를 작성하기
   * 서비스 레이어에서 옮겨 올 로직의 기능을 테스트한다.
3. 로직 옮기기
   * 기존 로직을 지우지 말고 새로운 로직을 만들어 수행한다.
   * 정상 동작 확인 후 기존 로직을 제거한다.

### 구현 리스트
* 지하철 구간 리스트 일급 컬렉션으로 분리
  * 구간 추가 기능 이동
  * 구간 삭제 기능 이동
  * 지하철 역 정렬 기능 이동
* 도메인 테스트 코드 작성

## 🚀 2단계 - 경로 조회 기능

### 요구사항
* 최단 경로 조회 기능 구현하기
  * 최단 경로 라이브러리(`jgrapht`) 활용하여 최단거리 조회 기능을 구현한다.
    * 정점: 지하철역(Station)
    * 간선: 지하철역 연결정보(Section)
    * 가중치: 거리
  * Outside In 혹은 Inside Out 방식으로 기능을 구현한다.
* 경로 조회 인수 테스트 만들기
  * 최단경로 조회 및 예외 케이스에 대한 인수 테스트 작성한다.

### 구현 리스트
* 경로를 나타내는 도메인과 최단경로를 조회할 도메인 분리
* 최단경로 조회 도메인에 대한 테스트 코드 작성
* 최단경로 조회 인수 테스트 코드 작성

## 🚀 3단계 - 인증을 통한 기능 구현

### 요구사항
* 토큰 발급 기능(로그인) 인수 테스트
  * 토큰 발급(로그인)을 검증하는 인수 테스트(`AuthAcceptanceTest`)를 작성한다.
  * 유효하지 않은 토큰으로 `/members/me`에 요청을 보내는 케이스에 대한 인수 테스트를 작성한다.
* [인증] 내 정보 조회 기능 구현 및 인수 테스트
  * 인수 테스트
    * `MemberAcceptanceTest` 클래스의 `manageMyInfo` 메서드에 인수 테스트를 추가한다.
    * `/members/me` URI 요청 시 내 정보 조회, 수정, 삭제 기능이 동작하도록 검증한다.
    * 로그인 후 발급 받은 토큰을 포함해서 요청한다.
  * 토큰을 통한 인증
    * `/members/me` 요청 시 토큰을 확인하여 로그인 정보를 받아올 수 있도록 한다.
    * `@AuthenticationPrincipal`과 `AuthenticationPrincipalArgumentResolver`를 활용한다.
    * `/members/me` URI 요청 시 내 정보 조회, 수정, 삭제 기능이 동작하도록 구현한다.
* [인증] 즐겨 찾기 기능 완성하기
  * 즐겨찾기 기능을 완성한다.
  * 인증을 포함하여 전체 ATDD 사이클을 경험할 수 있도록 기능을 구현한다.

## 🚀 4단계 - 요금 조회

### 요구사항
* 경로 조회 시 거리 기준 요금 정보 포함하기
  ```
  ■ 기본운임(10㎞ 이내)
    - 기본운임 1,250원
  ■ 이용 거리초과 시 추가운임 부과
    - 10km 초과 ∼ 50km 까지 : 5km마다 100원
    - 50km 초과 : 8km 마다 100원
  ```
* 노선별 추가 요금 정책 추가
  ```
  ■ 추가 요금이 있는 노선을 이용 할 경우 측정된 요금에 추가
    - ex) 900원 추가 요금이 있는 노선 8km 이용 시 1,250원 -> 2,150원 (= 1,250 + 900)
    - ex) 900원 추가 요금이 있는 노선 12km 이용 시 1,350원 -> 2,250원 (= 1,250 + 100 + 900)
  ■ 경로 중 추가요금이 있는 노선을 환승 하여 이용 할 경우 가장 높은 금액의 추가 요금만 적용
    - ex) 0원, 500원, 900원의 추가 요금이 있는 노선들을 경유하여 8km 이용 시 1,250원 -> 2,150원 (= 1,250 + 900)
  ```
* 연령별 할인 정책 추가
  ```
  ■ 로그인 사용자의 경우 연령별 요금 할인 적용
    - 청소년: 운임에서 350원을 공제한 금액의 20% 할인
    - 어린이: 운임에서 350원을 공제한 금액의 50% 할인
  ■ 연령 정의
    - 청소년: 13세 이상, 19세 미만
    - 어린이: 6세 이상, 13세 미만  
  ```
