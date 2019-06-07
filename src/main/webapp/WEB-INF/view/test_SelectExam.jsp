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
	var examnum = 9316;
	
	function pluscontent(){
		var contentnum = document.getElementsByName("contentnum");

		var tbody = document.getElementById("contentarea");
		var trLen = tbody.children.length+1; 
// 		alert(trLen);
		
// 		alert(nummax.value);
		var content = document.getElementById("contentarea");
		var tr = document.createElement("tr"); // 생성할 엘리먼트
		var td1 = document.createElement("td"); 
		td1.innerHTML = "<input type='hidden' value='"+trLen+"' name='contentnum'>&#"+(examnum);
		examnum += 1;
		var td2 = document.createElement("td"); 
		td2.innerHTML = "<div class='form-group'><input type='text' class='form-control' id='usr' name='examcontent'></div>";
		tr.appendChild(td1);
		tr.appendChild(td2);
		
		content.appendChild(tr);
	}
	function delcontent(){
		var tbody = document.getElementById("contentarea");
		var trLen = tbody.children.length;
		examnum = examnum-1;
		if(trLen>4){
			var tbody = document.getElementById("contentarea");
			tbody.removeChild(tbody.lastChild);	
		}	
	}
</script>
</head>
<body>
<%@include file="./include/header.jsp" %>
<div class="container">
	<h3 style="margin-top: 20px; margin-bottom: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;▶ 선택형 문제 등록</h3>
	<form action="./sel_ExamInput.do" method="post" onsubmit="return selexaminsert()">
		<table  class="table">
			<tr>
				<td>문제번호</td>
				<td>
<%-- 					<input name="examnum" readonly="readonly" value="${examnum}"> --%>
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="examnum"  readonly="readonly" value="${examnum}">
					</div>
				</td>
			</tr>
			<tr>
				<td>문제</td>
				<td>
<!-- 					<textarea  rows="5" cols="30" name="exam" placeholder="문제를 입력해주세요."></textarea> -->
					<div class="form-group">
	  					<textarea class="form-control" rows="2" id="comment" cols="30" name="exam"  placeholder="문제를 입력해주세요."></textarea>
					</div>
				</td>
			</tr>
		<tbody id="contentarea">
			<tr>
				<td><input type="hidden" value="1" name="contentnum">①</td>
				<td>
<!-- 					<input type="text" name="examcontent"> -->
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="examcontent">
					</div>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" value="2" name="contentnum">②</td>
				<td>
<!-- 					<input type="text" name="examcontent"> -->
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="examcontent">
					</div>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" value="3" name="contentnum">③</td>
				<td>
<!-- 					<input type="text" name="examcontent"> -->
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="examcontent">
					</div>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" value="4" name="contentnum">④</td>
				<td>
<!-- 					<input type="text" name="examcontent"> -->
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="examcontent">
					</div>
				</td>
			</tr>
		</tbody>
		<tr>
			<td colspan="2" style="text-align: center">
				<input type="button" value="+ 문항추가" onclick="pluscontent()" class="btn btn-success" style="margin-right: 20px;"><input type="button" value="- 문항삭제" onclick="delcontent()" class="btn btn-success">
			</td>
		</tr>
		
			<tr>
				<td>정답</td>
				<td>
<!-- 					<input type="text" name="c_answer" placeholder="정답을 입력해주세요."> -->
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="c_answer"  placeholder="정답을 입력해주세요.">
					</div>
				</td>
			</tr>
			<tr>
				<td>배점</td>
				<td>
<!-- 					<input type="text" name="allot" placeholder="배점을 입력해주세요."> -->
					<div class="form-group">
						<input type="text" class="form-control" id="usr"  name="allot" placeholder="배점을 입력해주세요.">
					</div>
				</td>
			</tr>
		</table>
		<input type="submit" value="문제등록" class="btn btn-primary active">
		<input type="button" value="취소" onclick="testback()"  class="btn btn-primary disabled" style="float: right">
	</form>
 </div>
<%@include file="./include/footer.jsp" %>

</body>
</html>