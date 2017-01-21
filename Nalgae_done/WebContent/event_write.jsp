<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>�̺�Ʈ ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function eventCreate() {
	if ( f.event_id.value == "" ) {
		alert("�̺�Ʈ ID�� �Է��Ͻʽÿ�.");
		f.event_id.focus();
		return false;
	} 
	if ( f.event_name.value == "" ) {
		alert("�̺�Ʈ �̸��� �Է��Ͻʽÿ�.");
		f.event_name.focus();
		return false;
	} 
	if ( f.content.value == "" ) {
		alert("������ �Է��Ͻʽÿ�.");
		f.content.focus();
		return false;
	}
	if ( f.discount.value == "" ) {
		alert("������ �Է��Ͻʽÿ�.");
		f.discount.focus();
		return false;
	}
	
	f.command.value = "eventInsert";
	f.action = "event_write.m2";
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
<table style="width: 100%">
  <tr>
    <td width="20"></td>
	<td>
      <!--contents-->
	  <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>�̺�Ʈ ���� - �̺�Ʈ �߰�</b></td>
		  </tr>
	  </table>  
	  <br>
	 
	  <!--  exception ��ü�� ���޵� ��� �����޽����� ��� -->
	  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	  </c:if>
	  <br>
	  
	  <table align="center" style="background-color: black">
	  <tr align="center" height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�̺�ƮID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="event_id">
			</td>
		  </tr>
	  	  <tr align="center" height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�̺�Ʈ��</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="event_name">
			</td>
		  </tr>
	  	  <tr align="center" height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">����</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="content">
			</td>
		  </tr>
		  
		  
		  <tr align="center" height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">������</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="discount">
			</td>
		  </tr> 
	  </table>
	  <br>
	  
	  <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="�̺�Ʈ ���" onClick="eventCreate()"> &nbsp;
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