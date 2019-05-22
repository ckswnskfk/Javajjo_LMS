<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석부 조회</title>
</head>
<body>
<%@include file="./include/header.jsp" %>
<a>${lists}</a>
<script type="text/javascript">
function sms(){
	var id = document.getElementById("inputId").value;
	var a_check = document.getElementById("inputA_check").value;
	alert(id+":"+a_check);
	
	
	if(a_check==null){
// 		location.href="/attended_SMS.do";
		var frm = document.forms[0];
		frm.action="./attended_SMS.do";
		var result = "";
		alert("문자발송 성공");
		
	
	}
	
}

</script>
	<input type="button" value="결석문자발송" onclick="sms()">


<a href="./attended_Detail.do?id=01011111111">출결 상세보기</a>


<%@include file="./include/footer.jsp" %>
</body>
</html>