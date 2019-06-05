<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='./css/cormain.css' rel='stylesheet' />
<link href='./css/daymain.css' rel='stylesheet' />
<script src='./js/cormain.js'></script>
<script src='./js/intmain.js'></script>
<script src='./js/daymain.js'></script>
<script type="text/javascript" src="./js/calendar.js"></script>
<script type="text/javascript">


var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();

if(dd<10) {
    dd='0'+dd
} 

if(mm<10) {
    mm='0'+mm
} 


today = yyyy+"-"+mm+"-"+dd;
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid' ],
      defaultDate: today,
      editable: true,
      eventLimit: true,
      events:room_event(),
      dateClick: function(info) {
    	  room_emptyboardlist(info.dateStr);
  	  }
    });
    calendar.render();
  });

function room_event(){
	var event = new Object();
	$.ajax({
		url : "./room_event.do",
		type : "post",
		async: false,
		success :function(obj){
			var list = JSON.parse(obj);
			event = list;
		},error : function(){
			alert("no");
		}
	});
	return event.lists;
}
// [0,{
//     title: 'All Day Event',
//     start: '2019-04-01'
//   },
//   {
//     title: 'Long Event',
//     start: '2019-04-07',
//     end: '2019-04-10'
//   },
//   {
// 	]
</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>
</head>
<body>
<%@ include file="./include/header.jsp" %>
<div class="container">
 <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">강의실 목록</button>
 <input type="hidden" id="id" value="${member.id}">
 
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">강의실 목록</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <c:forEach items="${lists}" var="dto" varStatus="vs">
        <div class="modal-body">
          <p>
          	<span>No : ${vs.count}</span><span> 수용인원 : ${dto.personel}</span>
          </p>
          <p>
          	<span>강의실 이름 : ${dto.name}</span>
          </p>
          <hr>
        </div>
        </c:forEach>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  

  <div id='calendar' style="width: 700px;"></div>
  <c:choose>
  	<c:when test="${member.table eq 'Admin'}">
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal2">강의실 추가</button>
	</c:when>
	</c:choose>
<!-- Modal -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">강의실 추가</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <form action="#">
        <div class="modal-body">
          <table>
          	<tr>
          		<th>강의실 이름 : </th>
          		<td>
          			<input type="text" name="name">
          		</td>
          	</tr>
          	<tr>
          		<th>수용 인원 : </th>
          		<td>
          			<input type="text" name="personel">
          		</td>
          	</tr>
          </table>
       </div>
       </form>
       <div class="modal-footer">
          <button type="button" class="btn btn-info" onclick="room_add()">추가</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
 </div>
</div>
<%@ include file="./include/footer.jsp" %>
</body>
</html>
