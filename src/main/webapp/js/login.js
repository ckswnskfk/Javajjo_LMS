function login(){
	var frm = document.getElementsByTagName("form")[0];
	if(document.getElementsByName("tableChk")[0].checked == true){
		frm.action = "./login.do";
		frm.submit();
	}else if(document.getElementsByName("tableChk")[1].checked == true){
		frm.action = "./teacher_login.do";
		frm.submit();
	}else if(document.getElementsByName("tableChk")[2].checked == true){
		frm.action = "./admin_login.do";
		frm.submit();
	}
}
function regiForm(){
	location.href="./student_joinForm.do";
}

function pwReset(){
	location.href="./student_pwReset.do";
}