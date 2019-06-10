<%@page import="happy.jaj.prj.dtos.Exam_Sel_DTO"%>
<%@page import="happy.jaj.prj.dtos.ContentSelect_DTO"%>
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
<link rel= "stylesheet" type="text/css" href="./css/template.css">
<script type="text/javascript" src="./js/test.js"></script>
<script type="text/javascript">
	function pluscontent(){
		var contentnum = document.getElementsByName("contentnum");

		var tbody = document.getElementById("contentarea");
		var trLen = tbody.children.length+1; 
// 		alert(trLen);
		
// 		alert(nummax.value);
		var content = document.getElementById("contentarea");
		var tr = document.createElement("tr"); // 생성할 엘리먼트
		var td1 = document.createElement("td"); 
		td1.innerHTML = "<input type='hidden' value='"+trLen+"' name='contentnum'>"+trLen;
		var td2 = document.createElement("td"); 
		td2.innerHTML = "<div class='form-group'><input type='text' class='form-control' id='usr' name='examcontent'></div>";
		tr.appendChild(td1);
		tr.appendChild(td2);
		
		content.appendChild(tr);
	}
	function delcontent(){
		var tbody = document.getElementById("contentarea");
		var trLen = tbody.children.length;
		if(trLen>4){
			var tbody = document.getElementById("contentarea");
			tbody.removeChild(tbody.lastChild);	
		}	
	}
</script>
</head>
<body>
<div class="content-wrapper">
<%-- <h1>${examcode}</h1> --%>
<%
	Exam_Sel_DTO dto = (Exam_Sel_DTO)request.getAttribute("dto");
	List<ContentSelect_DTO> list = (List<ContentSelect_DTO>)request.getAttribute("Clist");

%>
	<%@include file="./include/header.jsp" %>
	<div class="container">
	<h3 style="margin-top: 20px; margin-bottom: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;▶ 선택형문제 상세조회 / 수정</h3>
	<form action="./sel_Content_Modify.do" method="post" onsubmit="return selexaminsert()">
<%-- 		<input type="text" name="examcode" value='<%=dto.getExamcode()%>'> --%>
		<table  class="table">
			<tr>
				<td>문제번호</td>
				<td>
<%-- 					<input name="examnum" readonly="readonly" value='<%=dto.getExamnum()%>'> --%>
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="examnum"  readonly="readonly" value="<%=dto.getExamnum()%>">
					</div>
				</td>
			</tr>
			<tr>
				<td>문제</td>
				<td>
<%-- 					<textarea  rows="5" cols="30" name="exam"><%=dto.getExam() %></textarea> --%>
					<div class="form-group">
	  					<textarea class="form-control" rows="2" id="comment" cols="30" name="exam"><%=dto.getExam() %></textarea>
					</div>			
				</td>
			</tr>
<!-- 		<div> -->
			<tbody id="contentarea">
			<% 
			int cnt = 0;
			for(ContentSelect_DTO dto1 : list){
				cnt++;
				%>
				<tr>
					<td><input type="hidden" value='<%=cnt %>' name="contentnum"><%=cnt%></td>
					<td>
<%-- 						<input type="text" name="examcontent" value='<%=dto1.getExamcontent()%>'> --%>
						<div class="form-group">
							<input type="text" class="form-control" id="usr"  name="examcontent" value="<%=dto1.getExamcontent()%>">
						</div>
					</td>
				</tr>
				<% 
			}
			%>
			</tbody>
				<tr>
			<td colspan="2" style="text-align: center">
				<input type="button" value="+ 문항추가" onclick="pluscontent()" class="btn btn-success" style="margin-right: 20px;"><input type="button" value="- 문항삭제" onclick="delcontent()" class="btn btn-success">
			</td>
		</tr>
<!-- 		</div> -->
			<tr>
				<td>정답</td>
				<td>
<%-- 					<input type="text" name="c_answer" value="<%=dto.getC_answer()%>"> --%>
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="c_answer" value="<%=dto.getC_answer()%>">
					</div>
				</td>
			</tr>
			<tr>
				<td>배점</td>
				<td>
<%-- 					<input type="text" name="allot" value="<%=dto.getAllot()%>"> --%>
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="allot" value="<%=dto.getAllot()%>">
					</div>
				</td>
			</tr>
		</table>
		<input type="submit" value="문제등록" class="btn btn-primary active">
		<input type="button" value="취소" onclick="testback()" class="btn btn-primary disabled" style="float: right">
	</form>
</div>
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>