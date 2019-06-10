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
		<form action="#" method="get">
			<table class="table table-hover" style="width: 80%; height:210px; margin: 0 auto">
				<colgroup>
					<col width="10%">
					<col width="15%">
					<col width="30%">
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
						<td><a href="./notice_detail.do?seq=${dto.seq}">${dto.title}</a></td>
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
				<ul class="pagination">
					<li><a href="#" onclick="pageFrist(${pg.pageList},${pg.pageList},'notice','${find}')">&laquo;</a></li>
					<li><a href="#" onclick="pagePre(${pg.pageNum},${pg.pageList},'notice','${find}')">&lsaquo;</a></li>
					<c:forEach var="i" begin="${pg.pageNum}" end="${pg.count}" step="1">
						<li><a href="#" onclick="pageIndex(${i},'notice','${find}')">${i}</a></li>
					</c:forEach>
					<li><a href="#" onclick="pageNext(${pg.pageNum},${pg.total},${pg.listNum} ,${pg.pageList},'notice','${find}')">&rsaquo;</a></li>
					<li><a href="#" onclick="pageLast(${pg.pageNum},${pg.total},${pg.listNum} ,${pg.pageList},'notice','${find}')">&raquo;</a></li>
				</ul>
			</div>
			
			<div class="input-group" style="width: 50%; margin: 0 auto;">
		      <input type="text" class="form-control" placeholder="제목으로 검색..." name="title">
		      <div class="input-group-btn">
		        <button class="btn btn-default" type="button" onclick="notice_search()"><i class="glyphicon glyphicon-search"></i></button>
		      </div>
		    </div>
		    <c:choose>
		    <c:when test="${member.table eq 'Student'}">
		    
			</c:when>
			<c:otherwise>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" class="btn btn-info"
					 value="글 작성" onclick="notice_form()"/>
				</div>
			</div>
			</c:otherwise>
			</c:choose>
		</form>
	</div>
<%@ include file="./include/footer.jsp" %>
</div>
</body>
</html>