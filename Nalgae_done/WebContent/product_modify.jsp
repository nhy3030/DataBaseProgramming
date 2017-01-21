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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>물품 관리 - 물품 수정</b></td>
		  </tr>
	    </table>  
	    <br>
	  
	    <table style="background-color: Black">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<%= product.getProduct_id() %>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="product_name" value="<%= product.getProduct_name() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품 가격</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="product_price" value="<%= product.getProduct_price() %>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">카테고리</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10" style="background-color: #ffda44">
				<!-- <input type="text" style="width: 240" name="category_id" value="<%= product.getCategory_id() %>">-->
				<select name="category_id">
					 <option value="">카테고리 선택</option>
    				 <option value="101">도시락</option>
    				 <option value="102">샌드위치/햄버거</option>
   					 <option value="103">주먹밥/김밥</option>
   					 <option value="201">빵/스낵/비스켓</option>
    				 <option value="202">껌/초콜릿/캔디</option>
   					 <option value="300">아이스크림</option>
   					 <option value="401">가공식사</option>
    				 <option value="402">안주류</option>
   					 <option value="403">식재료</option>
   					 <option value="501">음료</option>
    				 <option value="502">아이스드링크</option>
   					 <option value="503">유제품</option>
   					 <option value="601">의약외품</option>
    				 <option value="602">생활잡화</option>
				</select>
			</td>
		  </tr>	  
	    </table>
	    <br>
	  
	    <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="button" value="수정" onClick="productModify()"> &nbsp;
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