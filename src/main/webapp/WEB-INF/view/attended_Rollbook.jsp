<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

body {
	 margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }


	table,td {
		text-align: center;
		max-width: 1000px;
		margin: 0 auto;
			border: 1px solid blue;
		border-collapse: collapse;
	}
	
	
	
	
	#rollbookform{
		margin: auto;
		display: inline;
		 border: 1px solid blue;
		border-collapse: collapse;
		
	}

	#back{
		margin-top: 15px;
		margin-left: 250px;
		
	}
</style>

<title>출석부 조회</title>

</head>





<%
	String chrome = (String)request.getAttribute("Chrome");
	String ie = (String)request.getAttribute("IE");
	String safiri = (String)request.getAttribute("Safiri");
	String opera = (String)request.getAttribute("Opera");
	String firefox = (String)request.getAttribute("Firefox");
	String etc = (String)request.getAttribute("Etc");
	
// 	List<ToastVisitDTO> vlists = (List<ToastVisitDTO>)request.getAttribute("vlists");
	
// 	String[]  weeklyCnt = (String[])request.getAttribute("weeklyCnt");
%>


<body>
	<%@include file="./include/header.jsp"%>


<script type="text/javascript">


	function attDetail(id) {
		var url = "./attended_Detail.do?id="+id;
		var name = "attended detail";
		var option = "width = 900,height = 500, top = 100, left = 200";
		window.open(url,name,option);
	}
	</script>
	<script type="text/javascript">
	function attChart(){
// 		var date = new Date();
		var container = document.getElementById('piechart-area');
		var data = {
			    categories: ['Browser'], // 상세정보의 타이틀
			    series: [ // 데이터 입력
			        {
			            name: 'Chrome', // 원형 차트의 각 세부 조각
			            data: <%=chrome%> // 각 세부 조각마다 입력되는 데이터
			        },
			        {
			            name: 'IE',
			            data: <%=ie%>
			        },
			        {
			            name: 'Firefox',
			            data:  <%=firefox%>
			        },
			        {
			            name: 'Safari',
			            data: <%=safiri%>
			        },
			        {
			            name: 'Opera',
			            data: <%=opera%>
			        },
			        {
			            name: 'Etc',
			            data: <%=etc%>
			        }
			    ]
			};
			var options = {
			    chart: { // 원형 차트 크기 조절
			        width: 550,
			        height: 450,
			        title: '브라우저별 접속 현황' // 차트 제목
			    },
			    tooltip: {
			        suffix: '명' //사용단위
			    },
			    series: {
			        showLegend: true,
			        showLabel: true,
			        labelAlign: 'center'
			    },
			    
			};
			var theme = {
					xAxis: {
				        title: { // 차트 제목의 css
				            fontSize: 14,
				            fontFamily: 'Verdana',
				            fontWeight: 'bold',
				            color: 'blue'
				        }
				    }
			};

			// For apply theme

			tui.chart.registerTheme('newTheme', theme);
			// options.theme = 'myTheme';

			tui.chart.pieChart(container, data, options); // 차트 실행	
			
			
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		
		if ((day+"").length < 2) {
			day = "0"+day;
		}
		
		var getToday = year+"-"+month+"-"+day; // 오늘 날짜 
		
		
		
		
// 		getToday - ${courseDTO.startdate};
		
		
		
		var theme = { // 차트 테마
			    series: {
			        colors: [ // 차트 색깔 지정
			            '#83b14e', '#458a3f'
			        ]
			    }
			};
			// For apply theme // 차트의 테마를 적용
			// tui.chart.registerTheme('myTheme', theme); // 테마의 이름을 입력
			// options.theme = 'myTheme';
			tui.chart.columnChart(container, data, options); // 차트를 생성

		
		
	}


</script>
			<div id="back">
				<input class="btn btn-sm btn-primary btn-center" type="button" value="돌아가기" onclick="history.back(-1)">
			</div>

	<div id="rollbook">
		<form action="#" method="get" id="rollbookform">
			<table class="table table-bordered">
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>출결(Y : 입실 , N : 퇴실)</th>
					<th>시간</th>
				
				</tr>
				<c:forEach var="AttendedDTO" items="${dlists}">
					<tr>
						<td><a onclick="attDetail('${AttendedDTO.id}')">${AttendedDTO.id}</a></td>
						<td>${AttendedDTO.name}</td>
						<c:choose>
						<c:when test="${AttendedDTO.a_check eq null}">
						<td>결석</td>
						</c:when>
						<c:otherwise>
						<td>${AttendedDTO.a_check}</td>
						</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${AttendedDTO.regdate eq null}">
								<td><input class="btn btn-sm btn-primary"  value="결석문자발송" onclick="sms('${AttendedDTO.id}','${regdate}')"></td>
							</c:when>
							<c:otherwise>
								<td>${AttendedDTO.regdate}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>


	<script type="text/javascript">
		function sms(id,regdate) {
			location.href = "./attended_SMS.do?id="+id+"&regdate="+regdate;
			alert("성공");
		}
	</script>

		


	<%@include file="./include/footer.jsp"%>
</body>
</html>