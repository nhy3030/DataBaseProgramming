<%@page contentType="text/html; charset=euc-kr"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>편의점모아-로그인페이지</title>
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
			alert("사용자 아이디를 입력하십시요.");
			f.user_id.focus();
			return false;
		}
		if (f.user_pwd.value == "") {
			alert("비밀번호를 입력하십시요.");
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
			alert("로그인 방법을 선택하세요.");
			return false;
		}
		 */
		var obj = document.getElementsByName("chk_login");

		for (var i = 0; i < 1; i++) {
			if (obj[i].checked == true && obj[i].value == "mng") {
				//관리자 로그인
				f.command.value = "mlist";
				f.action = "user_mlist.m2";
				f.submit();
			} else {
				//사용자 로그인
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

 <!--  exception 객체가 전달된 경우 오류메시지를 출력 --> <c:if
						test="${not empty exception}">
						<font color="red"><c:out value="${exception.getMessage()}" /></font>
					</c:if>


			<tr>
				<td align="right"><img src="img/check.PNG" width="300"></td>
				<td align="center"><table class="button">
						<tr height="40" bgcolor="yellow">
							<td width="150" align="center" colspan=2><input
								type="radio" name="chk_login" value="mng">관리자
								<input type="radio" name="chk_login" value="usr" checked="checked">사용자</td>
						</tr>

						<tr height="40">
							<td width="150" align="center">사용자 아이디</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="user_id"></td>
						</tr>
						<tr height="40">
							<td width="150" align="center">비밀번호</td>
							<td width="250" bgcolor="ffffff" style="padding-left: 10"><input
								type="password" style="width: 240" name="user_pwd"></td>
						</tr>
					</table>
			</td>
			</tr>

			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="center"><input type="button" value="로그인" onClick="login()">
					&nbsp; <input type="button" value="회원가입" onClick="userCreate()">
				</td>
				<p></p><p></p>
			</tr>

		</table>
	</form>
</body>
</html>