<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8"); 
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출결 상세</title>
</head>
<body>
<%@include file="./include/header.jsp" %>
<p>${lists.a_check}</p>

<%@include file="./include/footer.jsp" %>
</body>
</html>