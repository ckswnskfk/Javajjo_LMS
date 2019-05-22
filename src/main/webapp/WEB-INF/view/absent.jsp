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
		alert(stm);
		$.ajax({
			url: url,
			async : false,
			data: stm,
// 			dataType: "json",
			type: "POST",
			success: function(obj) {
				alert(obj);
				var ob = JSON.parse(obj);
				alert(ob);
				alert(ob.lists[0].form_seq);

				var html = "<table class='table'><tr><td>순번</td><td>신청일</td><td>과정명</td><td>상태</td></tr>";
				
				var htmlInvlud = "";
				for (var i = 0; i < ob.lists.length; i++) {
					htmlInvlud += "<tr>"
		     					+ "<td>"+(i+1)+"</td>"
		     					+ "<td><a href='./absent_detail_yes.do?seq="+ob.lists[i].form_seq+"&id="+ob.lists[i].recipient_id+"&stm="+ob.lists[i].stm+"'>"+ob.lists[i].app_date+"</a></td>"
		     					+ "<td>"+ob.lists[i].coursename+"</td>"
		     					+ "<td>"+ob.lists[i].stm+"</td>"
		     			+ "</tr>";
				}
				html += htmlInvlud;
				html += "</table>";
				
				$("#list").html(html);
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
        </div>
      </div>
    </div>
  </div>
<%@include file="./include/footer.jsp" %>
</body>
</html>