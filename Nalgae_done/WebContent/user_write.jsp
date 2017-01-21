<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>����� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="../css/user.css" type="text/css">
<script>
function userCreate() {
	if ( f.user_id.value == "" ) {
		alert("����� ���̵� �Է��Ͻʽÿ�.");
		f.user_id.focus();
		return false;
	} 
	if ( f.user_pwd.value == "" ) {
		alert("��й�ȣ�� �Է��Ͻʽÿ�.");
		f.user_pwd.focus();
		return false;
	}
	if ( f.user_name.value == "" ) {
		alert("�̸��� �Է��Ͻʽÿ�.");
		f.user_name.focus();
		return false;
	}
	
	f.command.value = "insert";
	f.action = "user_write.m2";
	f.submit();
}

function userList() {
	f.command.value = "list";
	f.action = "user_list.m2";
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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>����� ���� - ȸ�� ����</b></td>
		  </tr>
	  </table>  
	  <br>
	 
	  <!--  exception ��ü�� ���޵� ��� �����޽����� ��� -->
	  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	  </c:if>
	  <br>
	  
	  <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">�����ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="user_id">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��й�ȣ</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="user_pwd">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��й�ȣ Ȯ��</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="user_pwd2">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">�̸�</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="user_name">
			</td>
		  </tr>	
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��ȭ��ȣ</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="phone">
			</td>
		  </tr>		  
	  </table>
	  <br>
	  
	  <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="ȸ�� ����" onClick="userCreate()"> &nbsp;
			<input type="button" value="���" onClick="userList()">
			</td>
		  </tr>
	  </table>
	</td>
  </tr>
</table>  
</form>
</body>
</html>