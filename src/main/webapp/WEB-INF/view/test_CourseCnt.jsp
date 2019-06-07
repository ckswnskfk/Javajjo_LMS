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
<title>과제 등록</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/test.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
 #test_back{
 	float: right;
 }
</style>
</head>
<%
	List<Course_DTO> list = (List<Course_DTO>)request.getAttribute("list");
%>
<body>

<div style="margin-bottom: 20px; margin-top: 20px;">
	<h3 class="test_title">&nbsp;&nbsp;&nbsp;&nbsp;▶ 회차를 선택하세요.</h3>
</div>
 <form action="./test_CouresSel.do" method="post" onsubmit="return selectCoursecnt()"> 
 	<table class="table"> 
 		<tr> 
 			<td>과정명</td> 
 			<td>${testsession.coursename}</td> 
 		</tr> 
 		<tr> 
 			<td>회   차</td> 
 			<td> 
 			<select id="coursecode" name="coursecode"> 
 				<option>회차</option> 
 				<% 
				for(Course_DTO dto : list){
 					%> 
 				<option value='<%=dto.getCoursecode()%>'><%=dto.getCoursecnt() %></option> 
 					<% 	 
				}
 				%> 
 			</select> 
 			</td> 
 		</tr> 
 		<tr> 
 			<td><input type="submit" value="선택완료" class="btn btn-primary active"></td> 
 			<td><input type="button" value="취소" onclick="javascript:window.close()" class="btn btn-primary disabled"  id="test_back"> 
 		</tr> 
 	</table> 
 </form> 

</body>
</html>