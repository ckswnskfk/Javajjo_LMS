<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="happy.jaj.prj.dtos.Subject_DTO"%>
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
<%
	List<Subject_DTO> list = (List<Subject_DTO>)request.getAttribute("list");
	String coursename = (String)session.getAttribute("coursename");
%>
<body>
<h1>과목 조회</h1>
<h3>과정명 : <%=coursename %></h3>
<table>
	<tr>
		<td>과목명</td>
		<td>과목 유형</td>
		<td>과제 유형</td>
	</tr>
	<%
	for(Subject_DTO dto: list){
		%>
		<tr>
			<td><a href="./test_Input.do?subjectcode=<%=dto.getsubjectcode()%>&subjecttype=<%=dto.getsubjecttype()%>&examtype=<%=dto.getexamtype()%>&subjectname=<%=dto.getsubjectname()%>"><%=dto.getsubjectname() %></a></td>
			<td><%=dto.getsubjecttype() %></td>
			<td><%=dto.getexamtype() %></td>
		</tr>
		<% 
	}
	%>
	
</table>


</body>
</html>