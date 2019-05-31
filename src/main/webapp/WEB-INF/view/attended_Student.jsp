<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 출석 상세조회</title>
</head>
<body>
<%@include file="./include/header.jsp" %>
<div>
	<form action="#" method="get" id="attended_Student">
	<table class="table table-bordered">
		<tr>
		<th>출결(Y : 입실 , N : 퇴실)</th>
		<th>시간</th>
		</tr>	
	<c:forEach var="Attended_DTO" items="${alists}">
		<tr>
		<td>${Attended_DTO.a_check}</td>
		<td>${Attended_DTO.regdate}</td>
		</tr>
	</c:forEach>
	</table>
	</form>
</div>
<%-- <p>${lists.a_check}</p> --%>


<%@include file="./include/footer.jsp" %>
</body>
</html>