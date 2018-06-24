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
<script type="text/javascript">

	function addFactory() {
		var checkEmail = faxValidate();
		var checkPhone = phoneValidate();
		if (checkEmail == true && checkPhone == true) {
			return true;
		} else {
			return false;
		}
	}



	function phoneValidate() {
		var phoneError = document.getElementById("phoneError");
		var phone = document.getElementsByName("phone")[0].value;
		var phoneReg = new RegExp(/\d{3}-\d{8}|\d{4}-\d{7}/);
		//采用正则表达式判断电话
		if (!phoneReg.test(phone)) {
			phoneError.innerHTML = "<font color='red'>电话格式错误</font>";
			return false;
		} else {
			faxError.innerHTML = "";
			return true;
		}

	}
</script>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	修改厂商：<br><br>
	<form action="<%=basePath%>/servlet/factory/UpdateFactoryServlet"
		method="post">
		<input type="hidden" id="id" name="id" value=${factory.id }>
		名称：<input type="text" id="facotyName" name="factoryName"
			value=${factory.factoryName }><br> <br>
			地址：<input type="text"
			id="place" name="place" value=${factory.place }><br> <br>
			电话：<input
			type="text" id="phone" name="phone" value=${factory.phone }><span
			id="phoneError"></span><br> <br>
			传真：<input type="text" id="fax"
			name="fax" / value=${factory.fax }><span id="faxError"></span><br><br>
		邮箱：<input type="text" id="email" name="email" value=${factory.email }><font
			color="red" size="2"></font> <input type="submit" value="修改"
			onclick="return addFactory()" /><br>

	</form>
</body>
</html>