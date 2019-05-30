function join(){
	var frm = document.getElementsByTagName("form")[0];
	
	var id = document.getElementById("id").value;
	var pw = document.getElementById("pw").value;
	var pwRe = document.getElementById("pwRe").value;
	var remember = document.getElementsByName("remember")[0];
	
	if(!isNaN(pw) || !pwCheck()){
		$("#err_pw").css("color","red");
		document.getElementById("err_pw").innerHTML="8~16자의 영문 혹은 숫자 영문을 혼합하여 입력해 주세요"
		return false;
	}else{
		$("#err_pw").css("color","blue");
		document.getElementById("err_pw").innerHTML="사용가능한 비밀번호 입니다.";
	}
	
	if(!pwReCheck()){
		$("#err_pwRe").css("color","red");
		document.getElementById("err_pwRe").innerHTML="비밀번호 불일치"
		return false;
	}else{
		$("#err_pwRe").css("color","blue");
		document.getElementById("err_pwRe").innerHTML="비밀번호 일치";
	}
	
	if(!nameCheck()){
		$("#err_name").css("color","red");
		document.getElementById("err_name").innerHTML="이름에는 공백이나 숫자를 사용할 수 없습니다."
		return false;
	}else{
		$("#err_name").css("color","blue");
		document.getElementById("err_name").innerHTML="사용가능한 이름 입니다.";
	}
	
	if(!$("input:radio[name='gender']").is(":checked")){
		$("#err_gender").css("color","red");
		document.getElementById("err_gender").innerHTML="성별을 선택해 주세요"
		return false;
	}else{
		$("#err_gender").css("color","blue");
		document.getElementById("err_gender").innerHTML="성별이 선택 되었습니다.";
	}
	if(!dateCheck()){
		$("#err_birth").css("color","red");
		document.getElementById("err_birth").innerHTML="생년월일을 선택해 주세요";
		return false;
	}else{
		$("#err_birth").css("color","blue");
		document.getElementById("err_birth").innerHTML="생년월일이 선택 되었습니다.";
	}
	if(!remember.checked){
		$("#err_remember").css("color","red");
		document.getElementById("err_remember").innerHTML="약관에 동의 해주세요";
		remember.focus();
		return false;
	}
	frm.action="./student_join.do";
	frm.method="post";
	alert("회원가입이 완료되었습니다.");
	return true;
	
}

function pwCheck(){
	var re = /^[a-zA-Z0-9]{8,16}$/;
	var pw = document.getElementById("pw");
	
	 if((pw.value).match(re)) {
           return true; // 유효성에 맞는 비밀번호
       }
//	       alert(message);
       pw.focus();
       return false; // 유효성에 맞지않다
}
function pwReCheck(){ // true 일경우 비밀번호 일치, false 일경우 불일치
	var pw = document.getElementById("pw");
	var pwRe = document.getElementById("pwRe");
	if(pw.value != pwRe.value){
		return false;
	}else{
		return true;
	}
}

function nameCheck(){
	var re = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,12}$/;
	var name = document.getElementById("name");
	
	 if((name.value).match(re)) {
           return true; // 유효성에 맞는이름
       }
//	       alert(message);
       name.focus();
       return false; // 유효성에 맞지않다
}

function dateCheck(){
	var re = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/; 
	var birth = document.getElementById("birth");
	var date = new Date(birth.value);
	var today = new Date();
	
	
	if(date >= today){
		alert("오늘 이후의 날짜는 선택할 수 없습니다.");
		return false;
	}
	
	if((birth.value).match(re)) {
           return true; // 유효성에 맞는날짜
    }
//	       alert(message);
    birth.focus();
    return false; // 유효성에 맞지않다
      
}

function t_join(){
var frm = document.getElementsByTagName("form")[0];
	
	var id = document.getElementById("id").value;
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
	
	frm.action="./admin_teacher_add.do";
	frm.method="post";
	alert("강사등록이 완료되었습니다.");
	return true;
	
}
function t_modify(){
	var frm = document.getElementsByTagName("form")[0];
	
	var id = document.getElementById("id").value;
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
	
	frm.action="./admin_teacher_modify.do";
	frm.method="post";
	alert("강사 정보 수정이 완료되었습니다.");
	return true;
	
}