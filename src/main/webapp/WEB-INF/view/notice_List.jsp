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
		<h2>공지사항 게시판</h2>
		<form action="#" method="post" id="frm" onsubmit="return chkBox()">
			<table class="table table-hover" style="width: 80%; margin: 0 auto">
				<colgroup>
					<col width="10%">
					<col width="15%">
					<col width="30%">
					<col width="30%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<td>
							<input type="checkbox">
						</td>
						<th>No</th>
						<th>Title</th>
						<th>Id</th>
						<th>Regdate</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lists}" var="dto" varStatus="vs">
					<tr>
						<td>
							<input type="checkbox">
						</td>
						<td>${vs.count}</td>
						<td><a href="./notice_detail.do?seq=${dto.seq}">${dto.title}</a></td>
						<td>${dto.id}</td>
						<td>${dto.regdate}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="input-group" style="width: 70%; margin: 0 auto;">
		      <input type="text" class="form-control" placeholder="Search" name="search">
		      <div class="input-group-btn">
		        <button class="btn btn-default" type="button"><i class="glyphicon glyphicon-search"></i></button>
		      </div>
		    </div>
		    <c:choose>
		    <c:when test="${member.table eq 'Student'}">
		    
			</c:when>
			<c:otherwise>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" class="btn btn-default"
					 value="글 작성"/>
				</div>
			</div>
			</c:otherwise>
			</c:choose>
		</form>
	</div>
<%@ include file="./include/footer.jsp" %>
</body>
</html>