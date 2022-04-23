<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link rel="stylesheet" href="<%= root %>/resources/static/css/style.css" />
	<%@ include file="../template/header.jsp" %>
	<script src="<%=root %>/resources/static/js/util.js"></script>
    <script src="<%= root %>/resources/static/js/user.js"></script>
  </head>
  <body>
    <div class="px-0 h-100">
	  <%@ include file="../template/alertBar.jsp" %>
	  <div class="container-lg navbar navbar-light">
	  	<%@ include file="../template/topBar.jsp" %>
	  </div>
	  
      <!-- Contents -->
      <div
        class="container-lg position-relative px-4 d-flex flex-wrap mt-3"
        style="height: calc(100% - 255px)"
      >
        <!-- 내 정보 -->
        <div style="padding: 10px" style="width: max-content">
          <h3 class="fw-bolder">내 정보</h3>
          <!-- id, password, name, email, phone -->
          <div id="info" >
			<form id="myForm" action="" method="post" class="d-flex flex-column mt-4">
				<input type="hidden" name="sign" value="update" />
	            <span>
	              <label for="id" style="width: 80px">아이디&nbsp;</label>
	              <input
	                id="id"
	                name="id"
	                size="28"
	                type="text"
	                class="px-2 py-1 mb-3 border-1 rounded-2"
	                value="${user.id}"
	                readonly
	              />
	            </span>
	            <span>
	              <label for="pwd" style="width: 80px">비밀번호&nbsp;</label>
	              <input
	                id="pwd"
	                name="pwd"
	                size="28"
	                type="password"
	                class="px-2 py-1 mb-3 border-1 rounded-2"
	                value="${user.password}"
	                readonly
	              />
	            </span>
	            <span>
	              <label for="name" style="width: 80px">이름&nbsp;</label>
	              <input
	                id="name"
	                name="name"
	                size="28"
	                type="text"
	                class="px-2 py-1 mb-3 border-1 rounded-2"
	                value="${user.name}"
	                readonly
	              />
	            </span>
	            <span>
	              <label for="email" style="width: 80px">이메일&nbsp;</label>
	              <input
	                id="email"
	                name="email"
	                size="28"
	                type="text"
	                class="px-2 py-1 mb-3 border-1 rounded-2"
	                value="${user.email}"
	                readonly
	              />
	            </span>
	            <span>
	              <label for="phone" style="width: 80px">전화번호&nbsp;</label>
	              <input
	                id="phone"
	                name="phone"
	                size="28"
	                type="text"
	                class="px-2 py-1 mb-3 border-1 rounded-2"
	                value="${user.phone}"
	                readonly
	              />
	            </span>
	          </div>
	          <div class="w-100 d-flex justify-content-end mt-2">
	            <button
	              id="cancelBtn"
	              type="button"
	              class="px-4 py-2 border-0 rounded-3 text-dark fw-bold me-3"
	              style="display: none"
	            >
	              취소
	            </button>
	            <button
	              id="updateUserBtn"
	              type="button"
	              class="px-4 py-2 border-0 rounded-3 text-light fw-bold"
	              style="background-color: #86c232; display: none"
	            >
	              저장
	            </button>
	            <button
	              id="switchBtn"
	              type="button"
	              class="px-4 py-2 border-0 rounded-3 text-light fw-bold"
	              style="background-color: #86c232"
	            >
	              수정
	            </button>
			</form>
          </div>
          <div class="position-absolute bottom-0 mb-3">
            <a
              id="deleteUserBtn"
              class="text-dark text-opacity-50"
              style="text-decoration: none; cursor: pointer"
              >회원 탈퇴하기</a
            >
          </div>
        </div>

        <!-- 나의 관심 지역 -->
        <div style="padding: 10px">
          <h3 class="fw-bolder">나의 관심 지역</h3>
        </div>
      </div>

	  <%@ include file="../template/footer.jsp" %>
    </div>
  </body>
</html>
