<%@ page contentType="text/html; charset=UTF-8"%>
<%@page isErrorPage="true"%>
<%
	response.setStatus(200);
	String uri = request.getAttribute("javax.servlet.forward.request_uri").toString();
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>HTTP Status 404 - Error report</title>
<style>
<!--
body {
	background: #eee;
	color: #555;
	margin: 0;
	padding: 0;
}
body,td,p {
	font-family: Tahoma, Arial, sans-serif;
	font-size: 14px;
}
h1,h3,b {
	background-color: #F4F6F3;
	padding: 2px 6px;
	margin: 0;
}
h1 {
	font-family: "微软雅黑", "黑体", Arial, sans-serif;
	font-size: 20px;
	color: #336699;
	background-color: #E6F0F7;
	padding: 3px 60px 3px 6px;
}
h3 {
	font-size: 14px;
	background-color: #F4F6F3;
}
-->
</style>
</head>
<body>
<table width="100%" height="100%" border="0" cellspacing="10" cellpadding="10">
	<tr>
		<td align="center" valign="middle">
		<table border="0" cellspacing="0" cellpadding="3">
			<tr>
				<td align="left" valign="top" bgcolor="#FFFFFF" style="border: 1px solid #A8BDCF">
					<h1>没有找到页面 - <%=request.getAttribute("javax.servlet.forward.request_uri")%></h1>
					<p><b>类型</b> <%=request.getAttribute("javax.servlet.error.status_code")%></p>
					<p><b>讯息</b> <u><%=request.getAttribute("javax.servlet.error.message")%></u></p>
					<p><b>异常</b> <u><%=request.getAttribute("javax.servlet.error.exception_type")%></u></p>
					<h3><%=application.getServerInfo()%></h3>
				</td>
			</tr>
			<tr>
				<td height="50">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
