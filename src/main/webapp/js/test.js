/**
 * 과제 관리 javascript
 */

//
//function examinsert(){
////	action="./desc_ExamForm.do";
//	alert("등록버튼");
//}
function descexaminsert(){
//	alert("문제등록 버튼");
	var exam = document.getElementsByName("exam")[0];
	var explanation = document.getElementsByName("explanation")[0];
	var standard = document.getElementsByName("standard")[0];
	var c_answer = document.getElementsByName("c_answer")[0];
	var allot = document.getElementsByName("allot")[0];
//	alert(allot.value);
	if(exam.value==""||exam.value==null||exam.value==" "){
		alert("문제를 입력해주세요.");
		exam.focus();
		return false;
	}else if(explanation.value==""||explanation.value==null||explanation.value==" "){
		alert("설명을 입력해주세요.");
		explanation.focus();
		return false;
	}else if(standard.value==""||standard.value==null||standard.value==" "){
		alert("채점기준을 입력해주세요.");
		standard.focus();
		return false;
	}else if(c_answer.value==""||c_answer.value==null||c_answer.value==" "){
		alert("정답을 입력해주세요.");
		c_answer.focus();
		return false;
	}else if(allot.value==""||allot.value==null){
		alert("배점을 입력해주세요.");
		allot.focus();
		return false;
	}else if(isNaN(allot.value)){//숫자 
		alert("배점에는 숫자만 입력이 가능합니다.");
		allot.focus();
		return false;
	}else{
		return true;
	}

}

function selexaminsert(){
	var exam = document.getElementsByName("exam")[0];
	var c_answer = document.getElementsByName("c_answer")[0];
	var allot = document.getElementsByName("allot")[0];
//	alert("문제등록 버튼");
	var content = document.getElementsByName("content");
	var contentnum = document.getElementsByName("contentnum");
//	alert(contentlist.length);
//	for(var i=0; i<contentlist.length; i++){
//		
//	}		
	if(exam.value==""||exam.value==null||exam.value==" "){
		alert("문제를 입력해주세요.");
		exam.focus();
		return false;
	}else if(c_answer.value==""||c_answer.value==null||c_answer.value==" "){
		alert("정답을 입력해주세요.");
		c_answer.focus();
		return false;
	}else if(isNaN(c_answer.value)){
		alert("정답에는 숫자만 입력이 가능합니다.");
		c_answer.focus();
		return false;
	}else if(allot.value==""||allot.value==null){
		alert("배점을 입력해주세요.");
		allot.focus();
		return false;
	}else if(isNaN(allot.value)){//숫자 
		alert("배점에는 숫자만 입력이 가능합니다.");
		allot.focus();
		return false;
	}else{
		return true;
	} 
}


function testback(){
	alert("뒤로가기 버튼");
	window.history.back();
}