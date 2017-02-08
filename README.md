# Project

### Trello

### 일정관리
[일정관리](https://trello.com/b/SutlFoSu)

### 기획서
[기획서] (https://docs.google.com/presentation/d/1SnGbLKfjpiat-wvAXW7e066hoCg1-kDxeOSBf5GId58/edit#slide=id.g1c44c5147d_0_8)

#### 개발 환경
```
HTML5
CSS
Javascript
Spring Boot
JPA
Thymleaf
MySql
```

---
#### 개발 일지
###### 1월 31일 ~ 2월 5일
```
1. 트렐로 기능 파악
2. Spring Boot 학습(www.udemy.com)
3. Spring Boot Trello Project Launch
4. Database(MySql) 연동, JPA 사용
5. db에서 데이터를 읽어와 동적 페이지 할당 성공, Thymleaf 사용

```
###### 2월 6일
```
크롱과 상담 후 트렐로의 기본 동작 파악
1. 서버와 통신이 없는 동작은 자바스크립트로 구현
2. submit 이나 anchor로 서버에 request를 보낼 시 ajax 사용
3. 서버에서 response가 오면 그에 해당하는 동작 자바스크립트로 구현

- List add 동작 구현
```
###### 2월 7일
```
데이타 구조 변경
before : List(one) - Card(many)
after : Board(one) - List(many)
        Board(one) - Card(many)
        
Board
id - primary key
title


List
id - primary key
title
position - not null
board_id - foreign key

Card
id - primary key
title
position
list_position - foreign key
board_id - foreign key
```

```
크롱과 상담
백엔드 단에서의 동적 페이지 구축 최소화
웹페이지의 랜더링은 클라이언트 측에 json 파일로 자료를 넘겨줘서 구축

apiController 추가
웹페이지 로딩시 해당하는 보드의 json 파일 전송
클라이언트 측에서 랜더링 성공 - 지속적인 수정

card add 동작 구현
```

###### 2월 8일
```
list와 card 추가시 위치를 계산하여 position 값을 추가함
데이터를 받은 후 화면을 구축할 때 포지션값으로 정렬을 수행해 위치를 잡아줌
코드 리팩토링 - 중복된 코드 제거
```