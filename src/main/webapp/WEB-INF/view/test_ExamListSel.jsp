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
<link href="./css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
 #test_back{
 	float: right;
 }
</style>
</head>
<body>
<%
	List<Exam_Sel_DTO> list = (List<Exam_Sel_DTO>)request.getAttribute("list");
%>
<form action="./test_typecopy.do" method="post" >
<div class="container" style="margin-bottom: 20px; margin-top: 20px;" >
	<h4>&nbsp;&nbsp;&nbsp;&nbsp;▶ 추가할 문제를 선택하세요.</h4>
</div>
<table class="table">
	<thead style="background-color: #F2F2F2">
		<tr>
			<td></td>
			<td>문제</td>
			<td>정답</td>
		</tr>
	</thead>
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
<!-- 		<td colspan="5"><input type="button" value="선택완료" onclick="checkvaluePost()" class="btn btn-primary active"> -->
<!-- 		</td> -->
		<td colspan="5"><input type="button" value="선택완료" onclick="checkvaluePost()"  class="btn btn-primary active">
			<input type="button" value="취소" onclick="javascript:window.close()"  class="btn btn-primary disabled"  id="test_back">
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">
 function checkvaluePost(){
	var frms = document.forms[0];

	var check = document.getElementsByName("examcode");
	var cntCheck = 0;
	for (var i = 0; i < check.length; i++) {
		if (check[i].checked) {
			cntCheck++;
		}
	}
	if (cntCheck > 0) {
	frms.action = "./test_typecopy.do";
	frms.method = "post";
	frms.submit();
	} else {
		alert("문제를 한 개이상 선택해주세요.");
	}
	
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