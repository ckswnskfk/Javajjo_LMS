<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.name}님 마이 페이지</title>
<link rel= "stylesheet" type="text/css" href="./css/index.css">
<link rel= "stylesheet" type="text/css" href="./css/info.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript" src="./js/index.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="./js/info.js"></script>
</head>
<body>
<%@ include file="./include/header.jsp" %>
<div class="container">
<div id="user-profile-2" class="user-profile">
		<div class="tabbable">
			<ul class="nav nav-tabs padding-18">
				<li class="active">
					<a data-toggle="tab" href="#home">
						<i class="green ace-icon fa fa-user bigger-120"></i>
						사용자 정보 조회
					</a>
				</li>

				<li>
					<a data-toggle="tab" onclick="location.href='./teacher_student_list.do?coursecode=${dto.coursecode}'">
						<i class="orange ace-icon fa fa-rss bigger-120"></i>
						학생 정보 조회
					</a>
				</li>
			</ul>

			<div class="tab-content no-border padding-14">
				<div id="home" class="tab-pane in active">
					<div class="row">
						<div class="col-xs-9 col-sm-9">
							<h4 class="dark">
								<span class="middle">${dto.name} 님의 마이페이지</span>
							</h4>

							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name"> Id </div>

									<div class="profile-info-value">
										<span>${dto.id}</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> Name </div>

									<div class="profile-info-value">
										<i class="fa fa-map-marker light-orange bigger-110"></i>
										<span>${dto.name}</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Course</div>

									<div class="profile-info-value">
										<span>${dto.coursename}</span>
									</div>
								</div>
							</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<input type="button" class="btn btn-info"
										 value="수정하기" onclick="teacher_modify()"/>
									</div>
								</div>
							<div class="hr hr-5 dotted"></div>
						</div><!-- /.col -->
					</div><!-- /.row -->
					<div class="space-20"></div>
				</div><!-- /#home -->
			</div>
		</div>
	</div>
	</div>
	<%@ include file="./include/footer.jsp" %>
</body>
</html>