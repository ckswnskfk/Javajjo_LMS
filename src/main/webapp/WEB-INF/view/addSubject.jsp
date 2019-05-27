<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>
<title>Insert title here</title>
</head>
<body>
<%@include file="./include/header.jsp" %>
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
<button onclick="location.href='./add.do'">과정등록</button>

<%@include file="./include/footer.jsp" %>

</body>
<script type="text/javascript">
	function ccc(bool){
// 		var chks=document.querySelector('input[name="chk"]:checked');
		var chks=document.getElementsByName("chk");
		for(var i = 0; i < chks.length; i++){
			if(chks[i].checked){
				
			}
		}
		
		
		alert(typeof chks);
		
	}
	


</script>
</html>