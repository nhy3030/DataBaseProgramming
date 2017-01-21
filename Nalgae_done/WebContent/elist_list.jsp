<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="model.elist.*"%>
<%@ include file="loginCheck.jsp"%>
<%
	@SuppressWarnings("unchecked")
	List<Elist> elistList = (List<Elist>) request.getAttribute("elistList");
%>
<html>
<head>
<title>진행 중인 이벤트 목록</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<form name="f" method="post" action="elist_write.m2">
		<input type="hidden" name="command" value="elistInsertForm">
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">관리자 메인 페이지로</a></td>
				<!-- button -->
				<table style="width: 100%">
					<tr>
						<td align="right"><input type="submit" value="이벤트 리스트 추가"  class="Button"/></td>
					</tr>
				</table>
				<!-- <td><a href="productList.m2?command=productList">물품 추가 하기</a><br/></td> -->
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>
									"<%=session.getAttribute("user_id") %>"님의 브랜드에서 진행 중인 이벤트 - 리스트</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table style="width: 80%; background-color: Black" align="center">
						<tr>
							<td width="100" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">이벤트 번호</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">이벤트 명</td>
							<td width="200" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">이벤트 종류</td>
							<td width="250" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">해당 물품</td>
							<td width="250" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">시작 날짜</td>
							<td width="250" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">완료 날짜</td>
							<td width="250" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">삭제하기</td>
						</tr>
						<%
							if (elistList != null) {
								Iterator<Elist> eventIter = elistList.iterator();

								//이벤트 리스트를 클라이언트에게 보여주기 위하여 출력.
								while (eventIter.hasNext()) {
									Elist elist = (Elist) eventIter.next();
						%>
						<tr>
							<td width="100" align="center" bgcolor="ffffff" height="20">
								<%=elist.getElist_id()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=elist.getElist_content()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=elist.getEvent_name()%></a>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=elist.getProduct_name()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=elist.getStart_date()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=elist.getEnd_date()%>
							</td>
							<td width="100" align="center" bgcolor="ffffff" height="20">
								<a href="elist_delete.m2?elist_id=<%= elist.getElist_id() %>&command=elistDelete" class="product">
									삭제
								</a>
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