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

<input type="button" value="▼빼기">
<input type="button" value="▲추가" onclick="subadd()">




<form action="">
<table>
	<tr>
		<th>과목이름</th>
		<th>과목타입</th>
	</tr>
	<c:forEach items="${listss }" var="dto">
		<tr>
			<td>
			<div>
			<input type="checkbox"  name="chk" id="chk" value="${dto.subjectcode }">${dto.subjectname}
			<input type="hidden" name="chname" id="chname" value="${dto.subjectname}">
			<input type="hidden" name="subname" id="subname" value="${dto.subjectcode }">
			</div>
			</td>
			<td>${dto.subjecttype}</td>
		</tr>
	</c:forEach>
</table>
<!-- <input type="submit" value="보내기"> -->
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

<div id="myModal2" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">상세 등록</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
		<form action="#" role="form" method="post" id="frmModify1"></form>
      </div>
    </div>
  </div>
</div>
<label></label>
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
	var coursecode = "${dto.coursecode}";
	var frm=document.getElementById("frmModify");
	var name=document.getElementById("moname").value;
	var type=document.getElementById("motype").value;
	var exam=document.getElementById("typeselect").value;
	if(name==""||type==""){
		alert("모든 항목을 입력해주세요.");
		}else{
			location.href="./subject_add.do?subjectname="+name+"&subjecttype="+type+"&examtype="+exam+"&coursecode="+coursecode;
		}
	}
	
function subadd(){
	var items = [];
	$('input:checkbox[name="chk"]:checked').each(function () {
	    items.push($(this).val());
// 	    alert(items);
		
	});
		location.href="./submit.do?subjectcode="+items;
		alert(items)
}
	

// 	function subadd(){
// 		var checkArr=[];//
		
// 		$("input[name='chk']:checked").each(function(i)){
// 			checkArr.push($(this).val());
// 		}
// 	}
	
	
// 	function subadd() {
// 		var chk = document.getElementsByName("chk"); // 체크박스객체를 담는다
// 		var len = chk.length;    //체크박스의 전체 개수
// 		var checkRow = '';      //체크된 체크박스의 value를 담기위한 변수
// 		var checkCnt = 0;        //체크된 체크박스의 개수
// 		var checkLast = '';      //체크된 체크박스 중 마지막 체크박스의 인덱스를 담기위한 변수
// 		var rowid = '';             //체크된 체크박스의 모든 value 값을 담는다
// 		var cnt = 0;                 

// 		for(var i=0; i<len; i++){
// 		if(chk[i].checked == true){
// 		checkCnt++;        //체크된 체크박스의 개수
// 		checkLast = i;     //체크된 체크박스의 인덱스
// 		}
// 		} 

// 		for(var i=0; i<len; i++){
// 		if(chk[i].checked == true){  //체크가 되어있는 값 구분
// 		checkRow = chk[i].value;

// 		if(checkCnt == 1){                            //체크된 체크박스의 개수가 한 개 일때,
// 		rowid += "'"+checkRow+"'";        //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
// 		}else{                                            //체크된 체크박스의 개수가 여러 개 일때,
// 		if(i == checkLast){                     //체크된 체크박스 중 마지막 체크박스일 때,
// 		rowid += "'"+checkRow+"'";  //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
// 		}else{
// 		rowid += "'"+checkRow+"',";	 //'value',의 형태 (뒤에 ,(콤마)가 붙게)         			
// 		}
// 		}
// 		cnt++;
// 		checkRow = '';    //checkRow초기화.
// 		}
// 		alert(rowid);    //'value1', 'value2', 'value3' 의 형태로 출력된다.
// 		}



	
// 	var chks =  document.getElementsByName("chk");
// 	var subcode=$(item).attr("value1");
// 	var subname=$(item).attr("value2");
// 	alert(subname);
// 	   var c = 0;
// 	   for (var i = 0; i < chks.length; i++) {
// 	      if(chks[i].checked){
// 	        alert(chks[i].value1);
// 	    	  c++;
// 	      }
// 	   }
// }




// var htmltest ="";

// 	var chks = $("input[name=chk]:checked").each(function(index, item){
// 		var subcode=$(item).attr("value1");
// 		var subname=$(item).attr("value2");
		
// 		htmltest += "<label>"+subname+"</label>"+
// 		"<input type='text' name='"+subcode+"' id='subnam' required='required'><input type='text'>"+
// 		"</div>";
// 	});
	
// 	var htmlModal=
// 		"<div class='form-group'>"+
// 		htmltest +
// 		"<div class='modal-footer'>"+
// 		"<input class='btn btn-success' type='button' value='추가하기' onclick='textsub()'>"+
// 		"<button type='button' class='btn btn-default' data-dismiss='modal'>취소</button>"+
// 		"</div>";
	
// 		$("#frmModify1").html(htmlModal);
// 		$("#myModal2").modal();
// }
// 			alert(""htmltest)
// 			var htmlModal=
// 				"<div class='form-group'>"+ htmltest +
// 				"<div class='modal-footer'>"+
// 				"<input class='btn btn-success' type='button' value='추가하기'>"+
// 				"<button type='button' class='btn btn-default' data-dismiss='modal'>취소</button>"+
// 				"</div>";
// 				$("#frmModify1").html(htmlModal);
// 		}
// 	});
// }
// function textsub(){
// 	var dk=document.getElementById("subnam").value;
// 	alert(dk);
// }

</script>

</body>
</html>