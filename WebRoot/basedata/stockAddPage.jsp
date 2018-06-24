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
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function setFocus() {
		document.getElementById('productId').focus()
	}
</script>
</head>
<body onpageshow="setFocus()">
	资源入库：<br><br>
	<form action="<%=basePath %>/servlet/stock/AddStockServlet"
		method="post">
		产品：<select id="productId" name="productId" style="width: 149px">
			<c:forEach items="${productList }" var="product">


				<option value='${product.id }'>${product.name }</option>
			</c:forEach>
		</select><font color="red">*</font><br> <br> 数量：<input type="number"
			step="1" min="0" name="amount" required="required"
			placeholder="请输入数字" /><br> <br> 备注：<input type="text"
			name="remark" /> <input type="submit" value="添加" />

	</form>
</body>
</html>