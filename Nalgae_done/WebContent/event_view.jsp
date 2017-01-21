<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="model.*" %>
<%@page import="model.event.*"%>
<%@ include file="loginCheck.jsp" %>
<%
	Event event = (Event)request.getAttribute("event");
%>
<html>
<head>
<title>이벤트 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function eventList() {
	f.command.value = "eventList";
	f.action = "event_list.m2";
	f.submit();
}

function eventModify() {
	f.command.value = "eventUpdateForm";
	f.action = "event_modify.m2";
	f.submit();
}

function eventRemove() {
	if ( confirm("정말 삭제하시겠습니까?") ) {
		f.command.value = "eventDelete";
		f.action = "event_remove.m2";
		f.submit();
	}
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<form name="f" method="POST">
  <input type="hidden" name="command"/>
  <input type="hidden" name="event_id" value="<%= event.getEvent_id() %>"/>
  <table style="width: 100%">
    <tr>
	  <td width="20"></td>
	  <td>
  	    <!--contents-->
	    <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>이벤트 관리 - 이벤트 정보보기</b></td>
		  </tr>
	    </table>  
	    <br>	  
	    
	  	<table style="width: 100%; background-color: black">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">이벤트ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= event.getEvent_id() %>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">이벤트명</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= event.getEvent_name() %>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">내용</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= event.getContent() %>
			</td>
		  </tr>	
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">할인율</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= event.getDiscount() %>
			</td>
		  </tr>
	 	</table>
	    <br>
	    
 	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="수정" onClick="eventModify()"> &nbsp;
			<input type="button" value="삭제" onClick="eventRemove()"> &nbsp;
			<input type="button" value="목록" onClick="eventList()"> 
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>