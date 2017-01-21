<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="model.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	Userinfo userinfo = (Userinfo)request.getAttribute("userinfo");
%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="../css/user.css" type="text/css">
<script>
function userModify() {
	f.command.value = "update";
	f.action = "user_modify.m2";
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
  <input type="hidden" name="user_id" value="<%= userinfo.getUser_id() %>"/>	  
  <table style="width: 100%">
	<tr>
	  <td width="20"></td>
	  <td>
        <!--contents-->
	    <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 사용자 수정</b></td>
		  </tr>
	    </table>  
	    <br>
	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">사용자ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<%= userinfo.getUser_id() %>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="user_pwd" style="width: 240" name="password" value="<%= userinfo.getUser_pwd() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호 확인</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="user_pwd" style="width: 240" name="password2" value="<%= userinfo.getUser_pwd() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이름</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="user_name" value="<%= userinfo.getUser_name() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">전화번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
		 		<input type="text" style="width: 240" name="phone" value="<%= userinfo.getPhone() %>">
			</td>
		  </tr>		  
	    </table>
	    <br>
	  
	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="수정" onClick="userModify()"> &nbsp;
			<input type="button" value="목록" onClick="userList()">
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>