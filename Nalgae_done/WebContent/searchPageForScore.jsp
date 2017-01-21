<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="model.product.*"%>
<%@page import="model.search.*" %>
<%@ include file="loginCheck.jsp"%>
<%
	@SuppressWarnings("unchecked")
	List<Search> productList = (List<Search>) request.getAttribute("productList");
%>
<html>
<head>
<title>상품 검색하기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">

<script>
	function productListSearchByCategory() {
		if (f.category_id.value == "") {
			alert("카테고리를 선택해주세요");
			return false;
		}
		f.command.value = "productListSearchByCategory";
		f.action = "product_list_search_by_category.m2";
		f.submit();
	}
	
	function searchNewProduct() {
		f.command.value = "searchNewProduct";
		f.action = "search_new_product.m2";
		f.submit();
	}
	
	function searchHighScore() {
		f.command.value = "searchHighScore";
		f.action = "search_high_score.m2";
		f.submit();
	}
	
	function searchHighBasket() {
		f.command.value = "searchHighBasket";
		f.action = "search_high_basket.m2";
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
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="customer_main.jsp">사용자 메인 페이지로</a></td>
				<!-- button -->
				<table style="width: 100%">
					<tr>
						<td align="right"></td>
					</tr>
				</table>
				<!-- <td><a href="productList.m2?command=productList">물품 추가 하기</a><br/></td> -->
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>


			<tr>
				<td>
					<!--  신상품 검색 <br> -->
					<input type="button" value="신상품 검색" onClick="searchNewProduct()">&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<!-- 인기순 - 장바구니에 많이 담겨있는..<br> -->
					<input type="button" value="많이 찜한순" onClick="searchHighBasket()">&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<!-- 별점순 - 좋아요 많은 순서대로<br> -->
					<input type="button" value="좋아요 많은 순 검색" onClick="searchHighScore()">&nbsp;&nbsp;&nbsp;
				</td>
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>물품검색(입고된것만)</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table style="width: 100%; background-color: Black">
						<tr>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">가격</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44"><b>좋아요</b></td>
						</tr>
						<%
							if (productList != null) {
								Iterator<Search> productIter = productList.iterator();

								//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
								while (productIter.hasNext()) {
									Search product = (Search) productIter.next();
						%>
						<tr>
							<td width="200" bgcolor="ffffff" style="padding-left: 10"><a
								href="basket_product_view.m2?product_id=<%=product.getProduct_id()%>&command=basketProductViewForAdd"
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