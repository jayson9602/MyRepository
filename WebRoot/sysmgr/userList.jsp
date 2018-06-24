<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</script>
<style type="text/css">
<!--
body {
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

	<table width="90%" border="0" align="left" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15" height="30"><img src="images/tab_03.gif"
							width="15" height="30" /></td>
						<td background="images/tab_05.gif"><img src="images/311.gif"
							width="16" height="16" /> <span class="STYLE4">用户列表</span></td>
						<td width="14"><img src="images/tab_07.gif" width="14"
							height="30" /></td>
					</tr>
				</table>
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
										<div align="center" class="STYLE2 STYLE1">用户</div>
									</td>
									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2 STYLE1">分组</div>
									</td>

									<td width="10%" height="26" background="images/tab_14.gif"
										class="STYLE1">
										<div align="center" class="STYLE2">删除</div>
									</td>
								</tr>

								<c:forEach items="${pageModel.list }" var="user">

									<tr>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">${user.userName }</div>
										</td>
								<c:choose>
									<c:when test="${user.userType==2 }">
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">
												普通用户
												<td height="20" bgcolor="#FFFFFF"><div align="center">
														<img src="images/010.gif" width="9" height="9" /> <span
															class="STYLE1">[</span><a
															href="<%=basePath %>/servlet/user/DeleteUserServlet?id=${user.id}"
															onclick="return confirm('确定要删除吗?')">删除</a><span
															class="STYLE1">]</span>
													</div></td>
											</div>
										</td>
									</c:when>
									<c:when test="${user.userType==1 && sessionScope.user.userName != 1}">
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">
												管理员<td><div align="center" class="STYLE2 STYLE1">系统保护用户</div></td>
											</div>
										</td>
									</c:when>
									<c:when test="${user.userType==1 && sessionScope.user.userName == 1}">
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE2 STYLE1">
												管理员
												<td height="20" bgcolor="#FFFFFF"><div align="center">
														<img src="images/010.gif" width="9" height="9" /> <span
															class="STYLE1">[</span><a
															href="<%=basePath %>/servlet/user/DeleteUserServlet?id=${user.id}"
															onclick="return confirm('确定要删除吗?')">删除</a><span
															class="STYLE1">]</span>
													</div></td>
											</div>
										</td>
									</c:when>
								</c:choose>
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
						<td background="images/tab_21.gif"><a
							href="<%=basePath%>/sysmgr/userAddPage.jsp">添加用户</a>
							<div style='width:140px;float:right;'>
								<span style='color:lightgray'> <a
									href="<%=basePath %>/servlet/user/SearchUserServlet?pageNo=${pageModel.pageNo-1}"
									onclick="return pageUp(${pageModel.pageNo})">上一页</a>
								</span>&nbsp; ${pageModel.pageNo }/${pageModel.totalPages }&nbsp; <span
									style='color:lightgray'> <a
									href="<%=basePath %>/servlet/user/SearchUserServlet?pageNo=${pageModel.pageNo + 1}"
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