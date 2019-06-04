<%@page import="happy.jaj.prj.dtos.Answer_Des_DTO"%>
<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@page import="happy.jaj.prj.dtos.Test_Exam_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>과제 관리</title>
</script>
</head>
<script type="text/javascript" src="./js/test.js"></script>
<body>
<%@include file="./include/header.jsp" %>
<%
	Exam_Des_DTO dto = (Exam_Des_DTO)request.getAttribute("dto");
	Answer_Des_DTO answer = (Answer_Des_DTO)request.getAttribute("answer");
	int maxexam=(int)request.getAttribute("maxexamnum");
%>
<form action="#" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td></td>
			<td>
			<%
			for(int i=1; i<(maxexam+1);i++){
				%>
				<input type="button" value="<%=i%>번" onclick="numberclick(<%=i%>)">
 				<%  
			}
			%>
			</td>
		</tr>
		<tr>
			<td><p style="font-size: 20px">
			<input type="hidden" name="examcode" value='<%=dto.getExamcode()%>'>
			<input type="hidden" name="examnum" value='<%=dto.getExamnum()%>'>
			<input type="hidden" name="page" >
			<%=dto.getExamnum() %></p></td>
			<td><%=dto.getExam() %><a style="color:red"><%=dto.getAllot() %></a></td>
		</tr>
		<tr>
			<td>설명</td>
			<td colspan="2"><%=dto.getExplanation() %></td>
		</tr>
		<tr>
			<td>답안</td>
			<td colspan="2"><textarea cols="50" rows="5" name="answer"><c:choose><c:when test="${answer.answer eq null}">널</c:when><c:otherwise>${answer.answer}</c:otherwise></c:choose></textarea></td>
		</tr>
		<tr>
			<td colspan="3"><input type="file" id="originalfilename" name="originalfilename">${answer.originfile}</td>
		</tr>
		<tr>
			<td><input type="button" value="← 이전문제" onclick="pageexam(true,<%=maxexam%>)"></td>
			<td><input type="button" value="다음 문제 →" onclick="pageexam(false,<%=maxexam%>)"></td>
			<td><input type="button" value="시험 제출" onclick="examsubmit()"></td>
		</tr>
	</table>
</form>
<%@include file="./include/footer.jsp" %>
</body>
</html>