<%@page import="java.util.List"%>
<%@page import="happy.jaj.prj.dtos.Test_Exam_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 관리</title>
<script type="text/javascript">
	function moveupdate(){
		location.href= "./division.do";
	}
</script>
</head>
<body>
<%
	List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)request.getAttribute("dto");
%>
<%@include file="./include/header.jsp" %>
	<h1>과제 등록</h1>
		<h4>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h4>
		<h4>과목명 : ${testsession.subjectname}</h4>
		<hr>
		<h4>과목유형 : ${testsession.subjecttype}</h4>
		<h4>과제유형 : ${testsession.examtype}</h4>
		<hr>
		<h4>과제명 : ${testsession.testname}</h4>
		<h4>과제 날짜 : ${testsession.testday}</h4>
		<hr>
	<table>
		<tr>
			<td>No.</td>
			<td>문제</td>
			<td>배점</td>
		</tr>
		<% 
			for(int i=0; i<list.size(); i++){
				Test_Exam_DTO dto = list.get(i);
			%>
				<tr>
					<td><%=dto.getExamnum()%></td>
					<td><%=dto.getExam() %></td>
					<td><%=dto.getAllot() %></td>
				</tr>
			<% 
			}
			%>	
		<tr>
			<td colspan="3"><input type="button" value="수정" onclick="moveupdate()"></td>
		</tr>	
	</table>
<%@include file="./include/footer.jsp" %>
</body>
</html>