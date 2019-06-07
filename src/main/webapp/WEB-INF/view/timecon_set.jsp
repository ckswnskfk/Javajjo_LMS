<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel= "stylesheet" type="text/css" href="./css/template.css">
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
<style type="text/css">
</style>
</head>
<body>
<div class="content-wrapper"> 
<%@include file="./include/header.jsp" %>
<div class="container" id="main">
	<div class="row" style="width: 900px; margin: 0 auto;">
		<div class="col-lg-12">
<form action="#" method="post" id="formgo" name="formgo" onsubmit="return chk()">
<div id="list">
<table class="table">
	<tr>
		<th>과목명</th>
		<th>시간</th>
		<th>내용</th>
		<th>시작날짜</th>
	</tr>
<c:forEach items="${add}" var="dto">
	<tr>
		<td><input type="hidden" name="code" value="${dto.subjectcode}" id="code"> ${dto.subjectname}</td>
		<td><input type="text" name="time" id="time"></td>
		<td><input type="text" name="content" id="content"></td>
		<td><input type="date" name="startday" id="startday"></td>
	</tr>
</c:forEach>
</table>
</div>
<div style="float: right;">
<input type="submit" value="등록하기" class="btn btn-outline-success">
</div>
</form>
</div>
</div>
</div>


<%@include file="./include/footer.jsp" %>
</div>
<script type="text/javascript">
function chk(){
// 	alert("safdasdf");
	var ckdate=document.getElementById("startday").value;
// 	alert(ckdate);
	var date=new Date(ckdate);
// 	alert(date);
	var today=new Date();
	var content=document.getElementById("content").value;
	var time=document.getElementById("time").value;
	
	if(time==""||content==""){
		alert("모든 항목을 입력해주십시오.");
		var doc=document.getElementById("formgo");
		doc.action="./timecon_set.jsp";
	}else if(date < today){
		alert("오늘 또는 과거의 날짜에 생성할 수 없습니다.");
		var doc=document.getElementById("formgo");
		doc.action="./timecon_set.jsp";
	}else{
		var doc=document.getElementById("formgo");
		doc.action="./subject_add_course.do";
		return true;
	}
}

</script>
</body>
</html>