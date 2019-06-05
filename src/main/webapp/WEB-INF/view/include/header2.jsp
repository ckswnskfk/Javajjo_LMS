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
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/main.js"></script>
<script src="./js/bootstrap.bundle.min.js"></script>
<style>
body {
  font-family: "Lato", sans-serif;
}

.sidebar {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  right: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.2s;
  padding-top: 60px;
}

.sidebar a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidebar a:hover {
  color: white;
}

.sidebar .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

.openbtn {
  font-size: 20px;
  cursor: pointer;
  background-color: #111;
  color: white;
  padding: 10px 15px;
  border: none;
}

.openbtn:hover {
  background-color: blue;
}

#main {
  transition: margin-left .5s;
  padding: 10px;
  
}
.openbtn{
right:55px;
position: absolute;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
  .sidebar {padding-top: 15px;}
  .sidebar a {font-size: 18px;}
}


.dropbtn {
  background-color: #111111;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #111111;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: white;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  font-size: 14px;
}

.dropdown-content a:hover {background-color: #111111;}

.dropdown:hover .dropdown-content {display: block;}

/* .dropdown:hover .dropbtn {background-color:white;} */
#header{
	background-color: #818181;
	height: 70px;
	width: 100%;
}
#headerOne{
	float:left;
	width: 45%;
	height: 100%;
	margin-left: 15px;
}
#headerTwo{
font-family: "Lato", sans-serif;
	width: 40%;
	float:left;
	text-align: right;
	margin-top: 30px;

}
</style>
</head>
<body>
<div id = "header">
	<div id="headerOne">
	<h1><a href="./main.do"><img alt="로고 이미지" src="./img/logo2.png"></a></h1>
	</div>
	<div id="headerTwo" >
		<a href="./logout.do">로그아웃</a>
		<c:choose>
			<c:when test="${member.table eq 'Student'}"><a href="./student_info.do">${member.name}</a>님 환영합니다.</c:when>
			<c:when test="${member.table eq 'Teacher'}"><a href="./teacher_info.do">${member.name}</a>님 환영합니다.</c:when>
			<c:when test="${member.table eq 'Admin'}"><a href="./admin_info.do">${member.name}</a>님 환영합니다.</c:when>
		</c:choose>
	</div>
	<div id="main" style="display:inline-block; float:right;">
  		<button class="openbtn" onclick="openNav()">☰ </button>
	</div>
</div>


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
          	
          	<a href="./absentListForm.do">결석 신청</a>
  
</div>



<script>
function openNav() {
  document.getElementById("mySidebar").style.width = "250px";
  document.getElementById("main").style.marginRight = "250px";
}

function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginRight= "0";
}
</script>
   
</body>
</html> 
