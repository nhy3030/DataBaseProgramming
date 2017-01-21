<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel=stylesheet href="user.css" type="text/css">
<title>사용자 메인 페이지</title>
<script>
	function user_managing() {
		alert("사용자 목록을 불러오겠습니다.");

		f.command.value = "list";
		f.action = "user_list.m2";
		f.submit();
	}
	function product_managing() {
		alert("입고 목록을 불러오겠습니다.");

		f.command.value = "productList";
		f.action = "user_productList.m2";
		f.submit();
	}
</script>
</head>
<body>
	<center>
		<form name="f" method="POST">

			<br> <img src="img/user.PNG" width="470"> <br> <br>
			사용자 "<%=session.getAttribute("user_id") %>"님 환영합니다. <br> <br>
			<hr style="border: #ffda44 8px dashed;"></hr>
			<br> <br>
			<table border=1 width="700" height="300" border="1" cellspacing=0
				class="tt">
				<tr>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/boy-1.png" width="100"></td>
								<td><a href="basketList.m2?command=basketList">내장바구니보기</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/girl-2.png" width="100"></td>
								<td><a href="recommendList.m2?command=recommendList">추천상품보기</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/boy-4.png" width="100"></td>
								<td><a href="searchPage.jsp">상품검색</a></td>
							</tr>
						</table>
					</td>
					<td align="center">
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/girl.png" width="100"></td>
								<td><a href="recommendList.m2?command=customerElistList">진행 중인 이벤트 보기</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr align="center">
					<td colspan="2">
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/girl-4.png" width="100"></td>
								<td><a href="logout.m2?command=logout">로그아웃</a></td>
							</tr>
						</table>
					</td>
					
				</tr>
				
				
			</table>
			<br> <br> <input type=reset value="뒤로가기"
				onClick="history.go(-1);" class="Button">


		</form>
	</center>


</body>
</html>