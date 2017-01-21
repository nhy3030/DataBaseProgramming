<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="model.product.*"%>
<%@ include file="loginCheck.jsp"%>
<%
	@SuppressWarnings("unchecked")
	List<Product> productList = (List<Product>) request.getAttribute("productList");
%>
<html>
<head>
<title>��ǰ ����</title>
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
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">������ ���� ��������</a></td>
				<!-- button -->
				<table style="width: 100%">
					<tr>
						<td align="right"><input type="submit" value="��ǰ �߰�" class="button"/></td>
					</tr>
				</table>
				<!-- <td><a href="productList.m2?command=productList">��ǰ �߰� �ϱ�</a><br/></td> -->
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
					<select name="category_id">
							<option value="">ī�װ� ����</option>
							<option value="101">���ö�</option>
							<option value="102">������ġ/�ܹ���</option>
							<option value="103">�ָԹ�/���</option>
							<option value="201">��/����/����</option>
							<option value="202">��/���ݸ�/ĵ��</option>
							<option value="300">���̽�ũ��</option>
							<option value="401">�����Ļ�</option>
							<option value="402">���ַ�</option>
							<option value="403">�����</option>
							<option value="501">����</option>
							<option value="502">���̽��帵ũ</option>
							<option value="503">����ǰ</option>
							<option value="601">�Ǿ��ǰ</option>
							<option value="602">��Ȱ��ȭ</option>
					</select>
				</td>
				<td><input type="button" value="�˻�"
					onClick="productListSearchByCategory()"></td>
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>��ǰ ���� -
									����Ʈ</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table align="center" style="width: 80%; background-color: black">
						<tr>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">��ǰID</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ��</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">����</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">����</td>
						</tr>
						<%
							if (productList != null) {
								Iterator<Product> productIter = productList.iterator();

								//����� ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
								while (productIter.hasNext()) {
									Product product = (Product) productIter.next();
						%>
						<tr>
							<td width="190" align="center" bgcolor="ffffff" height="20">
								<%=product.getProduct_id()%>
							</td>
							<td width="200" bgcolor="ffffff" style="padding-left: 10"><a
								href="product_view.m2?product_id=<%=product.getProduct_id()%>&command=productView"
								class="product"> <%=product.getProduct_name()%>
							</a></td>
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