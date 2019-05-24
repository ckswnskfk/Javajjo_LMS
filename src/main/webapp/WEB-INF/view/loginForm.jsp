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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./js/login.js"></script>
</head>
<body>
	<div id="main">
		<div id="wrap">
		<form action="#" method="post">
	 		<table>
	 			<tr>
	 				<td colspan="2">
	 					<div class="toggle_radio">
		 					<input type="radio" class="toggle_option"  id="first_toggle" value="Student" name="tableChk" checked="checked">
		 					<label for="first_toggle"><p>학생 로그인</p></label>
		 					<input type="radio" class="toggle_option" id="second_toggle" value="Teacher" name="tableChk">
		 					<label for="second_toggle"><p>강사 로그인</p></label>
		 					<input type="radio" class="toggle_option" id="third_toggle" value="Admin" name="tableChk">
	    					<label for="third_toggle"><p>관리자 로그인</p></label>
		 					<div class="toggle_option_slider">
		 					</div>
	 					</div>
	 				</td>
	 			</tr>
	 			<tr>
	 				<th>아이디</th>
	 				<td><input type="text" id="id" name="id" value=""></td>
	 			</tr>
	 			<tr>
	 				<th>비밀번호</th>
	 				<td><input type="password" id="pw" name="pw" value=""></td>
	 			</tr>
	 			<tr>
	 				<td colspan="2">
	 					<input type="button" value="로그인" onclick="login()">
	 					<input type="button" value="회원가입" onclick="regiForm()">
	 					<input type="button" value="비밀번호 초기화" onclick="pwReset()">
	 				</td>
	 			</tr>
	 		</table>
 		</form>
 		</div>	
 	</div>
</body>
</html>