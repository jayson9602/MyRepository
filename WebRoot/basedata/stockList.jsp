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
	//向下翻页判断
	function pageDown(pageNo, totalPages) {
		if (pageNo >= totalPages) {
			return false;
		}
	}
	//向上翻页判断
	function pageUp(pageNo) {
		if (pageNo <= 1) {
			return false;
		}
	}
	//按id查询判断
	function idValidate() {
		var idElt = document.getElementsByName("searchId")[0];
		var id = userNameElement.value;
		//采用正则表达式判断用户名只能是数字
		var re = new RegExp(/^[0-9]{1,10}$/);
		if (!re.test(id)) {
			idElt.value = "id只能为数字";
			return false;
		} else {
			return true;
		}
	}
</script>
<style type="text/css">
<
style type ="text/css"><!--body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}

a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
-->
</style>
</head>

<body>

	<table width="80%" border="0" align="left" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30">
				<form action="<%=basePath%>/servlet/stock/SearchStockServlet"
					method="post">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30"><img src="images/tab_03.gif"
								width="15" height="30" /></td>
							<td background="images/tab_05.gif"><img src="images/311.gif"
								width="16" height="16" /> <span class="STYLE4">进货记录
									&nbsp;&nbsp;&nbsp;&nbsp;输入编号: <input type="number" step="1"
									min="1" name="searchId" size="3" /> <input type="submit"
									value="查询"> &nbsp;&nbsp;&nbsp;&nbsp;输入名称: <input
									type="text" name="searchName" size="15" /> <input
									type="submit" value="查询">
									<a href="<%=basePath %>/servlet/stock/StockExportServlet" style="margin-left:150px;text-align: center;">导出报表</a>
							</span></td>

							<td width="14"><img src="images/tab_07.gif" width="14"
								height="30" /></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="9" background="images/tab_12.gif">&nbsp;</td>
						<td bgcolor="e5f1d6">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="1" bgcolor="#CECECE">
								<tr>
									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2 STYLE1">编号</div>
									</td>
									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2 STYLE1">产品</div>
									</td>
									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2 STYLE1">数量</div>
									</td>
									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2 STYLE1">操作人</div>
									</td>
									<td width="20%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2 STYLE1">日期</div>
									</td>
									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2 STYLE1">备注</div>
									</td>
									<!-- <td width="10%" height="26" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2">编辑</div></td> -->
					<c:if test="${sessionScope.user.userType==1 }">
									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2">删除</div>
									</td>					
					</c:if>
								</tr>
								<c:forEach items="${pageModel.list }" var="stock">

									<tr>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">${stock.id}</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">${stock.product.name}</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">${stock.amount}</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">${stock.user.userName}</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">${stock.createDate}</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">${stock.remark}</div>
										</td>

						<c:if test="${sessionScope.user.userType==1 }">
										<td height="20" bgcolor="#FFFFFF"><div align="center">
												<img src="images/010.gif" width="9" height="9" /> <span
													class="STYLE1">[</span><a
													href="<%=basePath %>/servlet/sell/DeleteSellServlet?id=${sell.id}"
													onclick="return confirm('确定要删除吗')">删除</a><span
													class="STYLE1">]</span>
											</div></td>						
						</c:if>
									</tr>

								</c:forEach>

							</table>
						</td>
						<td width="9" background="images/tab_16.gif">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="29">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15" height="29"><img src="images/tab_20.gif"
							width="15" height="29" /></td>
						<td background="images/tab_21.gif">
					<c:if test="${sessionScope.user.userType==1 }">
							<a	href="<%=basePath%>/servlet/stock/ShowAddStockServlet">添加进货
					</c:if>
							</a>
							<div style='width:140px;float:right;'>
								<span style='color:lightgray'> <a
									href="<%=basePath %>/servlet/stock/SearchStockServlet?pageNo=${pageModel.pageNo-1}"
									onclick="return pageUp(${pageModel.pageNo})">上一页</a>
								</span>&nbsp; ${pageModel.pageNo }/${pageModel.totalPages }&nbsp; <span
									style='color:lightgray'> <a
									href="<%=basePath %>/servlet/stock/SearchStockServlet?pageNo=${pageModel.pageNo + 1}"
									onclick="return pageDown(${pageModel.pageNo },${pageModel.totalPages })">下一页</a>
								</span>
							</div></td>
						<td width="14"><img src="images/tab_22.gif" width="14"
							height="29" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>