<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>강사 캘린더 조회</title>
  
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>  
<script type="text/javascript" src="https://uicdn.toast.com/tui.code-snippet/latest/tui-code-snippet.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chance/1.0.13/chance.min.js"></script>
<script src="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.js"></script>

<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.css" />
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css">
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css">



</head>
<body>
<%@include file="./include/header.jsp" %>


<h1>강사 캘린더</h1>
<br>


<p>과정명</p>
<p>${cdto.coursename}</p>

<div id="calendar" style="height: 800px;"></div>	
	
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
			  useCreationPopup: true,
			  useDetailPopup: true
			});					
		
		
		// 스케쥴 생성
		calendar.on('beforeCreateSchedule',function(schedule){		
			
			 const result = {
					    calendarId: 'Major Lecture',
					    id: String(Math.random() * 100000000000000000),
					    title: schedule.title,
					    isAllDay: schedule.isAllDay,
					    start: schedule.start,
					    end: schedule.end,
					    category: schedule.isAllDay ? 'allday' : 'time'
			 };
			 calendar.createSchedules([result]);
			
			alert(schedule.title);
			
			//location.href="./insert.do?title="+schedule.title;
		});
		
		// 애가 기본 수정 
		calendar.on('beforeUpdateSchedule', function(scheduleData) {							
			  const {schedule} = scheduleData;
			   alert(schedule.title);
			  calendar.updateSchedule(schedule.id, schedule.calendarId, {start:schedule.start,end:schedule.end});
		});
		// 앤 모르겠다 
	 	calendar.on('clickSchedule', function(event) {
		       var schedule = event.schedule;
		     
		          if (lastClickSchedule) {
		              calendar.updateSchedule(lastClickSchedule.id, lastClickSchedule.calendarId, {
		                  isFocused: false
		              });
		          }
		          calendar.updateSchedule(schedule.id, schedule.calendarId, {
		             isFocused: true
		          });
		     
		         lastClickSchedule = schedule;
		         // open detail view
		     });
	 	
	 	// 마우스 드래그시 
	 	calendar.on('beforeUpdateSchedule', function(event) {
	 	         var schedule = event.schedule;
	 	         var startTime = event.start;
	 	         var endTime = event.end;
	 	    
	 	         calendar.updateSchedule(schedule.id, schedule.calendarId, {
	 	             start: startTime,
	 	             end: endTime
	 	         });
	 	     });
		 
	</script>







<div>


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
<%-- <p>${lists}</p> --%>

<!-- <a href="./attended_Teacher.do?coursecode=C201900001&regdate=201905">강사 캘린더 조회</a> -->
<a href="./attended_Teacher.do?coursecode=${Course_DTO.coursecode}&regdate=${regdate}">강사 캘린더 조회</a>
<a href="./attended_Rollbook.do?coursecode=${Course_DTO.coursecode}&regdate=20190517">출석부 조회</a>


<%@include file="./include/footer.jsp" %>
</body>
</html>