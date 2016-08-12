<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/Include/taglibs.jsp"%>
<%@ page import="com.skycity.framework.Config" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="SysInfo.Title"/></title>

</head>
<body class="z-body-detail">
<table width="100%" id="js_layoutTable" border="0" cellspacing="0"
	cellpadding="0" height="*" class="js_layoutTable">
	<tr valign="top">
		<td height="*" style="padding: 7px;">
			<div id="SP1" class="z-overflowPanel" style="height: 100%;">
			<table width="100%" border="0" cellspacing="6"
				style="border-collapse: separate; border-spacing: 6px;">
				<tr>
					<td width="50%" valign="top">
					<div class="z-legend"><b><z:lang id="SysInfo.AppInfo"/></b></div>
					<table width="100%" cellpadding="0" cellspacing="0"
						class="z-datagrid">
						<tr class="dataTableHead">
							<td width="36%" height="30" align="right">
								<z:lang id="Common.Item"/>
							</td>
							<td width="64%">
								<z:lang	id="Common.Value"/>
							</td>
						</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.AppCode"/>：</td>
								<td><z:attr type="SystemInfo" value="Config.Code"></z:attr></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.AppName"/>：</td>
								<td><z:attr type="SystemInfo" value="Config.Name"></z:attr></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.AppVersion"/>：</td>
								<td><z:attr type="SystemInfo" value="Config.MainVersion"></z:attr></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.AppVersion"/>：</td>
								<td><z:attr type="SystemInfo" value="Config.MinorVersion"></z:attr></td>
							</tr>
					</table>
					<p>&nbsp;</p>
					<div class="z-legend"><b><z:lang
						id="SysInfo.BasicSoftwareInfo"></z:lang></b></div>
					<table width="100%" cellpadding="0" cellspacing="0"
						class="z-datagrid">
						<tr class="dataTableHead">
							<td width="36%" height="30" align="right">
								<z:lang id="Common.Item"/>
							</td>
							<td width="64%">
								<z:lang	id="Common.Value"/>
							</td>
						</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.OSName"/>：</td>
								<td><z:attr type="SystemInfo" value="System.OSName"/></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.OSArch"/>：</td>
								<td><z:attr type="SystemInfo" value="System.OSArch"/></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.OSVersion"/>：</td>
								<td><z:attr type="SystemInfo" value="System.OSVersion"/></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.JDKVendor"/>：</td>
								<td><z:attr type="SystemInfo" value="System.JavaVendor"/></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.JDKVersion"/>：</td>
								<td><z:attr type="SystemInfo" value="System.JavaVersion"/></td>
							</tr>
							<tr>
								<td align="right"><z:lang id="SysInfo.JDKHome"/>：</td>
								<td><z:attr type="SystemInfo" value="System.JavaHome"/></td>
							</tr>
							
							<tr>
								<td align="right"><z:lang id="SysInfo.ContainerName"/>：</td>
								<td><z:attr type="SystemInfo" value="System.ContainerInfo"/></td>
							</tr>
							
							<tr>
								<td align="right"><z:lang id="SysInfo.FileEncoding"/>：</td>
								<td><z:attr type="SystemInfo" value="System.FileEncoding"/></td>
							</tr>
					</table>
					</td>
					<td width="50%" valign="top">
					<div class="z-legend"><b><z:lang id="SysInfo.LanguageInfo"/></b></div>
					<table width="100%" cellpadding="0" cellspacing="0"
						class="z-datagrid">
						<tr class="dataTableHead">
							<td width="38%" height="30" align="right">
								<z:lang id="Common.Item"/>
							</td>
							<td width="62%">
								<z:lang	id="Common.Value"/>
							</td>
						</tr>
						<tr>
							<td align="right"><z:lang id="SysInfo.SupportedLanguages"/>：</td>
							<td>
							<table>
								<tr>
									<td>简体中文(zh_cn),繁体中文(zh_tw),英文(en)</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td align="right" height="30">
								<z:lang	id="SysInfo.CurrentLanguage"/>：
							</td>
							<td><z:attr type="SystemInfo" value="System.OSUserLanguage"/></td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</div>
		</td>
	</tr>
</table>
</body>
</html>