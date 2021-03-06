<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${member.name}님 마이 페이지</title>
<link rel= "stylesheet" type="text/css" href="./css/template.css">
<link rel= "stylesheet" type="text/css" href="./css/index.css">
<link rel= "stylesheet" type="text/css" href="./css/info.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript" src="./js/info.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="content-wrapper">
<%@ include file="./include/header.jsp" %>
<div class="container">
<form action="#">
<input type="hidden" name="id" value="${id}">
<div id="user-profile-2" class="user-profile">
		<div class="tabbable">
			<ul class="nav nav-tabs padding-18">
				<li class="active">
					<a data-toggle="tab" onclick="#home">
						<i class="green ace-icon fa fa-user bigger-120"></i>
						학생 과정 연결
					</a>
				</li>
			</ul>
				<div class="tab-content no-border padding-14">
					<div id="home" class="tab-pane in active">
						<div class="row">
							<div class="col-xs-9 col-sm-9">
								<h4 class="dark">
									<span class="middle">${dto.name} 학생 과정 연결</span>
								</h4>
								<div class="profile-user-info">
									<div class="profile-info-row">
										<div class="profile-info-name"> Id </div>

										<div class="profile-info-value">
											<span>${id}</span>
										</div>
									</div>
									
									<div class="profile-info-row">
										<div class="profile-info-name"> 수강과정 </div>
										<c:forEach items="${Slists}" var="sdto" varStatus="vs">
											<div class="profile-info-row">
											<div class="profile-info-value">
												<input type="radio" id="coursecode" name="coursecode" value="${sdto.coursecode}">
												<span>${sdto.coursename} </span>
												<span>${sdto.coursecnt}회차</span>
											</div>
											</div>
											</c:forEach>
									</div>
									
									<div class="profile-info-row">
										<div class="profile-info-name"> 추가과정 </div>

										
											<div class="profile-info-value">
												<select name="Acoursecode" id="Acoursecode">
													<c:forEach items="${Alists}" var="adto" varStatus="vs">
													<option value="${adto.coursecode}">${adto.coursename} (${adto.coursecnt}회차)</option>
													</c:forEach>
												</select>
											</div>
										
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<input type="button" class="btn btn-default"
										 value="삭제하기" onclick="admin_student_cdelete()"/>
										 <input type="button" class="btn btn-default"
										 value="추가하기" onclick="admin_student_cconnect()"/>
										 <input type="button" class="btn btn-default"
										 value="목록으로" onclick="chkCourseModify()"/>
											<script type="text/javascript">
												
												function chkCourseModify() {
													if (sessionStorage.getItem("num") == null) {
														window.history.go(-1);
													} else {
														var num = parseInt(sessionStorage.getItem("num"));
														sessionStorage.removeItem("num");
														window.history.go((-num));
													}
												}
											</script>
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
		</form>
		</div>
	<%@ include file="./include/footer.jsp" %>
	</div>
</body>
</html>