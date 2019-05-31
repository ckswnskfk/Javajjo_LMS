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
      events: [
          {
            
          }
        ],
      dateClick: function(info) {
    	  room_emptyboardlist(info.dateStr);
  	  }
    });

    calendar.render();
  });
  
function room_add(){
	var frm = document.getElementsByTagName("form")[0];
	frm.action="./room_add.do";
	frm.method="post";
	frm.submit();
}

function room_emptyboardlist(msg){
	var id = document.getElementById("id").value;
	var year = msg.substr(0,4);
	var month = msg.substr(5,2);
	var day = msg.substr(8,2);
	var regdate = year+month+day
	location.href="./room_emptyboardlist.do?regdate="+regdate+"&id="+id;
}

function room_empty_request(code,regdate,id){
	alert("예약이 완료되었습니다.");
	location.href="./room_empty_request.do?code="+code+"&regdate="+regdate+"&id=0"+id;
}

function room_empty_cancle(code,regdate,id){
	alert("예약취소가 완료되었습니다.");
	location.href="./room_empty_cancle.do?code="+code+"&regdate="+regdate+"&id=0"+id;
}