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
					<li>
						<a data-toggle="tab" onclick="location.href='./teacher_info.do'">
							<i class="green ace-icon fa fa-user bigger-120"></i>
							사용자 정보 조회
						</a>
					</li>
	
					<li class="active">
						<a data-toggle="tab" href="#feed">
							<i class="orange ace-icon fa fa-rss bigger-120"></i>
							수강중인 과정 조회
						</a>
					</li>
				</ul>
	
				<div class="tab-content no-border padding-14">
					<div id="home" class="tab-pane in active">
						<div class="row">
							<div class="col-xs-9 col-sm-9">
								<h4 class="dark">
									<span class="middle">${member.name} 님의 수강 과정 조회</span>
								</h4>
	
								<div class="profile-user-info">
									<div class="profile-info-row">
										<div class="profile-info-name"> No </div>
										
										<div class="profile-info-name"> 아이디 </div>
	
										<div class="profile-info-name"> 이름 </div>
										
										<div class="profile-info-name"> 성별 </div>
										
										<div class="profile-info-name"> 생년월일 </div>
										
										<div class="profile-info-name"> 집주소 </div>
										
									</div>
									<c:forEach items="${lists}" var="dto" varStatus="vs">
									<div class="profile-info-row">
										<div class="profile-info-name">
											<span><c:out value="${vs.count}"></c:out></span>
										</div>
										<div class="profile-info-name">
											<span><c:out value="${dto.id}"></c:out></span>
										</div>
										<div class="profile-info-name">
											<span><c:out value="${dto.name}"></c:out></span>
										</div>
										<div class="profile-info-name">
											<span><c:out value="${dto.gender}"></c:out></span>
										</div>
										<div class="profile-info-name">
											<span><c:out value="${dto.birth}"></c:out></span>
										</div>
										<div class="profile-info-name">
											<span><c:out value="${dto.addr}"></c:out></span>
										</div>
									</div>
									</c:forEach>
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