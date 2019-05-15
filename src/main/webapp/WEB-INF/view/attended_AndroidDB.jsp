<%@page import="happy.jaj.prj.com.db.Attended_ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//////////////////
//테스트용 파일입니다./// 
//////////////////
	Attended_ConnectDB connectDB = Attended_ConnectDB.getInstance();
	
   String id = request.getParameter("id");
   String pw = request.getParameter("pw");
	
   String returns = connectDB.connectionDB(id, pw);

   System.out.println(returns);

   // 안드로이드로 전송
   out.println(returns);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>