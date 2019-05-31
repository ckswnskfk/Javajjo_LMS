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
			<table class="table table-bordered">
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>출결(Y : 입실 , N : 퇴실)</th>
					<th>시간</th>
				
				</tr>
				<c:forEach var="AttendedDTO" items="${dlists}">
					<tr>
						<td><a href="./attended_Detail.do?id=${AttendedDTO.id}">${AttendedDTO.id}</a></td>
						<td>${AttendedDTO.name}</td>
						<c:choose>
						<c:when test="${AttendedDTO.a_check eq null}">
						<td>결석</td>
						</c:when>
						<c:otherwise>
						<td>${AttendedDTO.a_check}</td>
						</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${AttendedDTO.regdate eq null}">
								<td><input type="submit"  value="결석문자발송"></td>
							</c:when>
							<c:otherwise>
								<td>${AttendedDTO.regdate}</td>
							</c:otherwise>
						</c:choose>
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




	<%@include file="./include/footer.jsp"%>
</body>
</html>