<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%
    request.setCharacterEncoding("UTF-8");
 	response.setContentType("text/html; charset=UTF-8");
%>   
    
<%-- <%@page import="net.sf.json.JSONArray"%> --%>
<%@page import="java.util.ArrayList"%>
<%@page import="happy.jaj.prj.dtos.Attended_DTO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    
  <%	
 	List<Attended_DTO> lists = (List<Attended_DTO>)request.getAttribute("lists");
%>    





  <link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.css" />
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">


<title>강사 캘린더 조회</title>



</head>
<body>


 <%@include file="./include/header.jsp" %>
 
 <script type="text/javascript" src="https://uicdn.toast.com/tui.code-snippet/latest/tui-code-snippet.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>
<script src="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.js"></script>



 
	<h1>과정명 : ${cdto.coursename}</h1>	
	
	<div id="menu">
		  <p id="now"></p>		  
	      <button id="prev">◀</button>
	      <button id="next">▶</button>      
	    </div>
	    
	<div id="calendar" style="height: 800px;" style="weight: 800px;"></div>	
	
		<script type="text/javascript">
	
		var Calendar = tui.Calendar;
		
		const templates = {
			    popupIsAllDay: function() {
			      return 'All Day';
			    },		
			    popupStateFree: function() {
			        return 'Free';
			      },
			      popupStateBusy: function() {
			        return 'Busy';
			      },
			    titlePlaceholder: function() {
			      return 'Subject';
			    },
			    locationPlaceholder: function() {
			      return 'Location';
			    },
			    startDatePlaceholder: function() {
			      return 'Start date';
			    },
			    endDatePlaceholder: function() {
			      return 'End date';
			    },
			    popupSave: function() {			    	
			      return 'Save';
			    },
			    popupUpdate: function() {
			      return 'Update';
			    },
			    popupDetailDate: function(isAllDay, start, end) {
			      var isSameDate = moment(start).isSame(end);
			      var endFormat = (isSameDate ? '' : 'YYYY.MM.DD ') + 'hh:mm a';

			      if (isAllDay) {
			        return moment(start).format('YYYY.MM.DD') + (isSameDate ? '' : ' - ' + moment(end).format('YYYY.MM.DD'));
			      }

			      return (moment(start).format('YYYY.MM.DD hh:mm a') + ' - ' + moment(end).format(endFormat));
			    },
			    popupDetailLocation: function(schedule) {
			      return 'Location : ' + schedule.location;
			    },
			    popupDetailUser: function(schedule) {
			      return 'User : ' + (schedule.attendees || []).join(', ');
			    },
			    popupDetailState: function(schedule) {
			      return 'State : ' + schedule.state || 'Busy';
			    },
			    popupDetailRepeat: function(schedule) {
			      return 'Repeat : ' + schedule.recurrenceRule;
			    },
			    popupDetailBody: function(schedule) {
			      return 'Body : ' + schedule.body;
			    },
			    popupEdit: function() {
			      return 'Edit';
			    },
			    popupDelete: function() {
			      return 'Delete';
			    }
			  };			
				
		var calendar = new Calendar('#calendar', {
			  defaultView: 'month',		 
			  template: templates,
			  useCreationPopup: false,
			  useDetailPopup: true
			});					
		
		
		// 스케쥴 생성
		calendar.on('beforeCreateSchedule',function(schedule){
			var date = schedule.start;
			var month = date.getMonth()+1+"";
			if(month.length == 1){
				month = "0"+month;
			}
			var day = ""+date.getDate();
			if(day.length == 1){
				day = "0"+day;
			}
			var result = date.getFullYear()+month+day;

			alert(result);
			location.href="attended_Rollbook.do?regdate="+result;
		});		
	 	
	 	$(document).ready(function (){
			// 달 이동 
		 	$("#prev").click(function() {
		 		calendar.move(-1);
		 		calendar.render();
		 		$("#now").html("<b>"+(calendar.getDate().getFullYear())+"년 "+(calendar.getDate().getMonth()+1)+"월</b>");
			});	 	
		 	
		 	$("#next").click(function() {
		 		calendar.move(1);
		 		calendar.render();
		 		$("#now").html("<b>"+(calendar.getDate().getFullYear())+"년 "+(calendar.getDate().getMonth()+1)+"월</b>");
			});	 	
	 	});
		 
	</script>


<script type="text/javascript">
	
	

</script>
<div>

<div>
		

</div>

	


		<form action="#" method="get" id="rollbook">
			<table>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>출결</th>
					<th>시간</th>

					<!-- 		<th>문자발송</th> -->
				</tr>
				<c:forEach var="AttendedDTO" items="${list}">
					<tr>
						<td><a href="./attended_Detail.do?id=${member.id}">${member.id}</a></td>
						<td>${AttendedDTO.name}</td>
						<td>${AttendedDTO.regdate}</td>
						<td>${AttendedDTO.a_check}</td>
						<td>${AttendedDTO.seq}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>



<%@include file="./include/footer.jsp" %>
</body>
</html>