<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="model.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	Userinfo userinfo = (Userinfo)request.getAttribute("userinfo");
%>
<html>
<head>
<title>����� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="../css/user.css" type="text/css">
<script>
function userList() {
	f.command.value = "list";
	f.action = "user_list.m2";
	f.submit();
}

function userModify() {
	f.command.value = "updateForm";
	f.action = "user_modify.m2";
	f.submit();
}

function userRemove() {
	if ( confirm("���� �����Ͻðڽ��ϱ�?") ) {
		f.command.value = "delete";
		f.action = "user_remove.m2";
		f.submit();
	}
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<form name="f" method="POST">
  <input type="hidden" name="command"/>
  <input type="hidden" name="user_id" value="<%= userinfo.getUser_id() %>"/>
  <table style="width: 100%">
    <tr>
	  <td width="20"></td>
	  <td>
  	    <!--contents-->
	    <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>����� ���� - ����� ��������</b></td>
		  </tr>
	    </table>  
	    <br>	  
	    
	  	<table style="width: 100%; background-color: YellowGreen">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">�����ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= userinfo.getUser_id() %>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">�̸�</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= userinfo.getUser_name() %>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">��ȭ��ȣ</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= userinfo.getPhone() %>
			</td>
		  </tr>	
	 	</table>
	    <br>
	    
 	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="����" onClick="userModify()"> &nbsp;
			<input type="button" value="����" onClick="userRemove()"> &nbsp;
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