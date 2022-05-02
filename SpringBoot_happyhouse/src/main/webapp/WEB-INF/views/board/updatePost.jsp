<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link rel="stylesheet" href="<%=root %>/resources/static/css/style.css" />
	<%@ include file="../template/header.jsp" %>
	<script src="<%=root %>/resources/static/js/util.js"></script>
	<script src="<%=root %>/resources/static/js/post.js"></script>
  </head>
  <body>
    <div class="px-0 h-100">
   	  <div class="container-lg navbar navbar-light">
      	<%@ include file="../template/topBar.jsp" %>
      </div>

      <!-- Post -->
      <div
        id="postDiv"
        class="bg-light container-2xl d-flex justify-content-center align-items-center"
      >
        <div class="position-relative w-50">
          <!-- Post Form -->
          <div id="loginForm" class="bg-white p-4 rounded-3 shadow-sm">
            <h3 class="fw-bolder text-start">게시글 수정하기</h3>
            
            <form class="d-flex flex-column" action="/board/${post.code}/update" method="post">
              <input type="hidden" id="code" name="code" value="${post.code}">
              <input
                class="px-3 py-2 my-3 border-1 rounded-2"
                type="text"
                id="title"
                name="title"
                size="44"
                value="${post.title}"
                required
              />
              <textarea
                name="content"
                id="content"
                name="content"
                cols="100"
                rows="10"
                class="mb-3 px-3 py-2 rounded-2"
              >${post.content}</textarea>

              <div class="w-100 d-flex justify-content-end">
                <button
                  id="deleteBtn"
                  type="button"
                  class="border-0 py-2 px-3 me-3 mb-3 rounded-2 text-dark fw-bold"
                  style="width: max-content"
                  onclick="location.href='/board/${post.code}/delete'"
                >
                  삭제하기
                </button>
                <button
                  type="submit"
                  class="border-0 mb-3 px-3 py-2 rounded-2 text-light fw-bold"
                  style="background-color: #86c232; width: max-content"
                  onclick="location.href='/board/${post.code}/update'"
                >
                  저장하기
                </button>
              </div>
              </form>
            </div>
          </div>
        </div>
        
        <%@ include file="../template/footer.jsp" %>
      </div>
    </div>
  </body>
</html>
