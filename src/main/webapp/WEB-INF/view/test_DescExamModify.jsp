<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 관리</title>
<script type="text/javascript" src="./js/test.js"></script>
</head>
<%
	Exam_Des_DTO dto = (Exam_Des_DTO)request.getAttribute("dto");
%>

<body>
	<h1>서술문제 상세/수정</h1>
	<form action="./desc_Exam_Modify.do" method="get" onsubmit="return descexaminsert()">
		<input type="hidden" name="examcode" value="<%=dto.getExamcode()%>">
		<table>
			<tr>
				<td>문제번호</td>
				<td><input name="examnum" value="<%=dto.getExamnum()%>" readonly="readonly"></td>	
			</tr>
			<tr>
				<td>문제</td>
				<td><textarea rows="5" cols="10" name="exam"><%=dto.getExam() %></textarea></td>
			</tr>
			<tr>
				<td>설명</td>
				<td><textarea rows="5" cols="10" name="explanation"><%=dto.getExplanation() %></textarea></td>
			</tr>
			<tr>
				<td>채점기준</td>
				<td><textarea rows="5" cols="10" name="standard"><%=dto.getStandard() %></textarea></td>
			</tr>
			<tr>
				<td>정답</td>
				<td><textarea rows="5" cols="10" name="c_answer"><%=dto.getC_answer() %></textarea></td>
			</tr>
			<tr>
				<td>배점</td>
				<td><input name="allot" value="<%=dto.getAllot()%>"></td>
			</tr>
		</table>
		<input type="submit" value="저장">
		<input type="button" value="취소" onclick="testback()">
	</form>
</body>
</html>