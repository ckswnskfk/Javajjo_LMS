<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생과정조회</title>
<link rel="stylesheet" type="text/css" href="./css/template.css">
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

table {
	text-align: center;
	max-width: 1000px;
	margin: 0 auto;
}

#list {
	text-align: center;
}

#attended_Student_Course {
	margin: auto;
}

table, td {
	text-align: center;
	border-collapse: collapse;
	border: 1px solid blue;
}

#back {
	margin-left: 250px;
	margin-top: 50px;
	margin-bottom: 20px;
}
</style>


</head>
<body>
	<div class="content-wrapper">
		<%@include file="./include/header.jsp"%>

		<div id="back">
			<input class="btn btn-sm btn-primary btn-center" type="button"
				value="돌아가기" onclick="history.back(-1)">
		</div>


		<div id="list">
			<h1>과정 목록</h1>
			<form action="#" method="get" id="attended_Student_Course">
				<c:forEach var="Course_DTO" items="${clists}">
					<table>
						<tr>
						</tr>
						<tr>
							<td><a href="./attended_Student.do?id=${member.id}">${Course_DTO.coursename}</a></td>
						</tr>
					</table>
				</c:forEach>
			</form>
		</div>





		<%@include file="./include/footer.jsp"%>
	</div>
</body>
</html>