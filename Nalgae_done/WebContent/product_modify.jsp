<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="model.*" %>
<%@page import="model.product.*"%>
<%@ include file="loginCheck.jsp" %>
<%
	Product product = (Product)request.getAttribute("product");
%>
<html>
<head>
<title>��ǰ ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function productModify() {
	f.command.value = "productUpdate";
	f.action = "product_modify.m2";
	f.submit();
}

function productList() {
	f.command.value = "productList";
	f.action = "product_list.m2";
	f.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<!-- write Form  -->
<form name="f" method="POST">
  <input type="hidden" name="command"/>
  <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>"/>	  
  <table style="width: 100%">
	<tr>
	  <td width="20"></td>
	  <td>
        <!--contents-->
	    <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>��ǰ ���� - ��ǰ ����</b></td>
		  </tr>
	    </table>  
	    <br>
	  
	    <table style="background-color: Black">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<%= product.getProduct_id() %>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ��</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="product_name" value="<%= product.getProduct_name() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ ����</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="product_price" value="<%= product.getProduct_price() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">ī�װ�</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10" style="background-color: #ffda44">
				<!-- <input type="text" style="width: 240" name="category_id" value="<%= product.getCategory_id() %>">-->
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
		  </tr>	  
	    </table>
	    <br>
	  
	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="����" onClick="productModify()"> &nbsp;
			<input type="button" value="���" onClick="productList()">
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>