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
<%
	List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)request.getAttribute("dto");
%>
<script type="text/javascript">
function allSel(bool){
	// checkbox는 value로 받으면 안된다.
//		alert(bool);
	var chks = document.getElementsByName("chk");
	for (var i = 0; i < chks.length; i++) {
		chks[i].checked = bool;
	}
}
function examdelete(){
	var frm1 = document.forms[0];
//		alert(frm1);
//		frm1.action = "./test_deleteexam.do";
//		frm1.method = "post";
//		frm1.submit();
	var examcode = document.getElementsByName("examcode");
	var queryString = $("form[name=del]").serialize() ;
	$.ajax({
		url : "./test_deleteexam.do",
		type : "post",
		data : queryString,
		success : function(){
//				alert("성공");
			location.reload();
		}
	});
}
</script>
<body>
<%@include file="./include/header.jsp" %>
	<h1>과제 등록</h1>
		<h4>과정명 : ${testsession.coursename}</h4>
		<h4>과목명 : ${testsession.subjectname}</h4>
		<hr>
		<h4>과목유형 : ${testsession.subjecttype}</h4>
		<h4>과제유형 : ${testsession.examtype}</h4>
		<hr>
		<h4>과제명 : ${testsession.testname}</h4>
		<h4>과제 날짜 : ${testsession.testday}</h4>
		<hr>
		<input type="button" value="등록" onclick="location.href='./sel_ExamForm.do'">
		<input type="button" value="추가" onclick="examsellist()">
		<input type="button" value="복사" onclick="coursecnt()">
		<input type="button" value="삭제" onclick="examdelete()">
<form action="#" name="del" >
	<table>
		<%
		if(list.size()==0){
			%>
			<tr>
				<td><p>아직 문제가 없습니다.<br> 문제를 추가해 주세요.</p></td>
			</tr>
			<% 
		}else{
			%>
			<tr>
				<td><input type="checkbox" name="allchk" onclick="allSel(this.checked)"></td>
				<td>No.</td>
				<td>문제</td>
				<td>배점</td>
			</tr>			
			<% 
			for(int i=0; i<list.size(); i++){
				Test_Exam_DTO dto = list.get(i);
			%>
				<tr>
					<td>
					<input type="hidden" name="exam" value="<%=dto.getExamcode()%>">
					<input type="checkbox" name="examcode" value='<%=dto.getExamcode()%>'></td>
					<td><input type="hidden" name="examnum" value='<%=i+1%>'><%=i+1 %></td>
					<td><a href="./sel_Exam_ModifyForm.do?examcode=<%=dto.getExamcode()%>&examnum=<%=dto.getExamnum()%>&allot=<%=dto.getAllot()%>"><%=dto.getExam() %></a></td>
					<td><input type="hidden" name="allot" value='<%=dto.getAllot()%>'><%=dto.getAllot() %></td>
				</tr>
			<% 
			}
			%>
			<div id="append"></div>
			<tr>
				<td>총 점</td>
				<td colspan="3">${total}</td>
			</tr>
			<% 
		}
		%>
	</table>
		<input type="button" value="문제 등록" onclick="examinsert()">
		<input type="button" value="뒤로 가기" onclick="testback()">
		</form>
<%@include file="./include/footer.jsp" %>
</body>
</html>