<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="model.event.*"%>
<%@ include file="loginCheck.jsp"%>
<%
	@SuppressWarnings("unchecked")
	List<Event> eventList = (List<Event>) request.getAttribute("eventList");
%>
<html>
<head>
<title>�̺�Ʈ ���</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<form name="f" method="post" action="event_write.m2">
		<input type="hidden" name="command" value="eventInsertForm">
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">������ ���� ��������</a></td>
				<!-- button -->
				<table style="width: 100%">
					<tr>
						<td align="right"><input type="submit" value="�̺�Ʈ �߰�" class="button"/></td>
					</tr>
				</table>
				<!-- <td><a href="productList.m2?command=productList">��ǰ �߰� �ϱ�</a><br/></td> -->
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="20"></td>
				<td>
					<!--contents-->
					<table style="width: 100%">
						<tr>
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>�̺�Ʈ ���� -
									����Ʈ</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table align="center" style="width: 80%; background-color: black">
						<tr>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�̺�ƮID</td>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">�̺�Ʈ��</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">����</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">������</td>
						</tr>
						<%
							if (eventList != null) {
								Iterator<Event> eventIter = eventList.iterator();

								//�̺�Ʈ ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
								while (eventIter.hasNext()) {
									Event event = (Event) eventIter.next();
						%>
						<tr>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=event.getEvent_id()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<a
								href="event_view.m2?event_id=<%=event.getEvent_id()%>&command=eventView"
								class="event"><%=event.getEvent_name()%></a>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=event.getContent()%> <!-- </a> -->
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=event.getDiscount()%>
							</td>
						</tr>
						<%
							}
							}
						%>
					</table> <!-- /list --> <br> <!-- button -->
				</td>
			</tr>
		</table>
	</form>
</body>
</html>