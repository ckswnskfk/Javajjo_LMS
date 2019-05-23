<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출결 메인 페이지</title>

</head>
<body>
<%@include file="./include/header.jsp" %>
<input class="btn btn-sm btn-success" type="submit" value="출 석" onclick="attended()">

<a href="./attended_Student_Course.do?id=01022222222">학생 과정 조회</a>


<%@include file="./include/footer.jsp" %>
</body>
</html>