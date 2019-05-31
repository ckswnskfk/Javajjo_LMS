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
<h1>도착했다네</h1>
<form action="./cntsel.do" method="post">
<select name="coursecnt">
<c:forEach items="${cnt}" var="dto">
	<option id="cntsel" value="${dto.coursecnt }">${dto.coursecnt}</option>
	
</c:forEach>
</select>
<input type="submit" value="조회">
</form>
</body>
</html>