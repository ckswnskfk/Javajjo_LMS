<%@page import="happy.jaj.prj.dtos.Subject_Test_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%

	Subject_Test_DTO dto = (Subject_Test_DTO)request.getAttribute("dto");
	
	
%>
</head>
<body>

	<h1>과제 등록</h1>
	<h4>과정명 : ${testsession.coursename}</h4>
	<h4>과목명 : ${testsession.subjectname}</h4>
	<hr>
	<h4>과목유형 : ${testsession.subjecttype}</h4>
	<h4>과제유형 : ${testsession.examtype}</h4>
	<hr>
	<h4>과제명 :${testsession.testname}</h4>
	<h4>과제 날짜 : ${testsession.testday}</h4>
	<input type="button" value="과제 제출" onclick="StuTest(${testsession.testday})">
	<input type="button" value="뒤로가기" onclick="javascript:.history.back()"> 

</body>
</html>