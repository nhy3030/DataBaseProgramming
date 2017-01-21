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
<title>물품 추천</title>
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
				<td><img src="img/home-1.png" width="50">&nbsp;<a href="customer_main.jsp">사용자 메인 페이지로</a><br /></td>
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>오늘의 핫딜<br><br>&nbsp;
							사용자 "<%= session.getAttribute("user_id") %>"님에게 추천하는 오늘의 핫딜</b></td>
						</tr>
					</table> <br> <!-- list -->
					<table style="width: 80%; background-color: Black" align="center">
						<tr>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">물품명</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">행사 브랜드</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품 가격</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">할인 가격</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">이벤트 이름</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">아벤트 내용</td>
						</tr>
						<%
							if (recommendList != null) {
								Iterator<Product> productIter = priceList.iterator();

								//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
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
							<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>관심 카테고리 신상품 추천<br><br>&nbsp;
							사용자 "<%= session.getAttribute("user_id") %>"님의 관심 카테고리 : <%=recommendList.iterator().next().getCategory_name() %></b></td>
						</tr>
					</table> <br> <!-- list -->
					<table style="width: 80%; background-color: Black" align="center">
						<tr>
							<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">물품ID</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">브랜드</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">가격</td>
							<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">별점</td>
						</tr>
						<%
							if (recommendList != null) {
								Iterator<Product> productIter = recommendList.iterator();

								//사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
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