<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 등록</title>
</head>
<body>
<%
	List<Exam_Des_DTO> list = (List<Exam_Des_DTO>)request.getAttribute("list");
%>
<form action="./test_typecopy.do" method="post" onsubmit="return testcopy()">
<table>
	<tr>
		<td>chk</td>
		<td>문제</td>
		<td>설명</td>
		<td>채점기준</td>
		<td>정답</td>
	</tr>
	<%
	for(Exam_Des_DTO dto : list){
		%>
		<tr>	
			<td><input type="checkbox" name="examcode" value="<%=dto.getExamcode()%>"></td>
			<td><%=dto.getExam() %></td>
			<td><%=dto.getExplanation() %></td>
			<td><%=dto.getStandard() %></td>
			<td><%=dto.getC_answer() %></td>
		</tr>
		<% 
	}
	%>
	<tr>
		<td colspan="5"><input type="submit" value="선택완료"></td>
	</tr>
</table>
</form>



</body>
</html>