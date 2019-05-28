<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청 내역 리스트 페이지</title>
</head>
<body>
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
			async : false,
			data: stm,
// 			dataType: "json",
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
		     						+ "<td>"+ob.lists[i].stm+"</td>";
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
	
	function addSignature() {
		alert("aaa");
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
    <div class="row">
      <div class="col-lg-12 text-center">
        <h1 class="mt-5">결석 신청 내역</h1>
        <div>
        	<select id="select_stm" name="select_stm" onchange="stmSelect(this.value)">
        		<option selected="selected">선택하세요</option>
        		<option value="N">진행중</option>
        		<option value="Y">승인</option>
        		<option value="R">미승인</option>
        	</select>
        </div>
        <div id="list">
        
        </div>
        <div>
        	<c:if test="${member.table eq 'Student'}">
        		<button id="toAppForm" onclick="location.href='./to_app_form.do'">결석 신청</button>
        	</c:if>
        	<c:if test="${member.table ne 'Student'}">
        		<button class="btn btn-outline-success" onclick="chkSignature()">사인 등록</button>
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
								<form action="./addSignature.do" method="post" enctype="multipart/form-data" onsubmit="addSignature()">
									<input type="hidden" name="id" value="${member.id}">
									<input type="file" name="originalfilename">
									<button type="submit">등록</button>
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
</body>
</html>