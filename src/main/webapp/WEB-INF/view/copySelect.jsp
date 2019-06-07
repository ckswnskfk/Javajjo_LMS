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
<link rel= "stylesheet" type="text/css" href="./css/template.css">
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
</head>
<body>
<div class="content-wrapper">
<%@include file="./include/header.jsp" %>

<div class="container" id="main">
	<div class="row" style="width: 900px; margin: 0 auto;">
		<div class="col-lg-12">
		
<div style="margin-bottom: 30px; margin-top:20px; float: right;">
<select id="coursecnt">
<c:forEach items="${cnt}" var="dto">
	<option id="cntsel" value="${dto.coursecnt}">과정명:${dto.coursename} (회차:${dto.coursecnt})</option>
</c:forEach>
</select>
<input type="button" class="btn btn-outline-primary" value="조회" onclick="cntsel()"/>
</div>
	<input type="hidden" id="coname" value="${cnt[0].coursename}">
	 <div id="list">
<table class="table">
	<tr>
		<th>과목명</th>
		<th>수업시간</th>
		<th>과목타입</th>
	</tr>
	<c:forEach items="${slist}" var="sto">
		<tr>
			<td><input type="checkbox" id="chk" name="chk" value="${sto.seq}">${sto.subjectname}</td>
			<td>${sto.subjecttime}</td>
			<td>${sto.subjecttype}</td>
		</tr>
	</c:forEach>
	</table>
	</div>
	<div style="float: right;">
	<input class="btn btn-outline-primary" type="button" value="복사하기" onclick="copy()">
	</div>
</div>
</div>
</div>
<%@include file="./include/footer.jsp" %>
</div>
<script type="text/javascript">
function cntsel(){
	var cnt=document.getElementById("coursecnt").value;
	var name=document.getElementById("coname").value;
	
// 	alert(cnt);
// 	alert(name);
	
	location.href="./cntsel.do?coursename="+name+"&coursecnt="+cnt;

}
function copy(){
	var items=[];
	$('input:checkbox[name="chk"]:checked').each(function(){
		items.push($(this).val());
	});
	if(items==""){
	alert("하나이상 선택해주십시오.");	
	}else{
	location.href="./timeset.do?seq="+items;
		
	}
}
</script>
</body>
</html>