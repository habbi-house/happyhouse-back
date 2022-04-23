<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
  <head>
	<link rel="stylesheet" href="<%=root %>/resources/static/css/style.css" />
    <%@ include file="./template/header.jsp" %>
    <script src="<%=root %>/resources/static/js/util.js"></script>
    <script src="<%=root %>/resources/static/js/user.js"></script>
  </head>
  <body>
    <div class="px-0">
	  <%@ include file="./template/alertBar.jsp" %>
	  <div class="container-lg navbar navbar-light">
	  	<%@ include file="./template/topBar.jsp" %>
	  </div>

      <!-- Search Banner -->
      <div
        id="banner"
        class="mb-4 bg-dark container-2xl position-relative"
        style="height: 430px"
      >
        <!-- Image -->
        <div class="container-lg">
          <img src="" alt="" />
        </div>
        <div
          class="position-absolute top-50 start-50 translate-middle"
          style="min-width: max-content"
        >
          <div class="my-3 d-flex flex-column">
            <span class="text-white h2 fw-bolder m-0"
              >어떤 집에서 살고 싶으신가요?&nbsp;&nbsp;&nbsp;&nbsp;</span
            >
            <span class="text-white h5 fw-normal my-2"
              >원하는 집의 실거래가를 확인하세요.</span
            >
          </div>

          <!-- Search Input -->
          <div class="d-flex">
            <div class="input-group flex-grow-1 border-0 me-2">
              <select class="form-select" name="category1" id="category1">
                <option selected>도/광역시</option>
                <option value="1">서울특별시</option>
                <option value="2">경기도</option>
                <option value="3">제주도</option>
              </select>
            </div>
            <div class="input-group flex-grow-1 border-0 me-2">
              <select class="form-select" name="category2" id="category2">
                <option selected>시/군/구</option>
                <option value="1">강남구</option>
                <option value="2">강동구</option>
                <option value="3">광진구</option>
              </select>
            </div>
            <div class="input-group flex-grow-1 border-0 me-2">
              <select class="form-select" name="category2" id="category2">
                <option selected>동</option>
                <option value="1">대치동</option>
                <option value="2">잠실동</option>
                <option value="3">익선동</option>
              </select>
            </div>
            <button
              class="border-0 p-0 main-bg-color rounded"
              style="width: max-content"
            >
              <i
                class="fa-solid fa-magnifying-glass h6 text-light px-3 m-0"
              ></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Contents -->
      <div class="container-lg position-relative px-4">
		  <!-- 관심 지역 -->
		  <div class="w-100 mb-5"> 
		          <h3 class="fw-bolder">관심 지역</h3>
		          <!-- Tags
		  <div class="mt-4">           
		            설정한 관심 지역이 없을 때: 랜덤 추천?
		            <span>설정한 관심 지역이 없어요..</span>
		            관심 지역 태그 클릭 시: filtering
		 	<span class="bg-light px-3 py-2 rounded-3 tag shadow-sm">청운동</span>
          </div> -->
          <div class="d-grid mt-4">
            <div class="card rounded-3" style="width: 18rem">
              <img
                class="card-img-top"
                src="<%=root %>/resources/static/img/sample1.jpg"
                alt="Card image cap"
              />
              <div class="card-body">
                <h5 class="card-title mb-3">E 편한 세상</h5>
                <p class="card-text mb-1">거래금액: 50,000만원</p>
                <p class="card-text mb-2">면적: 84,965</p>
              </div>
            </div>
          </div>
       </div>
        <div class="d-flex flex-wrap justify-content-between w-100">
          <!-- News -->
          <div
            class="d-flex flex-column flex-grow-1"
            style="width: max-content; min-width: 460px; max-width: 600px; margin-right: 20px;"
          >
            <h3 class="fw-bolder">주요 뉴스</h3>
            <table class="table table-hover mb-5">
              <thead>
                <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>날짜</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td
                    style="
                      width: 50%;
                      text-overflow: ellipsis;
                      overflow: hidden;
                      white-space: nowrap;
                    "
                  >
                    아파트값 상승 멈췄다... 20개월만에 가격 하락
                  </td>
                  <td>1일 전</td>
                </tr>
              </tbody>
            </table>
          </div>

          <!-- Notice -->
          <div
            class="d-flex flex-column flex-grow-1"
            style="
              width: max-content;
              min-width: 460px;
              max-width: 600px;
              margin-right: 20px;
            "
          >
            <h3 class="fw-bolder">공지사항</h3>
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>날짜</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td class="text-truncate" style="max-width: 70%">
                    [공지사항] 페이지 리뉴얼
                  </td>
                  <td>8시간 전</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

	  <%@ include file="./template/footer.jsp" %>
    </div>
  </body>
</html>
