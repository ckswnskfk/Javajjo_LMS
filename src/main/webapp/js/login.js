function login(){
	var frm = document.getElementsByTagName("form")[0];
	var id = document.getElementById("id").value;
	var pw = document.getElementById("pw").value;
	if(document.getElementsByName("tableChk")[0].checked == true){
		$.ajax({
			url: "./student_join_check.do",
			data:{"id" : id, "pw":pw},
			type:"POST",
			success:function(obj){
				if(obj == "N"){
					alert("회원가입 승인 대기중 입니다.");
					
				}else{
					frm.action = "./login.do";
					frm.submit();
				}
			}
		});
		
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