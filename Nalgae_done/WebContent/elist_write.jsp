<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String[] elist_recs = request.getParameterValues("elist_product");
	session.setAttribute("elist_recs", elist_recs);
%>
<html>
<head>
<title>�̺�Ʈ �߰�</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function elistCreate() {
	/*if ( f.elist_id.value == "" ) {
		alert("�̺�Ʈ ��ȣ�� �Է��Ͻʽÿ�.");
		f.elist_id.focus();
		return false;
	} 
	if ( f.elist_content.value == "" ) {
		alert("�̺�Ʈ ���� �Է��Ͻʽÿ�.");
		f.elist_content.focus();
		return false;
	} 
	if ( f.event_id.value == "" ) {
		alert("�̺�Ʈ ������ �Է��Ͻʽÿ�.");
		f.event_name.focus();
		return false;
	}
	*/
	f.command.value = "elistInsert";
	f.action = "elist_list.m2";
	f.submit();
}

function elistList() {
	f.command.value = "elistList";
	f.action = "elist_list.m2";
	f.submit();
}

function RecSearchByCategory() {
	if (f.category_id.value == "") {
		alert("��ǰ ������ �������ּ���");
		return false;
	}
	f.command.value = "RecSearchByCategory";
	f.action = "rec_search_by_category.m2";
	f.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<!-- write Form  -->
<form name="f" method="POST" action="elist_list.m2">
<input type="hidden" name="command" value="elistInsert"/>
<table style="width: 100%">
  <tr>
    <td width="20"></td>
	<td>
      <!--contents-->
	  <table style="width: 100%">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>���� ���� �̺�Ʈ ���� - �̺�Ʈ �߰�</b></td>
		  </tr>
	  </table>  
	  <br>
	 
	  <!--  exception ��ü�� ���޵� ��� �����޽����� ��� -->
	  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	  </c:if>
	  <br>
	  
	  <table style="background-color: Black">
	  	<tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�ش� ��ǰ</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<!--  <input type="text" style="width: 240;" name="rec_id">-->
				<a href="elist_addlist.m2?command=elistRecAddForm">�԰����̺� ����</a><br>
				<%
					if(elist_recs != null) {
						for(int i=0;i<elist_recs.length;i++) {
							out.println(elist_recs[i]);
							//out.println("<input type=\"hidden\" name=\"elist_rec\" values=\"elist_recs[i]\">" + elist_recs[i]);
							
						}
						request.setAttribute("elist_recs", elist_recs);
					}
				%>
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�̺�Ʈ ��</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="elist_content">
			</td>
		  </tr>
		  
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�̺�Ʈ ����</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10"><select
					name="event_id">
						<option value="">�̺�Ʈ ����</option>
						<option value="112">1+1��ǰ</option>
						<option value="212">2+1��ǰ</option>
						<option value="313">3+1��ǰ</option>
						<option value="101">10%����</option>
						<option value="201">20%����</option>
						<option value="301">30%����</option>
						<option value="401">40%����</option>
						<option value="501">50%����</option>
						<option value="601">60%����</option>
						<option value="701">70%����</option>
						<option value="801">80%����</option>
						<option value="901">90%����</option>
				</select>
				</td>
		  </tr>
		  <!--  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">�ش� ��ǰ</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="button" value="�˻�"
					onClick="RecSearchByCategory()">
			</td>
		  </tr>
		  -->
		  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">���� ��¥</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="start_date">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">�Ϸ� ��¥</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="end_date">
			</td>
		  </tr>
	  </table>
	  <br>
	  *���۳�¥�� �Ϸᳯ¥�� " 2016-12-21 " ������ �����ּ���.<br>
	  <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="submit" value="�̺�Ʈ ���" class="Button"> &nbsp;
			<input type="button" value="���" onClick="elistList()">
			</td>
		  </tr>
	  </table>
	</td>
  </tr>
</table>  
</form>
</body>
</html>