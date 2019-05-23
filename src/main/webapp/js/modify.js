function join(){
	var frm = document.getElementsByTagName("form")[0];
	
	var id = document.getElementById("id").value;
	var pw = document.getElementById("pw").value;
	
	if(isNaN(id) || id == "" || id == null){
		$("#err_id").css("color","red");
		document.getElementById("err_id").innerHTML="아이디는 -를 제외한 번호를 입력해주세요"
		return false;
	}
	
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
	
	frm.action="./admin_student_modify.do";
	alert("학생정보 수정이 완료되었습니다.");
	return true;
	
}

function pwCheck(){
	var re = /^[a-zA-Z0-9]{8,16}$/;
	var pw = document.getElementById("pw");
	
	 if((pw.value).match(re)) {
           return true; // 유효성에 맞는아이디
       }
//	       alert(message);
       pw.focus();
       return false; // 유효성에 맞지않다
}

function nameCheck(){
	var re = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,12}$/;
	var name = document.getElementById("name");
	
	 if((name.value).match(re)) {
           return true; // 유효성에 맞는아이디
       }
//	       alert(message);
       name.focus();
       return false; // 유효성에 맞지않다
}

function dateCheck(){
	var re = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/; 
	var birth = document.getElementById("birth");
	 if((birth.value).match(re)) {
           return true; // 유효성에 맞는아이디
       }
//	       alert(message);
       birth.focus();
       return false; // 유효성에 맞지않다
}