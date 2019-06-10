<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="./js/test.js"></script>
<style type="text/css">
 #test_back{
 	float: right;
 }
</style>
<title>과제 관리</title>
<link rel= "stylesheet" type="text/css" href="./css/template.css">
</head>
<body>
<div class="content-wrapper">
<script type="text/javascript">

// 	window.onload=function(){
// 		var input = document.getElementsByTagName("input");
// 		$("input[name=allot]").blur(function(){
// 			if(input[1].value==""){
// 				alert("비었다.");
// 				$("input[name=err_allot]").css("color","red");
// 				document.getElementsName("err_allot")[0].innerHTML="필수 정보입니다."
// 			}
// // 			else if(!isNaN(input[0].value) || !idcheck()){
// // 				$("#err_id").css("color","red");
// // 				document.getElementById("err_id").innerHTML="5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
// // 			}
// 		});
// 	}
</script>
<%@include file="./include/header.jsp" %>
<div class="container">
	<h3 style="margin-top: 20px; margin-bottom: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;▶ 서술형 문제 등록</h3>
	<form action="./desc_ExamInput.do" method="post" onsubmit="return descexaminsert()">
		<table class="table" style="margin-top: 20px">
			<tr>
				<td>문제번호</td>
				<td>
<%-- 					<input name="examnum" readonly="readonly" value="${examnum}"> --%>
						<div class="form-group">
<!-- 						  <label for="usr">Name:</label> -->
						  <input type="text" class="form-control" id="usr"  name="examnum"  readonly="readonly" value="${examnum}">
						</div>
				</td>
			</tr>
			<tr>
				<td>문제</td>
				<td>
<!-- 					<textarea  rows="5" cols="30" name="exam" placeholder="문제를 입력해주세요."></textarea> -->
						<div class="form-group">
  							<textarea class="form-control" rows="2" id="comment" cols="30" name="exam" placeholder="문제를 입력해주세요."></textarea>
						</div>
				</td>
			</tr>
			<tr>
				<td>설명</td>
				<td>
<!-- 					<textarea rows="5" cols="30" name="explanation" placeholder="문제의 설명을 입력해 주세요 "></textarea> -->
						<div class="form-group">
  							<textarea class="form-control" rows="2" id="comment" cols="30" name="explanation" placeholder="문제의 설명을 입력해 주세요 "></textarea>
						</div>
				</td>
			</tr>
			<tr>
				<td>채점기준</td>
				<td>
<!-- 					<textarea rows="5" cols="30" name="standard" placeholder="채점기준을 입력해주세요."></textarea> -->
						<div class="form-group">
  							<textarea class="form-control" id="comment"  rows="2" cols="30" name="standard" placeholder="채점기준을 입력해주세요."></textarea>
						</div>
				</td>
			</tr>
			<tr>
				<td>정답</td>
				<td>
<!-- 					<textarea rows="5" cols="30" name="c_answer" placeholder="정답을 입력해주세요."></textarea> -->
						<div class="form-group">
  							<textarea class="form-control" id="comment"  rows="2" cols="30" name="c_answer" placeholder="정답을 입력해주세요."></textarea>
						</div>
				</td>
				
			</tr>
			<tr>
				<td>배점</td>
				<td>
<!-- 					<input name="allot" > -->
					<div class="form-group">
<!-- 						  <label for="usr">Name:</label> -->
						  <input type="text" class="form-control" id="usr" name="allot">
					</div>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td><span class="err" id="err_allot"></span></td> -->
<!-- 			</tr> -->
		</table>
		<input type="submit" value="문제등록" onclick="descexaminsert()" class="btn btn-primary active">
		<input type="button" value="취소" onclick="testback()" class="btn btn-primary disabled" id="test_back">
	</form>
</div>
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>