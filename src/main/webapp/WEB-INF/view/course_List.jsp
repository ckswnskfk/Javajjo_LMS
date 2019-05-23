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

<form action="">
<table>
<c:forEach items="${list}" var="dto">
	<tr>
		<td><input type="radio" name="radio"> ${dto.coursename }</td>
		<td>${dto.coursecode }</td>
		<td>${dto.startdate }</td>
		<td>${dto.coursecnt }</td>
	</tr>
</c:forEach>
</table>
<input type="date">
</form>


<div>
	<input type="button" value="과정추가">
	<input type="button" value="과정등록" onclick="location.href='./move.do'">
</div>


</body>
</html>