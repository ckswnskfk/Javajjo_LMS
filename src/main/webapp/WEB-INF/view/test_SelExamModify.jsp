<%@page import="happy.jaj.prj.dtos.Exam_Sel_DTO"%>
<%@page import="happy.jaj.prj.dtos.ContentSelect_DTO"%>
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

</head>
<body>
<%-- <h1>${examcode}</h1> --%>
<%
	Exam_Sel_DTO dto = (Exam_Sel_DTO)request.getAttribute("dto");
	List<ContentSelect_DTO> list = (List<ContentSelect_DTO>)request.getAttribute("Clist");

%>
	<%@include file="./include/header.jsp" %>
	<h1>과제등록</h1>
	<form action="./sel_Content_Modify.do" method="post" onsubmit="return selexaminsert()">
		<input type="text" name="examcode" value='<%=dto.getExamcode()%>'>
		<table>
			<tr>
				<td>문제번호</td>
				<td><input name="examnum" readonly="readonly" value='<%=dto.getExamnum()%>'></td>
			</tr>
			<tr>
				<td>문제</td>
				<td><textarea  rows="5" cols="30" name="exam"><%=dto.getExam() %></textarea></td>
			</tr>
<!-- 		<div> -->

			<% 
			int cnt = 0;
			for(ContentSelect_DTO dto1 : list){
				cnt++;
				%>
				<tr>
					<td><input type="hidden" value='<%=cnt %>' name="contentnum"><%=cnt%></td>
					<td><input type="text" name="examcontent" value='<%=dto1.getExamcontent()%>'></td>
				</tr>
				<% 
			}
			%>
			
<!-- 		</div> -->
			<tr>
				<td>정답</td>
				<td><input type="text" name="c_answer" value="<%=dto.getC_answer()%>"></td>
			</tr>
			<tr>
				<td>배점</td>
				<td><input type="text" name="allot" value="<%=dto.getAllot()%>"></td>
			</tr>
		</table>
		<input type="submit" value="문제등록">
		<input type="button" value="취소" onclick="testback()">
	</form>
<%@include file="./include/footer.jsp" %>
</body>
</html>