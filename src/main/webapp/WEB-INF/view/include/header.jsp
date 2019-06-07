<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link rel= "stylesheet" type="text/css" href="./css/header2.css">

<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/main.js"></script>
<script src="./js/bootstrap.bundle.min.js"></script>

</head>
<body>
<div id = "header">
	<div id="headerOne">
	<h1><a href="./main.do"><img alt="로고 이미지" src="./img/logo2.png"></a></h1>
	</div>
	<div id="headerTwo" >
		<c:choose>
			<c:when test="${member.table eq 'Student'}"><a href="./student_info.do">${member.name}</a>님 환영합니다.</c:when>
			<c:when test="${member.table eq 'Teacher'}"><a href="./teacher_info.do">${member.name}</a>님 환영합니다.</c:when>
			<c:when test="${member.table eq 'Admin'}"><a href="./admin_info.do">${member.name}</a>님 환영합니다.</c:when>
		</c:choose>
		<a href="./logout.do">로그아웃</a>
	</div>
	<div id="main" style="float:right;">
  		<button class="openbtn" onclick="openNav()">☰ </button>
	</div>
</div>

<hr class="new1">

<div id="mySidebar"  class="sidebar">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  
  <div class="dropdown">
  	<a class="dropbtn">게시판▼</a>
  		<div class="dropdown-content">
 			<a href="./notice_list.do">공지 게시판</a>
    		<a href="./file_infoboardlist.do">자료 게시판</a>
    		<a href="./room_main.do">빈 강의실</a>
  		</div>
  </div>

  <c:choose>
            <c:when test="${member.table eq 'Student'}">
				  <a href="./attended_Student_Main.do">출결 관리</a>
         	</c:when>
         	<c:when test="${member.table eq 'Teacher'}">
         			<a href="./attended_Teacher_Main.do">출결 관리</a>
         	</c:when>
  </c:choose>
  
  <c:choose>
          	<c:when test="${member.table eq 'Admin'}">
            		<a href="./course_List.do">과정 관리</a>
          	</c:when>
          	<c:when test="${member.table eq 'Teacher'}">
	           	 	<a href="./subject_Course1.do">과목 관리</a>
          	</c:when>
  </c:choose>
  
          	<a href="./absentListForm.do">결석 신청</a>
  
  <div class="dropdown">
  <c:choose>
  
      <c:when test="${member.table eq 'Student'}">
         
  			<a class="dropbtn">과제관리▼</a>
  				<div class="dropdown-content">
 					<a href="./test_Course_Submit.do">과제 제출</a>
    				<a href="./test_Course_ResultStu.do">성적 조회</a>
  				</div>
  	
      </c:when>
         	<c:when test="${member.table eq 'Teacher'}">
         		<a class="dropbtn">과제관리▼</a>
  				<div class="dropdown-content">
 					<a href="./test_Course_Insert.do">과제 등록</a>
  					<a href="./test_Course_Mark.do">과제 채점</a>
  					<a href="./test_Course_Result.do">성적 조회</a>
  				</div>
         	</c:when>
          </c:choose>
          	</div>
          	
  
</div>



<script>
function openNav() {
  document.getElementById("mySidebar").style.width = "300px";
  document.getElementById("main").style.marginRight = "250px";
}

function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginRight= "0";
}
</script>
   
</body>
</html> 
