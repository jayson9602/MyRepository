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
<script src="<%=basePath %>/script/client_validate.js"></script>
<script type="text/javascript">

	function setFocus() {
		document.getElementById('userName').focus()
	}

	function addUser() {
		var checkUser = userNameValidate();
		var checkPass = passwordValidate();
		if (checkUser == true && checkPass == true) {
			return true;
		} else {
			return false;
		}
	}


	function userNameValidate() {
		var userNameElement = document.getElementById("userName");
		var userName = userNameElement.value;
		var userNameError = document.getElementById("userNameError");
		//采用正则表达式判断用户名只能是数字
		var re = new RegExp(/^[0-9]{1,10}$/);
		if (!re.test(userName)) {
			userNameError.innerHTML = "<font color='red'>用户名只能是10位以内数字</font>";
			return false;
		} else {
			userNameError.innerHTML = "";
			return true;
		}

	}

	function passwordValidate() {
		var passwordElement = document.getElementById("password");
		var password = passwordElement.value;
		var passwordError = document.getElementById("passwordError");
		//采用正则表达式判断密码只能是数字或字母
		var re = new RegExp(/^[a-zA-Z0-9]{6,20}$/);
		if (!re.test(password)) {
			passwordError.innerHTML = "<font color='red'>密码只能为6-20位字母或数字</font>";
			return false;
		} else {
			passwordError.innerHTML = "";
			return true;
		}

	}


	//采用Ajax判断用户是否已经存在
	var xmlHttpRequest = null;
	function validate(field) {
		var checkUser = userNameValidate();
		if (checkUser == true) {
			//1.创建XmlHttpRequest对象
			if(window.XMLHttpRequest) {//浏览器支持XmlHttpRequest
				xmlHttpRequest = new XMLHttpRequest();
			} else {//浏览器不支持xmlHttpRequest（老版本浏览器，如IE5,IE6)
				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}
			//2.设置提交方式为GET提交，设置提交的url，设置为异步提交
				//为了防止得到缓存的结果，加上Time使得该value为唯一的
			var url = "servlet/user/UserValidateServlet?userName=" + field.value + "&time=" + new Date().getTime();
			xmlHttpRequest.open("GET",url,true);
			//3.将请求发送给服务器
			xmlHttpRequest.send(null);
			//4.当请求状态发生改变时调用callback
			xmlHttpRequest.onreadystatechange = callback;
		}
	}
	
	function callback() {
		//当请求执行完成，并且服务器状态为OK时执行
		if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200) {
			//当来自服务器的响应字符串不为空时执行
			if(trim(xmlHttpRequest.responseText) != "") {
				document.getElementById("userNameError").innerHTML = "<font color='red'>用户名已存在</font>";
			} else {
				document.getElementById("userNameError").innerHTML = "<font color='green'>恭喜！该用户名尚未注册</font>";
			}
		} 
	}
		
		
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onpageshow = "setFocus()">
	添加用户：<br><br>
	<form action="<%=basePath %>/servlet/user/AddUserServlet" method="post">
		用户：<input type="number" step="1" min="0" id="userName" name="userName"
			required="required" onblur="validate(this)" /><span
			id="userNameError"></span><br> <br> 密码：<input type="text"
			name="password" id="password" required="required"
			onblur="passwordValidate()" /><span id="passwordError"></span><br>
		<br> 类型：<select name="userType" style="width:149px;">
	
			<option value='1'>管理员</option>
	
			<option value='2'>普通用户</option>
	
		</select> <input type="submit" value="添加" onclick="return addUser()" />
	</form>
</body>
</html>