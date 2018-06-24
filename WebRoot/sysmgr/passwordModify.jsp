<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<base href="<%=basePath %>">
<script type="text/javascript">

	function check() {

		var newPassword1 = document.getElementById("newPassword1").value;
		var newPassword2 = document.getElementById("newPassword2").value;
		if(newPassword1 != newPassword2) {
			document.getElementById("sameError").innerHTML = "<font color='red'>两次输入的新密码不同</font>";
			return false;
		}
	}
	
	function setFocus() {
		document.getElementById('oldPassword').focus()
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
</head>
<body onpageshow="setFocus()">
	修改密码：<br><br>
	<form action="<%=basePath %>/servlet/user/ModifyPasswordServlet" method="post">
		原&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码：<input id="oldPassword" type="password"  required="required" name="oldPassword"><font color="red" size="2">${strError }</font><br><br>
		新&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码：<input type="password" id = "newPassword1" required="required" name="newPassword1"><span id="sameError"></span><br><br>
		确认新密码：<input type="password" id = "newPassword2" required="required" name="newPassword2">
		<input type="submit" value="修改" onclick="return check()"/>
	</form>

</body>
</html>