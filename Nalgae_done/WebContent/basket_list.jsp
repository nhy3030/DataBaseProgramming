<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="model.basket.*" %>
<%@ include file="loginCheck.jsp" %>
<%
	@SuppressWarnings("unchecked") 
	List<Basket> basketList = (List<Basket>)request.getAttribute("basketList");
//List<Product> productList = (List<Product>)request.getAttribute("productList");
%>
<html>
<head>
<title>��ٱ��� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function basketRemove() {
	if ( confirm("���� �����Ͻðڽ��ϱ�?") ) {
		//request.setAttribute("product_id", product_id);
		f.command.value = "basketDelete";
		f.action = "product_remove.m2";
		f.submit();
	}
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<form name="f" method="post" action="basket_add.m2">
<input type="hidden" name="command" value="basketAddForm">
<table style="width: 100%">
  <tr>
  	<td width="20"></td>
    <td><img src="img/home-1.png" width="50">&nbsp;<a href="customer_main.jsp">����� ���� ��������</a></td>
    <!-- button -->
	  <table style="width: 100%">
		<tr>
			<td align="right">
				<input type="submit" value="��ٱ��Ͽ� ��ǰ �߰�" class="Button"/>
			</td>
		</tr>
	  </table>	
   <!-- <td><a href="productList.m2?command=productList">��ǰ �߰� �ϱ�</a><br/></td> --> 
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  
    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
    
  <tr>
	<td width="20"></td>
	<td>
	  <!--contents-->
	  <table style="width:100%">
		<tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>����� "<%= session.getAttribute("user_id") %>"���� ��ٱ��� ����Ʈ</b></td>
		</tr>
	  </table>  
	  <br>	
	  
	  <!-- list -->
	  <table style="width: 80%; background-color: Black" align="center">
		<tr>
			<td width="100" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">�߰� ��¥</td>
			<td width="400" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ��</td>
			<td width="100" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ٱ��Ͽ��� ����</td>
		</tr>
		<%
	if (basketList != null) {	
	  Iterator<Basket> basketIter = basketList.iterator();
	
	  //����� ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
	  while ( basketIter.hasNext() ) {
		Basket basket = (Basket)basketIter.next();
%>		  	
		<tr>
			<td width="100" align="center" bgcolor="ffffff" height="20">
				<%= basket.getBasket_date() %>
			</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10" align="center">
				<a href="basket_product_view.m2?product_id=<%= basket.getProduct_id() %>&command=basketProductView" class="product">
					<%= basket.getProduct_name() %>
				</a>
			</td>
			<td width="100" align="center" bgcolor="ffffff" height="20">
				<!-- <input type="button" name="product_id" value="<%= basket.getProduct_id() %>" onClick="basketRemove()"> -->
				<a href="basket_delete.m2?product_id=<%= basket.getProduct_id() %>&command=basketDelete" class="product">
					����
				</a>
			</td>
		</tr>

<%
	  }
	}
%>	  	
	  </table>
 	  <!-- /list -->	 
	  <br>
	  
	 	
	</td>
  </tr>
</table>  
</form>
</body>
</html>