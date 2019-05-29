<%@page import="happy.jaj.prj.dtos.Exam_Sel_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 관리</title>
</head>
<body>
<%@include file="./include/header.jsp" %>
<%
	Exam_Sel_DTO dto = (Exam_Sel_DTO)request.getAttribute("dto");
%>
	<table>
		<tr>
			<td><p style="font-size: 20px">
				<input type="hidden" name="examcode" value='<%=dto.getExamcode()%>'>
				<input type="hidden" name="examnum" value='<%=dto.getExamnum()%>'>
				<%=dto.getExamnum() %></p>
			</td>
			<td>
				<%=dto.getExam() %><a style="color:red"><%=dto.getAllot() %></a>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>



<%@include file="./include/footer.jsp" %>

</body>
</html>