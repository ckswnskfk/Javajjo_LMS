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
<%@include file="./include/header.jsp" %>
<script type="text/javascript">
	function subjectmove(){
		var frm = document.forms[0];
		frm.action="./test_Subject_Result.do";
		frm.method = "post";
		frm.submit();
	}
</script>
<%
	Course_DTO dto = (Course_DTO)request.getAttribute("dto");
%>
<div class="container">
	<h3 style="margin-top: 20px">▶ 담당과정</h3>
	<form action="">
		<table class="table" style="margin-top: 20px">
			<thead>
				<tr>
					<th>과정명</th>
					<th>시작날짜</th>
					<th>회차</th>
				</tr>
			</thead>
			<%
			if(dto==null){
				%>
				<tr>
					<td colspan="3">담당과정이 없습니다. </td>
				</tr>
				<% 
			}else{
			%>
			<tr>
				<td><input type="hidden" name="coursename" value="<%=dto.getCoursename()%>"><%=dto.getCoursename() %></td>
				<td><input type="hidden" name="coursecode" value="<%=dto.getCoursecode()%>"><%=dto.getStartdate() %></td>
				<td><input type="hidden" name="coursecnt" value="<%=dto.getCoursecnt()%>"><%=dto.getCoursecnt() %></td>
			</tr>
			<%
			}
			%>
		</table>
	</form>
	<input type="button" class="btn btn-success" value="다음"  onclick="subjectmove()">
</div>
<%@include file="./include/footer.jsp" %>
</body>
</html>