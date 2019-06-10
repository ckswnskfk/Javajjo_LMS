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
</head>
<body>
<div class="content-wrapper"> 
<%@include file="./include/header.jsp" %>
<div class="container" id="main">
	<div class="row" style="width: 900px; margin: 0 auto;">
		<div class="col-lg-12">
<form action="./endset.do" method="post">
<div id="list">
<table class="table">
	<tr>
		<th>과목명</th>
		<th>수업시간</th>
		<th>수업내용</th>
		<th>시작날짜</th>
	</tr>
	<c:forEach items="${add}" var="dto">
	<tr>
		<td>${dto.subjectname}</td>
		<td>${dto.subjecttime}</td>
		<td>${dto.content}</td>
		<td>
			<input type="date" name="startdate" id="date">
			<input type="hidden" name="seq" value="${dto.seq }">
		</td>
	</tr>
	</c:forEach>
	
	
	</table>
	</div>
	<input type="submit" value="등록하기">
	</form>
	</div>
	</div>
	</div>
	<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>