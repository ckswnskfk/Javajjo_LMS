

function student_modify(){
	location.href="student_Modify_Form.do";
}

function stu_mod(){
	var frm = document.getElementsByTagName("form")[0];
	var pw = document.getElementById("pw").value;
	if(!isNaN(pw) || !pwCheck()){
		$("#err_pw").css("color","red");
		document.getElementById("err_pw").innerHTML="8~16자의 영문 혹은 숫자 영문을 혼합하여 입력해 주세요"
		return false;
	}else{
		$("#err_pw").css("color","blue");
		document.getElementById("err_pw").innerHTML="사용가능한 비밀번호 입니다.";
	}
	if(!nameCheck()){
		$("#err_name").css("color","red");
		document.getElementById("err_name").innerHTML="이름에는 공백이나 숫자를 사용할 수 없습니다."
		return false;
	}else{
		$("#err_name").css("color","blue");
		document.getElementById("err_name").innerHTML="사용가능한 이름 입니다.";
	}
	frm.action = "student_modify.do";
	frm.method = "post";
	alert("회원 정보 수정이 완료되었습니다.");
	return true;
}

function pwCheck(){
	var re = /^[a-zA-Z0-9]{8,16}$/;
	var pw = document.getElementById("pw");
	 if((pw.value).match(re)) {
           return true; // 유효성에 맞는아이디
       }
//	       alert(message);
       return false; // 유효성에 맞지않다
}

function nameCheck(){
	var re = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,12}$/;
	var name = document.getElementById("name");
	
	 if((name.value).match(re)) {
           return true; // 유효성에 맞는아이디
       }
//	       alert(message);
       return false; // 유효성에 맞지않다
}

function course(){
	
}
