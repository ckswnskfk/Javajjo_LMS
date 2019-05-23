<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석부 조회</title>
</head>
<body>
	<%@include file="./include/header.jsp"%>

	<div>


		<form action="#" method="get" id="rollbook">
			<table>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>출결</th>
					<th>시간</th>

					<!-- 		<th>문자발송</th> -->
				</tr>
				<c:forEach var="AttendedDTO" items="${dlists}">
					<tr>
						<td><a href="./attended_Detail.do?id=${AttendedDTO.id}">${AttendedDTO.id}</a></td>
						<td>${AttendedDTO.name}</td>
						<td>${AttendedDTO.a_check}</td>
						<td>${AttendedDTO.regdate}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>


	<script type="text/javascript">
		function sms() {
			var id = document.getElementById("inputId").value;
			var a_check = document.getElementById("inputA_check").value;
			alert(id + ":" + a_check);

			if (a_check == null) {
				// 		location.href="/attended_SMS.do";
				var frm = document.forms[0];
				frm.action = "./attended_SMS.do";
				var result = "";
				alert("문자발송 성공");

			}

		}
	</script>
	<input type="button" value="결석문자발송" onclick="sms()">


	<a href="./attended_Detail.do?id=01011111111">출결 상세보기</a>


	<%@include file="./include/footer.jsp"%>
</body>
</html>