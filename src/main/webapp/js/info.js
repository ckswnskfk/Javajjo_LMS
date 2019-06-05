
function student_modify() {
	location.href = "student_Modify_Form.do";
}

function stu_mod() {
	var frm = document.getElementsByTagName("form")[0];
	var pw = document.getElementById("pw").value;
	if (!isNaN(pw) || !pwCheck()) {
		$("#err_pw").css("color", "red");
		document.getElementById("err_pw").innerHTML = "8~16자의 영문 혹은 숫자 영문을 혼합하여 입력해 주세요"
		return false;
	} else {
		$("#err_pw").css("color", "blue");
		document.getElementById("err_pw").innerHTML = "사용가능한 비밀번호 입니다.";
	}
	if (!nameCheck()) {
		$("#err_name").css("color", "red");
		document.getElementById("err_name").innerHTML = "이름에는 공백이나 숫자를 사용할 수 없습니다."
		return false;
	} else {
		$("#err_name").css("color", "blue");
		document.getElementById("err_name").innerHTML = "사용가능한 이름 입니다.";
	}
	frm.action = "student_modify.do";
	frm.method = "post";
	alert("회원 정보 수정이 완료되었습니다.");
	return true;
}

function pwCheck() {
	var re = /^[a-zA-Z0-9]{8,16}$/;
	var pw = document.getElementById("pw");
	if ((pw.value).match(re)) {
		return true; // 유효성에 맞는아이디
	}
	//	       alert(message);
	return false; // 유효성에 맞지않다
}

function nameCheck() {
	var re = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,12}$/;
	var name = document.getElementById("name");

	if ((name.value).match(re)) {
		return true; // 유효성에 맞는아이디
	}
	//	       alert(message);
	return false; // 유효성에 맞지않다
}

function teacher_modify() {
	location.href = "teacher_Modify_Form.do";
}

function tea_mod() {
	var frm = document.getElementsByTagName("form")[0];
	var pw = document.getElementById("pw").value;
	if (!isNaN(pw) || !pwCheck()) {
		$("#err_pw").css("color", "red");
		document.getElementById("err_pw").innerHTML = "8~16자의 영문 혹은 숫자 영문을 혼합하여 입력해 주세요"
		return false;
	} else {
		$("#err_pw").css("color", "blue");
		document.getElementById("err_pw").innerHTML = "사용가능한 비밀번호 입니다.";
	}
	if (!nameCheck()) {
		$("#err_name").css("color", "red");
		document.getElementById("err_name").innerHTML = "이름에는 공백이나 숫자를 사용할 수 없습니다."
		return false;
	} else {
		$("#err_name").css("color", "blue");
		document.getElementById("err_name").innerHTML = "사용가능한 이름 입니다.";
	}
	frm.action = "teacher_modify.do";
	frm.method = "post";
	alert("회원 정보 수정이 완료되었습니다.");
	return true;
}

function admin_modify() {
	location.href = "admin_Modify_Form.do";
}

function adm_mod() {
	var frm = document.getElementsByTagName("form")[0];
	var pw = document.getElementById("pw").value;
	if (!isNaN(pw) || !pwCheck()) {
		$("#err_pw").css("color", "red");
		document.getElementById("err_pw").innerHTML = "8~16자의 영문 혹은 숫자 영문을 혼합하여 입력해 주세요"
		return false;
	} else {
		$("#err_pw").css("color", "blue");
		document.getElementById("err_pw").innerHTML = "사용가능한 비밀번호 입니다.";
	}
	if (!nameCheck()) {
		$("#err_name").css("color", "red");
		document.getElementById("err_name").innerHTML = "이름에는 공백이나 숫자를 사용할 수 없습니다."
		return false;
	} else {
		$("#err_name").css("color", "blue");
		document.getElementById("err_name").innerHTML = "사용가능한 이름 입니다.";
	}
	frm.action = "admin_modify.do";
	frm.method = "post";
	alert("회원 정보 수정이 완료되었습니다.");
	return true;
}

function allChk1(obj) {
	var chkObj = document.getElementsByName("RowCheck");
	var rowCnt = chkObj.length - 1;
	var check = obj.checked;
	if (check) {
		for (var i = 0; i <= rowCnt; i++) {
			if (chkObj[i].type == "checkbox")
				chkObj[i].checked = true;
		}
	} else {
		for (var i = 0; i <= rowCnt; i++) {
			if (chkObj[i].type == "checkbox") {
				chkObj[i].checked = false;
			}
		}
	}
}

function admin_student_modify() {
	location.href = "./admin_student_modify_Form.do";

}

function admin_student_course() {
	location.href = "./admin_student_course.do";
}

function admin_student_delete() {
	var check = document.getElementsByName("RowCheck");
	var cntCheck = 0;
	for (var i = 0; i < check.length; i++) {
		if (check[i].checked) {
			cntCheck++;
		}
	}
	if (cntCheck > 0) {
		var obj = document.forms[0]; //form 요소 모두 가져올 수 있음
		obj.action = "./admin_student_delete.do";
		obj.method = "post";
		alert("학생 탈퇴 완료");
		obj.submit();
	} else {
		alert("탈퇴시킬 학생을 선택해 주세요");
	}
}


function admin_student_cdelete() {
	var check = document.getElementsByName("coursecode");
	var cntCheck = 0;
	for (var i = 0; i < check.length; i++) {
		if (check[i].checked) {
			cntCheck++;
		}
	}
	if (cntCheck > 0) {
		var obj = document.forms[0]; //form 요소 모두 가져올 수 있음
		if (sessionStorage.getItem("num") == null) {
			var num = 2;
			sessionStorage.setItem("num", num);
		} else {
			var num = parseInt(sessionStorage.getItem("num"));
			num += 1;
			sessionStorage.setItem("num", num);
		}
		obj.action = "./admin_student_cdelete.do";
		obj.method = "post";
		obj.submit();
	} else {
		alert("삭제할 과정을 선택해 주세요");
	}
}


function admin_student_cconnect() {
	var con = confirm("과정을 추가 하시겠습니까?");
	var coursecode = document.getElementById("coursecode");
	var sele = document.getElementById("Acoursecode");
	if(coursecode == null || sele.value != coursecode.value){
		if (con) {
			var obj = document.forms[0]; //form 요소 모두 가져올 수 있음
			if (sessionStorage.getItem("num") == null) {
				var num = 2;
				sessionStorage.setItem("num", num);
			} else {
				var num = parseInt(sessionStorage.getItem("num"));
				num += 1;
				sessionStorage.setItem("num", num);
			}
			obj.action = "./admin_student_cconnect.do";
			obj.method = "post";
			obj.submit();
		} else {
			alert("취소하였습니다.");
		}
	}else{
		alert("같은 과정을 여러번 추가할 수 없습니다.");
	}
}

function admin_teacher_add_form() {
	location.href = "./admin_teacher_add_form.do";
}

function admin_teacher_delete() {
	var check = document.getElementsByName("RowCheck");
	var cntCheck = 0;
	for (var i = 0; i < check.length; i++) {
		if (check[i].checked) {
			cntCheck++;
		}
	}
	if (cntCheck > 0) {
		var obj = document.forms[0]; //form 요소 모두 가져올 수 있음
		obj.action = "./admin_teacher_delete.do";
		obj.method = "post";
		alert("강사 탈퇴 완료");
		obj.submit();
	} else {
		alert("탈퇴시킬 강사를 선택해 주세요");
	}
}

function admin_accept(){
	var check = document.getElementsByName("RowCheck");
	var cntCheck = 0;
	for (var i = 0; i < check.length; i++) {
		if (check[i].checked) {
			cntCheck++;
		}
	}
	if (cntCheck > 0) {
		var obj = document.forms[0]; //form 요소 모두 가져올 수 있음
		obj.action = "./admin_accept.do";
		obj.method = "post";
		alert("학생 회원가입 승인 완료");
		obj.submit();
	} else {
		alert("학생을 먼저 선택해 주세요");
	}
	
}

function admin_accept_refuse(){
	var check = document.getElementsByName("RowCheck");
	var cntCheck = 0;
	for (var i = 0; i < check.length; i++) {
		if (check[i].checked) {
			cntCheck++;
		}
	}
	if (cntCheck > 0) {
		var obj = document.forms[0]; //form 요소 모두 가져올 수 있음
		obj.action = "./admin_accept_refuse.do";
		obj.method = "post";
		alert("학생 회원가입 미승인");
		obj.submit();
	} else {
		alert("학생을 먼저 선택해 주세요");
	}
}

/////////////page
function pageList(){
	var index = document.getElementById("index");
	var pageNum = document.getElementById("pageNum");
	var listNum = document.getElementById("listNum");
	
	index.value = 0;
	pageNum.value = 1;
	listNum.value= document.getElementById("list").value;
	
}

//페이지 숫자 눌렀을때
function pageIndex(pageNum,table){
	var frm = document.getElementsByTagName("form")[0];
	var index = document.getElementById("index");
	index.value = pageNum-1;
	if(table == "teacher_student"){
			frm.action="./teacher_student_list.do";
			frm.submit();
	}else if(table == "admin_accept"){
		frm.action="./admin_accept_list.do";
		frm.submit();
	}else if(table == "admin_teacher"){
		frm.action="./admin_teacher_list.do";
		frm.submit();
	}else if(table == "admin_student"){
		frm.action="./admin_student_list.do";
		frm.submit();
	}
}

function pageFrist(num, pageList,table){
	var frm = document.getElementsByTagName("form")[0];
	var pageNum = document.getElementById("pageNum");
	var index = document.getElementById("index");
	
	pageNum.value = 1;
	index.value = 0;
	if(table == "teacher_student"){
			frm.action="./teacher_student_list.do";
			frm.submit();
	}else if(table == "admin_accept"){
		frm.action="./admin_accept_list.do";
		frm.submit();
	}else if(table == "admin_teacher"){
		frm.action="./admin_teacher_list.do";
		frm.submit();
	}else if(table == "admin_student"){
		frm.action="./admin_student_list.do";
		frm.submit();
	}
}
function pagePre(num, pageList,table){
	var frm = document.getElementsByTagName("form")[0];
	if(0<num-pageList){ // 한페이지가 더 있다.
		num -= pageList;
		varpageNum  = document.getElementById("pageNum");
		var index = document.getElementById("index");
		
		pageNum.value = num;
		index.value = num-1;
		if(table == "teacher_student"){
				frm.action="./teacher_student_list.do";
				frm.submit();
		}else if(table == "admin_accept"){
			frm.action="./admin_accept_list.do";
			frm.submit();
		}else if(table == "admin_teacher"){
			frm.action="./admin_teacher_list.do";
			frm.submit();
		}else if(table == "admin_student"){
			frm.action="./admin_student_list.do";
			frm.submit();
		}
	}
}

function pageNext(num, total, listNum, pageList,table){
	var frm = document.getElementsByTagName("form")[0];
	var index = Math.ceil(total/listNum); //묶음 40/5 => 8
	var max = Math.ceil(index/pageList); // 글의 갯수 8/5 => 2
	
	if(max*pageList > num+pageList){
		num += pageList;
		
		var pageNum = document.getElementById("pageNum");
		var index = document.getElementById("index");
		
		pageNum.value = num;
		index.value = num-1;
		if(table == "teacher_student"){
				frm.action="./teacher_student_list.do";
				frm.submit();
		}else if(table == "admin_accept"){
			frm.action="./admin_accept_list.do";
			frm.submit();
		}else if(table == "admin_teacher"){
			frm.action="./admin_teacher_list.do";
			frm.submit();
		}else if(table == "admin_student"){
			frm.action="./admin_student_list.do";
			frm.submit();
		}
	}
	
}

function pageLast(num, total, listNum, pageList,table){
	var frm = document.getElementsByTagName("form")[0];
	var idx = Math.ceil(total/listNum);
	var max = Math.ceil(idx/pageList);
	
	while(max*pageList > num+pageList){
		num += pageList
	}
	
	var pageNum = document.getElementById("pageNum");
	var index = document.getElementById("index");
	
	pageNum.value= num;
	index.value = idx-1;
	if(table == "teacher_student"){
		frm.action="./teacher_student_list.do";
		frm.submit();
	}else if(table == "admin_accept"){
		frm.action="./admin_accept_list.do";
		frm.submit();
	}else if(table == "admin_teacher"){
		frm.action="./admin_teacher_list.do";
		frm.submit();
	}else if(table == "admin_student"){
		frm.action="./admin_student_list.do";
		frm.submit();
	}
}