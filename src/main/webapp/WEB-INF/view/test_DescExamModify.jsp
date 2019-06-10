<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
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
<script type="text/javascript" src="./js/test.js"></script>
<style type="text/css">
 #test_back{
 	float: right;
 }
</style>
</head>
<%
	Exam_Des_DTO dto = (Exam_Des_DTO)request.getAttribute("dto");
%>

<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<div class="container">
	<div style="margin-top: 30px; margin-top: 30px;"> 
		<h3>&nbsp;&nbsp;&nbsp;&nbsp;▶ 서술형문제 상세조회 / 수정</h3>
	</div>
	<div style="margin-left: 50px;">
		<form action="./desc_Exam_Modify.do" method="get" onsubmit="return descexaminsert()">
			<input type="hidden" name="examcode" value="<%=dto.getExamcode()%>">
			<table  class="table">
				<tr>
					<td>문제번호</td>
					<td>
	<%-- 					<input name="examnum" value="<%=dto.getExamnum()%>" readonly="readonly"> --%>
						<div class="form-group">
							<input type="text" class="form-control" id="usr"  name="examnum"  readonly="readonly" value="<%=dto.getExamnum()%>">
						</div>
					</td>	
				</tr>
				<tr>
					<td>문제</td>
					<td>
	<%-- 					<textarea rows="5" cols="10" name="exam"><%=dto.getExam() %></textarea> --%>
							<div class="form-group">
	  							<textarea class="form-control" rows="2" id="comment" cols="30" name="exam"><%=dto.getExam() %></textarea>
							</div>
					</td>
				</tr>
				<tr>
					<td>설명</td>
					<td>
	<%-- 					<textarea rows="5" cols="10" name="explanation"><%=dto.getExplanation() %></textarea> --%>
						<div class="form-group">
	  						<textarea class="form-control" rows="2" id="comment" cols="30" name="explanation"><%=dto.getExplanation() %></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td>채점기준</td>
					<td>
	<%-- 					<textarea rows="5" cols="10" name="standard"><%=dto.getStandard() %></textarea> --%>
						<div class="form-group">
	  						<textarea class="form-control" id="comment"  rows="2" cols="30" name="standard"><%=dto.getStandard() %></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td>정답</td>
					<td>
	<%-- 					<textarea rows="5" cols="10" name="c_answer"><%=dto.getC_answer() %></textarea> --%>
						<div class="form-group">
	  						<textarea class="form-control" id="comment"  rows="2" cols="30" name="c_answer"><%=dto.getC_answer() %></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td>배점</td>
					<td>
	<%-- 					<input name="allot" value="<%=dto.getAllot()%>"> --%>
						<div class="form-group">
							  <input type="text" class="form-control" id="usr" name="allot" value="<%=dto.getAllot()%>">
						</div>
					</td>
				</tr>
			</table>
			<input type="submit" value="저장" class="btn btn-primary active">
			<input type="button" value="취소" onclick="testback()" class="btn btn-primary disabled" id="test_back">
		</form>
	</div>
</div>
	<%@include file="./include/footer.jsp" %>
	</div>
</body>
</html>