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
		<form action="#" method="get">
			<table class="table table-hover" style="width: 80%; margin: 0 auto">
				<colgroup>
					<col width="10%">
					<col width="30%">
					<col width="30%">
					<col width="10%">
					<col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Id</th>
						<th>Readcount</th>
						<th>Regdate</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lists}" var="dto" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td><a href="./file_infodetailboard.do?seq=${dto.seq}">${dto.title}</a></td>
						<td>${dto.id}</td>
						<td>${dto.readcount}</td>
						<td>${dto.regdate}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="input-group" style="width: 50%; margin: 0 auto;">
				<select name="searchOption">
					<option value="id">아이디</option>
					<option value="title">제목</option>
					<option value="all">아이디+제목</option>
				</select>
		      <input type="text" class="form-control" placeholder="Search" name="keyword">
		      <div class="input-group-btn">
		        <button class="btn btn-default" type="button" onclick="file_infosearchboard()"><i class="glyphicon glyphicon-search"></i></button>
		      </div>
		    </div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" class="btn btn-default"
					 value="글 작성" onclick="file_infowriteboardform()"/>
				</div>
			</div>
		</form>
	</div>
<%@ include file="./include/footer.jsp" %>
</body>
</html>