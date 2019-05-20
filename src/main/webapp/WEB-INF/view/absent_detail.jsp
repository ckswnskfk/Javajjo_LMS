<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청 내역 상세 조회 페이지</title>
<!-- <link href="./css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link rel= "stylesheet" type="text/css" href="./css/main.css"> -->

<!-- <script src="https://code.jquery.com/jquery-latest.js"></script> -->
<!-- <script type="text/javascript" src="./js/main.js"></script> -->
<!-- <script src="./js/bootstrap.bundle.min.js"></script> -->
</head>
<body>
<%@include file="./include/header.jsp" %>
<div class="container" id="main">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h1 class="mt-5">결석 신청 상세 조회</h1>
        <p class="lead">${dto}</p>
      </div>
    </div>
  </div>
<%@include file="./include/footer.jsp" %>
</body>
</html>