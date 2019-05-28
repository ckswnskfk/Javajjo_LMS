<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>과제 관리</title>
</head>
<body>
<%
	Exam_Des_DTO dto = (Exam_Des_DTO)request.getAttribute("dto");
%>

	<table>
		<tr>
			<td><p style="font-size: 20px"><%=dto.getExamnum() %></p></td>
			<td><%=dto.getExam() %><a style="color:red"><%=dto.getAllot() %></a></td>
		</tr>
		<tr>
			<td>채점기준</td>
			<td><%=dto.getStandard() %></td>
		</tr>
		<tr>
			<td>설명</td>
			<td><%=dto.getExplanation() %></td>
		</tr>
		<tr>
			<td>답안</td>
			<td><textarea cols="50" rows="5" name="answer"></textarea></td>
		</tr>
	</table>

</body>
</html>