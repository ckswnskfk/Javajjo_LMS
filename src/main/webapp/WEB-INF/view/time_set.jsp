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
</head>
<body>
<form action="./endset.do" method="post">
<table>
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
	<input type="submit" value="등록하기">
	</form>
</body>
</html>