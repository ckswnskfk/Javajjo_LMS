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
<style type="text/css">
.test_session{
	border: 1px solid black;
	padding: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}
 tbody>tr:hover { background-color: lightyellow; } 
 #test_back{
 	float: right;
 }
</style>
<script type="text/javascript">
	function testback(){
		location.href="./test_Course_Insert.do";
	}
</script>
</head>
<body>
<%
	List<Subject_DTO> list = (List<Subject_DTO>)request.getAttribute("list");
// 	String coursename = (String)session.getAttribute("coursename");
%>
<%@include file="./include/header.jsp" %>
<div class="container">
	<div class="test_session">
		<h2>과목 조회</h2>
		<h3>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h3>
	</div>
	<table class="table">
	<%
	if(list.size()==0){
		%>
		<tr>
			<td>등록된 과목이 없습니다.</td>
		</tr>
		<% 
	}else{
	%>
	<thead>
		<tr>
			<th>과목명</th>
			<th>과목 유형</th>
			<th>과제 유형</th>
		</tr>
	</thead>
	<tbody>
		<%
		for(Subject_DTO dto: list){
			%>
			<tr>
				<td><a id="test_line" href="./test_Input.do?subjectcode=<%=dto.getsubjectcode()%>&subjecttype=<%=dto.getsubjecttype()%>&examtype=<%=dto.getexamtype()%>&subjectname=<%=dto.getsubjectname()%>"><%=dto.getsubjectname() %></a></td>
				<label for="test_line">
				<td><%=dto.getsubjecttype() %></td>
				<td><%=dto.getexamtype() %></td>
				</label>
			</tr>
			<% 
		}
		%>
	</tbody>
	<%
	}
	%>
	
	</table>
	<div>
		<input id="test_back" type="button" value="뒤로가기" class="btn btn-default" onclick="testback()">
	</div>
</div>
<%@include file="./include/footer.jsp" %>
</body>
</html>