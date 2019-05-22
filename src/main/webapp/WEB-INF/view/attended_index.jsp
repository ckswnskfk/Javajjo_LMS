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
<input class="btn btn-sm btn-success" type="submit" value="출 석" onclick="attended()">
<a href="./attended_Teacher.do?coursecode=C201900001&regdate=201905">강사 캘린더 조회</a>
<a href="./attended_Student_Course.do?id=01022222222">학생 과정 조회</a>

</body>
</html>