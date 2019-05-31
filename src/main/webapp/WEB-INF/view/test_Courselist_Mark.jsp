<%@page import="happy.jaj.prj.dtos.Course_DTO"%>
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
<%
	Course_DTO dto = (Course_DTO)request.getAttribute("dto");
%>
<%@include file="./include/header.jsp" %>
	<h1>수강과정</h1>
	<table>
		<%
		if(dto==null){
			%>
			<tr>
				<td>담당과정이 없습니다. </td>
			</tr>
			<%
		}else{
			%>
			<tr>
				<td>과정명</td>
				<td>시작 날짜</td>
				<td>회차</td>
			</tr>
			<tr>
				<td><a href="./test_Subject_Mark.do?coursecode=<%=dto.getCoursecode()%>&coursename=<%=dto.getCoursename()%>"><%=dto.getCoursename() %></a></td>
				<td><%=dto.getStartdate() %></td>
				<td><%=dto.getCoursecnt() %></td>
			</tr>
			<% 	
		}
		%>
	</table>

<%@include file="./include/footer.jsp" %>
</body>
</html>