<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>물품 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function productCreate() {
	if ( f.product_name.value == "" ) {
		alert("물품 이름을 입력하십시요.");
		f.product_name.focus();
		return false;
	} 
	if ( f.product_price.value == "" ) {
		alert("물품 가격을 입력하십시요.");
		f.product_price.focus();
		return false;
	}
	if ( f.category_id.value == "" ) {
		alert("카테고리를 입력하십시요.");
		f.category_id.focus();
		return false;
	}
	
	f.command.value = "productInsert";
	f.action = "product_write.m2";
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
<table style="width: 100%">
  <tr>
    <td width="20"></td>
	<td>
      <!--contents-->
	  <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>물품 관리 - 물품 추가</b></td>
		  </tr>
	  </table>  
	  <br>
	 
	  <!--  exception 객체가 전달된 경우 오류메시지를 출력 -->
	  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	  </c:if>
	  <br>
	  
	  <table style="background-color: black">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품명</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="product_name">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">물품가격</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="product_price">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">카테고리</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<!-- <input type="" style="width: 240" name="category_id"> -->
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
			<input type="button" value="물품 등록" onClick="productCreate()"> &nbsp;
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