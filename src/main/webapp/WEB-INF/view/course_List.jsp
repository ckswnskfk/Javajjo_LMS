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
<%@include file="./include/header.jsp" %>

<div class="container" id="main">
	<div class="row" style="width: 900px; margin: 0 auto;">
		<div class="col-lg-12">

<form action="./codeDel.do" method="post">
 <div id="list">
<table class="table">
	<tr>
		<th>과정 이름</th>
		<th>시작 날짜</th>
		<th>회차</th>
		<th>삭제</th>
	</tr>
<c:forEach items="${list}" var="dto">
	<tr>
		<td>
			<input type="radio" name="radio" id="coname" value="${dto.coursename }"> ${dto.coursename }
		</td>
		<td>
		<fmt:parseDate value="${dto.startdate}" pattern="yyyy-MM-dd HH:mm:ss" var="Stringdate"/>
		<fmt:formatDate value="${Stringdate}" pattern="yyyy-MM-dd"/>
		</td>
		<td><div style="text-align: center;">${dto.coursecnt}</div></td>
		<td>&nbsp;&nbsp;&nbsp;<input type="checkbox" name="coursecode" value="${dto.coursecode}"></td>
	</tr>
</c:forEach>
</table>
</div>
<div>
<input type="date" id="date">
</div>
<div style="float: right;">
<input class="btn btn-outline-success" type="submit" value="과정삭제">
<input class="btn btn-outline-success" type="button" value="과정등록" onclick="momo()">
</div>
</form>
<div style="margin-top: 5px;">
	<button class="btn btn-outline-success" type="button" name="button" id="readioButton"  onclick="check(this.checked)">과정회차증가</button>
</div>
</div>

<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">신규 과정 등록</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
		<form action="#" role="form" method="post" id="frmModify"></form>
      </div>
    </div>
  </div>
  	</div>
  </div>
</div>
<%@include file="./include/footer.jsp" %>
<script type="text/javascript">
function nameCheck(){
	var orgname=document.getElementsByName("radio");
	var savename=document.getElementById("motitle").value;
	var cnt = 0;
	for(var i = 0 ; i < orgname.length; i++){
		if(orgname[i].value.trim()==savename.trim()){
			cnt++;
		}
	}
	if(cnt>0){
		return true;
	}else{
		return false;
	}
}

function dateCheck(){
	var mocontent = document.getElementById("mocontent");
	var date = new Date(mocontent.value);
	var today = new Date();
	if(date < today){
		return false;
	}
	return true;
}
	function momo(){
		ajaxCoadd();
		$("#myModal").modal();
	}
	var ajaxCoadd=function(){
// 		alert("asd");
		$.ajax({
			url:"./move.do",
			type:"get",
// 			data:"",
// 			dataType:"json",
			success:function(){
// 				alert("haha");
				var htmlModal=
					"<div class='form-group'>"+
						"<label>과정명 :&nbsp;&nbsp;</label>"+
						"<input type='text' name='course' id='motitle' required='required'>"+
						"</div>"+
						"<div class='form-group'>"+
						"<label>시작날짜 :&nbsp;&nbsp;</label>"+
						"<input type='date' name='date' id='mocontent' required='required'>"+
						"</div>"+
						"<div class='modal-footer'>"+
						"<input class='btn btn-success' type='button' value='과정등록' onclick='update()'>"+
						"<button type='button' class='btn btn-default' data-dismiss='modal'>취소</button>"+
						"</div>";
						$("#frmModify").html(htmlModal);
			}
		});
	}
	function update(){
		var frm=document.getElementById("frmModify");
		frm.action="./course_add.do";
		var title=document.getElementById("motitle").value;	
		var content=$("#mocontent").val();
// 		alert(title+"/"+content);
		if(title==""||content==""){
			alert("모든항목을 입력해주세요.");
		}else if(!dateCheck()){
			alert("오늘 또는 과거에 과정을 생성할 수 없습니다.");
		}else if(nameCheck()){
			alert("같은 이름의 과정은 새로 생성하지 못합니다.");
		}
		else{
			location.href="./course_add.do?coursename="+title+"&startdate="+content;
		}
	}
	function check(bool){
		var chks=document.querySelector('input[name="radio"]:checked').value;
// 		var ddate=document.querySelector('input[id="date"]');
		var mocontent = document.getElementById("date").value;
		var date = new Date(mocontent);
		var today = new Date();
		if(date < today){
			alert("오늘 또는 과거에 과정을 생성할 수 없습니다.");	
		}else if(mocontent==""){
			alert("날짜를 선택 해주세요.")
		}else{
		location.href="./course_cnt.do?coursename="+chks+"&startdate="+mocontent;
			
		}
	}

</script>
</body>
</html>