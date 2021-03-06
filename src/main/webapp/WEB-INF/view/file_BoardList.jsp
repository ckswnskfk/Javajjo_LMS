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
		<form action="#" method="get">
			<table class="table table-hover" style="width: 80%; height:210px; margin: 0 auto">
				<colgroup>
					<col width="10%">
					<col width="25%">
					<col width="20%">
					<col width="15%">
					<col width="30%">
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
			
			<input type="hidden" name="index" id="index" value="${pg.index}">
			<input type="hidden" name="pageNum" id="pageNum" value="${pg.pageNum}">
			<input type="hidden" name="listNum" id="listNum" value="${pg.listNum}">
			
			<div class="center">
				<ul class="pagination" style="width: 300px;">
					<li><a href="#" onclick="pageFrist(${pg.pageList},${pg.pageList},'file','${find}')">&laquo;</a></li>
					<li><a href="#" onclick="pagePre(${pg.pageNum},${pg.pageList},'file','${find}')">&lsaquo;</a></li>
					<c:forEach var="i" begin="${pg.pageNum}" end="${pg.count}" step="1">
						<li><a href="#" onclick="pageIndex(${i},'file','${find}')">${i}</a></li>
					</c:forEach>
					<li><a href="#" onclick="pageNext(${pg.pageNum},${pg.total},${pg.listNum} ,${pg.pageList},'file','${find}')">&rsaquo;</a></li>
					<li><a href="#" onclick="pageLast(${pg.pageNum},${pg.total},${pg.listNum} ,${pg.pageList},'file','${find}')">&raquo;</a></li>
				</ul>
			</div>
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
					<input type="button" class="btn btn-info"
					 value="글 작성" onclick="file_infowriteboardform()"/>
				</div>
			</div>
		</form>
	</div>
<%@ include file="./include/footer.jsp" %>
</div>
</body>
</html>