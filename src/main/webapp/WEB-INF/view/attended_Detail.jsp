<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8"); 
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출결 상세</title>
</head>
<body>
<%@include file="./include/header.jsp" %>

<div>
	<form action="#" method="get" id="attended_Detail">
	<table class="table table-bordered">
		<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>성별</th>
		<th>생년월일</th>
		<th>출석날짜</th>
		<th>출결(Y : 입실 , N : 퇴실)</th>
		</tr>
		<c:forEach var="Student_DTO" items="${slists}">
		<c:choose>
			<c:when test="${Student_DTO.regdate ne null}">

		<tr>
		<td>${Student_DTO.id}</td>
		<td>${Student_DTO.name}</td>
		<td>${Student_DTO.gender}</td>
		<td>${Student_DTO.birth}</td>
		<td>${Student_DTO.regdate}</td>
		<td>${Student_DTO.a_check}</td>
		</tr>
	
			</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">없음</td>
					</tr>
				</c:otherwise>
		</c:choose>
		</c:forEach>
	
	</table>
	</form>

</div>
<%@include file="./include/footer.jsp" %>
</body>
</html>