<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	@SuppressWarnings("unchecked") 
	List<Userinfo> userinfoList = (List<Userinfo>)request.getAttribute("userinfoList");
%>
<html>
<head>
<title>����� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<!-- script language="JavaScript">
function userCreate() {
	f.command.value = "insertForm";
	f.action = "user_write.m2";
	f.submit();
}
</script -->
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<form name="f" method="post" action="user_write.m2">
<input type="hidden" name="command" value="insertForm">
<table style="width: 100%">
  <tr>
  	<td width="20"></td>
    <td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">������ ���� ��������</a></td>
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <!--contents-->
	  <table style="width:100%">
		<tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>����� ���� - ����Ʈ</b></td>
		</tr>
	  </table>  
	  <br>	
	  
	  <!-- list -->
	  <table style="width: 80%; background-color: black" align="center">
		<tr>
			<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">�����ID</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�̸�</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ȭ��ȣ</td>
		</tr>
<%
	if (userinfoList != null) {	
	  Iterator<Userinfo> userIter = userinfoList.iterator();
	
	  //����� ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
	  while ( userIter.hasNext() ) {
		Userinfo userinfo = (Userinfo)userIter.next();
%>		  	
		<tr>
			<td width="190" align="center" bgcolor="ffffff" height="20">
				<%= userinfo.getUser_id() %>
			</td>
			<td width="200" bgcolor="ffffff" style="padding-left: 10">
				<a href="user_view.m2?user_id=<%= userinfo.getUser_id() %>&command=view" class="userinfo">
					<%= userinfo.getUser_name() %>
				</a>
			</td>
			<td width="190" align="center" bgcolor="ffffff" height="20">
				<%= userinfo.getPhone() %>
			</td>
		</tr>
<%
	  }
	}
%>	  	
	  </table>
 	  <!-- /list -->	 
	  <br>
	  
	  <!-- button -->
	  	
	</td>
  </tr>
</table>  
</form>
</body>
</html>