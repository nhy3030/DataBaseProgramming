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
<title>�԰� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">

<script>
function ReceivingListSearchByCategory() {
	if(f.category_id.value == "") {
		alert("ī�װ��� �������ּ���");
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
    <td><img src="img/home-1.png" width="50">&nbsp;<a href="manager_main.jsp">������ ���� ��������</a></td>
    <!-- button -->
	  <table style="width: 100%">
		<tr>
			<td align="right">
				<input type="submit" value="�԰�ǰ �߰�" class="tt"/>
			</td>
		</tr>
	  </table>	
   <!-- <td><a href="productList.m2?command=productList">��ǰ �߰� �ϱ�</a><br/></td> --> 
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <!-- 
  <tr>
	  	<td width="250" bgcolor="ffffff" style="padding-left: 10">
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
		<td><input type="button" value="�˻�" onClick="receivingListSearchByCategory()"></td>
	  </tr>
    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  
  
  
  
  <tr>
	<td width="20"></td>
	<td>
	  <!--contents-->
	  <table style="width:100%">
		<tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>�԰�ǰ ���� - ����Ʈ</b></td>
		</tr>
	  </table>  
	  <br>	
	  
	  <!-- list -->
	  <table style="width: 80%; background-color: black" align="center">
		<tr bgcolor="#ffda44">
			<td width="190" align="center" bgcolor="E6ECDE" height="22" style="background-color: #ffda44">�԰� ��ȣ</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�԰�¥</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�귣��</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">��ǰ��</td>
			<td width="200" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">����</td>
		</tr>
<%
	if (receivingList != null) {	
	  Iterator<Receiving> receivingIter = receivingList.iterator();
	
	  //����� ����Ʈ�� Ŭ���̾�Ʈ���� �����ֱ� ���Ͽ� ���.
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
					�����ϱ�
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