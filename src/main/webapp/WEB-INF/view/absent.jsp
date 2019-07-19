<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청 내역 리스트 페이지</title>
</head>
<link rel= "stylesheet" type="text/css" href="./css/template.css">
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>
<script type="text/javascript">
	var url = "";
	
	$(document).ready(function() {
		if("${member.table}"=="Student"){
			url = "./absentList.do";
		}else{
			url = "./recipient_absent_list.do";
		}
	});
	
	function stmSelect(stm) {
		$.ajax({
			url: url,
			data: stm,
			type: "POST",
			success: function(obj) {
				var ob = JSON.parse(obj);

				var html = "<table class='table'><tr><td>문서 번호</td><td>신청일</td>";
				if (stm != "N") {
					html += "<td>처리일</td>";
				}
				if (${member.table != 'Student'}) {
					html += "<td>신청자 이름</td>";
				}
				html += "<td>과정명</td><td>상태</td></tr>";
				
				var htmlInvlud = "";
				for (var i = 0; i < ob.lists.length; i++) {
					htmlInvlud += "<tr>"
		     					+ "<td>"+ob.lists[i].form_seq+"</td>"
		     					+ "<td><a href='./absent_detail_yes.do?seq="+ob.lists[i].form_seq+"&id="+ob.lists[i].recipient_id+"&stm="+ob.lists[i].stm+"'>"+ob.lists[i].app_date+"</a></td>";
		     		if (stm != "N") {
		     			htmlInvlud += "<td>"+ob.lists[i].process_date+"</td>";
					}
		     		if(${member.table != 'Student'}){
		     			htmlInvlud += "<td>"+ob.lists[i].student_name+"</td>";
		     		}
		     		if(ob.lists[i].coursename == null){
		     			htmlInvlud += "<td>관리자</td>"
		     			if (ob.lists[i].stm == 'N') {
		     				htmlInvlud += "<td>진행중</td>";
						} else if (ob.lists[i].stm == 'Y') {
							htmlInvlud += "<td>승인</td>";
						} else {
							htmlInvlud += "<td>미승인</td>";
						}
		     		} else {
		     			htmlInvlud += "<td>"+ob.lists[i].coursename+"</td>";
		     			if (ob.lists[i].stm == 'N') {
		     				htmlInvlud += "<td>진행중</td>";
						} else if (ob.lists[i].stm == 'Y') {
							htmlInvlud += "<td>승인</td>";
						} else {
							htmlInvlud += "<td>미승인</td>";
						}
					}
				}
				html += htmlInvlud;
				html += "</table>";

				if (html.indexOf("<a") == -1) {
					html += "<tr><td colspan='4'>상태에 해당하는 신청이 없습니다</td></tr>"
					$("#list").html(html);
				} else {
					$("#list").html(html);
				}
			}
		});
	}
	
	
	function fileChk(targetObj, $target) {
		var ext = targetObj.value.split(".").pop().toLowerCase();
		if ($.inArray(ext, ["gif", "jpg", "jpeg", "png"]) == -1) {
			alert("이미지 파일(gif, jpg, jpeg, png)만 업로드 가능합니다.");
			$("#originalfilename").val("");
			$('#img_preview').attr('style', 'display:none');
			return;
		} else {
			if (targetObj.files && targetObj.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#img_preview').attr('src', e.target.result);
					$('#img_preview').attr('style', 'display:');
				}
				reader.readAsDataURL(targetObj.files[0]);
			}
			$("#submit").attr("disabled", false);
		}
	}
	
	function chkSignature() {
		var id = "${member.id}";
		$.ajax({
			url: "./chkSignature.do",
			data: id,
			type: "post",
			success: function(n) {
				alert("이미 사인을 등록하셨습니다.");
			}, error: function() {
				$("#addSignature").click();
			}
		});
	}
	
</script>

<!-- Page Content -->
  <div class="container" id="main">
    <div class="row" style="width: 950px; margin: 0 auto;">
      <div class="col-lg-12 text-center">
        <h4 class="mt-5">결석 신청 내역</h4>
        <div><br>
        	<select id="select_stm" name="select_stm" onchange="stmSelect(this.value)">
        		<option value="N" selected="selected">진행중</option>
        		<option value="Y">승인</option>
        		<option value="R">미승인</option>
        	</select>
        </div>
        <br>
        <div id="list">
        	<table class="table">
        		<tr>
        			<td>문서 번호</td>
        			<td>신청일</td>
        			<c:if test="${member.table ne 'Student'}">
	        			<td>신청자 이름</td>
        			</c:if>
        			<td>과정명</td>
        			<td>상태</td>
        		</tr>
        		<c:forEach var="dto" items="${jArray}">
        			<tr>
        				<td>
        					${dto.form_seq}
        				</td>
        				<td>
        					<a href="./absent_detail_yes.do?seq=${dto.form_seq}&id=${dto.recipient_id}&stm=${dto.stm}">${dto.app_date}</a>
        				</td>
        				<c:if test="${member.table ne 'Student'}">
        					<td>
        						${dto.student_name}
        					</td>
        				</c:if>
        				<c:if test="${dto.coursename == null}">
        					<td>
        						관리자
        					</td>
        				</c:if>
        				<c:if test="${dto.coursename != null}">
	        				<td>
	        					${dto.coursename}
	        				</td>
        				</c:if>
        				<td>
        					진행중
        				</td>
        			</tr>
        		</c:forEach>
        	</table>
        </div>
        <br>
        <div>
        	<c:if test="${member.table eq 'Student'}">
        		<button class="btn btn-outline-primary" id="toAppForm" onclick="location.href='./to_app_form.do'">결석 신청</button>
        	</c:if>
        	<c:if test="${member.table ne 'Student'}">
        		<button class="btn btn-outline-success" onclick="chkSignature()">사인 등록</button>
<!--         		<button class="btn btn-outline-success">사인 확인</button> -->
        		<input id="addSignature" type="hidden" data-toggle="modal" data-target="#layerpop">
        	</c:if>
        	
        		<div class="modal fade" id="layerpop" >
					<div class="modal-dialog">
						<div class="modal-content">
							<!-- header -->
							<div class="modal-header">
								<!-- header title -->
								<h4 class="modal-title">사인 등록</h4>
							</div>
							<!-- body -->
							
							<div class="modal-body">
								<form action="./addSignature.do" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${member.id}">
									<input type="file" id="originalfilename" name="originalfilename" onchange="fileChk(this,$('#img_preview'))">
									<img id="img_preview" alt="img_preview" src="" style="display:none;" width="150px" height="100">
									<button type="submit" id="submit" disabled="disabled">등록</button>
								</form>
							</div>
							<!-- Footer -->
							<div class="modal-footer">
								Footer
								<button type="button" class="btn btn-outline-success" data-dismiss="modal">닫기</button>
							</div>
						</div>
					</div>
				</div>
        </div>
      </div>
    </div>
  </div>
<%@include file="./include/footer.jsp" %>
</div>
</body>
</html>