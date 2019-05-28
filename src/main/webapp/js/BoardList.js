function notice_form(){
	location.href="./notice_form.do";
}

function notice_write(){
	var frm = document.getElementsByTagName("form")[0];
	frm.action="./notice_write.do";
	frm.submit();
}

function notice_search(){
	var frm = document.getElementsByTagName("form")[0];
	var search = document.getElementsByName("title")[0].value;
	if(search.length < 2){
		alert("검색어를 두글자 이상 입력하세요");
	}else{
		frm.action="./notice_search.do"
		frm.submit();
	}
}

function file_infomodifyboardform(){
	var frm = document.getElementsByTagName("form")[0];
	frm.action="./file_infomodifyboardform.do";
	frm.method="post";
	frm.submit();
}

function file_infomodifyboard(){
	var frm = document.getElementsByTagName("form")[0];
	frm.action="./file_infomodifyboard.do";
	frm.submit();
}

function file_infodeleteboard(){
	var con = confirm("진짜로 삭제하시겠습니까?");
	if(con){
	var frm = document.getElementsByTagName("form")[0];
	frm.action="./file_infodeleteboard.do";
	frm.submit();
	}else{
		alert("취소하셨습니다.");
	}
}

function file_infowriteboardform(){
	location.href="./file_infowriteboardform.do";
}

function file_infowriteboard(){
	var frm = document.getElementsByTagName("form")[0];
	frm.action="./file_infowriteboard.do";
	frm.submit();
}

function file_infosearchboard(){
	var frm = document.getElementsByTagName("form")[0];
	frm.action="./file_infosearchboard.do";
	frm.submit();
}
