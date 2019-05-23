<%@page import="happy.jaj.prj.dtos.Test_Exam_DTO"%>
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
<script type="text/javascript" src="./js/test.js"></script>
<%
	List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)request.getAttribute("dto");
%>
</head>
<body>
	<h1>과제 등록</h1>
		<h4>과정명 : ${testsession.coursename}</h4>
		<h4>과목명 : ${testsession.subjectname}</h4>
		<hr>
		<h4>과목유형 : ${testsession.subjecttype}</h4>
		<h4>과제유형 : ${testsession.examtype}</h4>
		<hr>
		<h4>과제명 : ${testsession.testname}</h4>
		<h4>과제 날짜 : ${testsession.testday}</h4>
		<hr>
		<input type="button" value="등록" onclick="location.href='./sel_ExamForm.do'">
		<input type="button" value="추가" onclick="examlist()">
		<input type="button" value="복사" onclick="coursecnt()">
		<input type="button" value="삭제" onclick="examdelete()">

	<table>
		<%
		if(list.size()==0){
			%>
			<tr>
				<td><p>아직 문제가 없습니다.<br> 문제를 추가해 주세요.</p></td>
			</tr>
			<% 
		}else{
			%>
			<tr>
				<td>삭제</td>
				<td>No.</td>
				<td>문제</td>
				<td>배점</td>
			</tr>			
			<% 
			for(Test_Exam_DTO dto : list){
			%>
				<tr>
					<td><input type="checkbox" name="del"></td>
					<td><%=dto.getExamnum() %></td>
					<td><a href="./sel_Exam_ModifyForm.do?examcode=<%=dto.getExamcode()%>&examnum=<%=dto.getExamnum()%>&allot=<%=dto.getAllot()%>"><%=dto.getExam() %>||<%=dto.getExamcode()%></a></td>
					<td><%=dto.getAllot() %></td>
				</tr>
			<% 
			}
			%>
			<tr>
				<td>총 점</td>
				<td colspan="3">${total}</td>
			</tr>
			<% 
		}
		%>
	</table>
		<input type="button" value="문제 등록" onclick="examinsert()">
		<input type="button" value="뒤로 가기" onclick="testback()">
</body>
</html>