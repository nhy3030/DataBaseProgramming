<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel=stylesheet href="user.css" type="text/css">
<title>������ ���� ������</title>
<script>
	function user_managing() {
		alert("����� ����� �ҷ����ڽ��ϴ�.");

		f.command.value = "list";
		f.action = "user_list.m2";
		f.submit();
	}
	function product_managing() {
		alert("�԰� ����� �ҷ����ڽ��ϴ�.");

		f.command.value = "productList";
		f.action = "user_productList.m2";
		f.submit();
	}
</script>
</head>
<body>
	<center>
		<form name="f" method="POST">

			<br> <img src="img/manager.PNG" width="470"> <br>
			<br>
			<hr style="border: #ffda44 8px dashed;"></hr>
			<br> <br>
			<table border=1 width="700" height="300" border="1" cellspacing=0 class="tt">
				<tr>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/boy-1.png" width="100"></td>
								<td><a href="receivingList.m2?command=receivingList">�԰�
										����</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/girl-2.png" width="100"></td>
								<td><a href="productList.m2?command=productList">��ǰ ����</a>
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
								<td><a href="eventList.m2?command=eventList">�̺�Ʈ ����</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/girl-4.png" width="100"></td>
								<td><a href="elistList.m2?command=elistList">&nbsp;���� ����<br>�̺�Ʈ
										����</a></td>
							</tr>
						</table>
					</td>
				</tr>


				<tr>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/boy.png" width="100"></td>
								<td><a href="List.m2?command=list">����� ����</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table border="0" width="350" height="200">
							<tr>
								<td align="center"><img src="img/girl.png" width="100"></td>
								<td><a href="logout.m2?command=logout">�α׾ƿ�</a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br> <br> <input type=reset value="�ڷΰ���"
				onClick="history.go(-1);" class="Button">


		</form>
	</center>
</body>
</html>