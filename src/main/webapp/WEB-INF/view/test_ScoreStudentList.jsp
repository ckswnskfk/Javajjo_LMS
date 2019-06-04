<%@page import="happy.jaj.prj.dtos.Score_DTO"%>
<%@page import="happy.jaj.prj.dtos.Student_DTO"%>
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
<%
	List<Student_DTO> list = (List<Student_DTO>)request.getAttribute("list");
	List<Score_DTO> scorelist = (List<Score_DTO>)request.getAttribute("scorelist");
%>
<body>
<%@include file="./include/header.jsp" %>
<%-- ${list} --%>
<!-- <hr> -->
<%-- ${scorelist} --%>

<table>
	<%
	if(list.size()==0){
		%>
		<tr>
			<td>수강하는 학생이 없습니다.</td>
		</tr>
		<% 
	}
	%>
	<tr>
		<td>학생 평균 : </td>
		<td colspan="2">${avg}</td>
	</tr>
	<tr>
		<td>ID</td>
		<td>NAME</td>
		<td>SCORE</td>
	</tr>
	<%
	for(int i=0; i<list.size(); i++){
	 	Student_DTO dto = list.get(i);
	 	Score_DTO score = scorelist.get(i);
		%>
		<tr>
			<td><%=dto.getId() %></td>

			<td><%=dto.getName() %></td>
			<%
			if(score.getScore()==(-1)){
				%>
			<td>
				아직 채점 전입니다. 
			</td>
				<% 
			}else{
				%>
				<td>
				<%=score.getScore() %> 
			</td>
								
				<%
			}
			%>
		</tr>
		<% 
	}
	%>
</table>
<%@include file="./include/footer.jsp" %>
</body>
</html>