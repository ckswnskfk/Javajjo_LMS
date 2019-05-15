<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link rel= "stylesheet" type="text/css" href="./css/index.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/index.js"></script>
</head>
<body>
	<div id="main">
		<div id="wrap">
		<form action="./login.do" method="post">
	 		<table>
	 			<tr>
	 				<th>아이디</th>
	 				<td><input type="text" name="id" value=""></td>
	 			</tr>
	 			<tr>
	 				<th>비밀번호</th>
	 				<td><input type="password" name="pw" value=""></td>
	 			</tr>
	 			<tr>
	 				<td colspan="2">
	 					<input type="submit" value="로그인">
	 					<input type="button" value="회원가입" onclick="regiForm()">
	 				</td>
	 			</tr>
	 		</table>
 		</form>
 		</div>	
 	</div>
</body>
</html>