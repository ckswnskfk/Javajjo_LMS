<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청 내역 상세 조회 페이지</title>
<!-- <link href="./css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link rel= "stylesheet" type="text/css" href="./css/main.css"> -->

<!-- <script src="https://code.jquery.com/jquery-latest.js"></script> -->
<!-- <script type="text/javascript" src="./js/main.js"></script> -->
<!-- <script src="./js/bootstrap.bundle.min.js"></script> -->
</head>
<body>
<%@include file="./include/header.jsp" %>
<div class="container" id="main">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h1 class="mt-5">결석 신청 상세 조회</h1>
        <table class="table">
			<tr>
				<td>
					<label><b>이름</b></label><br/>
					${yesMap.dto.student_name}
				</td>
				<td>
					<label><b>결재란</b></label><br/>
					${yesMap.newfilename}
				</td>
				<td>
					<label><b>진행상태</b></label><br/>
					<c:if test="${yesMap.dto.stm eq 'N'}">
						진행중
					</c:if>
					<c:if test="${yesMap.dto.stm eq 'Y'}">
						승인
					</c:if>
					<c:if test="${yesMap.dto.stm eq 'R'}">
						미승인
					</c:if>
				</td>
			</tr>
			<tr>
				<td><label><b>수강 과정</b></label><br/>
					${yesMap.dto.coursename}
				</td>
				<td><label><b>결석 시작일</b></label><br/>
					${yesMap.dto.start_date}
				</td>
				<td><label><b>총 결석일</b></label><br />
					${yesMap.dto.absent_days}
				</td>
			</tr>
			<tr>
				<td colspan="3"><label><b>결석 사유</b></label><br/>
					<textarea rows="4" cols="70" readonly="readonly">${yesMap.dto.reason}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<label><b>첨부 파일</b></label><br/>
					${yesMap.dto.newfilename}
				</td>
			</tr>
			<c:if test="${yesMap.dto.stm eq 'R'}">
				<tr>
					<td colspan="3"><label><b>미승인 사유</b></label><br/>
						<textarea rows="4" cols="70" readonly="readonly">${yesMap.dto.unapproved_reason}</textarea>
					</td>	
				</tr>
			</c:if>
			<c:if test="${yesMap.dto.stm eq 'N'}">
				<tr>
					<td>
						<button onclick="location.href='./update_is_approve_Yes.do?seq=${yesMap.dto.form_seq}&stm=Y'">승인</button>
					</td>
					<td>
						<button>미승인</button>
					</td>
				</tr>
			</c:if>
		</table>
      </div>
    </div>
  </div>
<%@include file="./include/footer.jsp" %>
</body>
</html>