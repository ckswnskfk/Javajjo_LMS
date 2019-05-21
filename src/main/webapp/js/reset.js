var ajaxSuc;
function idduplicate(id,name){
	$.ajax({
		url : "./student_pwre.do",
		type : "post",
		async : false,
		data : {"id":id,"name":name},
		success : function(msg){
			ajaxSuc = msg;
		}
	})
}

function pwreset(){
	var frm = document.getElementsByTagName("form")[0];
	var id = document.getElementById("id").value;
	var name = document.getElementById("name").value;
	var con = confirm("입력하신 정보가 맞습니까?\nid : "+id+"\n이름 : "+name);
	idduplicate(id, name);
	if(con){
		if(ajaxSuc){ // 아이디가 존재할 경우 true
			frm.action="./password_Reset.do";
			frm.method="post";
			return true;
		}else{
			alert("정보가 올바르지 않습니다.");
			return false;
		}
	}else{
		return false;
	}
}

