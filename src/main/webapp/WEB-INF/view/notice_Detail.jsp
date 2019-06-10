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
		<h2>공지사항 게시판</h2>
			<table class="table table-hover" style="width: 80%; margin: 0 auto">
				<tbody>
					<tr>
						<th>ID</th>
						<td>${dto.id}</td>
					</tr>
					<tr>
						<th>ReadCount</th>
						<td>${dto.readcount}</td>
					</tr>
					<tr>
						<th>Title</th>
						<td>${dto.title}</td>
					</tr>
					<tr>
						<th>Content</th>
						<td>${dto.content}</td>
					</tr>
					<tr>
						<th>Regdate</th>
						<td>${dto.regdate}</td>
					</tr>
				</tbody>
			</table>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" class="btn btn-info"
					 value="글 목록" onclick="history.back()"/>
				</div>
			</div>
	</div>
<%@ include file="./include/footer.jsp" %>
</div>
</body>
</html>