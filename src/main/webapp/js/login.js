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
					
				}else if(obj == "Y"){
					frm.action = "./login.do";
					frm.submit();
				}else{
					alert("회원 정보가 없습니다.");
				}
			}
		});
		
	}else if(document.getElementsByName("tableChk")[1].checked == true){
		$.ajax({
			url: "./teacher_login_check.do",
			data:{"id" : id, "pw":pw},
			type:"POST",
			success:function(obj){
				if(obj == "Y"){
					frm.action = "./teacher_login.do";
					frm.submit();
				}else{
					alert("회원 정보가 없습니다.");
				}
			}
		});
	}else if(document.getElementsByName("tableChk")[2].checked == true){
		$.ajax({
			url: "./admin_login_check.do",
			data:{"id" : id, "pw":pw},
			type:"POST",
			success:function(obj){
				if(obj == "Y"){
					frm.action = "./admin_login.do";
					frm.submit();
				}else{
					alert("회원 정보가 없습니다.");
				}
			}
		});
		
	}
}
function regiForm(){
	location.href="./student_joinForm.do";
}

function pwReset(){
	location.href="./student_pwReset.do";
}