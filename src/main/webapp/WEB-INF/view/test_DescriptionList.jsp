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
<style type="text/css">
	.test_session{
	border: 1px solid black;
	padding: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
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
<div class="container">
<!-- 	<div class="test_session"> -->
<!-- 		<h2>과제 등록</h2> -->
<%-- 			<h4>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h4> --%>
<%-- 			<h4>과목명 : ${testsession.subjectname}</h4> --%>
<!-- 			<hr> -->
<%-- 			<h4>과목유형 : ${testsession.subjecttype}</h4> --%>
<%-- 			<h4>과제유형 : ${testsession.examtype}</h4> --%>
<!-- 			<hr> -->
<%-- 			<h4>과제명 : ${testsession.testname}</h4> --%>
<%-- 			<h4>과제 날짜 : ${testsession.testday}</h4> --%>
<!-- 	</div> -->
	<table style="margin-bottom: 20px; margin-top: 20px;  width:100%;margin-bottom:1rem;background-color:transparent;">
		<thead>
			<tr>
				<td colspan="3"><p style="font-size: 20px;">▶ 과제 목록</p></td>
			</tr>
		</thead>
		<tr>
			<td style="width:34%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</td>
			<td style="width:33%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과목유형 : ${testsession.subjecttype}</td>
			<td style="width:33%; padding-left: 12px; text-align:justify;"> 과제명 : ${testsession.testname}</td>
		</tr>
		<tr>
			<td style="width:34%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과목명 : ${testsession.subjectname}</td>
			<td style="width:33%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과제유형 : ${testsession.examtype}</td>
			<td style="width:33%; padding-left: 12px;  text-align:justify;">과제 날짜 : ${testsession.testday}</td>
		</tr>
	</table>
	<table class="table">
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
			<td colspan="3"><input type="button" value="수정" onclick="moveupdate()"  class="btn btn-primary active"></td>
		</tr>	
	</table>
</div>
<%@include file="./include/footer.jsp" %>
</body>
</html>