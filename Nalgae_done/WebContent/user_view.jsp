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
	if ( confirm("정말 삭제하시겠습니까?") ) {
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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 사용자 정보보기</b></td>
		  </tr>
	    </table>  
	    <br>	  
	    
	  	<table style="width: 100%; background-color: YellowGreen">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">사용자ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= userinfo.getUser_id() %>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">이름</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= userinfo.getUser_name() %>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">전화번호</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= userinfo.getPhone() %>
			</td>
		  </tr>	
	 	</table>
	    <br>
	    
 	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="수정" onClick="userModify()"> &nbsp;
			<input type="button" value="삭제" onClick="userRemove()"> &nbsp;
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