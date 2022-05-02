<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/style.css" />
	<%@ include file="../template/header.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/static/js/util.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/post.js"></script>
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
        <div class="position-relative">
          <!-- Login Form -->
          <div
            id="loginForm"
            class="bg-white p-4 rounded-3 shadow-sm"
            style="width: max-content"
          >
            <h3 class="fw-bolder text-start">글 작성하기</h3>
            <form class="d-flex flex-column" action="create" method="post">
              <c:if test="${!empty originNo}">          
	              <input type="hidden" name="originNo" value="${originNo}" />
              </c:if>
              <c:if test="${!empty groupLayer}">          
	              <input type="hidden" name="groupLayer" value="${groupLayer}" />
              </c:if>
              <input
                class="px-3 py-2 my-3 border-1 rounded-2"
                type="text"
                name="title"
                id="title"
                size="44"
                placeholder="제목"
                required
              />
              <textarea
                name="content"
                id="content"
                cols="100"
                rows="10"
                placeholder="내용"
                class="mb-3 px-3 py-2 rounded-2"
              ></textarea>

              <div class="w-100 d-flex justify-content-end">
                <button
                  type="button"
                  onclick="location.href='../board'"
                  class="border-0 py-2 px-3 me-3 mb-3 rounded-2 text-dark fw-bold"
                  style="width: max-content"
                >
                  돌아가기
                </button>
                <button
                  id="submitPostBtn"
                  type="submit"
                  class="border-0 mb-3 px-3 py-2 rounded-2 text-light fw-bold"
                  style="background-color: #86c232; width: max-content"
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
  </body>
</html>
