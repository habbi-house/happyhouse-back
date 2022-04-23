<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link rel="stylesheet" href="<%=root %>/resources/static/css/style.css" />
	<%@ include file="../template/header.jsp" %>
	<script src="<%=root %>/resources/static/js/util.js"></script>
	<script src="<%=root %>/resources/static/js/user.js"></script>
  </head>
  <body>
    <div class="px-0 h-100">
	  <%@ include file="../template/alertBar.jsp" %>
	  <div class="container-lg navbar navbar-light">
	  	<%@ include file="../template/topBar.jsp" %>
	  </div>

      <!-- SignIn -->
      <div
        id="signInDiv"
        class="bg-light container-2xl d-flex justify-content-center align-items-center"
      >
        <div class="position-relative">
          <!-- SignIn Form -->
          <div
            id="signInForm"
            class="bg-white p-4 rounded-3 shadow-sm"
            style="width: max-content"
          >
            <h3 class="fw-bolder text-center">회원 가입</h3>
            <p class="text-end mt-3">
              <a
                class="text-decoration-none"
                style="color: #86c232"
                href="/user/login"
                >이미 계정이 있으신가요?</a
              >
            </p>
            <form class="d-flex flex-column" action="/user/signIn" method="post">
              <input
                id="id"
                name="id"
                type="text"
                size="44"
                placeholder="아이디"
                class="px-3 py-2 mb-3 border-1 rounded-2"
                required
              />
              <input
                id="password"
                name="password"
                type="password"
                placeholder="비밀번호"
                class="px-3 py-2 mb-3 border-1 rounded-2"
                required
              />
              <input
                id="name"
                name="name"
                type="text"
                placeholder="이름"
                class="px-3 py-2 mb-3 border-1 rounded-2"
                required
              />
              <input
                id="email"
                name="email"
                type="email"
                placeholder="이메일"
                class="px-3 py-2 mb-3 border-1 rounded-2"
                required
              />
              <input
                id="phone"
                name="phone"
                type="text"
                placeholder="전화번호"
                class="px-3 py-2 mb-4 border-1 rounded-2"
                required
              />

              <button
                id="signInBtn"
                type="button"
                style="background-color: #86c232"
                class="flex-grow-1 border-0 mb-3 py-2 rounded-2 text-light fw-bold"
              >
                등록
              </button>
            </form>
          </div>
        </div>
      </div>

      <%@ include file="../template/footer.jsp" %>
    </div>
  </body>
</html>
