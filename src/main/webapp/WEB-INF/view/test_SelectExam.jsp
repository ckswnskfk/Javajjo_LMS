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
		<div>
			<tr>
				<td><input type="hidden" value="1" name="contentnum">①</td>
				<td><input type="text" name="content"></td>
			</tr>
			<tr>
				<td><input type="hidden" value="2" name="contentnum">②</td>
				<td><input type="text" name="content"></td>
			</tr>
			<tr>
				<td><input type="hidden" value="3" name="contentnum">③</td>
				<td><input type="text" name="content"></td>
			</tr>
			<tr>
				<td><input type="hidden" value="4" name="contentnum">④</td>
				<td><input type="text" name="content"></td>
			</tr>
		</div>
			<tr>
				<td>정답</td>
				<td><input type="text" name="c_answer" placeholder="정답을 입력해주세요."></textarea></td>
			</tr>
			<tr>
				<td>배점</td>
				<td><input type="text" name="allot" placeholder="배점을 입력해주세요."></td>
			</tr>
		</table>
		<input type="submit" value="문제등록">
		<input type="button" value="취소" onclick="testback()">
	</form>
<%@include file="./include/footer.jsp" %>

</body>
</html>