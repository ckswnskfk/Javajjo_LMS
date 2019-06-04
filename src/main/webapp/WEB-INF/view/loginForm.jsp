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
	<link rel="stylesheet" type="text/css" href="./css/animate.css">
	<link rel="stylesheet" type="text/css" href="./css/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="./css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="./css/select2.min.css">
	<link rel="stylesheet" type="text/css" href="./css/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="./css/util.css">
	<link rel="stylesheet" type="text/css" href="./css/loginmain.css">
	<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="./css/icon-font.min.css">
<script type="text/javascript" src="./js/login.js"></script>
</head>
<body style="background-color: #F8FBEF;">
<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90 p-l-50 p-r-50" style="width: 450px;">
				<form class="login100-form validate-form flex-sb flex-w" action="#" method="post">
					<span class="login100-form-title p-b-51">
						Login
					</span>
					<div id="uldiv" style="width: 350px;">
					<ul>
						<li>
		 					<input type="radio" class="toggle_option"  id="first_toggle" value="Student" name="tableChk" checked="checked">
		 					<label for="first_toggle"><p>학생</p></label>
		 				</li>
		 				<li>
		 					<input type="radio" class="toggle_option" id="second_toggle" value="Teacher" name="tableChk">
		 					<label for="second_toggle"><p>강사</p></label>
	 					</li>
	 					<li>
		 					<input type="radio" class="toggle_option" id="third_toggle" value="Admin" name="tableChk">
	    					<label for="third_toggle"><p>관리자</p></label>
    					</li>
				 	</ul>
				 	</div>
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
						<input class="input100" type="text" name="id" id="id" placeholder="Id">
						<span class="focus-input100"></span>
					</div>
					
					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
						<input class="input100" type="password" name="pw" id="pw" placeholder="Password">
						<span class="focus-input100"></span>
					</div>
					
					<div class="flex-sb-m w-full p-t-3 p-b-24">
						<div class="contact100-form-checkbox">
							<a class="txt1" href="#" onclick="regiForm()">
								Sign In
							</a>
						</div>
					
						<div>
							<a class="txt1" href="#" onclick="pwReset()">
								Forget?
							</a>
						</div>
					</div>

					<div class="container-login100-form-btn m-t-17">
						<input type="button" class="login100-form-btn" onclick="login()" value="Login"/>
					</div>

				</form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>

</body>
</html>