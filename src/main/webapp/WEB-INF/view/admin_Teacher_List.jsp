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
<form action="#" method="get">
<div id="user-profile-2" class="user-profile">
			<div class="tabbable">
				<ul class="nav nav-tabs padding-18">
				<li>
					<a data-toggle="tab" onclick="location.href='./admin_info.do'">
						<i class="green ace-icon fa fa-user bigger-120"></i>
						사용자 정보 조회
					</a>
				</li>
				
				<li>
					<a data-toggle="tab" onclick="location.href='./admin_student_list.do'">
						<i class="orange ace-icon fa fa-rss bigger-120"></i>
						전체 학생 조회
					</a>
				</li>

				<li class="active">
					<a data-toggle="tab" onclick="location.href='./admin_teacher_list.do'">
						<i class="orange ace-icon fa fa-rss bigger-120"></i>
						전체 강사 조회
					</a>
				</li>
				
				<li>
					<a data-toggle="tab" onclick="location.href='./admin_accept_list.do'">
						<i class="orange ace-icon fa fa-rss bigger-120"></i>
						회원 가입 신청 조회
					</a>
				</li>
			</ul>
	
				<div class="tab-content no-border padding-14">
					<div id="home" class="tab-pane in active">
						<div class="row">
							<div class="col-xs-9 col-sm-9">
								<h4 class="dark">
									<span class="middle" style="font-size: 25px; margin-top: 20px">${member.name} 님의 전체 강사 조회</span>
								</h4>
	
								<div class="profile-user-info" style="margin-top: 20px;">
									<div class="profile-info-row">
										<div class="profile-info-name" style="width: 5%"> 
											<input id="allChk" type="checkbox" onclick="allChk1(this)"/>
										</div>
									
										<div class="profile-info-name"> No </div>
										
										<div class="profile-info-name"> 아이디 </div>
	
										<div class="profile-info-name"> 이름 </div>
										
										<div class="profile-info-name"> 과정
										 </div>
										
									</div>
									<c:forEach items="${lists}" var="dto" varStatus="vs">
									<div class="profile-info-row">
										<div class="profile-info-name">
											<input name="RowCheck" type="checkbox" value="${dto.id}">
										</div>
										<div class="profile-info-name">
											<span><c:out value="${vs.count}"></c:out></span>
										</div>
										<div class="profile-info-name">
											<span><c:out value="${dto.id}"></c:out></span>
										</div>
										<div class="profile-info-name">
											<a href="admin_teacher_modify_form.do?id=${dto.id}">
											<span><c:out value="${dto.name}"></c:out></span>
											</a>
										</div>
										<div class="profile-info-name">
											<span><c:out value="${dto.coursename}"></c:out></span>
										</div>
									</div>
									</c:forEach>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										 <input type="button" class="btn btn-primary active"
										 value="강사등록" onclick="admin_teacher_add_form()"style="font-size: 18px"/>
										<input type="button" class="btn btn-primary disabled"
										 value="강사탈퇴" onclick="admin_teacher_delete()" style="font-size: 18px"/>
									</div>
								</div>
							<div class="hr hr-5 dotted"></div>
							</div><!-- /.col -->
						</div><!-- /.row -->
						<div class="space-20"></div>
					</div><!-- /#home -->
					<input type="hidden" name="index" id="index" value="${pg.index}">
						<input type="hidden" name="pageNum" id="pageNum" value="${pg.pageNum}">
						<input type="hidden" name="listNum" id="listNum" value="${pg.listNum}">
						
						<div class="center">
							<ul class="pagination">
								<li><a href="#" onclick="pageFrist(${pg.pageList},${pg.pageList},'admin_teacher')">&laquo;</a></li>
								<li><a href="#" onclick="pagePre(${pg.pageNum},${pg.pageList},'admin_teacher')">&lsaquo;</a></li>
								<c:forEach var="i" begin="${pg.pageNum}" end="${pg.count}" step="1">
									<li><a href="#" onclick="pageIndex(${i},'admin_teacher')">${i}</a></li>
								</c:forEach>
								<li><a href="#" onclick="pageNext(${pg.pageNum},${pg.total},${pg.listNum} ,${pg.pageList},'admin_teacher')">&rsaquo;</a></li>
								<li><a href="#" onclick="pageLast(${pg.pageNum},${pg.total},${pg.listNum} ,${pg.pageList},'admin_teacher')">&raquo;</a></li>
							</ul>
						</div>
				</div>
			</div>
		</div>
		</form>
		</div>
	<%@ include file="./include/footer.jsp" %>
	</div>
</body>
</html>