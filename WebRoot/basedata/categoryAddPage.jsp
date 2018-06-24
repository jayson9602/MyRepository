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
	function setFocus() {
		document.getElementById('categoryName').focus()
	}
</script>
</head>
<body onpageshow="setFocus()">
	添加类别：<br><br>
	<form action="<%=basePath%>/servlet/category/AddCategoryServlet"
		method="post">
		名称：<input id="categoryName" type="text" name="categoryName"> <br> <br>
		备注：<input type="text" name="remark"> <input type="submit"
			value="添加" />
	</form>
</body>
</html>