<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결석 신청 페이지</title>
<link rel= "stylesheet" type="text/css" href="./css/template.css">
</head>
<script type="text/javascript">
	var id = "${member.id}";
	
	function selsectCoursecode() {
		var v2 = $("#recipient_id  option:selected").attr("coursecode"); //지정 value2 값
// 		alert(v2);
		document.getElementById("coursecode").value = v2;
	}
	
	onload = (function() {
// 		alert(id);
		$.ajax({
			url: "./absent_course.do",
// 			async : false,
			data: id,
			type: "post",
			success: function(msg) {
				$.each(msg, function(key, value) {
// 					alert(value.coursename);
					$("#recipient_id").append("<option value='"+value.id+"' coursecode='"+value.coursecode+"'>"+value.coursename+" "+value.coursecnt+"회차, "+value.teacher_name+"선생님"+"</option>")
				});
			}
		});
	});
	
	function apply() {
		var frm = document.getElementsByTagName("form")[0];
		
		var start_date = document.getElementById("start_date").value;
		var absent_days = document.getElementById("absent_days").value;
		var reason = document.getElementById("reason").value;
		var recipient_id = $("#recipient_id option:selected").val();
		
		if (recipient_id == "choice") {
			$("#recipient_id_chk").html("결석 신청하려는 과정 혹은 관리자를 선택해주세요").css("color", "red");
			return false;
		}
		if (start_date == "" || !startDateChk()){
			$("#start_date_chk").html("형식에 맞춰 날짜를 입력해주세요").css("color", "red");
			return false;
		}
		if (isNaN(absent_days) || absent_days == "") {
			$("#absent_days_chk").html("숫자를 입력해주세요").css("color", "red");
			return false;
		}
		if (reason == "") {
			$("#reason_chk").html("사유를 입력해주세요").css("color", "red");
			return false;
		}
		
// 		var originalfilename = $("#originalfilename").val();
// 		alert(filename);
// 		var idAdd = "${member.id}_";
// 		idAdd += originalfilename;
// 		$("#newfilename").val(newFileName);
// 		alert(newFileName);
		frm.action="./insert_absent_form.do";
		frm.method="post";
		frm.encoding="multipart/form-data";
		frm.submit();
	}
	
	function startDateChk() {
		var date_pattern = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		var start_date = document.getElementById("start_date").value;
		if(start_date.match(date_pattern)){
			return true;
		}
	}
</script>

<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>

	<div class="container" id="main">
    <div class="row" style="width: 800px; margin: 0 auto;">
      <div class="col-lg-12 text-center">
        <h4 class="mt-5">결석 신청</h4>
		<form action="" onsubmit="apply(); return false;">
			<input type="hidden" name="student_id" value="${member.id}">
			<input id="coursecode" type="hidden" name="coursecode" value="">
			<table class="table">
				<tr>
					<td>
						<label><b>이름</b></label><br/>
						<input type="text" maxlength="50" style="width: 200px" value="${member.name}" readonly="readonly"/>
					</td>
					<td>
						<label><b>결석 신청하려는 과정(관리자)</b></label><br/>
						<select id="recipient_id" name="recipient_id" onchange="selsectCoursecode()">
						<option value="choice" selected="selected">선택</option>
						<option value="01099999999">관리자</option>
						</select>
						<div id="recipient_id_chk">

						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label><b>결석 시작일</b></label><br/>
						<input id="start_date" name="start_date" type="date" maxlength="255" style="width: 200px" placeholder="ex) 20XX-XX-XX"/>
						<div id="start_date_chk">

						</div>
					</td>
					<td>
						<label><b>총 결석일</b></label><br/>
						<input id="absent_days" name="absent_days" type="text" maxlength="100" style="width: 200px"/>
						<div id="absent_days_chk">
					
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label><b>결석 사유</b></label><br/>
						<textarea id="reason" name="reason" rows="4" cols="70"></textarea>
						<div id="reason_chk">

						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label><b>첨부 파일</b></label><br/>
						<input id="originalfilename" name="originalfilename" type="file" maxlength="50" style="width: 260px"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="신청서 제출">
					</td>
				</tr>
			</table>
		</form>
        </div>
      </div>
    </div>
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>