<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Top Bar -->
<div class="w-100 d-flex justify-content-between flex-nowrap px-4 my-1">
  <div style="width: max-content">
    <a
      id="navBrand"
      href="${root}/index.jsp"
      class="navbar-brand fw-bolder flex-grow-1 align-middle"
      >HappyHouse</a
    >
  </div>
  <div class="justify-content-end">
    <ul class="navbar-nav d-flex flex-row me-auto">
      <li class="nav-item px-3">
        <a class="nav-link" href="${root}/search.jsp">실거래가 조회</a>
      </li>
      <li class="nav-item px-3">
        <a class="nav-link" href="${root}/board?sign=getAllPost">공지사항</a>
      </li>
      <c:if test="${empty currentUser}">
	      <li id="loginNav" class="nav-item ps-3">
	        <a class="nav-link" href="${root}/auth?sign=mvLogin">로그인</a>
	      </li>
      </c:if>      
      
      <c:if test="${!empty currentUser}">
	      <li id="logoutNav" class="nav-item ps-3">
	        <a class="nav-link" href="${root}/auth?sign=logout">로그아웃</a>
	      </li>
	      <li
	        id="userNav"
	        class="nav-item ps-4 pe-2 d-flex align-items-center"
	      >
	        <a href="${root}/auth?sign=mvMypage">
	          <i class="fa-solid fa-circle-user h2 mb-0 text-dark"></i>
	        </a>
	      </li>
      </c:if>
    </ul>
  </div>
</div>
