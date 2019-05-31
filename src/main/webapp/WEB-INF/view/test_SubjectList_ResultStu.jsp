<%@page import="happy.jaj.prj.dtos.Subject_DTO"%>
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
	List<Subject_DTO> list = (List<Subject_DTO>)request.getAttribute("list");
// 	String coursename = (String)session.getAttribute("coursename");
%>
<%@include file="./include/header.jsp" %>
<h1>과목 조회</h1>
<h3>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h3>
<table>
<% 
	if(list==null){
		%>
		<tr>
			<td colspan="3">과목에 등록되 과제가 없습니다. </td>
		</tr>
		<% 
	}else{
%>
	<tr>
		<td>과목명</td>
		<td>과목 유형</td>
		<td>과제 유형</td>
	</tr>
	<%
	for(Subject_DTO dto: list){
		%>
		<tr>
			<td><a href="./test_Test_ResultStu.do?subjectcode=<%=dto.getsubjectcode()%>&subjecttype=<%=dto.getsubjecttype()%>&examtype=<%=dto.getexamtype()%>&subjectname=<%=dto.getsubjectname()%>"><%=dto.getsubjectname() %></a></td>
			<td><%=dto.getsubjecttype() %></td>
			<td><%=dto.getexamtype() %></td>
		</tr>
		<% 
	}
	}
	%>
	
</table>

<%@include file="./include/footer.jsp" %>

</body>
</html>