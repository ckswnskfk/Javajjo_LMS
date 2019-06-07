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
<script type="text/javascript">
	function subjectmove(){
		var frm = document.forms[0];
		frm.action="./test_Subject_ResultStu.do";
		frm.method = "post";
		frm.submit();
	}
</script>
<div class="container">
	<h2>▶ 수강과정</h2>
	<form action="">
		<table class="table">
		<% 
			if(list.size()==0){%>
				<tr>
					<td colspan="3">수강중인 과정이 없습니다. </td>
				</tr>
		<% 
			}else{
			%>
			
			<tr>
				<th>과정명</th>
				<th>시작날짜</th>
				<th>회차</th>
			</tr>
			<% 
				for(Course_DTO dto : list ){ %>
			<tr>
				<td><input type="hidden" name="coursename" value="<%=dto.getCoursename()%>"><%=dto.getCoursename() %></td>
				<td><input type="hidden" name="coursecode" value="<%=dto.getCoursecode()%>"><%=dto.getStartdate() %></td>
				<td><input type="hidden" name="coursecnt" value="<%=dto.getCoursecnt()%>"><%=dto.getCoursecnt() %></td>
			</tr>
			<% 
			}}%>
		</table>
	</form>
	<input type="button" class="btn btn-success" value="다음" onclick="subjectmove()">
</div>
<%@include file="./include/footer.jsp" %>

</body>
</html>