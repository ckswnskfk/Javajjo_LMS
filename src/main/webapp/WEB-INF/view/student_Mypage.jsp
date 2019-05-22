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
					<a data-toggle="tab" href="#feed">
						<i class="orange ace-icon fa fa-rss bigger-120"></i>
						수강중인 과정 조회
					</a>
				</li>
			</ul>

			<div class="tab-content no-border padding-14">
				<div id="home" class="tab-pane in active">
					<div class="row">
						<div class="col-xs-12 col-sm-9">
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
									<div class="profile-info-name">Gender</div>

									<div class="profile-info-value">
										<span>${dto.gender}</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Birth</div>

									<div class="profile-info-value">
										<span>${dto.birth}</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Addr</div>

									<div class="profile-info-value">
										<span>${dto.addr}</span>
									</div>
								</div>
							</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<input type="button" class="btn btn-default"
										 value="수정하기" onclick="student_modify()"/>
									</div>
								</div>
							<div class="hr hr-5 dotted"></div>

							
						</div><!-- /.col -->
					</div><!-- /.row -->

					<div class="space-20"></div>
				</div><!-- /#home -->

				<div id="feed" class="tab-pane">
					<div class="profile-feed row">
						<div class="col-sm-6">
							<div class="profile-activity clearfix">
								<div>
									<img class="pull-left" alt="Alex Doe's avatar" src="http://bootdey.com/img/Content/avatar/avatar1.png">
									<a class="user" href="#"> Alex Doe </a>
									changed his profile photo.
									<a href="#">Take a look</a>

									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										an hour ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<img class="pull-left" alt="Susan Smith's avatar" src="http://bootdey.com/img/Content/avatar/avatar2.png">
									<a class="user" href="#"> Susan Smith </a>

									is now friends with Alex Doe.
									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										2 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<i class="pull-left thumbicon fa fa-check btn-success no-hover"></i>
									<a class="user" href="#"> Alex Doe </a>
									joined
									<a href="#">Country Music</a>

									group.
									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										5 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<i class="pull-left thumbicon fa fa-picture-o btn-info no-hover"></i>
									<a class="user" href="#"> Alex Doe </a>
									uploaded a new photo.
									<a href="#">Take a look</a>

									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										5 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<img class="pull-left" alt="David Palms's avatar" src="http://bootdey.com/img/Content/avatar/avatar3.png">
									<a class="user" href="#"> David Palms </a>

									left a comment on Alex's wall.
									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										8 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>
						</div><!-- /.col -->

						<div class="col-sm-6">
							<div class="profile-activity clearfix">
								<div>
									<i class="pull-left thumbicon fa fa-pencil-square-o btn-pink no-hover"></i>
									<a class="user" href="#"> Alex Doe </a>
									published a new blog post.
									<a href="#">Read now</a>

									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										11 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<img class="pull-left" alt="Alex Doe's avatar" src="http://bootdey.com/img/Content/avatar/avatar4.png">
									<a class="user" href="#"> Alex Doe </a>

									upgraded his skills.
									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										12 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
									<a class="user" href="#"> Alex Doe </a>

									logged in.
									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										12 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<i class="pull-left thumbicon fa fa-power-off btn-inverse no-hover"></i>
									<a class="user" href="#"> Alex Doe </a>

									logged out.
									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										16 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>

							<div class="profile-activity clearfix">
								<div>
									<i class="pull-left thumbicon fa fa-key btn-info no-hover"></i>
									<a class="user" href="#"> Alex Doe </a>

									logged in.
									<div class="time">
										<i class="ace-icon fa fa-clock-o bigger-110"></i>
										16 hours ago
									</div>
								</div>

								<div class="tools action-buttons">
									<a href="#" class="blue">
										<i class="ace-icon fa fa-pencil bigger-125"></i>
									</a>

									<a href="#" class="red">
										<i class="ace-icon fa fa-times bigger-125"></i>
									</a>
								</div>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->

					<div class="space-12"></div>

					<div class="center">
						<button type="button" class="btn btn-sm btn-primary btn-white btn-round">
							<i class="ace-icon fa fa-rss bigger-150 middle orange2"></i>
							<span class="bigger-110">View more activities</span>

							<i class="icon-on-right ace-icon fa fa-arrow-right"></i>
						</button>
					</div>
				</div><!-- /#feed -->
			</div>
		</div>
	</div>
	<%@ include file="./include/footer.jsp" %>
</body>
</html>