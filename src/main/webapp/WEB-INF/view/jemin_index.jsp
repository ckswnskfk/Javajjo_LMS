<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${dto}<br>
${lists}<br>
${isc}<br>
${room}<br>
<a href="./notice_List.do">공지사항 전체 조회</a><br>
<a href="./notice_Detail.do?seq=1">공지사항 상세 조회</a><br>
<a href="./notice_Write.do?title='목입니다'&content='내용'&id='01011111111'">공지사항 작성</a><br>
<a href="./notice_Search.do?title='목입니다'">공지사항 검색</a><br>
<a href="./notice_Readcount.do?seq=3">공지사항 조회수 증가</a><br>
<a href="./file_infoboardlist.do">자료게시판 전체 조회</a><br>
<a href="./file_infodetailboard.do?seq=2">자료게시판 상세 조회</a><br>
<a href="./file_infodeleteboard.do?seq=1">자료 게시판 삭제</a><br>
<a href="./file_infomodifyboard.do?seq=3&title='새로운글'&content=새로운 내용">자료게시판 글 수정</a><br>
<a href="./file_infowriteboard.do?id=01011111111&title='작성한글'&content='작성한내용'">자료게시판 글 작성</a><br>
<a href="./file_infosearchboard.do?id=01011111111&title='작성한글'">자료 게시판 검색</a><br>
<a href="./file_inforeadcount.do?seq=2">자료게시판 조회수 증가</a><br>
<a href="./room_boardlist.do">강의실 전체 조회</a><br>
<a href="./room_emptyboardlist.do?regdate='2019-05-20'">빈강의실 전체 조회</a><br>
<a href="./room_empty_check.do?code=1111&id=01011111111&regdate=20190520">예약확인</a><br>
<a href="./room_empty_request.do?code=2222&id=01022222222&regdate=2019-05-21">예약</a><br>
<a href="./room_empty_cancle.do?code=1111&id=01011111111&regdate=20190520">예약취소</a><br>
<a href="./room_add.do?code=1111&name='C강의실'&personel=10">강의실 추가</a><br>
</body>
</html>