<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="model.*" %>
<%@page import="model.event.*"%>
<%@ include file="loginCheck.jsp" %>
<%
	Event event = (Event)request.getAttribute("event");
%>
<html>
<head>
<title>�̺�Ʈ ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="../css/user.css" type="text/css">
<script>
function eventModify() {
	f.command.value = "eventUpdate";
	f.action = "event_modify.m2";
	f.submit();
}

function eventList() {
	f.command.value = "eventList";
	f.action = "event_list.m2";
	f.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<!-- write Form  -->
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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>�̺�Ʈ ���� - �̺�Ʈ ����</b></td>
		  </tr>
	    </table>  
	    <br>
	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">�̺�ƮID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<%= event.getEvent_id() %>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">�̺�Ʈ��</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="event_name" value="<%= event.getEvent_name() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">����</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="content" value="<%= event.getContent() %>">
			</td>
		  </tr>
		  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">������</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="discount" value="<%= event.getDiscount() %>">
			</td>
		  </tr>
	    </table>
	    <br>
	  
	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="����" onClick="eventModify()"> &nbsp;
			<input type="button" value="���" onClick="eventList()">
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>