<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생과정조회</title>
</head>
<body>
<%@include file="./include/header.jsp" %>
<h1>과정 목록 </h1>
<p>${id}</p>
<div>

	<form action="#" method="get" id="attended_Student_Course">
	<c:forEach var="Course_DTO" items="${clists}">
	<table>
	<tr>
		<th>과정명</th>
	</tr>	
		<tr>	
			<td><a href="././attended_Student.do?id=${member.id}">${Course_DTO.coursename}</a></td>
		
		</tr>
	</table>
	</c:forEach>
	</form>
</div>
<%@include file="./include/footer.jsp" %>
</body>
</html>