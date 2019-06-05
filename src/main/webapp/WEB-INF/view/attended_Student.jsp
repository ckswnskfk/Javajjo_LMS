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

td {
		border: 1px solid blue;
		border-collapse: collapse;
	}

#attended_Student{
		margin: auto;
		display: inline;
		 border: 1px solid blue;
		border-collapse: collapse;

}

</style>






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
			<div>
				<input class="btn btn-sm btn-primary btn-center" type="button" value="돌아가기" onclick="history.back(-1)">
			</div>


<%@include file="./include/footer.jsp" %>
</body>
</html>