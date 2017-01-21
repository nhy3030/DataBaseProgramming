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
<title>장바구니 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function basketRemove() {
	if ( confirm("정말 삭제하시겠습니까?") ) {
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
    <td><img src="img/home-1.png" width="50">&nbsp;<a href="customer_main.jsp">사용자 메인 페이지로</a></td>
    <!-- button -->
	  <table style="width: 100%">
		<tr>
			<td align="right">
				<input type="submit" value="장바구니에 물품 추가" class="Button"/>
			</td>
		</tr>
	  </table>	
   <!-- <td><a href="productList.m2?command=productList">물품 추가 하기</a><br/></td> --> 
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  
    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
    
  <tr>
	<td width="20"></td>
	<td>
	  <!--contents-->
	  <table style="width:100%">
		<tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 "<%= session.getAttribute("user_id") %>"님의 장바구니 리스트</b></td>
		</tr>
	  </table>  
	  <br>	
	  
	  <!-- list -->
	  <table style="width: 80%; background-color: Black" align="center">
		<tr>
			<td width="100" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">추가 날짜</td>
			<td width="400" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
			<td width="100" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">장바구니에서 삭제</td>
		</tr>
		<%
	if (basketList != null) {	
	  Iterator<Basket> basketIter = basketList.iterator();
	
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
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
					삭제
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