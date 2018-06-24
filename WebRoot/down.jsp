<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #147233;
}
-->
</style></head>
<script type="text/javascript">

	function getTime() {
		document.getElementById("time").innerHTML = new Date().toLocaleString();
		window.setInterval("getTime();", 1000); //每隔1s取一次函数值
	}
</script>
<body onload="getTime()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="23" background="images/main_25.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="181" height="23" background="images/main_24.gif">&nbsp;</td>
        <td><div align="right" class="STYLE1">
        <td><div id="time" align="right" class="STYLE1">
    			 
        </div></td>

        
        </div></td>
        <td width="25"><img src="images/main_27.gif" width="25" height="23" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>