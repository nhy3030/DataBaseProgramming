<%@page contentType="text/html; charset=euc-kr" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="model.receiving.*"%>
<%@ include file="loginCheck.jsp" %>
<%
	@SuppressWarnings("unchecked") 
	List<Receiving> receivingList = (List<Receiving>)request.getAttribute("receivingList");
%>
<html>
<head>
<title>입고 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">

<script>
function ReceivingListSearchByCategory() {
	if(f.category_id.value == "") {
		alert("카테고리를 선택해주세요");
		return false;
	}
	f.command.value = "ReceivingListSearchByCategory";
	f.action = "receiving_list_search_by_category.m2";
	f.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<form name="f" method="post" action="receiving_write.m2">
<input type="hidden" name="command" value="ReceivingInsertList">
<table style="width: 100%">
  <tr>
  	<td width="20"></td>
    <td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">관리자 메인 페이지로</a></td>
    <!-- button -->
	  <table style="width: 100%">
		<tr>
			<td align="right">
				<input type="submit" value="입고품 추가" class="tt"/>
			</td>
		</tr>
	  </table>	
   <!-- <td><a href="productList.m2?command=productList">물품 추가 하기</a><br/></td> --> 
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <!-- 
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
		<td><input type="button" value="검색" onClick="receivingListSearchByCategory()"></td>
	  </tr>
    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  
  
  
  
  <tr>
	<td width="20"></td>
	<td>
	  <!--contents-->
	  <table style="width:100%">
		<tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>입고품 관리 - 리스트</b></td>
		</tr>
	  </table>  
	  <br>	
	  
	  <!-- list -->
	  <table style="width: 80%; background-color: black" align="center">
		<tr bgcolor="#ffda44">
			<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">입고 번호</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">입고날짜</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">브랜드</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">삭제</td>
		</tr>
<%
	if (receivingList != null) {	
	  Iterator<Receiving> receivingIter = receivingList.iterator();
	
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	  while ( receivingIter.hasNext() ) {
		Receiving receiving = (Receiving)receivingIter.next();
%>		  	
		<tr>
			<td width="190" align="center" bgcolor="ffffff" height="20">
				<%= receiving.getRec_id() %>
			</td>
		
			<td width="190" align="center" bgcolor="ffffff" height="20">
				<%= receiving.getRec_date() %>
			</td>
			<td width="200" bgcolor="ffffff" style="padding-left: 10">
				<%= receiving.getBrand_name() %>
			</td>
			<td width="190" align="center" bgcolor="ffffff" height="20">
				<a href="basket_product_view.m2?product_id=<%= receiving.getProduct_id() %>&command=basketProductView" class="product">
				<%= receiving.getProduct_name() %>
				</a>
			</td>
			
			<td width="190" align="center" bgcolor="ffffff" height="20">
				<a href="receiving_delete.m2?rec_id=<%= receiving.getRec_id() %>&command=receivingDelete" class="product">
					삭제하기
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