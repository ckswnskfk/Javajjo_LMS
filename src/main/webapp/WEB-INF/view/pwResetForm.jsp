<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>JAJ 비밀번호 초기화</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./js/reset.js"></script>
</head>
<body>

<div class="container">
  <h2>비밀번호 초기화 페이지</h2>
  <hr>
  <form class="form-horizontal" action="#" onsubmit="return pwreset()">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">이름</label>
      <div class="col-sm-8">          
        <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
      </div>
    </div>
     <div class="form-group">        
      <div class="col-sm-offset-5 col-sm-6">
        <button type="submit" class="btn btn-default">초기화</button>
        <button type="button" class="btn btn-default" onclick="history.back(-1)">취소</button>
      </div>
    </div>
    <div>
    	<h2 style="text-align: center;">입력한 ID로 비밀번호가 전송됩니다.</h2>
    </div>
  </form>
</div>

</body>
</html>
