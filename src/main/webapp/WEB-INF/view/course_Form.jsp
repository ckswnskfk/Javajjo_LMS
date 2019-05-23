<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="./include/header.jsp"%>
	<div>
		과정명 :&nbsp;&nbsp;<input type="text"><br> 시작날짜
		:&nbsp;&nbsp;<input type="date">
	</div>

	<div>
		<input type="button" value="과정등록"> <input type="button"
			value="취소" onclick="history.back(-1)">
	</div>
	<%@include file="./include/footer.jsp"%>
</body>
</html>