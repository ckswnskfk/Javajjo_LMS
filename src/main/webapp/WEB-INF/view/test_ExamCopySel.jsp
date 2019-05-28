<%@page import="happy.jaj.prj.dtos.Exam_Sel_DTO"%>
<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
<%@page import="happy.jaj.prj.dtos.Test_Exam_DTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 관리</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/test.js"></script>
</head>
<%
	String[] examcode = (String[]) request.getAttribute("examcode");
	int examnum = (int) request.getAttribute("examnum");
	List<Exam_Sel_DTO> list = (List<Exam_Sel_DTO>) request.getAttribute("list");
%>
<body>
	<%=examcode%>
	<form action="./test_typecopyexam.do" method="post" name="testForm"
		onsubmit="return test()">
		<table>
			<tr>
				<td>문제 번호</td>
				<td>문제</td>
				<td>배점</td>
			</tr>
			<%
				for (Exam_Sel_DTO dto : list) {
			%>
			<tr>

				<td><input type="hidden" value="${testsession.testcode}"	name="testcode">
				    <input type="hidden"value='<%=dto.getExamcode()%>' name="examcode">
					 <input	type="hidden" value='<%=examnum%>' name="examnum"><%=examnum++%></td>
				<td><input type="text" value='<%=dto.getExam()%>'	name="exam"><%=dto.getExam()%></td>
				<td><input type="text" id="allot" name="allot"></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td><input type="button" value="등록" onclick="process()"></td>
				<td colspan="2"><input type="button" value="취소"
					onclick="testback()"></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
 function process(){
	 var obj = document.getElementById("allot");
	 if(obj.value == null || obj.value == "" ){
		alert("값을 입력해 주세요");
		obj.focus();
	 }else if(isNaN(obj.value)){
		 alert("숫자를 입력해 주세요");
		 obj.focus();
	 }else{
		 var queryString = $("form[name=testForm]").serialize() ;
		 $.ajax({
				url : "./test_typecopyexam.do",
				data : queryString,
				type : "post",
				async : false,
				success : function(){
					opener.location.reload(); 
					window.close();
				}
			});
	 }
 }


</script>

</html>