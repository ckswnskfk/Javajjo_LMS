<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@page import="happy.jaj.prj.dtos.Test_Exam_DTO"%>
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
 tbody>tr:hover { background-color: lightyellow; } 
 #test_back{
 	float: right;
 }
</style>
<script type="text/javascript" src="./js/test.js"></script>
</head>
<%
	List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)request.getAttribute("dto");
	
%>
<script type="text/javascript">
	window.onload=function(){
		var div = document.getElementsByTagName("div")[0];
		
	}
		function allSel(bool){
			// checkbox는 value로 받으면 안된다.
// 	 		alert(bool);
			var chks = document.getElementsByName("examcode");
			for (var i = 0; i < chks.length; i++) {
				chks[i].checked = bool;
			}
		}
		function examdelete(){
			var frm1 = document.forms[0];
// 			alert(frm1);
			frm1.action = "./test_deleteexam.do";
			frm1.method = "post";
			frm1.submit();
// 			location.reload();
// 			var examcodes = document.getElementsByName("examcode");
// 			var examcode = new Array();
// 			for(var i = 0; i < examcodes.length;i++){
// 				if(examcodes[i].checked){
// 					examcode[i] = examcodes[i].value;
// 				}
// 			}
// 			alert(examcode);
// 			var queryString = $("input:checkbox[name='examcode']").is(":checked");
// 			alert(queryString);
// 			$.ajax({
// 				url : "./test_deleteexam.do",
// 				type : "post",
// 				data : examcode,
// 				success : function(){
// // 					alert("성공");
// 					location.reload();
// 				}
// 			});
		}
</script>
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<div class="container">
		<table style="margin-bottom: 20px; margin-top: 20px;  width:100%;margin-bottom:1rem;background-color:transparent;">
		<thead>
			<tr>
				<td colspan="3"><p style="font-size: 20px;">▶ 과제 목록</p></td>
			</tr>
		</thead>
		<tr>
			<td style="width:34%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</td>
			<td style="width:33%; border-right:1px solid black; padding-right:12px; text-align:justify; padding-left: 12px">과목유형 : ${testsession.subjecttype}</td>
			<td style="width:33%; padding-left: 12px; text-align:justify;"> 과제명 : ${testsession.testname}</td>
		</tr>
		<tr>
			<td style="width:34%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과목명 : ${testsession.subjectname}</td>
			<td style="width:33%; border-right:1px solid black; padding-right:12px; text-align:justify;  padding-left: 12px">과제유형 : ${testsession.examtype}</td>
			<td style="width:33%; padding-left: 12px;  text-align:justify;">과제 날짜 : ${testsession.testday}</td>
		</tr>
	</table>
	<hr>
<!-- 	<div class="test_session"> -->
<!-- 		<h2>과제 등록</h2> -->
<%-- 			<h4>과정명 : ${testsession.coursename} (${testsession.coursecnt}회차)</h4> --%>
<%-- 			<h4>과목명 : ${testsession.subjectname}</h4> --%>
<!-- 			<hr> -->
<%-- 			<h4>과목유형 : ${testsession.subjecttype}</h4> --%>
<%-- 			<h4>과제유형 : ${testsession.examtype}</h4> --%>
<!-- 			<hr> -->
<%-- 			<h4>과제명 : ${testsession.testname}</h4> --%>
<%-- 			<h4>과제 날짜 : ${testsession.testday}</h4> --%>
<!-- 	</div> -->
		<input type="button" value="등록" onclick="location.href='./desc_ExamForm.do'" class="btn btn-primary">
		<input type="button" value="추가" onclick="examdesclist()" class="btn btn-success">
		<input type="button" value="복사" onclick="coursecnt()" class="btn btn-info">
		<input type="button" value="삭제" onclick="examdelete()" class="btn btn-warning">

					<h4>총 점 : ${total}</h4>
				</tr>
	<form action="#" name="del" method="post">
	<table class="table">
		<%
		if(list.size()==0){
			%>
			<tr>
				<td><p>아직 문제가 없습니다.<br> 문제를 추가해 주세요.</p></td>
			</tr>
			<% 
		}else{
			%>
			<thead>
				<tr>
					<td><input type="checkbox" name="allchk" onclick="allSel(this.checked)"></td>
					<td>No.</td>
					<td>문제</td>
					<td>배점</td>
				</tr>	
			</thead>
			<tbody>		
			<% 
			for(int i=0; i<list.size(); i++){
				Test_Exam_DTO dto = list.get(i);
			%>
				<tr>
					<td>
					<input type="hidden" name="exam" value="<%=dto.getExamcode()%>">
					<input type="checkbox" class="del" name="examcode" value="<%=dto.getExamcode()%>"></td>
					<td><input type="hidden" name="examnum" value='<%=i+1%>'><%=i+1 %></td>
					<td><a href="./desc_Exam_ModifyForm.do?examcode=<%=dto.getExamcode()%>&examnum=<%=dto.getExamnum()%>&allot=<%=dto.getAllot()%>"><%=dto.getExam() %></a></td>
					<td><input type="hidden" name="allot" value="<%=dto.getAllot()%>"><%=dto.getAllot() %></td>
				</tr>
			<% 
			}
			%>
			</tbody>
			<div></div>
			<% 
		}
		%>
	</table>
		<input type="button" value="문제 등록" onclick="examinsert()" class="btn btn-primary active">
		<input type="button" value="뒤로 가기" onclick="testback()" class="btn btn-primary disabled" style="float: right">
	</form>
</div>
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>