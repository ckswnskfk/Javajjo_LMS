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
</head>
<%
	List<Course_DTO> list = (List<Course_DTO>)request.getAttribute("list");
%>
<body>


<h1>회차</h1>
 <form action="./test_CouresSel.do" method="post" onsubmit="return selectCoursecnt()"> 
 	<table> 
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
 			<td><input type="submit" value="선택완료"></td> 
 			<td><input type="button" value="취소" onclick="javascript:window.close()"> 
 		</tr> 
 	</table> 
 </form> 

</body>
</html>