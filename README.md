
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
|   		| 실거래가 상세정보	| - 검색 결과에서 관심이 가는 물건의 상세정보 조회가 가능하다. |
| 게시판 		| 글 쓰기 		| - 회원은 게시글을 작성할 수 있다.
|   		| 글 수정 		| - 자신이 작성한 글은 수정, 삭제가 가능하다. |
|   		| 답글 			| - 글을 클릭해 답글을 작성해 의견을 나눌 수 있다.|
| 회원 		| 회원 관리 		| - 유저는 언제나 회원 가입, 탈퇴, 수정을 할 수 있다. |
|  			| 로그인 관리 	| - 로그인, 로그아웃이 가능하다.|
| 시스템		| 인터셉터 		| - 인터셉터는 사용자의 로그인 여부를 체크하고 권한을 부여한다.  |
<br>

## 3. 구현 화면

### 3.0 메인페이지
<img  src="https://user-images.githubusercontent.com/49544744/166240919-a5670c69-5d6d-4ced-b2d1-6adb8e0c523f.png"  width="800">

### 3.1 주택 실거래가 조회 관련
| 구현 상세 | 구현 결과 |
| ------ | ------ |
| 메인페이지에서 지역을 선택해 검색| <img  src="https://user-images.githubusercontent.com/49544744/166240916-5ed6903b-dc18-4663-b45f-60fdfac30363.png"  width="800"> |
| 화면 상단의 실거래가 조회버튼을 클릭했을 때 | <img  src="https://user-images.githubusercontent.com/49544744/166240884-c7c95ecb-e2ab-4176-b2f1-9df67779d238.png"  width="800">   |
| 검색 결과 | <img  src="https://user-images.githubusercontent.com/49544744/166240895-370e65fa-0d1c-427d-9b56-f68424a50290.png"  width="800">   |
| 물건의 상세정보 조회 | <img  src="https://user-images.githubusercontent.com/49544744/166240900-43ac3d6c-bd7b-4646-bf17-e7e5e54a173c.png"  width="800">  |
<br>

### 3.2 게시글, 공지사항 관련
| 구현 상세 | 구현 결과 |
| ------ | ------ |
| 게시글 작성하기 |<img  src=""  width="800"> |
| 게시글 확인|<img  src=""  width="800"> |
| 게시글 수정|<img  src=""  width="800"> |
| 게시글 삭제|<img  src=""  width="800"> |
| 답글 |<img  src=""  width="800"> |
<br>

### 3.3 회원 관련
| 구현 상세 | 구현 결과 |
| ------ | ------ |
| 회원가입 |<img  src="https://user-images.githubusercontent.com/49544744/166241875-66d9420a-50d4-4ed1-b251-c323a4afc339.png"  width="800"> |
| 중복가입 불가 |<img  src="https://user-images.githubusercontent.com/49544744/166241880-da3a040a-9e72-4483-8d9d-16c1cb2c8a5e.png"  width="800"> |
| 로그인 메인 화면|<img  src="https://user-images.githubusercontent.com/49544744/166240911-3ee39d9c-22f8-42c8-b0df-b12fa7407b1d.png"  width="800"> |
| 로그인 성공 메시지 |<img  src="https://user-images.githubusercontent.com/49544744/166240912-d9afb4c3-040f-4962-8271-0e72d0d21b75.png"  width="800"> |
| 로그인 실패 메시지 |<img  src="https://user-images.githubusercontent.com/49544744/166240914-287939d9-7e36-48fb-aad8-0ee673da10b3.png"  width="800"> |
| 회원 정보 수정 |<img  src="https://user-images.githubusercontent.com/49544744/166241882-8c76d202-3776-4e28-a049-6167e3b87b60.png"  width="800"> |
| 회원 탈퇴😪 |<img  src="https://user-images.githubusercontent.com/49544744/166241869-26859da9-755d-4586-8efc-45e3f0507b88.png"  width="800"> |

