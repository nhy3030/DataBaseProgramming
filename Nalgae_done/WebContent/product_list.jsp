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
<title>물품 관리</title>
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
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">관리자 메인 페이지로</a></td>
				<!-- button -->
				<table style="width: 100%">
					<tr>
						<td align="right"><input type="submit" value="물품 추가" class="button"/></td>
					</tr>
				</table>
				<!-- <td><a href="productList.m2?command=productList">물품 추가 하기</a><br/></td> -->
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="250" bgcolor="ffffff" style="padding-left: 10">
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
				<td><input type="button" value="검색"
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>물품 관리 -
									리스트</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table align="center" style="width: 80%; background-color: black">
						<tr>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">물품ID</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">가격</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">별점</td>
						</tr>
						<%
							if (productList != null) {
								Iterator<Product> productIter = productList.iterator();

								//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
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