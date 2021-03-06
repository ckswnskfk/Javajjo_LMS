<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>과제 등록 테스트 </h1>
<a href="./test_Input.do?testname=화면설계&subjecttype=JAVA&examtype=서술형&subjectcode=S00001&testday=2019-05-16">과제 추가●</a><br>
<a href="./desc_ExamInput.do?exam=A=1,B=2일 때 A+B는 무엇인가요?&explanation=연산문제&standard=답과 설명을 모두 쓰면 5점, 답만 쓰면 2점, 설명만 쓰면 2점&c_answer=A+B=3입니다.">서술형 문제 등록●</a><br>
<a href="./sel_ExamInput.do?exam=선택형 문제의 문제 내용 작성&c_answer=3&examnum=1&examcontent=문항1번">선택형 문제 등록●</a><br>
<a href="./test_Exam.do?testcode=T00001&examcode=ED00002&allot=20&examnum=2">과제에 문제 등록●</a><br>
<a href="./test_ExamModify.do?allot=20&examnum=3&examcode=ED00003">과제에 등록된 문제 수정●</a><br>
<a href="./score_total.do?testcode=T00001">배점 총점 계산●</a><br>
<a href="./sel_Content_Modify.do?exam=선택형 문제의 문제 내용 수정&c_answer=3&examcode=ES00006&examcontent=문항 1번수정&examnum=1">선택형 문제 수정●</a><br>
<a href="./desc_Exam_Modify.do?examcode=ED00004&exam=서술형 문제의 문제 내용 수정&explanation=문제 설명 수정&standard=채점기준 수정&c_answer=정답 수정">서술형 문제 수정●</a><br>
<a href="./desc_Detail.do?testcode=T00001&examcode=ED00003">등록된 서술형문제 조회●</a><br>
<a href="./sel_Detail.do?testcode=T00001&examcode=ES00003">등록된 선택형문제 조회</a><br>
<a href="./desc_ListForm.do?testcode=T00001">과제에 해당하는 문제리스트 조회(서술형)●</a><br>
<a href="./sel_ListForm.do?testcode=T00001">과제에 해당하는 문제리스트 조회(선택형)●</a><br>
<a href="./detail_Content.do?examcode=ES00005">선택형 문제에 해당하는 문항 조회●</a><br>
<a href="./sel_Answer_Submit.do?id=01011111111&examcode=ED00003&examnum=3&answer=정답 등록&originfile=파일없음&newfilename=파일없음">서술형 문제의 답안 등록●</a><br>
<a href="./desc_Answer_Submit.do?id=01011111111&examcode=ES00003&examnum=2&answer=4">선택형 문제의 답안 등록● </a><br>
<a>서술형 답안 수정</a><br>
<a href="./desc_Detail_Exam.do?id=01011111111&examcode=ED00003&examnum=3">과제에 해당하는 문제,답 조회(서술형)●</a><br>
<a href="./sel_Detail_Exam.do?id=01011111111&examcode=ES00003">과제에 해당하는 문제,답 조회(선택형)●</a><br>
<a href="./test_Sel_Score.do?id=01011111111&testcode=T00001&examcode=ES00003">선택형 문제 자동 점수 등록● </a><br>
<a href="./test_Desc_Score.do?id=01011111111&testcode=T00001&examcode=ED00003&score=20">서술형 문제 점수 등록● </a><br>
<a href="./test_Total_Result.do?id=01011111111&testcode=T00001">총점 점수 조회●</a>

</body>
</html>