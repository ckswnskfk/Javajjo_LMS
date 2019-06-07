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
<script type="text/javascript">
	function StuScore(){
		location.href = "./test_Total_ResultStu.do";
	}
</script>
<body>
<%@include file="./include/header.jsp" %>
<div class="container">
<table style="margin-bottom: 20px; margin-top: 20px;  width:100%;margin-bottom:1rem;background-color:transparent;">
		<thead>
			<tr>
				<td colspan="3"><p style="font-size: 20px;">▶ 과제 목록</p></td>
			</tr>
		</thead>
		<tr>
			<td style="width:40%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</td>
			<td style="width:35%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과목유형 : ${testsession.subjecttype}</td>
			<td style="width:35%; padding-left: 12px; text-align:justify;"> 과제명 : ${testsession.testname}</td>
		</tr>
		<tr>
			<td style="width:40%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과목명 : ${testsession.subjectname}</td>
			<td style="width:35%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과제유형 : ${testsession.examtype}</td>
			<td style="width:35%; padding-left: 12px;  text-align:justify;">과제 날짜 : ${testsession.testday}</td>
		</tr>
	</table>

<!-- 	<h1>과제 등록</h1> -->
<%-- 	<h4>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h4> --%>
<%-- 	<h4>과목명 : ${testsession.subjectname}</h4> --%>
<!-- 	<hr> -->
<%-- 	<h4>과목유형 : ${testsession.subjecttype}</h4> --%>
<%-- 	<h4>과제유형 : ${testsession.examtype}</h4> --%>
<!-- 	<hr> -->
<%-- 	<h4>과제명 :${testsession.testname}</h4> --%>
<%-- 	<h4>과제 날짜 : ${testsession.testday}</h4> --%>
	<hr>
	<input type="button" class="btn btn-primary active" value="성적확인" onclick="StuScore()">
	<input type="button" value="뒤로가기" class="btn btn-primary disabled" onclick="javascript:location.href='./test_Course_ResultStu.do'"> 
</div>
<%@include file="./include/footer.jsp" %>	

</body>
</html>