<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>JAJ 회원 가입</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./js/join.js"></script>
</head>
<body>

<div class="container">
  <h2>회원 가입</h2>
  <hr>
  <form class="form-horizontal" action="#" onsubmit="return join()">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
        <span id="err_id"></span>
        <input type="button" value="본인 인증"/>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="idRe">인증번호</label>
      <div class="col-sm-8">          
        <input type="text" class="form-control" id="idRe" placeholder="Enter id Check" name="idRe">
        <input type="button" value="본인 인증 확인"/>
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
      <label class="control-label col-sm-2" for="pwRe">PW확인</label>
      <div class="col-sm-8">          
        <input type="password" class="form-control" id="pwRe" placeholder="Enter password Check" name="pwRe">
        <span id="err_pwRe"></span>
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
     <label class="control-label col-sm-2" for="genderM">성별</label>
      <label class="control-label col-sm-2" for="genderM">남성</label>
      <div class="col-sm-1">          
        <input type="radio" class="form-control" id="genderM" placeholder="Enter password" name="gender" value="M">
      </div>
      <label class="control-label col-sm-2" for="genderF">여성</label>
      <div class="col-sm-1">          
        <input type="radio" class="form-control" id="genderF" placeholder="Enter password" name="gender" value="F">
      </div>
      <div>
    	<span id="err_gender"></span>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="birth">생년월일</label>
      <div class="col-sm-8">          
        <input type="date" class="form-control" id="birth" placeholder="Enter birth" name="birth">
        <span id="err_birth"></span>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="addr">주소</label>
      <div class="col-sm-8">          
        <input type="text" class="form-control" id="addr" placeholder="Enter address" name="addr">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label><input type="checkbox" name="remember"> 약관 동의</label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
        <button type="button" class="btn btn-default" onclick="history.back(-1)">취소</button>
      </div>
    </div>
  </form>
</div>

</body>
</html>
