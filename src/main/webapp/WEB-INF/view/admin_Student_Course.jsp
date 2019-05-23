<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${member.name}님 마이 페이지</title>
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
					<a data-toggle="tab" onclick="#home">
						<i class="green ace-icon fa fa-user bigger-120"></i>
						학생 과정 연결
					</a>
			</ul>
				<div class="tab-content no-border padding-14">
					<div id="home" class="tab-pane in active">
						<div class="row">
							<div class="col-xs-9 col-sm-9">
								<h4 class="dark">
									<span class="middle">${dto.name} 학생 상세 조회</span>
								</h4>
								<div class="col-sm-offset-2 col-sm-10">
									<input type="button" class="btn btn-default"
										value="과정 연결" onclick="admin_student_course()"/>
								</div>
								<div class="profile-user-info">
									<div class="profile-info-row">
										<div class="profile-info-name"> Id </div>

										<div class="profile-info-value">
											<span>${dto.id}</span>
										</div>
									</div>
									
									<div class="profile-info-row">
										<div class="profile-info-name"> Pw </div>

										<div class="profile-info-value">
											<span>${dto.pw}</span>
										</div>
									</div>
									
									<div class="profile-info-row">
										<div class="profile-info-name"> Name </div>

										<div class="profile-info-value">
											<span>${dto.name}</span>
										</div>
									</div>
								
									<div class="profile-info-row">
										<div class="profile-info-name"> Gender </div>
	
										<div class="profile-info-value">
										<c:choose>
											<c:when test="${dto.gender eq 'M'}">
												<span>남자</span>
											</c:when>
											<c:when test="${dto.gender eq 'F'}">
												<span>여자</span>
											</c:when>
										</c:choose>
										</div>
									</div>
								
									<div class="profile-info-row">
										<div class="profile-info-name"> Birth </div>
	
										<div class="profile-info-value">
											<span>${dto.birth}</span>
										</div>
									</div>
								
									<div class="profile-info-row">
										<div class="profile-info-name"> Addr </div>
	
										<div class="profile-info-value">
											<span>${dto.addr}</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<input type="button" class="btn btn-default"
										 value="수정하기" onclick="admin_student_modify()"/>
										 <input type="button" class="btn btn-default"
										 value="목록으로" onclick="history.back()"/>
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
	<%@ include file="./include/footer.jsp" %>
</body>
</html>