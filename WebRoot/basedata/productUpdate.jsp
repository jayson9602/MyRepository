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
	修改资源：<br><br>
	<form action="<%=basePath%>/servlet/product/UpdateProductServlet"
		method="post">
		<input type="hidden" name="id" value=${product.id }> <input
			type="hidden" name="sell" value=${product.sell }> <input
			type="hidden" name="stock" value=${product.stock }> 厂商：<select
			name="factoryId" style="width:155px;">
			<c:forEach items="${factoryList }" var="factory">
				<c:if test="${product.factory.id == factory.id }">
					<c:set var="selectedStr" value="selected" />
				</c:if>
				<option value='${factory.id }' <c:out value="${selectedStr }"/>>${factory.factoryName }</option>
			</c:forEach>
		</select><br> 类型：<select name="categoryId" style="width:155px;">
			<c:forEach items="${categoryList }" var="category">
				<c:if test="${product.category.id == category.id }">
					<c:set var="selectedStr" value="selected" />
				</c:if>
				<option value='${category.id }' ${selectedStr } />${category.categoryName }</option>
			</c:forEach>
		</select><br> 名称：<input type="text" name="name" placeholder="请输入资源名称"
			value="${product.name }" required="required" /><br> <br>
		进价：<input type="number" step="0.00001" min="0" name="priceIn"
			placeholder="支持整数和小数" value="${product.priceIn }" required="required" /><br>
		<br> 售价：<input type="number" step="0.00001" min="0"
			name="priceOut" placeholder="支持整数和小数" value="${product.priceOut }"
			required="required" /> <input type="submit" value="修改" />

	</form>
</body>
</html>