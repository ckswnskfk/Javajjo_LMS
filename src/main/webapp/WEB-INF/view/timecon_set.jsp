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
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
</head>
<body>
<%@include file="./include/header.jsp" %>

<form action="./subject_add_course.do" method="post">
<table>
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
<div>
<input type="submit" value="등록하기">
</div>
</form>


<%@include file="./include/footer.jsp" %>


</body>
</html>