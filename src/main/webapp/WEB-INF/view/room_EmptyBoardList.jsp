<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script type="text/javascript" src="./js/calendar.js"></script>
<style>
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }
  table{
  	text-align: center;
  }

</style>
</head>
<body>
<%@ include file="./include/header.jsp" %>
	<table>
	<c:forEach items="${lists}" var="dto" varStatus="vs">
		<tr>
			<th>강의실 이름 : </th>
			<td>${dto.name}</td>
			<th>수용인원 : </th>
			<td>${dto.personel}</td>
		</tr>
		<tr>
			<th>ID : </th>
			<td>${member.id}</td>
			<th>날짜 : </th>
			<td>${regdate}</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: right; border-bottom: 1px solid black;">
			<c:choose>
				<c:when test="${dto.check eq 'Y'}">
					<input type="button" value="예약" onclick="room_empty_request('${dto.code}','${regdate}','${member.id}')">
				</c:when>
				<c:otherwise>
					<input type="button" value="예약취소" onclick="room_empty_cancle('${dto.code}','${regdate}','${member.id}')">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</c:forEach>
	</table>
					<input type="button" value="취소" onclick="location.href='./room_main.do'">
<%@ include file="./include/footer.jsp" %>
</body>
</html>
