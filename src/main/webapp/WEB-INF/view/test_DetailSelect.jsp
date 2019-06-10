<%@page import="happy.jaj.prj.dtos.Answer_Sel_DTO"%>
<%@page import="happy.jaj.prj.dtos.ContentSelect_DTO"%>
<%@page import="java.util.List"%>
<%@page import="happy.jaj.prj.dtos.Exam_Sel_DTO"%>
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
</head>
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<script type="text/javascript">


	function pageexam(bool, max){ //판단
		
		var examnum = document.getElementsByName("examnum")[0].value;
		
		if((Number(examnum)-1)=="0"&&bool){
			alert("첫번째 문제입니다.");
		}else if(examnum==max&&bool==false){
			alert("마지막 문제입니다.");
		}else{
			pageUpDown(bool, examnum);
		}
	//	location.href = "./desc_Detail.do?examnum="+examnum;
	}
	
	function pageUpDown(bool, examnum){ //보냄
	
		var frm = document.forms[0];
		
		if(bool){
			document.getElementsByName("page")[0].value="-1";
	//		document.getElementsByName("examnum")[0].value = Number(examnum)-1;		
		}else{
			document.getElementsByName("page")[0].value="0";
	//		document.getElementsByName("examnum")[0].value = Number(examnum)+1;
		}
	//	alert(document.getElementsByName("examnum")[0].value);
		frm.method="post";
		frm.action = "./sel_Detail.do";
		frm.submit();
	}
	function check_answer(){
		var answer = document.getElementsByName("answer");
		answer[i].checked = true;
	}
	function numberclicksel(examnum){
		var frm = document.forms[0];
		document.getElementsByName("page")[0].value=examnum;
		frm.method = "post";
		frm.action="./sel_Detail.do";
		frm.submit();
	}
	function examsubmit(){
		var submit = confirm("제출하면 수정이 불가능합니다. \n 그래도 제출하시겠습니까?");
		var frm = document.forms[0];
		if(submit){
			alert("제출이 완료되었습니다.");
			frm.action="./test_Sel_Score.do";
			frm.method="post";
			frm.submit();
		}
	}
</script>
<%
	Exam_Sel_DTO dto = (Exam_Sel_DTO)request.getAttribute("dto");
	List<ContentSelect_DTO> content = (List<ContentSelect_DTO>)request.getAttribute("contentlist");
	int maxexam=(int)request.getAttribute("maxexamnum");
	String answer = (String)request.getAttribute("answer");
%>
<div class="container">
<form action="#" method="post">
	<table  class="table">
		<tr>
			<td></td>
			<td>
			<%
			for(int i=1; i<(maxexam+1);i++){
				%>
					<input type="button" value="<%=i%>번" onclick="numberclicksel(<%=i%>)">
				<% 
			}
			%>
			</td>
		</tr>
		<tr>
			<td><p style="font-size: 20px">
				<input type="hidden" name="examcode" value='<%=dto.getExamcode()%>'>
				<input type="hidden" name="examnum" value='<%=dto.getExamnum()%>'>
				<input type="hidden" name="page">
				
				<%=dto.getExamnum() %>
				</p>
			</td>
			<td colspan="2">
				<%=dto.getExam() %><a style="color:red"><%=dto.getAllot() %></a>
			</td>
		</tr>
		
		<%
		for(int i=1; i<=content.size(); i++){
			ContentSelect_DTO csdto = content.get(i-1);
			%>
			<tr>
				<td>
				
				<input type="radio" name="answer" value="<%=i%>" <%=(answer.equals(String.valueOf(i)))?"checked":""%>></td>
				
				<td><%=csdto.getContentnum() %></td>
				<td><%=csdto.getExamcontent() %></td>
			</tr>
			<% 
		}
		%>
		<tr>
			<td><input type="button" class="btn btn-success" value="← 이전문제" onclick="pageexam(true,<%=maxexam%>)"></td>
			<td><input type="button" class="btn btn-success" value="다음 문제 →" onclick="pageexam(false,<%=maxexam%>)"></td>
			<td><input type="button" class="btn btn-warning" value="시험 제출" onclick="examsubmit()"></td>
		</tr>
	</table>
</form>
</div>
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>