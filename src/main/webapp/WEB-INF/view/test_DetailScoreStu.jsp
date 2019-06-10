<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 관리</title>
<link rel= "stylesheet" type="text/css" href="./css/template.css">
</head>
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<div class="">
	<table style="margin-bottom: 20px; margin-top: 20px;  width:100%;margin-bottom:1rem;background-color:transparent;">
		<thead>
			<tr>
				<td colspan="3"><p style="font-size: 20px;">▶ 과제 목록</p></td>
			</tr>
		</thead>
		<tr>
			<td style="width:34%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</td>
			<td style="width:33%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과목유형 : ${testsession.subjecttype}</td>
			<td style="width:33%; padding-left: 12px; text-align:justify;"> 과제명 : ${testsession.testname}</td>
		</tr>
		<tr>
			<td style="width:34%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과목명 : ${testsession.subjectname}</td>
			<td style="width:33%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과제유형 : ${testsession.examtype}</td>
			<td style="width:33%; padding-left: 12px;  text-align:justify;">과제 날짜 : ${testsession.testday}</td>
		</tr>
	</table>
	<p style="font-size: 30px">${total}점</p>
</div>
<!-- 	<h1>과제 등록</h1> -->
<%-- 	<h4>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h4> --%>
<%-- 	<h4>과목명 : ${testsession.subjectname}</h4> --%>
<!-- 	<hr> -->
<%-- 	<h4>과목유형 : ${testsession.subjecttype}</h4> --%>
<%-- 	<h4>과제유형 : ${testsession.examtype}</h4> --%>
<!-- 	<hr> -->
<%-- 	<h4>과제명 :${testsession.testname}</h4> --%>
<%-- 	<h4>과제 날짜 : ${testsession.testday}</h4> --%>
	
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>