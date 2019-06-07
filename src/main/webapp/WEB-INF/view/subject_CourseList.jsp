<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
<title>Insert title here</title>
</head>
<body>
<%@ include file="./include/header.jsp" %>
<div class="container" id="main">
	<div class="row" style="width: 900px; margin: 0 auto;">
		<div class="col-lg-12">
		<div id="list">
<table class="table">
	<tr>
		<th>과정명</th>
		<th>시작날짜</th>
		<th>회차</th>
	</tr>
	<c:forEach items="${list }" var="dto">
		<tr>
			<td>
				<a href="./subject_select_all.do?coursecode=${dto.coursecode}&coursename=${dto.coursename}">${dto.coursename }</a>
			</td>
			<td>
				<fmt:parseDate value="${dto.startdate}" pattern="yyyy-MM-dd HH:mm:ss" var="Stringdate"/>
				<fmt:formatDate value="${Stringdate}" pattern="yyyy-MM-dd"/>
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;${dto.coursecnt }</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
</div>
<%@include file="./include/footer.jsp" %>
</body>
</html>