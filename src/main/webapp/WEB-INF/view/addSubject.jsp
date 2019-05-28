<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="./include/header.jsp" %>
<table>
	<tr>
		<th>과목이름</th>
		<th>과목타입</th>
	</tr>
	<c:forEach items="${lists }" var="dto">
		<tr>
			<td>
			<input type="checkbox" name="chk1" value="${dto.subjectcode}">${dto.subjectname}
			</td>
			<td>${dto.subjecttype}</td>
		</tr>
	</c:forEach>
</table>

<form action="">
<table>
	<tr>
		<th>과목이름</th>
		<th>과목타입</th>
	</tr>
	<c:forEach items="${listss }" var="dto">
		<tr>
			<td>
			<input type="checkbox" name="chk" onclick="ccc(this.checked)" value="${dto.subjectcode}">${dto.subjectname}
			</td>
			<td>${dto.subjecttype}</td>
		</tr>
	</c:forEach>
</table>
</form>
<input type="button" value="과목 등록" onclick="momo()">


<div id="myModal1" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">신규 과목 등록</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
		<form action="#" role="form" method="post" id="frmModify"></form>
      </div>
    </div>
  </div>
</div>
<%@include file="./include/footer.jsp" %>


<script type="text/javascript">
function momo(){
	ajaxSubadd();
	$("#myModal1").modal();
}
var ajaxSubadd=function(){
	$.ajax({
		url:"./add.do",
		type:"get",
		success:function(){
// 			alert("fff");
			var htmlModal=
				"<div class='form-group'>"+
				"<label>과목명 :&nbsp;&nbsp;</label>"+
				"<input type='text' name='subject' id='moname' required='required'>"+
				"</div>"+
				"<div class='form-group'>"+
				"<label>과목타입 :&nbsp;&nbsp;</label>"+
				"<input type='text' name='subjecttype' id='motype' required='required'>"+
				"</div>"+
				"<div class='form-group'>"+
				"<label>시험유형 :&nbsp;&nbsp;</label>"+
				"<select name='typeselect' id='typeselect'>"+
				"<option>서술형</option>"+
				"<option>선택형</option>"+
				"</select>"+
				"</div>"+
				"<div class='modal-footer'>"+
				"<input class='btn btn-success' type='button' value='과목등록' onclick='update()'>"+
				"<button type='button' class='btn btn-default' data-dismiss='modal'>취소</button>"+
				"</div>";
				$("#frmModify").html(htmlModal);
		}
	});
}
function update(){
	
	var frm=document.getElementById("frmModify");
	var name=document.getElementById("moname").value;
	var type=document.getElementById("motype").value;
	var exam=document.getElementById("typeselect").value;
	if(name==""||type==""){
		alert("모든 항목을 입력해주세요.");
		}else{
			location.href="./subject_add.do?subjectname="+name+"&subjecttype="+type+"&examtype="+exam
		}
	}


// 	function ccc(bool){
// // 		var chks=document.querySelector('input[name="chk"]:checked');
// 		var chks=document.getElementsByName("chk");
// 		for(var i = 0; i < chks.length; i++){
// 			if(chks[i].checked){
				
// 			}
// 		}
		
		
		alert(typeof chks);
		
	}
	


</script>
</body>
</html>