<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%@include file="./include/header.jsp" %>
<form action="">
<table>
	<tr>
		<td>과정 이름</td>
		<td>시작 날짜</td>
		<td>회차</td>
	</tr>

<c:forEach items="${list}" var="dto">
	<tr>
		<td>
			<input type="radio" name="radio" value="${dto.coursecode}"> ${dto.coursename }
		</td>
		<td>
		<fmt:parseDate value="${dto.startdate}" pattern="yyyy-MM-dd HH:mm:ss" var="Stringdate"/>
		<fmt:formatDate value="${Stringdate}" pattern="yyyy-MM-dd"/>
		</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;${dto.coursecnt }</td>
	</tr>
</c:forEach>
</table>
<input type="date">
</form>


<div>
	<input type="button" value="과정추가">
	<input type="button" value="과정등록" onclick="location.href='./move.do'">
</div>

<%@include file="./include/footer.jsp" %>
</body>
</html>