<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>学生信息登录界面</title>
<script type="text/javascript">
	function resetvalue() {
		document.getElementById("userName").value = "";
		document.getElementById("password").value = "";
	}
</script>
</head>
<body>
	<div align="center" style="padding-top: 50px;">
		<form action="login" method="post">
			<table width="740" height="500" background="images/login.jpg">
				<tr height="170">
					<td colspan="4"></td>
				</tr>
				<tr height="10">
					<td width="35%"></td>
					<td width="10%">用户名：</td>
					<td><input type="text" id="userName" name="userName"
						value="${userName}" /></td>
					<td width="35%"></td>
				</tr>
				<tr height="10">
					<td width="35%"></td>
					<td width="10%">密码：</td>
					<td><input type="password" id="password" name="password"
						value="${password}"></td>
					<td width="35%"></td>
				</tr>
				<tr height="10">
					<td width="35%"></td>
					<td width="10%">验证码：</td>
					<td><input id="input" name="input" maxlength="4" /><img
						border=0 src="image.jsp"></td>
					<td width="35%"></td>
				</tr>
				<tr height="10">
					<td width="40%"></td>
					<td width="10%"><input type="submit" value="登录"></td>
					<td><input type="button" value="重置" onclick="resetvalue()" /></td>
					<td width="30%"></td>
				</tr>

				<tr height="10">
					<td width="40%"></td>
					<td colspan="3"><font color="red">${error }</font></td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>

		</form>
	</div>


</body>
</html>