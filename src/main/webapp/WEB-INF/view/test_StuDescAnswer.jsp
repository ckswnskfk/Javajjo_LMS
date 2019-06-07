<%@page import="happy.jaj.prj.dtos.Answer_Des_DTO"%>
<%@page import="happy.jaj.prj.dtos.Exam_Des_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제 관리</title>
<script type="text/javascript">
function pageexam(bool, max){ //판단
	
	var examnum = document.getElementsByName("examnum")[0].value;
	var score = document.getElementsByName("score")[0];
	if(score.value==null || score.value==""){
		alert("점수를 입력해주세요.");
		score.focus();
	}else if (isNaN(score.value)){
		alert("점수는 숫자 입력만 가능합니다.");
		score.focus();
	}else{
		
		if((Number(examnum)-1)=="0"&&bool){
			alert("첫번째 문제입니다.");
		}else if(examnum==max&&bool==false){
			alert("마지막 문제입니다.");
		}else{
			pageUpDown(bool, examnum);
		}
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
	frm.action = "./desc_Detail_Exam.do";
	frm.submit();
}

function markexam(id, testcode ,maxnum, examcode){
	var score = document.getElementsByName("score")[0].value;
	
	var chk = confirm("채점을 완료하시면 다시 채점할 수 없습니다.");
	if(chk){
			
		if(score==null || score=="" || score==" "){
			alert("점수를 입력해주세요.");
		}else if (isNaN(score)){
			alert("점수는 숫자 입력만 가능합니다.");
			document.getElementsName("score")[0].focus();
		}else{
		
			// 현재 학생의 모든 문제 채점 완료?
			var allchk;
			$.ajax({
				url : "./test_AllMark.do",
				type : "post",
				data : {"id":id, "testcode":testcode, "maxnum":maxnum, "examcode":examcode,"score":score}, // id, testcode 
				async : false,
				success : function(exam){
					allchk = exam;
				}
			});
			alert("채점한 문제 갯수 : "+allchk);
			if(allchk!=maxnum){
				alert("모든 문제를 채점해주세요.");
			}else{
				// 모든 문제를 채점 했으면 EXAMCHECK를 Y로 바꾸어 준다. 
				$.ajax({
					url : "./score_chkupdate.do",
					type : "post",
					data : {"id":id, "testcode":testcode},
					success: function (){
		// 				alert("성공");
						location.href="./test_Student_List.do";
						
					}
				});
			}
		}
	}
	
}


</script>
</head>
<%
	Answer_Des_DTO answer = (Answer_Des_DTO)request.getAttribute("answer");
	Exam_Des_DTO dto = (Exam_Des_DTO)request.getAttribute("dto");
	int maxexam = (int)request.getAttribute("maxexamnum");
%>
<body>
<%@include file="./include/header.jsp" %>
<div class="container">
	<form action="#" method="post">
		<table class="table">
			<tr>
					<td><p style="font-size: 20px">
					<input type="hidden" name="examcode" value='<%=dto.getExamcode()%>'>
					<input type="hidden" name="examnum" value='<%=dto.getExamnum()%>'>
					<input type="hidden" name="page" >
					<input type="hidden" name="id" value="${id}">
					<%=dto.getExamnum() %></p></td>
					<td><%=dto.getExam() %></td>
				</tr>
				<tr>
					<td>설명</td>
					<td colspan="2"><%=dto.getExplanation() %></td>
				</tr>
				<tr>
					<td>채점기준</td>
					<td><%=dto.getStandard() %></td>
				</tr>
				<tr>
					<td>답안</td>
					<td colspan="2">
						<div class="form-group">
							<textarea class="form-control" cols="30" rows="2" name="answer"><c:choose><c:when test="${answer.answer eq null}">정답을 작성해주세요.</c:when><c:otherwise>${answer.answer}</c:otherwise></c:choose></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<c:choose>
							<c:when test="${answer.originfile eq null}">
											파일이 없습니다.
							</c:when>
						<c:otherwise>
							<a href="./test_submitdownload.do?filename=${answer.originfile}&newfilename=${answer.newfilename}">${answer.originfile}</a>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>배점</td>
					<td><%=dto.getAllot() %></td>
				</tr>
				<tr>
					<td>점 수 </td>
					<td>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="점수를 입력해주세요." name="score" value="<c:choose><c:when test="${scoredto eq null}"></c:when><c:otherwise>${scoredto}</c:otherwise></c:choose>">
						</div>
					<td>
				</tr>
				<tr>
					<td><input type="button" class="btn btn-success" value="← 이전문제" onclick="pageexam(true,<%=maxexam%>)"></td>
					<td><input type="button" class="btn btn-success" value="다음 문제 →" onclick="pageexam(false,<%=maxexam%>)"></td>
					<td><input type="button" class="btn btn-warning" value="채점 완료" onclick="markexam('${id}', '${testsession.testcode}', '<%=maxexam%>', '<%=dto.getExamcode()%>')"></td>
				</tr>
		</table>
	</form>
</td>
<%@include file="./include/footer.jsp" %>
</body>
</html>