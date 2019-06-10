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
<link rel= "stylesheet" type="text/css" href="./css/template.css">
</head>
<script type="text/javascript" src="./js/test.js"></script>
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<%
	Exam_Des_DTO dto = (Exam_Des_DTO)request.getAttribute("dto");
	Answer_Des_DTO answer = (Answer_Des_DTO)request.getAttribute("answer");
	int maxexam=(int)request.getAttribute("maxexamnum");
%>
<div class="container">
<form action="#" method="post" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<td colspan="10">
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
			<td style="width: 30px"><p style="font-size: 20px">
<!-- 				<div class="form-group"> -->
<%-- 					<input type="text" class="form-control" id="usr"  name="examnum"  readonly="readonly" value="<%=dto.getExamnum() %>"> --%>
<!-- 				</div> -->
				<%=dto.getExamnum() %>
			</p>
			</td>
			<td colspan="9"><%=dto.getExam() %><a style="color:red"><%=dto.getAllot() %></a>
			<input type="hidden" name="examcode" value='<%=dto.getExamcode()%>'>
			<input type="hidden" name="examnum" value='<%=dto.getExamnum()%>'>
			<input type="hidden" name="page" >
			</td>
		</tr>
		<tr>
			<td>설명</td>
			<td colspan="9">
<%-- 				<%=dto.getExplanation() %> --%>
				<div class="form-group">
  					<textarea class="form-control" rows="2" id="comment" cols="30" name="explanation" readonly="readonly"><%=dto.getExplanation() %></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td>답안</td>
			<td colspan="9">
				<div class="form-group">
					<textarea class="form-control" cols="30" rows="2" name="answer"><c:choose><c:when test="${answer.answer eq null}">정답을 입력해 주세요.</c:when><c:otherwise>${answer.answer}</c:otherwise></c:choose></textarea>
				</div>	
			</td>
		</tr>
		<tr>
			<td colspan="9">
				<div class="form-group">
					<input type="file"  class="form-control" id="originalfilename" name="originalfilename">${answer.originfile}
				</div>
			</td>
		</tr>
		<tr> 
			<td><input type="button" class="btn btn-success" value="← 이전문제" onclick="pageexam(true,<%=maxexam%>)"></td>
			<td><input type="button" class="btn btn-success" value="다음 문제 →" onclick="pageexam(false,<%=maxexam%>)"></td>
			<td colspan="8"><input type="button" class="btn btn-warning" value="시험 제출" onclick="examsubmit()"></td>
		</tr>
	</table>
</form>
</div>
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>