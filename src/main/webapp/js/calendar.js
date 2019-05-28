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
      dateClick: function(info) {
  	    alert(info.dateStr);
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
