<%@page contentType="text/html; charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>���������-�α���������</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet href="user.css" type="text/css">
<script>
	function userCreate() {
		f.command.value = "insertForm";
		f.action = "user_write.m2";
		f.submit();
	}

	function login() {
		if (f.user_id.value == "") {
			alert("����� ���̵� �Է��Ͻʽÿ�.");
			f.user_id.focus();
			return false;
		}
		if (f.user_pwd.value == "") {
			alert("��й�ȣ�� �Է��Ͻʽÿ�.");
			f.user_pwd.focus();
			return false;
		}

		/*
		if(f.chk_login.value == "usr") {
			f.command.value = "mlist";
			f.action = "user_mlist.m2";
			f.submit();	
		}
		
		if(f.chk_login.value == "") {
			alert("�α��� ����� �����ϼ���.");
			return false;
		}
		 */
		var obj = document.getElementsByName("chk_login");

		for (var i = 0; i < 1; i++) {
			if (obj[i].checked == true && obj[i].value == "mng") {
				//������ �α���
				f.command.value = "mlist";
				f.action = "user_mlist.m2";
				f.submit();
			} else {
				//����� �α���
				f.command.value = "clist";
				f.action = "user_clist.m2";
				f.submit();
			}

		}
	}
</script>
</head>
<body>
	<form name="f" method="POST">
		<input type="hidden" name="command" />
		<table align="center" style="width: 100%">

			<tr>
				<td colspan="2" align="center"><img src="img/title.PNG"
					width="500"></td>
			</tr>

 <!--  exception ��ü�� ���޵� ��� �����޽����� ��� --> <c:if
						test="${not empty exception}">
						<font color="red"><c:out value="${exception.getMessage()}" /></font>
					</c:if>


			<tr>
				<td align="right"><img src="img/check.PNG" width="300"></td>
				<td align="center"><table class="button">
						<tr height="40" bgcolor="yellow">
							<td width="150" align="center" colspan=2><input
								type="radio" name="chk_login" value="mng">������
								<input type="radio" name="chk_login" value="usr" checked="checked">�����</td>
						</tr>

						<tr height="40">
							<td width="150" align="center">����� ���̵�</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="user_id"></td>
						</tr>
						<tr height="40">
							<td width="150" align="center">��й�ȣ</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="password" style="width: 240" name="user_pwd"></td>
						</tr>
					</table>
			</td>
			</tr>

			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="center"><input type="button" value="�α���" onClick="login()">
					&nbsp; <input type="button" value="ȸ������" onClick="userCreate()">
				</td>
				<p></p><p></p>
			</tr>

		</table>
	</form>
</body>
</html>