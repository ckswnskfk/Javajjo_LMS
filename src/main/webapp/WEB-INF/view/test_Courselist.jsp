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
<link rel= "stylesheet" type="text/css" href="./css/template.css">
</head>
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<script type="text/javascript">
	function subjectmove(){
		var frm = document.forms[0];
		frm.action="./test_Subject_Insert.do";
		frm.method = "post";
		frm.submit();
	}
</script>
<!-- <h1>담당과정</h1> -->
<%
	Course_DTO dto = (Course_DTO)request.getAttribute("dto");
%>
<div class="container">
	<h3 style="margin-top: 20px">▶ 담당과정</h3>
  	
	<form action="">
	<table class="table" style="margin-top: 20px">
		<thead>
		<tr>
			<td>과정명</td>
			<td>시작날짜</td>
			<td>회차</td>
		</tr>
		</thead>
		<tr>
			<td><input type="hidden" name="coursename" value='<%=dto.getCoursename()%>'><%=dto.getCoursename() %></td>
			<td><input type="hidden" name="coursecode" value='<%=dto.getCoursecode()%>'><%=dto.getStartdate() %></td>
			<td><input type="hidden" name="coursecnt" value='<%=dto.getCoursecnt()%>'><%=dto.getCoursecnt() %></td>
		</tr>
	</table>
	</form>
	<input type="button" class="btn btn-success" value="다음" onclick="subjectmove()">
</div>

<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>