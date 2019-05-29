<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/BoardList.css">
<script type="text/javascript" src="./js/BoardList.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="./include/header.jsp" %>
	<div id="container">
		<h2>자료 게시판</h2>
		<form action="#" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${member.id}">
			<table class="table table-hover" style="width: 80%; margin: 0 auto">
				<tbody>
					<tr>
						<th>ID</th>
						<td>${member.id}</td>
					</tr>
					<tr>
						<th>Title</th>
						<td>
							<input type="text" name="title" placeholder="Write title">
						</td>
					</tr>
					<tr>
						<th>Content</th>
						<td>
							<textarea rows="20" cols="100" name="content" placeholder="Write content"></textarea>
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
					<input type="button" class="btn btn-default"
					 value="작성 완료" onclick="file_infowriteboard()"/>
					<input type="button" class="btn btn-default"
					 value="취소" onclick="history.back()"/>
				</div>
			</div>
		</form>
	</div>
<%@ include file="./include/footer.jsp" %>
</body>
</html>