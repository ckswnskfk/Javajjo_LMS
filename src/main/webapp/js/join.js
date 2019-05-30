function join(){
	var frm = document.getElementsByTagName("form")[0];
	
	var id = document.getElementById("id").value;
	var pw = document.getElementById("pw").value;
	var pwRe = document.getElementById("pwRe").value;
	var remember = document.getElementsByName("remember")[0];
	var id_check = document.getElementById("id_check").value;
	
	
	if(id_check == "N"){
		$("#err_id").css("color","red");
		document.getElementById("err_id").innerHTML="아이디 중복 확인과 본인인증을 완료해주세요"
		return false;
	}else{
		$("#err_id").css("color","blue");
		document.getElementById("err_id").innerHTML="사용가능한 아이디 입니다.";
	}
	
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

function id_duplicate(){
	var id = document.getElementById("id").value;
	$.ajax({
		url: "./student_join_duplicate.do",
		data: {"id" : id},
		type: "POST",
		success: function(obj) {
			if(obj == null || obj == ""){
				alert("사용가능한 아이디 입니다.");
				$("#yeah").attr("disabled", false);
			}else{
				alert("중복된 아이디는 사용이 불가능 합니다.");
			}
		}
		});
}
function id_check_go(){
	var id = document.getElementById("id").value;
	$.ajax({
		url: "./id_check_num.do",
		data:{"id" : id},
		type:"POST",
		success:function(obj){
			document.getElementById("id_check_number").value = obj;
		}
	});
	alert(id+"번호로 인증번호가 발송 되었습니다.");
}

function id_check_ok(){
	var id_check_number = document.getElementById("id_check_number").value;
	var idRe = document.getElementById("idRe").value;
	var id_check = document.getElementById("id_check").value;
	
	if(id_check_number == idRe){
		alert("인증 번호가 일치 합니다. 회원가입을 진행해 주세요");
		document.getElementById("id_check").value = "Y";
	}else{
		alert("인증 번호가 일치하지 않습니다. 다시 시도해 주세요");
	}
}