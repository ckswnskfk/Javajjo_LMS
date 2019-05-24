<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="./include/header.jsp"%>
	<div>
		과정명 :&nbsp;&nbsp;<input type="text" name="course" ><br> 시작날짜
		:&nbsp;&nbsp;<input type="date" id="date">
	</div>

	<div>
		<input type="button" value="과정등록" id="course_cho" onclick="course_cho()">
		 <input type="button" value="취소" onclick="location.href='./course_List.do'">
	</div>
	<%@include file="./include/footer.jsp"%>
	
	<script type="text/javascript">
		function course_cho(){
			var course=document.querySelector('input[name="course"]').value;
			var date=document.querySelector('input[id="date"]').value;
// 			alert(course);
// 			alert(date);
			location.href="./course_add.do?coursename="+course+"&startdate="+date
		}
	</script>
</body>
</html>