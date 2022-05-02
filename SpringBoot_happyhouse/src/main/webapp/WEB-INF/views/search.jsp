<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
  <head>
	<link rel="stylesheet" href="<%= root %>/resources/static/css/style.css" />
	<%@ include file="./template/header.jsp" %>
	<script 
		type="text/javascript" 
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=98fdc3f673b7351babf96a5390213c10&libraries=services">
	</script>
    <script src="<%= root %>/resources/static/js/search.js"></script>
  </head>
  <body>
    <!-- Header -->
    <div class="container-2xl navbar navbar-light border-bottom">
		<%@ include file="./template/topBar.jsp" %>
    </div>

    <div
      class="w-100 bg-white shadow-sm p-3 d-flex"
      style="min-width: max-content"
    >
    <c:choose>
	    <c:when test="${!empty areaInfo}">
	      <div
	        class="input-group flex-grow-1 border-0 me-2"
	        style="max-width: 160px"
	      >
	        <select class="form-select" name="category1" id="category1">
	          <option selected>${areaInfo["sigu"]}</option>
	          <option value="11">서울특별시</option>
	          <option value="26">부산광역시</option>
	          <option value="27">대구광역시</option>
	          <option value="28">인천광역시</option>
	          <option value="29">광주광역시</option>
	          <option value="30">대전광역시</option>
	          <option value="31">울산광역시</option>
	          <option value="41">경기도</option>
	          <option value="42">강원도</option>
	          <option value="43">충청북도</option>
	          <option value="44">충청남도</option>
	          <option value="45">전라북도</option>
	          <option value="46">전라남도</option>
	          <option value="47">경상북도</option>
	          <option value="48">경상남도</option>
	          <option value="50">제주도</option>
	        </select>
	      </div>
	      <div
	        class="input-group flex-grow-1 border-0 me-2"
	        style="max-width: 160px"
	      >
	        <select class="form-select" name="category2" id="category2">
	          <option selected>${areaInfo["gugun"]}</option>
	        </select>
	      </div>
	      <div
	        class="input-group flex-grow-1 border-0 me-2"
	        style="max-width: 160px"
	      >
	        <select class="form-select" name="category3" id="category3">
	          <option selected>${areaInfo["dong"]}</option>
	        </select>
	      </div>
		</c:when>
		<c:otherwise>
		  <div
	        class="input-group flex-grow-1 border-0 me-2"
	        style="max-width: 160px"
	      >
	        <select class="form-select" name="category1" id="category1">
	          <option selected>도/광역시</option>
	          <option value="11">서울특별시</option>
	          <option value="26">부산광역시</option>
	          <option value="27">대구광역시</option>
	          <option value="28">인천광역시</option>
	          <option value="29">광주광역시</option>
	          <option value="30">대전광역시</option>
	          <option value="31">울산광역시</option>
	          <option value="41">경기도</option>
	          <option value="42">강원도</option>
	          <option value="43">충청북도</option>
	          <option value="44">충청남도</option>
	          <option value="45">전라북도</option>
	          <option value="46">전라남도</option>
	          <option value="47">경상북도</option>
	          <option value="48">경상남도</option>
	          <option value="50">제주도</option>
	        </select>
	      </div>
	      <div
	        class="input-group flex-grow-1 border-0 me-2"
	        style="max-width: 160px"
	      >
	        <select class="form-select" name="category2" id="category2">
	          <option selected>시/군/구</option>
	        </select>
	      </div>
	      <div
	        class="input-group flex-grow-1 border-0 me-2"
	        style="max-width: 160px"
	      >
	        <select class="form-select" name="category3" id="category3">
	          <option selected>동</option>
	        </select>
	      </div>
		</c:otherwise>
	</c:choose>
      <button
        class="btn rounded border-0 shadow-sm text-light"
        type="button"
        onclick="search()"
        style="height: 40px; background-color: #86c232"
      >
        <i class="fa-solid fa-magnifying-glass text-light"></i>
      </button>
    </div>

    <!-- Contents -->
    <div class="d-flex" id="wrapper" style="height: calc(100% - 143px)">
      <!-- Sidebar-->
      <div
        class="shadow-sm bg-white px-4 py-3 h-100"
        id="sidebar-wrapper"
        style="min-width: 320px"
      >
        <div
          class="list-group list-group-flush overflow-auto"
          style="height: calc(100% - 50px)"
        >
          <!-- 데이터를 불러와서 처리하는 부분-->
	    <c:if test="${!empty areaInfo}">
	    	<c:forEach var="item" items="${list}" varStatus="status">
	    		<div class='list-group-item list-group-item-action list-group-item-light p-3' data-bs-toggle='modal' data-bs-target='#detailedInfo' style="cursor: pointer;" onclick='loadDetailedInfo(${status.index})'>
		    		<div style='font-weight: bold'>${item.aptName}</div><br>
		    		<div>거래금액: ${item.dealAmount}만원</div>
		    		<div>전용면적: ${item.area}제곱미터</div>
		    		<div><i class='fa fa-calendar' aria-hidden='true'></i><span> ${item.dealYear}.${item.dealMonth}.${item.dealDay}</span></div>
	    		</div>
	    	</c:forEach>
	    </c:if>
	    <c:if test="${empty list}">
	    	거래 정보가 없습니다.
	    </c:if>
        </div>
      </div>

      <!-- sidebar end -->

      <!-- Modal -->
      <div
        class="modal fade"
        id="detailedInfo"
        tabindex="-1"
        aria-labelledby="detailedInfoLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="detailedInfoLabel">상세정보</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <!-- 로드되는 부분 -->
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Page content wrapper-->
      <div id="map-wrapper" class="w-100 h-100 position-relative">
        <div id="map" class="w-100 h-100">
          <!-- Map 동적으로 생성 -->
        </div>
        <div
          id="zoom"
          class="border-1 rounded-2 position-absolute bg-light d-flex flex-column"
          style="
            top: 20px;
            right: 20px;
            width: 36px;
            height: 80px;
            z-index: 1;
            cursor: pointer;
          "
        >
          <div
            id="zoomIn"
            class="text-center h-50 d-flex align-items-center justify-content-center"
          >
            <img
              width="20px"
              src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
              alt="확대"
            />
          </div>
          <hr class="m-0" style="border: solid 1px #c5c6c6" />
          <div
            id="zoomOut"
            class="text-center h-50 d-flex align-items-center justify-content-center"
          >
            <img
              width="20px"
              src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
              alt="축소"
            />
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
