<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/style.css" />
	<%@ include file="./template/header.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/static/js/util.js"></script>
  </head>
  <body>
    <div class="container-lg px-0 h-100">
	  <%@ include file="./template/alertBar.jsp" %>
	  <div class="container-lg navbar navbar-light">
	  	<%@ include file="./template/topBar.jsp" %>
	  </div>
	  
	  <div class="container-lg w-100 ms-3 me-3 mt-5 mb-5 p-3 flex justify-content-between">
	  	<h2 class="mb-5">404 Page Not Found</h2>
	  	<span class="h5 mt-5">존재하지 않는 페이지입니다.</span>
	  </div>

      <%@ include file="./template/footer.jsp" %>
    </div>
  </body>
</html>
