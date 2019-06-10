<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel= "stylesheet" type="text/css" href="./css/template.css">
<link rel="stylesheet" href="./css/BoardList.css">
<script type="text/javascript" src="./js/BoardList.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="content-wrapper">
<%@ include file="./include/header.jsp" %>
	<div class="container">
		<h2>자료 게시판</h2>
		<form action="#" method="post" enctype="multipart/form-data">
			<input type="hidden" name="seq" value="${dto.seq}">
			<table class="table table-hover" style="width: 80%; margin: 0 auto">
				<tbody>
					<tr>
						<th>ID</th>
						<td>${member.id}</td>
					</tr>
					<tr>
						<th>Title</th>
						<td>
							<div class="form-group">
								<input type="text" class="form-control" name="title" placeholder="${dto.title}">
							</div>
						</td>
					</tr>
					<tr>
						<th>Content</th>
						<td>
							<div class="form-group">
								<textarea rows="20" cols="100" class="form-control" name="content" placeholder="${dto.content}"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<th>파일 첨부 : </th>
						<td>
							<input type="file" id="originalfilename" name="originalfilename">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" class="btn btn-info"
					 value="수정 완료" onclick="file_infomodifyboard()"/>
					<input type="button" class="btn btn-default"
					 value="취소" onclick="history.back()"/>
				</div>
			</div>
		</form>
	</div>
<%@ include file="./include/footer.jsp" %>
</div>
</body>
</html>