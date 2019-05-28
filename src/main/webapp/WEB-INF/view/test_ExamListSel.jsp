<%@page import="happy.jaj.prj.dtos.Exam_Sel_DTO"%>
<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 등록</title>
<script type="text/javascript" src="./js/test.js"></script>
</head>
<body>
<%
	List<Exam_Sel_DTO> list = (List<Exam_Sel_DTO>)request.getAttribute("list");
%>
<form action="./test_typecopy.do" method="post" >
<table>
	<tr>
		<td>chk</td>
		<td>문제</td>
		<td>정답</td>
	</tr>
	<%
	for(Exam_Sel_DTO dto : list){
		%>
		<tr>	
			<td><input type="checkbox" name="examcode" value="<%=dto.getExamcode()%>"></td>
			<td><%=dto.getExam() %></td>
			<td><%=dto.getC_answer() %></td>
		</tr>
		<% 
	}
	%>
	<tr>
		<td colspan="5"><input type="button" value="선택완료" onclick="checkvaluePost()"></td>
	</tr>
</table>
</form>
<script type="text/javascript">
 function checkvaluePost(){
	var frms = document.forms[0];
	frms.action = "./test_typecopy.do";
	frms.method = "post";
	frms.submit();
 }
</script>

<!-- <input type="button" value="닫음" onclick="clo()"> -->
<!-- <script type="text/javascript"> -->
<!-- // 	function clo(){ -->
<!-- // 		opener.location.reload(); -->
<!-- // 		window.close(); -->
<!-- // 	} -->
<!-- </script> -->

</body>
</html>