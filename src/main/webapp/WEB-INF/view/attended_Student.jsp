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
	<c:forEach items="${lists}" var="aDto">
		${aDto.a_check}
		${aDto.regdate}
	</c:forEach>
<%-- <p>${lists.a_check}</p> --%>
</body>
</html>