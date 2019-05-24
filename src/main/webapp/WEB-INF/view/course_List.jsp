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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>

</head>
<body>
<%@include file="./include/header.jsp" %>
<form action="">
<table>
	<tr>
		<td>과정 이름</td>
		<td>시작 날짜</td>
		<td>회차</td>
	</tr>
<%


%>
<c:forEach items="${list}" var="dto">
	<tr>
		<td>
			<input type="radio" name="radio" value="${dto.coursename }"> ${dto.coursename }
		</td>
		<td>
		<fmt:parseDate value="${dto.startdate}" pattern="yyyy-MM-dd HH:mm:ss" var="Stringdate"/>
		<fmt:formatDate value="${Stringdate}" pattern="yyyy-MM-dd"/>
		</td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;${dto.coursecnt }</td>
	</tr>
</c:forEach>
</table>
<input type="date" id="date">
</form>
	<button type="button" name="button" id="readioButton"  onclick="check(this.checked)">과정횟수증가</button>
	<input type="button" value="과정등록" onclick="location.href='./move.do'">


<%@include file="./include/footer.jsp" %>



<script type="text/javascript">
	function check(bool){
		var chks=document.querySelector('input[name="radio"]:checked').value;
		var date=document.querySelector('input[id="date"]').value;
// 		alert(date);
// 		alert(chks);
		location.href="./course_cnt.do?coursename="+chks+"&startdate="+date
	}

</script>
</body>
</html>