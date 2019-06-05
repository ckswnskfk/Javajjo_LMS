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
<link rel= "stylesheet" type="text/css" href="./css/main.css">
<link href="./css/bootstrap.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/main.js"></script>
<script src="./js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div id = "header">
	<div id="headerOne">
	<h1><a href="./main.do"><img alt="로고 이미지" src="./img/logo2.png"></a></h1>
	</div>
	<div id="headerTwo">
		<a href="./logout.do">로그아웃</a>
		<c:choose>
			<c:when test="${member.table eq 'Student'}"><a href="./student_info.do">${member.name}</a>님 환영합니다.</c:when>
			<c:when test="${member.table eq 'Teacher'}"><a href="./teacher_info.do">${member.name}</a>님 환영합니다.</c:when>
			<c:when test="${member.table eq 'Admin'}"><a href="./admin_info.do">${member.name}</a>님 환영합니다.</c:when>
		</c:choose>
	</div>
</div>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-white static-top">
    <div class="container">
      <a class="navbar-brand" href="#">네비 게이션 바</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">게시판
            <span class="caret"></span></a>
	        <ul class="dropdown-menu">
	          <li><a href="./notice_list.do">공지 게시판</a></li>
	          <li><a href="./file_infoboardlist.do">자료 게시판</a></li>
	          <li><a href="./room_main.do">빈 강의실</a></li>
	        </ul>
	      </li>
          <c:choose>
         	 <c:when test="${member.table eq 'Student'}">
          		<li class="nav-item">
				  <a class="nav-link" href="./attended_Student_Main.do">출결 관리</a>
         		</li>
         	</c:when>
         	<c:when test="${member.table eq 'Teacher'}">
         		<li class="nav-item">
         			<a class="nav-link" href="./attended_Teacher_Main.do">출결 관리</a>
         		</li>
         	</c:when>
          </c:choose>
          <c:choose>
          	<c:when test="${member.table eq 'Admin'}">
          		<li class="nav-item">
            		<a class="nav-link" href="./course_List.do">과정 관리</a>
          		</li>
          	</c:when>
          	<c:when test="${member.table eq 'Teacher'}">
	          	<li class="nav-item">
	           	 	<a class="nav-link" href="./subject_Course1.do">과목 관리</a>
	          	</li>
          	</c:when>
          </c:choose>
          <c:choose>
         	 <c:when test="${member.table eq 'Student'}">
          		<li class="dropdown">
		            <a class="dropdown-toggle" data-toggle="dropdown" href="#">과제 관리
		            <span class="caret"></span></a>
			        <ul class="dropdown-menu">
			          <li><a href="./test_Course_Submit.do">과제 제출</a></li>
			          <li><a href="./test_Course_ResultStu.do">성적 조회</a></li>
			        </ul>
			      </li>
         	</c:when>
         	<c:when test="${member.table eq 'Teacher'}">
         		<li class="dropdown">
		            <a class="dropdown-toggle" data-toggle="dropdown" href="#">과제 관리
		            <span class="caret"></span></a>
			        <ul class="dropdown-menu">
			          <li><a href="./test_Course_Insert.do">과제 등록</a></li>
			          <li><a href="./test_Course_Mark.do">과제 채점</a></li>
			          <li><a href="./test_Course_Result.do">성적 조회</a></li>
			        </ul>
			      </li>
         	</c:when>
          </c:choose>
          <li class="nav-item">
            <a class="nav-link" href="./absentListForm.do">결석 신청</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</body>
</html>