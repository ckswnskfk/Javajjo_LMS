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
		td2.innerHTML = "<input type='text' name='examcontent'>";
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
<%@include file="./include/header.jsp" %>
	<h1>과제등록</h1>
	<form action="./sel_ExamInput.do" method="post" onsubmit="return selexaminsert()">
		<table>
			<tr>
				<td>문제번호</td>
				<td><input name="examnum" readonly="readonly" value="${examnum}"></td>
			</tr>
			<tr>
				<td>문제</td>
				<td><textarea  rows="5" cols="30" name="exam" placeholder="문제를 입력해주세요."></textarea></td>
			</tr>
		<tbody id="contentarea">
			<tr>
				<td><input type="hidden" value="1" name="contentnum">①</td>
				<td><input type="text" name="examcontent"></td>
			</tr>
			<tr>
				<td><input type="hidden" value="2" name="contentnum">②</td>
				<td><input type="text" name="examcontent"></td>
			</tr>
			<tr>
				<td><input type="hidden" value="3" name="contentnum">③</td>
				<td><input type="text" name="examcontent"></td>
			</tr>
			<tr>
				<td><input type="hidden" value="4" name="contentnum">④</td>
				<td><input type="text" name="examcontent"></td>
			</tr>
		</tbody>
			<tr>
				<td>정답</td>
				<td><input type="text" name="c_answer" placeholder="정답을 입력해주세요."></textarea></td>
			</tr>
			<tr>
				<td>배점</td>
				<td><input type="text" name="allot" placeholder="배점을 입력해주세요."></td>
			</tr>
		</table>
		<input type="button" value="+ 문항추가" onclick="pluscontent()"><input type="button" value="- 문항삭제" onclick="delcontent()"><br>
		<input type="submit" value="문제등록">
		<input type="button" value="취소" onclick="testback()">
	</form>
<%@include file="./include/footer.jsp" %>

</body>
</html>