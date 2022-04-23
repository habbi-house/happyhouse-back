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

      <!-- Login -->
      <div
        id="loginDiv"
        class="bg-light container-2xl d-flex justify-content-center align-items-center"
      >
        <div class="position-relative">
          <!-- Login Form -->
          <div
            id="loginForm"
            class="bg-white p-4 rounded-3 shadow-sm"
            style="width: max-content"
          >
            <h3 class="fw-bolder text-center">로그인</h3>
            <form class="d-flex flex-column" action="/user/login" method="post">
              <input
                class="px-3 py-2 my-3 border-1 rounded-2"
                type="text"
                id="id"
                name="id"
                size="44"
                placeholder="아이디"
                required
              />
              <input
                class="px-3 py-2 mb-3 border-1 rounded-2"
                type="password"
                id="password"
                name="password"
                placeholder="비밀번호"
                required
              />
              <p class="text-end">
                <a
                  class="text-decoration-none"
                  style="color: #86c232"
                  href="findPassword.html"
                  >비밀번호를 잊으셨나요?</a
                >
              </p>

              <button
                id="loginBtn"
                type="button"
                class="flex-grow-1 border-0 mb-3 py-2 rounded-2 text-light fw-bold"
                style="background-color: #86c232"
              >
                로그인
              </button>
              <button
                type="button"
                onclick="location.href='/user/signIn'"
                class="flex-grow-1 border-0 py-2 mb-3 rounded-2 text-dark fw-bold"
              >
                회원가입
              </button>
            </form>
          </div>
        </div>
      </div>

      <%@ include file="../template/footer.jsp" %>
    </div>
  </body>
</html>
