<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel= "stylesheet" type="text/css" href="./css/header.css">
</head>
<body>
<div id = "header">
<h1>헤더!</h1>
</div>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
      <a class="navbar-brand" href="#">네비 게이션 바</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">게시판
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">출결 관리</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">과정 관리</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">과목 관리</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./absent.do">결석 신청</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">메신저</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</body>
</html>