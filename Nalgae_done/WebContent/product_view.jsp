<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="model.*" %>
<%@page import="model.product.*"%>
<%@ include file="loginCheck.jsp" %>
<%
	Product product = (Product)request.getAttribute("product");
%>
<html>
<head>
<title>물품 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function productList() {
	f.command.value = "productList";
	f.action = "product_list.m2";
	f.submit();
}

function productModify() {
	f.command.value = "productUpdateForm";
	f.action = "product_modify.m2";
	f.submit();
}

function productRemove() {
	if ( confirm("정말 삭제하시겠습니까?") ) {
		f.command.value = "productDelete";
		f.action = "product_remove.m2";
		f.submit();
	}
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>물품 관리 - 물품 정보보기</b></td>
		  </tr>
	    </table>  
	    <br>	  
	    
	  	<table style="width: 100%; background-color: black">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">물품ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= product.getProduct_id() %>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">물품명</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= product.getProduct_name() %>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">물품가격</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= product.getProduct_price() %>
			</td>
		  </tr>	
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">카테고리</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= product.getCategory_id() %>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">별점</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<%= product.getScore() %>
			</td>
		  </tr>
	 	</table>
	    <br>
	    
 	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="수정" onClick="productModify()"> &nbsp;
			<input type="button" value="삭제" onClick="productRemove()"> &nbsp;
			<input type="button" value="목록" onClick="productList()"> 
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>