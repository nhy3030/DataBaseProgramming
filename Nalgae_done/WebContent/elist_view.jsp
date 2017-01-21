<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="model.*" %>
<%@page import="model.elist.*"%>
<%@ include file="loginCheck.jsp" %>
<%
	Elist elist = (Elist)request.getAttribute("elist");
%>
<html>
<head>
<title>진행 중인 이벤트 목록</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function elistList() {
	f.command.value = "elistList";
	f.action = "elist_list.m2";
	f.submit();
}

</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<form name="f" method="POST">
  <input type="hidden" name="command"/>
  <input type="hidden" name="elist_id" value="<%= elist.getElist_id() %>"/>
  <table style="width: 100%">
    <tr>
	  <td width="20"></td>
	  <td>
  	    <!--contents-->
	    <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>이벤트 종류에 대한 구체적 설명</b></td>
		  </tr>
	    </table>  
	    <br>	  
	    
	  	<table style="width: 100%; background-color: Black">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">이벤트 ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= elist.getEvent_id() %>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">내용</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= elist.getContent() %>
			</td>
		  </tr>	
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">할인율</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%=elist.getDiscount()%>
			</td>
		  </tr>
	 	</table>
	    <br>
	    
 	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="목록" onClick="elistList()"> 
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>