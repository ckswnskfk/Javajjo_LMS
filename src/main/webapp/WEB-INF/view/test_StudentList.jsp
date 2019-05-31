<%@page import="happy.jaj.prj.dtos.Student_DTO"%>
<%@page import="happy.jaj.prj.dtos.UserCourse_DTO"%>
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
	List<Student_DTO> list = (List<Student_DTO>)request.getAttribute("list");
%>
<script type="text/javascript">
	function markStudent(id){
		location.href="./desc_Detail_Exam.do?id="+id;
	}
</script>
<%@include file="./include/header.jsp" %>
<h1>수강학생 조회</h1>
<table>
	<%
	if(list==null){
		%>
		<tr>
			<td>수강하는 학생이 없습니다.</td>
		</tr>
		<% 
	}else{
		%>
		<tr>
			<td>ID</td>
			<td>이름</td>
			<td></td>
		</tr>
		<% 
		for(Student_DTO dto : list){
			%>
			<tr>
				<td><%=dto.getId() %></td>
				<td><%=dto.getName() %></td>
				<td><input type="button" value="채점" onclick="markStudent('<%=dto.getId()%>')"></td>
			</tr>
			<% 
		}
	}
	%>
</table>

<%@include file="./include/footer.jsp" %>
</body>
</html>