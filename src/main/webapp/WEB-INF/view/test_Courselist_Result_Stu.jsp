<%@page import="happy.jaj.prj.dtos.Course_DTO"%>
<%@page import="java.util.List"%>
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
	List<Course_DTO> list = (List<Course_DTO>)request.getAttribute("dto");
%>
<%@include file="./include/header.jsp" %>
<h1>수강과정</h1>
<table>
<% 
	if(list==null){%>
		<tr>
			<td colspan="3">수강중인 과정이 없습니다. </td>
		</tr>
<% 
	}else{
	%>
	
	<tr>
		<td>과정명</td>
		<td>시작날짜</td>
		<td>회차</td>
	</tr>
	<% 
		for(Course_DTO dto : list ){ %>
	<tr>
		<td><a href="./test_Subject_ResultStu.do?coursecode=<%=dto.getCoursecode() %>&coursename=<%=dto.getCoursename()%>"><%=dto.getCoursename() %></a></td>
		<td><%=dto.getStartdate() %></td>
		<td><%=dto.getCoursecnt() %></td>
	</tr>
	<% 
	}}%>
</table>
<%@include file="./include/footer.jsp" %>

</body>
</html>