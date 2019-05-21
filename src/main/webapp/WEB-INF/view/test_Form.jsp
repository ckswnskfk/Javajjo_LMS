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
<%
// 	Map<String, String> map = (Map<String, String>)request.getAttribute("map");
	String subjectcode = (String)request.getAttribute("subjectcode");
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
	
</script>
<body>
<h1>과제 등록</h1>
<h4>과정명 : ${coursename}</h4>
<h4>과목명 : ${subjectname}</h4>
<hr>
<h4>과목유형 : ${subjecttype}</h4>
<h4>과제유형 : ${examtype}</h4>
<hr>
<form action="./test_Regi.do" method="get" onsubmit="return testinsert()">
	
	<input type="hidden" name="subjectcode" value='<%=subjectcode%>'>
	<table>
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
	</table>
</form>
</body>
</html>