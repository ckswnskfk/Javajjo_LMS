<%@page import="happy.jaj.prj.dtos.Subject_Test_DTO"%>
<%@page import="java.util.Map"%>
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
<style type="text/css">
.test_session{
	border: 1px solid black;
	padding: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
<%
// 	Map<String, String> map = (Map<String, String>)request.getAttribute("map");
// 	String subjectcode = (String)request.getAttribute("subjectcode");

	Subject_Test_DTO dto = (Subject_Test_DTO)request.getAttribute("dto");
	
	
%>
</head>
<script type="text/javascript">
	function testinsert() {
// 		alert("과제 등록");
		var testname = document.getElementsByName("testname")[0].value;
		var testday =  document.getElementsByName("testday")[0].value;
// 		alert(testname);
		if(testname==""||testname==null){
			alert("과제명을 입력해주세요.");
			return false;
		}else if(testday==""||testday==null){
			alert("과제 날짜를 입력해주세요.");
			return false;
		}else{
			return true;
		}
	}

	function testForm() {
		
		location.href = "./division.do";
	}
</script>
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<div class="container">

	
<%-- 	<input type="hidden" name="subjectcode" value='${testsession.subjectcode}%>'> --%>
<!-- 	<div class="test_session"> -->
<!-- 		<h2>과제 등록</h2> -->
<!-- 		<hr> -->
<%-- 		<h4>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h4> --%>
<%-- 		<h4>과목명 : ${testsession.subjectname}</h4> --%>
<!-- 		<hr> -->
<%-- 		<h4>과목유형 : ${testsession.subjecttype}</h4> --%>
<%-- 		<h4>과제유형 : ${testsession.examtype}</h4> --%>
<!-- 	</div> -->
	<table style="margin-bottom: 20px; margin-top: 20px; width:100%;margin-bottom:1rem;background-color:transparent;">
		<thead>
			<tr>
				<td colspan="2"><h3>▶ 과제 목록</h3></td>
			</tr>
		</thead>
		<tr>
			<td style="width:50%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</td>
			<td style=" padding-right:12px; text-align:justify; padding-left: 12px">과목유형 : ${testsession.subjecttype}</td>
		</tr>
		<tr>
			<td style=" border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과목명 : ${testsession.subjectname}</td>
			<td style=" padding-right:12px; text-align:justify;  padding-left: 12px">과제유형 : ${testsession.examtype}</td>
		</tr>
	</table>
	<table class="table">
		<form action="./test_Regi.do" method="get" onsubmit="return testinsert()">
		

	<%
		if(dto==null){
	%>		
		<tr>
			<td>과제명</td>
			<td><input type="text" name="testname"></td>
		</tr>
		<tr>
			<td>과제날짜</td>
			<td><input type="date" name="testday" id="date"></td>
		</tr>
		<tr>
			<td><input type="submit" value="과제 등록"></td>
		</tr>
		</form>
	</table>
	
		<%
		}else{
			%>
<!-- 		<div class="test_session"> -->
<%-- 			<h4>과제명 :${testsession.testname }</h4> --%>
<%-- 			<h4>과제 날짜 : ${testsession.testday}</h4> --%>
<!-- 			<input type="button" class="btn btn-success" value="다음" onclick="testForm()"> -->
<!-- 		</div> -->
			<hr>
			<table style="margin-bottom: 20px; margin-top: 20px; width:100%;margin-bottom:1rem;background-color:transparent;">
				<tr><td>과제명 :${testsession.testname }</td></tr>
				<tr><td>과제 날짜 : ${testsession.testday}</td></tr>
			</table>
				<input type="button" class="btn btn-success" value="다음" onclick="testForm()" style="margin-top: 20px">
				<input type="button" class="btn btn-success" value="뒤로가기" onclick="testForm()" style="float:right">
				</td>
			<% 
		}
		%>
			
	</div>	
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>