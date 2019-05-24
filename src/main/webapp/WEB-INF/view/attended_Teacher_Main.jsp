<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="./include/header.jsp" %>

<a href="./attended_Teacher_Course.do?id=${member.id}">출결관리 이동</a>

<%@include file="./include/footer.jsp" %>
</body>
</html>