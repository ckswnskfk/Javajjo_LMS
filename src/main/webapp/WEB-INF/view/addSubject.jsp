<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="./include/header2.jsp" %>
<form action="#" method="post" id="formgo" onsubmit="return check()" >
<b style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---in Course---</b>
<table>
	<tr>
		<th>과목이름</th>
		<th>과목타입</th>
		<th>수업 시간</th>
		<th>시작 날짜</th>
	</tr>
	<c:forEach items="${lists}" var="dto">
	
		<tr>
			<td>
				<input type="checkbox" name="seq" value="${dto.seq}">${dto.subjectname}
			</td>
			<td>${dto.subjecttype}</td>

			<td>${dto.subjecttime}</td>
			
			<td>
			<fmt:parseDate value="${dto.startdate}" pattern="yyyy-MM-dd HH:mm:ss" var="Stringdate"/>
			<fmt:formatDate value="${Stringdate}" pattern="yyyy-MM-dd"/>
			</td>

			<td><input type="hidden" id="inin" value="${dto.subjectcode}"></td>

		</tr>
	</c:forEach>

</table>
<div>
<input type="submit" value="▼빼기" >
</div>
</form>
<input type="button" value="▲추가" onclick="subadd()">




<form action="./subDel.do" method="post">
<b style="color: blue;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---All Subject---</b>
<table>
	<tr>
		<th>과목이름</th>
		<th>과목타입</th>
	</tr>
	<c:forEach items="${listss }" var="dto">
		<tr>
			<td id="soso">
			<div id="soso">
			<input type="checkbox"  name="subjectcode" id="chk" value="${dto.subjectcode }">${dto.subjectname}
			</div>
			</td>
			<td>${dto.subjecttype}</td>
			
		</tr>
	</c:forEach>
</table>
<input type="button" value="과목삭제">
</form>
<input type="button" value="과목 등록" onclick="momo()">

<c:if test="${lists[0] eq null}">

<input type="button" value="복사하기" onclick="location.href='./copySelect.do?coursename=${coursename}'">
</c:if>


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
	var itemtest = items.join();
	$('input:checkbox[name="subjectcode"]:checked').each(function () {
	    items.push($(this).val());
	});
	var soso = [];
	$('input:hidden[id="inin"]').each(function () {
	    soso.push($(this).val());
	});
	var sosotest = soso.join();
		var count = 0;
		for (var i = 0; i < items.length; i++) {
			if (sosotest.indexOf(items[i]) > -1) {
				count++;
			}
		}
		if (count >= 1) {
			alert("이미 존재하는 과목이 있습니다.");
		}else if(items==""){
			alert("하나이상의 과목을 선택해주십시오.");
		}
		else {
			location.href = "./submit.do?subjectcode=" + items;
		}
	}
	
function check(){
	var items=[];
	$('input:checkbox[name="seq"]:checked').each(function(){
		items.push($(this).val());
	});
	if(items==""){
		alert("하나이상 선택해주십시오.");
		return false;
	}else{
		var doc=document.getElementById("formgo");
		doc.action="./cosubDel.do"
		return true;
	}
}
</script>
</body>
</html>