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
function pageIndex(pageNum,table,find){
	var frm = document.getElementsByTagName("form")[0];
	var index = document.getElementById("index");
	index.value = pageNum-1;
	if(table == "notice"){
		if(find == "find"){
			frm.action="./notice_search_page.do";
			frm.submit();
		}else{
			frm.action="./notice_list.do";
			frm.submit();
		}
	}else{
		if(find == "find"){
			frm.action="./file_infosearchboard_page.do";
			frm.submit();
		}else{
			frm.action="./file_infoboardlist.do";
			frm.submit();
		}
	}
}

function pageFrist(num, pageList,table,find){
	var frm = document.getElementsByTagName("form")[0];
	var pageNum = document.getElementById("pageNum");
	var index = document.getElementById("index");
	
	pageNum.value = 1;
	index.value = 0;
	if(table == "notice"){
		if(find == "find"){
			frm.action="./notice_search_page.do";
			frm.submit();
		}else{
			frm.action="./notice_list.do";
			frm.submit();
		}
	}else{
		if(find == "find"){
			frm.action="./file_infosearchboard_page.do";
			frm.submit();
		}else{
			frm.action="./file_infoboardlist.do";
			frm.submit();
		}
	}
}
function pagePre(num, pageList,table,find){
	var frm = document.getElementsByTagName("form")[0];
	if(0<num-pageList){ // 한페이지가 더 있다.
		num -= pageList;
		varpageNum  = document.getElementById("pageNum");
		var index = document.getElementById("index");
		
		pageNum.value = num;
		index.value = num-1;
		if(table == "notice"){
			if(find == "find"){
				frm.action="./notice_search_page.do";
				frm.submit();
			}else{
				frm.action="./notice_list.do";
				frm.submit();
			}
		}else{
			if(find == "find"){
				frm.action="./file_infosearchboard_page.do";
				frm.submit();
			}else{
				frm.action="./file_infoboardlist.do";
				frm.submit();
			}
		}
	}
}

function pageNext(num, total, listNum, pageList,table,find){
	var frm = document.getElementsByTagName("form")[0];
	var index = Math.ceil(total/listNum); //묶음 40/5 => 8
	var max = Math.ceil(index/pageList); // 글의 갯수 8/5 => 2
	
	if(max*pageList > num+pageList){
		num += pageList;
		
		var pageNum = document.getElementById("pageNum");
		var index = document.getElementById("index");
		
		pageNum.value = num;
		index.value = num-1;
		if(table == "notice"){
			if(find == "find"){
				frm.action="./notice_search_page.do";
				frm.submit();
			}else{
				frm.action="./notice_list.do";
				frm.submit();
			}
		}else{
			if(find == "find"){
				frm.action="./file_infosearchboard_page.do";
				frm.submit();
			}else{
				frm.action="./file_infoboardlist.do";
				frm.submit();
			}
		}
	}
	
}

function pageLast(num, total, listNum, pageList,table,find){
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
	if(table == "notice"){
		if(find == "find"){
			frm.action="./notice_search_page.do";
			frm.submit();
		}else{
			frm.action="./notice_list.do";
			frm.submit();
		}
	}else{
		if(find == "find"){
			frm.action="./file_infosearchboard_page.do";
			frm.submit();
		}else{
			frm.action="./file_infoboardlist.do";
			frm.submit();
		}
	}
}
