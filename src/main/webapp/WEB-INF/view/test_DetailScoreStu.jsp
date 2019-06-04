<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 관리</title>
</head>
<body>
<%@include file="./include/header.jsp" %>
	<h1>과제 등록</h1>
	<h4>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h4>
	<h4>과목명 : ${testsession.subjectname}</h4>
	<hr>
	<h4>과목유형 : ${testsession.subjecttype}</h4>
	<h4>과제유형 : ${testsession.examtype}</h4>
	<hr>
	<h4>과제명 :${testsession.testname}</h4>
	<h4>과제 날짜 : ${testsession.testday}</h4>
	${total}
	
<%@include file="./include/footer.jsp" %>
</body>
</html>