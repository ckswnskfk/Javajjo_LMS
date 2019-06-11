<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

table, tr, td {
	text-align: center;
	max-width: 1000px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

#rollbookform {
	margin: auto;
}

#back {
	margin-top: 15px;
	margin-left: 250px;
}

#rollbook {
	text-align: center;
}
</style>

<title>출석부 조회</title>
<link rel="stylesheet" type="text/css" href="./css/template.css">
</head>






<body>
	<div class="content-wrapper">
		<%@include file="./include/header.jsp"%>


		<script type="text/javascript">
			function attDetail(id) {
				var url = "./attended_Detail.do?id=" + id;
				var name = "attended detail";
				var option = "width = 600,height = 500, top = 100, left = 200";
				window.open(url, name, option);
			}
		</script>
		<script type="text/javascript">
			
		</script>
		<div id="back">
			<input class="btn btn-sm btn-primary btn-center" type="button"
				value="돌아가기" onclick="history.back(-1)">
		</div>

		<div id="rollbook">
			<h1>출 석 부</h1>
			<form action="#" method="get" id="rollbookform">
				<table class="table table-bordered" id="rollbook_list">
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>출결(Y : 입실 , N : 퇴실)</th>
						<th>시간</th>

					</tr>
					<c:forEach var="AttendedDTO" items="${dlists}">
						<tr>
							<td><a onclick="attDetail('${AttendedDTO.id}')">${AttendedDTO.id}</a></td>
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
									<td><input class="btn btn-sm btn-primary" value="결석문자발송"
										onclick="sms('${AttendedDTO.id}','${regdate}')"></td>
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
			function sms(id, regdate) {
				location.href = "./attended_SMS.do?id=" + id + "&regdate="
						+ regdate;
				alert("성공");
			}
		</script>




		<%@include file="./include/footer.jsp"%>
	</div>
</body>
</html>