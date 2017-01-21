<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="model.receiving.*"%>
<%@ include file="loginCheck.jsp"%>
<%
	@SuppressWarnings("unchecked")
	List<Receiving> recList = (List<Receiving>) request.getAttribute("receivingList");
%>
<html>
<head>
<title>이벤트 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">

<script>
	function basketByCategory() {
		
		f.command.value = "basketByCategory";
		f.action = "basket_add_list_by_category.m2";
		f.submit();
	}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<br>
	<form name="f" method="post" action="elist_write.jsp">
		<!-- <input type="hidden" name="command" value="elistInsertForm">-->
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">관리자 메인 페이지로</a></td>
				<!-- button -->
				<table style="width: 100%">
					<tr>
						<td align="right"><input type="submit" value="이벤트 물품에 추가" class="button"/></td>
					</tr>
				</table>
				<!-- <td><a href="productList.m2?command=productList">물품 추가 하기</a><br/></td> -->
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>이벤트리스트 물품 추가</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table style="width: 80%; background-color: black" align="center">
						<tr>
							<td width="30" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">추가</td>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">물품ID</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">가격</td>
						</tr>
						<%
							if (recList != null) {
								Iterator<Receiving> recIter = recList.iterator();

								//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
								while (recIter.hasNext()) {
									Receiving rec = (Receiving) recIter.next();
						%>
						<tr>
							<td width="30" align="center" bgcolor="ffffff" height="20">
								<input type="checkbox" name="elist_product" value="<%=rec.getRec_id() %>">
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=rec.getProduct_id()%>
							</td>
							<td width="200" bgcolor="ffffff" style="padding-left: 10"><a
								href="product_view.m2?product_id=<%=rec.getProduct_id()%>&command=basketProductView"
								class="product"> <%=rec.getProduct_name()%>
							</a></td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=rec.getProduct_price()%>
							</td>
						</tr>
						<%
							}
							}
						%>
					</table> <!-- /list --> <br>

				</td>
			</tr>
		</table>
	</form>
</body>
</html>