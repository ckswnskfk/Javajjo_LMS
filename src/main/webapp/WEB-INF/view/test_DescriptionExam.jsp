<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="./js/test.js"></script>
<title>과제 관리</title>
</head>
<body>
	<h1>과제등록</h1>
	<form action="./desc_ExamInput.do" method="post" onsubmit="return descexaminsert()">
		<table>
			<tr>
				<td>문제</td>
				<td><textarea  rows="5" cols="30" name="exam" placeholder="문제를 입력해주세요."></textarea></td>
			</tr>
			<tr>
				<td>설명</td>
				<td><textarea rows="5" cols="30" name="explanation" placeholder="문제의 설명을 입력해 주세요 "></textarea></td>
			</tr>
			<tr>
				<td>채점기준</td>
				<td><textarea rows="5" cols="30" name="standard" placeholder="채점기준을 입력해주세요."></textarea></td>
			</tr>
			<tr>
				<td>정답</td>
				<td><textarea rows="5" cols="30" name="c_answer" placeholder="정답을 입력해주세요."></textarea></td>
			</tr>
		</table>
		<input type="submit" value="문제등록" >
		<input type="button" value="취소" onclick="location.href='history.back()'">
	</form>
</body>
</html>