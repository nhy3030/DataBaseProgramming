<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="model.product.*"%>
<%@ include file="loginCheck.jsp"%>
<%
	@SuppressWarnings("unchecked")
	List<Product> recommendList = (List<Product>) request.getAttribute("recommendList");
	List<Product> priceList = (List<Product>) request.getAttribute("priceList");
%>
<html>
<head>
<title>��ǰ ��õ</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">

<script>
	function productListSearchByCategory() {
		if (f.category_id.value == "") {
			alert("ī�װ��� �������ּ���");
			return false;
		}
		f.command.value = "productListSearchByCategory";
		f.action = "product_list_search_by_category.m2";
		f.submit();
	}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<br>
	<form name="f" method="post" action="product_write.m2">
		<input type="hidden" name="command" value="productInsertForm">
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="customer_main.jsp">����� ���� ��������</a><br /></td>
				<!-- button -->
				
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>������ �ֵ�<br><br>&nbsp;
							����� "<%= session.getAttribute("user_id") %>"�Կ��� ��õ�ϴ� ������ �ֵ�</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table style="width: 80%; background-color: Black" align="center">
						<tr>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">��ǰ��</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��� �귣��</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ ����</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">���� ����</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�̺�Ʈ �̸�</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�ƺ�Ʈ ����</td>
						</tr>
						<%
							if (recommendList != null) {
								Iterator<Product> productIter = priceList.iterator();

								//����� ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
								while (productIter.hasNext()) {
									Product product = (Product) productIter.next();
						%>
						<tr>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<a
								href="product_view.m2?product_id=<%=product.getProduct_id()%>&command=basketProductViewForAdd"
								class="product"><%=product.getProduct_name()%></a>
							</td>
							<td width="200" bgcolor="ffffff" style="padding-left: 10">
								<%=product.getBrand_name()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getProduct_price()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getDc_price()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getElist_content()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getContent()%>
							</td>
						</tr>
						<%
							}
							}
						%>
					</table> <!-- /list --> <br>
				</td>
			</tr>
			
			

			<tr>
				<td width="20"></td>
				<td>
					<!--contents-->
					<table style="width: 100%">
						<tr>
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>���� ī�װ� �Ż�ǰ ��õ<br><br>&nbsp;
							����� "<%= session.getAttribute("user_id") %>"���� ���� ī�װ� : <%=recommendList.iterator().next().getCategory_name() %></b></td>
						</tr>
					</table> <br> <!-- list -->
					<table style="width: 80%; background-color: Black" align="center">
						<tr>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">��ǰID</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ��</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�귣��</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">����</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">����</td>
						</tr>
						<%
							if (recommendList != null) {
								Iterator<Product> productIter = recommendList.iterator();

								//����� ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
								while (productIter.hasNext()) {
									Product product = (Product) productIter.next();
						%>
						<tr>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getProduct_id()%>
							</td>
							<td width="200" bgcolor="ffffff" style="padding-left: 10"><a
								href="product_view.m2?product_id=<%=product.getProduct_id()%>&command=basketProductViewForAdd"
								class="product"> <%=product.getProduct_name()%>
							</a></td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getBrand_name()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getProduct_price()%>
							</td>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getScore()%>
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