<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청 내역 리스트 페이지</title>
<!-- <link href="./css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link rel= "stylesheet" type="text/css" href="./css/main.css"> -->

<!-- <script src="https://code.jquery.com/jquery-latest.js"></script> -->
<!-- <script type="text/javascript" src="./js/main.js"></script> -->
<!-- <script src="./js/bootstrap.bundle.min.js"></script> -->
</head>
<script type="text/javascript">
	
	function stmSelect(stm) {
// 		alert(stm);
		$.ajax({
			
		});
	}
	
</script>
<body>
<%@include file="./include/header.jsp" %>

<!-- Page Content -->
  <div class="container" id="main">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h1 class="mt-5">결석 신청 내역</h1>
        <div>
        	<select id="select_stm" name="select_stm" onchange="stmSelect(this.value)">
        		<option value="N">진행중</option>
        		<option value="Y">승인</option>
        		<option value="R">미승인</option>
        	</select>
        </div>
        <div>
        	<table class="table">
        		<tr>
        			<td>순번</td>
        			<td>신청일</td>
        			<td>과정명</td>
        			<td>상태</td>
        		</tr>
        		<c:forEach var="dto" items="${list}" varStatus="vs">
        			<tr>
	        			<td>${vs.count}</td>
	        			<td><a href="./absent_detail_no.do?seq=${dto.form_seq}">${dto.app_date}</a></td>
	        			<td>${dto.coursename}</td>
	        			<td>${dto.stm}</td>
	        		</tr>
        		</c:forEach>
        	</table>
        </div>
      </div>
    </div>
  </div>
  
  
<%@include file="./include/footer.jsp" %>
</body>
</html>