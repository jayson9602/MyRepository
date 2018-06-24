<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	
	function emailValidate() {
		var email = document.getElementById("email");
		var re = new RegExp(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/);
		if (!re.test(email.value)) {
			alert("邮箱格式不正确");
			return false;
		} else {
			return true;
		}
	}
	
	function setFocus() {
		document.getElementById('factoryName').focus()
	}
</script>
</head>
<body onpageshow="setFocus()">
	添加厂商：<br><br>
	<form action="<%=basePath%>/servlet/factory/AddFactoryServlet"
		method="post">
		名称：<input id="factoryName" type="text" name="factoryName" required="required" /><br>
		<br> 地址：<input type="text" name="place" required="required" /><br>
		<br> 电话：<input id="phone" type="text" name="phone" required="required" /><span
			id="phoneError"></span><br> <br> 传真：<input type="text"
			name="fax" /><br> <br> 邮箱：<input id="email" type="text" step="1"
			min="0" placeholder="请输入邮箱" name="email" onblur="return emailValidate()" /> <input type="submit"
			value="添加" onclick="return emailValidate()" />

	</form>

</body>
</html>