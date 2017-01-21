<%@page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String[] elist_recs = request.getParameterValues("elist_product");
	session.setAttribute("elist_recs", elist_recs);
%>
<html>
<head>
<title>이벤트 추가</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
function elistCreate() {
	/*if ( f.elist_id.value == "" ) {
		alert("이벤트 번호를 입력하십시요.");
		f.elist_id.focus();
		return false;
	} 
	if ( f.elist_content.value == "" ) {
		alert("이벤트 명을 입력하십시요.");
		f.elist_content.focus();
		return false;
	} 
	if ( f.event_id.value == "" ) {
		alert("이벤트 종류를 입력하십시요.");
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
		alert("물품 종류를 선택해주세요");
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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>진행 중인 이벤트 관리 - 이벤트 추가</b></td>
		  </tr>
	  </table>  
	  <br>
	 
	  <!--  exception 객체가 전달된 경우 오류메시지를 출력 -->
	  <c:if test="${not empty exception}">
	  	<font color="red"><c:out value="${exception.getMessage()}" /></font>
	  </c:if>
	  <br>
	  
	  <table style="background-color: Black">
	  	<tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">해당 물품</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<!--  <input type="text" style="width: 240;" name="rec_id">-->
				<a href="elist_addlist.m2?command=elistRecAddForm">입고테이블 보기</a><br>
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
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">이벤트 명</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="elist_content">
			</td>
		  </tr>
		  
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">이벤트 종류</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10"><select
					name="event_id">
						<option value="">이벤트 종류</option>
						<option value="112">1+1상품</option>
						<option value="212">2+1상품</option>
						<option value="313">3+1상품</option>
						<option value="101">10%할인</option>
						<option value="201">20%할인</option>
						<option value="301">30%할인</option>
						<option value="401">40%할인</option>
						<option value="501">50%할인</option>
						<option value="601">60%할인</option>
						<option value="701">70%할인</option>
						<option value="801">80%할인</option>
						<option value="901">90%할인</option>
				</select>
				</td>
		  </tr>
		  <!--  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">해당 물품</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="button" value="검색"
					onClick="RecSearchByCategory()">
			</td>
		  </tr>
		  -->
		  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">시작 날짜</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="start_date">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE" style="background-color: #ffda44">완료 날짜</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="end_date">
			</td>
		  </tr>
	  </table>
	  <br>
	  *시작날짜와 완료날짜는 " 2016-12-21 " 형식을 지켜주세요.<br>
	  <table style="width: 100%">
		  <tr>
			<td align="center">
			<input type="submit" value="이벤트 등록" class="Button"> &nbsp;
			<input type="button" value="목록" onClick="elistList()">
			</td>
		  </tr>
	  </table>
	</td>
  </tr>
</table>  
</form>
</body>
</html>