<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='./css/cormain.css' rel='stylesheet' />
<link href='./css/daymain.css' rel='stylesheet' />
<script src='./js/cormain.js'></script>
<script src='./js/intmain.js'></script>
<script src='./js/daymain.js'></script>
<script type="text/javascript" src="./js/calendar.js"></script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>
</head>
<body>
<%@ include file="./include/header.jsp" %>
<div class="container">
 <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">강의실 목록</button>
 <input type="hidden" id="id" value="${member.id}">
 
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">강의실 목록</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <c:forEach items="${lists}" var="dto" varStatus="vs">
        <div class="modal-body">
          <p>
          	<span>No : ${vs.count}</span><span> 수용인원 : ${dto.personel}</span>
          </p>
          <p>
          	<span>강의실 이름 : ${dto.name}</span>
          </p>
          <hr>
        </div>
        </c:forEach>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  

  <div id='calendar'></div>
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal2">강의실 추가</button>
<!-- Modal -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <h4 class="modal-title">강의실 추가</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <form action="#">
        <div class="modal-body">
          <table>
          	<tr>
          		<th>강의실 이름 : </th>
          		<td>
          			<input type="text" name="name">
          		</td>
          	</tr>
          	<tr>
          		<th>수용 인원 : </th>
          		<td>
          			<input type="text" name="personel">
          		</td>
          	</tr>
          </table>
       </div>
       </form>
       <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="room_add()">추가</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
 </div>
</div>
<%@ include file="./include/footer.jsp" %>
</body>
</html>
