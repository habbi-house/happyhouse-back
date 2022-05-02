# Happy House Project

## 1. 프로젝트 진행 방식

- 기존의 Back-End Happy House 프로젝트를 Spring으로 이전한다.
- MyBatis framework를 통해 코드와 SQL을 분리한다.

<br>
  
## 2. 요구사항 분석
- 요구사항을 분석하여 팀 프로젝트 방향과 알맞게 요구사항을 수정 및 반영한다. 
  
| 분류 | 요구사항 | 요구사항 상세 |
| ------ | ------ | ------ |
| 실거래가 	| 주택 실거래가 검색 | - 지역을 선택해 주택 실거래가 데이터를 제공한다.|
|   		| 실거래가 상세 정보	| - 검색 결과에서 관심 있는 주택의 상세 정보 조회가 가능하다. |
| 게시판 		| 글 쓰기 		| - 회원은 게시글을 작성할 수 있다.
|   		| 글 수정 		| - 자신이 작성한 글은 수정, 삭제가 가능하다. |
|   		| 답글 			| - 글을 클릭해 답글을 작성해 의견을 나눌 수 있다.|
| 회원 		| 회원 관리 		| - 유저는 언제나 회원 가입, 탈퇴, 수정을 할 수 있다. |
|  			| 로그인 관리 	| - 로그인, 로그아웃이 가능하다.|
| 시스템		| 인터셉터 (구현ing)		| - 인터셉터는 사용자의 로그인 여부에 따라, 그리고 사용자에 따라 권한을 부여한다.  |

<br>

## 3. 구현 화면

0. [메인 페이지](#30-메인-페이지)

1. [실거래가 조회 페이지](#31-주택-실거래가-조회-관련)

2. [공지사항 페이지](#32-게시글,-공지사항-관련)

3. [회원 페이지](#33-회원-관련)


<br/>

### 3.0 메인 페이지

<img src="./img/main_1.png" />

<img src="./img/main_2.png" />

<br/>

<br/>


### 3.1 주택 실거래가 조회 관련

1. [메인 페이지에서 검색](#311-메인-페이지에서-검색)

2. [실거래가 조회 페이지에서 검색](#312-실거래가-조회-페이지에서-검색)

3. [주택 거래 내역 상세 정보 조회](#313-주택-거래-내역-상세-정보-조회)

<br/>

#### 3.1.1 메인 페이지에서 검색

<img src="./img/search_1.png" />

<img src="./img/search_2.png" />

메인 페이지에서 검색할 지역을 선택한 후, 검색 버튼을 누르면

`/search` 페이지로 넘어가서, 지도에 해당 동의 위치를 찍어줌과 동시에 좌측에 거래 내역 정보들을 출력

-> 동기 방식으로 구현

<br/>


#### 3.1.2 실거래가 조회 페이지에서 검색

<img src="./img/search_3.png" />

-> 비동기 방식으로 구현

<br/>


#### 3.1.3 주택 거래 내역 상세 정보 조회

<img src="./img/search_4.png" />

<br>

<br/>

### 3.2 게시글, 공지사항 관련

1. [게시글 작성 및 조회](#321-게시글(원글)-작성-및-조회)

2. [답글 작성](#322-답글-작성)

3. [게시글 수정](#323-게시글-수정)

4. [게시글 삭제](#324-게시글-삭제)


<br/>

#### 3.2.1 게시글(원글) 작성 및 조회

<img src="./img/board_2.png" />

<img src="./img/board_3.png" />

게시글 작성자가 현재 로그인한 사용자인 경우, 우측 상단에 게시글 수정/삭제 페이지로 이동할 수 있는 버튼 생성됨

<img src="./img/board_1.png" />

게시글 리스트를 통해 잘 추가되었음을 확인

<br/>

#### 3.2.2 답글 작성

<img src="./img/board_4.png" />

게시글 작성자와 현재 로그인한 사용자가 다른 경우, 우측 상단에 수정/삭제 페이지로 이동할 수 있는 버튼 없음

답글 작성하기 버튼을 통해 답글 작성 페이지로 넘어갈 수 있음

<br/>

<img src="./img/board_5.png" />

답글 작성 후, 게시글 리스트 (공지사항 메인 페이지) 페이지로 이동하면, `RE:` 로 답글이 생성되었음을 확인

<br/>


#### 3.2.3 게시글 수정

<img src="./img/board_6.png" />

<img src="./img/board_7.png" />

글 수정 성공 시, 글 조회 페이지로 이동과 동시에 수정 성공했다는 알림이 뜸

<br/>

#### 3.2.4 게시글 삭제

<img src="./img/board_8.png" />

<br/>

<br>

### 3.3 회원 관련

1. [회원 가입 성공](#331-회원-가입-성공)

2. [회원 가입 실패: 중복 아이디 체크](#332-회원-가입-실패:-중복-아이디-체크)

3. [로그인](#333-로그인)

4. [로그아웃](#334-로그아웃)

5. [회원 정보 수정](#335-회원-정보-수정)

6. [회원 탈퇴](#336-회원-탈퇴)

<br/>

#### 3.3.1 회원 가입 성공

<img src="./img/user_1.png" />

<img src="./img/user_2.png" />

<br/>

#### 3.3.2 회원 가입 실패: 중복 아이디 체크

<img src="./img/user_10.png" />

<img src="./img/user_11.png" />

<br/>

#### 3.3.3 로그인

<img src="./img/user_3.png" />

<img src="./img/user_5.png" />

-> 로그인 실패: 잘못된 아이디 혹은 비밀번호

<br/>

<img src="./img/user_6.png" />

-> 로그인 성공

<br/>


#### 3.3.4 로그아웃

<img src="./img/user_4.png" />

<br/>


#### 3.3.5 회원 정보 수정

<img src="./img/user_7.png" />

-> 마이 페이지에서 회원 정보 수정 및 탈퇴 가능

<br/>

<img src="./img/user_8.png" />

수정 버튼을 눌러, readonly 속성이었던 input 태그들을 활성화

<br/>

<img src="./img/user_9.png" />

(비동기) 회원 정보 수정 성공

<br/>

#### 3.3.6 회원 탈퇴


<img src="./img/user_12.png" />

<br/>

<img src="./img/user_13.png" />

회원 탈퇴 성공 시, 자동으로 로그아웃 되며 첫 메인 화면으로 이동
