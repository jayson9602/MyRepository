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
</head>
<body>
	修改类别：<br><br>
	<form action="<%=basePath%>/servlet/category/UpdateCategoryServlet"
		method="post">
		<input type="hidden" name="id" value=${category.id }> 名称：<input
			type="text" name="categoryName" value=${category.categoryName }><br>
		<br> 备注：<input type="text" name="remark"
			value=${category.remark }> <input type="submit" value="修改" />
	</form>

</body>
</html>