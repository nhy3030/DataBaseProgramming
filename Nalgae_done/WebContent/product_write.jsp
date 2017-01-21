<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>��ǰ ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function productCreate() {
	if ( f.product_name.value == "" ) {
		alert("��ǰ �̸��� �Է��Ͻʽÿ�.");
		f.product_name.focus();
		return false;
	} 
	if ( f.product_price.value == "" ) {
		alert("��ǰ ������ �Է��Ͻʽÿ�.");
		f.product_price.focus();
		return false;
	}
	if ( f.category_id.value == "" ) {
		alert("ī�װ��� �Է��Ͻʽÿ�.");
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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>��ǰ ���� - ��ǰ �߰�</b></td>
		  </tr>
	  </table>  
	  <br>
	 
	  <!--  exception ��ü�� ���޵� ��� �����޽����� ��� -->
	  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	  </c:if>
	  <br>
	  
	  <table style="background-color: black">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ��</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="product_name">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ����</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="product_price">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">ī�װ�</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<!-- <input type="" style="width: 240" name="category_id"> -->
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
			<input type="button" value="��ǰ ���" onClick="productCreate()"> &nbsp;
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