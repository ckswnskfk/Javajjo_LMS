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
<%-- <a>${lists.coursename}</a> --%>
	<c:forEach items="${lists}" var="cDto">
		${cDto.coursename}
	</c:forEach>

<a href="./attended_Student.do?id=01022222222">출결상세조회</a>

</body>
</html>