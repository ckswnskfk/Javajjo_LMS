

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
	location.href="./room_empty_request.do?code="+code+"&regdate="+regdate+"&id="+id;
}

function room_empty_cancle(code,regdate,id){
	alert("예약취소가 완료되었습니다.");
	location.href="./room_empty_cancle.do?code="+code+"&regdate="+regdate+"&id="+id;
}