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
	<table width="90%" border="0" align="left" cellpadding="0"
		cellspacing="1" bgcolor="#CECECE">
		<tr>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">编号</div>
			</td>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">名称</div>
			</td>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">类型</div>
			</td>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">进价</div>
			</td>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">售价</div>
			</td>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">库存</div>
			</td>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">销量</div>
			</td>
			<td width="10%" height="26" background="images/tab_14.gif"
				class="STYLE1">
				<div align="center" class="STYLE2 STYLE1">厂商</div>
			</td>
		</tr>
		<tr>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.id }</div>
			</td>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.name }</div>
			</td>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.category.categoryName }</div>
			</td>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.priceIn }</div>
			</td>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.priceOut}</div>
			</td>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.stock}</div>
			</td>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.sell}</div>
			</td>
			<td height="20" bgcolor="#FFFFFF">
				<div align="center" class="STYLE2 STYLE1">${product.factory.factoryName}</div>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<div>
		<form action="<%=basePath%>/servlet/product/UploadProductServlet"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="productId" value=${product.id}> <br><br>
			图片:<br> <img src="upload/${product.id}.gif?id=" + new Date().getTime()><br><br> <br>
			更新图片:<input name="fileName" type="file" size="40" maxlength="40"> 
			&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="上传" />
		</form>
	</div>
</body>
</html>