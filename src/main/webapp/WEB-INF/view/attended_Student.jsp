<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 출석 상세조회</title>
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

td {
	border: 1px solid blue;
	border-collapse: collapse;
}

#attended_Student {
	margin: auto;
	display: inline;
	border: 1px solid blue;
	border-collapse: collapse;
}

#back {
	margin-left: 250px;
}
</style>

<script type="text/javascript">
	var id = '${member.id}';

	onload = (function() {
		$
				.ajax({
					url : "./chk_Attended.do",
					data : id,
					type : "POST",
					success : function(n) {
						if (n > 1) {
							$("#attended_button").html("");
						} else if (n == 1) {
							var html = "<button class='btn btn-sm btn-danger btn-center' onclick=\"location.href='./exit_Attended.do?id=${member.id}'\">퇴실</button>";
							$("#attended_button").html(html);
						} else if (n == 0) {
							var html = "<button class='btn btn-sm btn-success btn-center' onclick=\"location.href='./insert_Attended.do?id=${member.id}'\">입실</button>";
							$("#attended_button").html(html);
						}
					}
				});
	});
</script>




</head>
<body>
	<div class="content-wrapper">
		<%@include file="./include/header.jsp"%>
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
		<div id="back">
			<input class="btn btn-sm btn-primary btn-center" type="button"
				value="돌아가기" onclick="history.back(-1)">
		</div>

		<%@include file="./include/footer.jsp"%>
	</div>
</body>
</html>