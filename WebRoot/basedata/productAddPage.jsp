<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<script type="text/javascript">
	function setFocus() {
		document.getElementById('factoryId').focus()
	}
</script>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onpageshow="setFocus()">
	添加资源：<br><br>
	<form action="<%=basePath %>/servlet/product/AddProductServlet"
		method="post">
		厂商：<select id="factoryId" name="factoryId" style="width:155px;">
			<c:forEach items="${factoryList }" var="factory">
				<option value='${factory.id }'>${factory.factoryName }</option>
			</c:forEach>
		</select><br> <br> 类型：<select name="categoryId" style="width:155px;">
			<c:forEach items="${categoryList }" var="category">
				<option value='${category.id }'>${category.categoryName }</option>
			</c:forEach>
		</select><br> <br> 名称：<input type="text" name="name"
			placeholder="请输入资源名称" required="required" /><br> <br> 进价：<input
			type="number" step="0.00001" min="0" name="priceIn"
			placeholder="支持整数和小数" required="required" /><br> <br> 售价：<input
			type="number" step="0.00001" min="0" name="priceOut"
			placeholder="支持整数和小数" required="required" /> <input type="submit"
			value="添加" />
	</form>
</body>
</html>