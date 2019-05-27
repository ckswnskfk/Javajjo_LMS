<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>강사 정보 수정</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./js/join.js"></script>
</head>
<body>
<%@ include file="./include/header.jsp" %>
<div class="container">
  <h2>강사 정보 수정</h2>
  <hr>
  <form class="form-horizontal" action="#" method="post" onsubmit="return t_modify()">
  <input type="hidden" name="find_id" value="${id}">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
        <span id="err_id"></span>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pw">PW</label>
      <div class="col-sm-8">          
        <input type="password" class="form-control" id="pw" placeholder="Enter password" name="pw">
        <span id="err_pw"></span>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">이름</label>
      <div class="col-sm-8">          
        <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
        <span id="err_name"></span>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="coursecode">과정</label>
      <div class="col-sm-8">          
        <select name="coursecode" id="coursecode">
        	<c:forEach items="${Alists}" var="adto" varStatus="vs">
				<option value="${adto.coursecode}">${adto.coursename}</option>
			</c:forEach>
        </select>
        <span id="err_course"></span>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">수정</button>
        <button type="button" class="btn btn-default" onclick="history.back(-1)">취소</button>
      </div>
    </div>
  </form>
</div>
<%@ include file="./include/footer.jsp" %>
</body>
</html>
