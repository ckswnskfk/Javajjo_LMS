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
		alert("해당없음");
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

function examinsert(){
	alert("문제등록 버튼");
	var exam = document.getElementsByName("exam");
	if(exam.length==0){
		alert("문제를 한개이상 등록해 주세요.");
	}else{
		var frm = document.forms[0];
		frm.action="./updateExam.do";
		frm.method="post";
		frm.submit();
	}
}

function coursecnt(){
//	window.open("./test_Course_Cnt.do", "회차선택", "width=400, height=300, left=100, top=50"); 
	window.open("./test_Course_Cnt.do", "회차선택", "width=400, height=300, left=100, top=50"); 
//	location.href="./test_Course_Cnt.do";
//	$.ajax({
//		url : "./test_Course_Cnt.do",
//		type : "post",
//		success : function(msg){
//			// 팝업창 띄움
//			return msg;
//		}
//	});
}

//function examlist(){
//	//location.href="./test_ExamList.do";
//	a.jax({
//		url : "./test_ExamList.do",
//		type : "post",
//		success : function(){
//			// 팝업창 띄움
//		}
//	});
//}
function examdesclist(){
	window.open("./test_examdesclist.do", "문제리스트", "width=400, height=300, left=100, top=50");
}
function examsellist(){
	window.open("./test_examsellist.do", "문제리스트", "width=400, height=300, left=100, top=50");
}
function coursecnt(){
	var coursecode = window.open("./test_Course_Cnt.do", "회차선택", "width=400, height=300, left=100, top=50"); 
}

function selectCoursecnt(){ // 팝업에서 선택완료 클릭시
	
	var chk = confirm("문제를 복사하기 위해서는 등록되어있는 문제를 삭제해야 합니다. 정말삭제하시겠습니까?");
	var coursecode = document.getElementById("coursecode").value;
//	alert(coursecode[coursecode.selectedIndex].value);

	window.opener.getReturnValue(coursecode);

//	window.close();
//	alert(chk);
	
//	var coursecnt = document.getElementsByName("coursecode");
//	var coursecode = coursecode[coursecode.selectedIndex].value;
//	if(chk){
//		$.ajax({
//			url : "./test_CouresSel.do",
//			data : {"coursecode":coursecode},
//			type : "post",
//			success : function(){
//				opener.location.reload(); 
//				window.close();
//			}
//		});
//	}
	
}

function getReturnValue(coursecode){
	alert(coursecode);
	$.ajax({
		url : "./test_CouresSel.do",
		data : {"coursecode": coursecode},
		dataType : "json",
		type : "post",
		success : function(){
			
//			alert(msg.lists[0].examnum);
//			results = msg.list;
//			var html = "<tr>";
//			$.each(results , function(i){
//                html += '<td>' + results[i].examnum + '</td><td>' + results[i].exam + '</td><td>' + results[i].allot + '</td>';
//                html += '</tr>';
//			document.getElementById("append").innerHTML = "";
			
		}
	});
}

function testcopy(){
	var input = document.getElementById("append");
	var chk =  document.getElementsByName("examcode");
	var allot = document.getElementsByName("allot");
	var html = "";
	for(var i=0; i<chk; i++){
		if(chk[i].checked){
			
		}
	}
	input.innerHTML = html;

	
//	var chk = $("input:checkbox[name='examcode']").val();
//	alert(chk);
//	
//	 $('input:checkbox[name="examcode"]').each(function() {
//	      this.checked = true; //checked 처리
//	      if(this.checked){//checked 처리된 항목의 값
//	         
//	      }
//	 });

	
	return false;
}




function test(){
	var bol = testExaminsert();
	if(bol){
		$.ajax({
			url : "./test_typecopyexam.do",
			data : {"allot": allot, "examcode" : examcode, "examnum":examnum},
			type : "post",
			async : false,
			success : function(){
//				opener.location.reload();
				window.close();
			}
		});
	}else{
		return false;
	}
	
//	
	
}


function testExaminsert(){
	var allot = document.getElementsByName("allot");
	var examcode = document.getElementsByName("examcode");
	var examnum = document.getElementsByName("examnum");
	for(var i=0; i<allot.length; i++){
		if(allot[i].value==""||allot[i].value==null){
			alert("배점을 모두 입력해주세요.");
			allot[i].focus();
			return false;
		}
	}
	
	return true;
	
}


function testback(){
	alert("뒤로가기 버튼");
	window.history.back();
}

function StuTest(testday){

//	var today = new Date();
//	var dd = today.getDate();
//	var mm = today.getMonth()+1;
//	var yyyy = today.getFullYear();
//	
//	var testday = new Date(testday); 
//
//	
//	if(dd<10){
//		dd='0'+dd;
//	}
//	if(mm<10){
//		mm='0'+mm;
//	}
//
//	var diff = (today.getTime()-testday.getTime());
//    diff = Math.ceil(diff / (1000 * 3600 * 24));
    location.href = "./division_Stu.do";
    
	
//	if(diff<0){
//		alert("시험기간 전입니다.");
//	}
//	else if(diff==0){
//		var chk = confirm("과제를 진행하시겠습니까?");
//		if(chk){
//		}
//	}else{
//		alert("과제 제출기간이 지났습니다.");
//	}
}



function pageexam(bool, max){ //판단
	
	var examnum = document.getElementsByName("examnum")[0].value;
	
	if((Number(examnum)-1)=="0"&&bool){
		alert("첫번째 문제입니다.");
	}else if(examnum==max&&bool==false){
		alert("마지막 문제입니다.");
	}else{
		pageUpDown(bool, examnum);
	}
//	location.href = "./desc_Detail.do?examnum="+examnum;
}

function pageUpDown(bool, examnum){ //보냄

	var frm = document.forms[0];
	
	if(bool){
		document.getElementsByName("page")[0].value="-1";
//		document.getElementsByName("examnum")[0].value = Number(examnum)-1;		
	}else{
		document.getElementsByName("page")[0].value="0";
//		document.getElementsByName("examnum")[0].value = Number(examnum)+1;
	}
//	alert(document.getElementsByName("examnum")[0].value);
	frm.method="post";
	frm.action = "./desc_Detail.do";
	frm.submit();
}

function numberclick(examnum){
	var frm = document.forms[0];
	document.getElementsByName("page")[0].value=examnum;
	frm.method = "post";
	frm.action="./desc_Detail.do";
	frm.submit();
}




