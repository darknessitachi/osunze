<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<@c.set var="ctx" value="/osunze"/>

<link rel="stylesheet" type="text/css" href="${ctx }/Style/Default.css">
<link rel="stylesheet" type="text/css" href="${ctx }/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/js/themes/icon.css" />

<script type="text/javascript" src="${ctx }/Framework/Main.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/Framework/myjs/Current.js"></script>

<link rel="shortcut icon" href="${ctx}/Include/favicon.ico" type="image/x-icon" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
</head>
<body class="dialogBody">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
     <tr>
      <td width="30%" height="10" align="right" ></td>
      <td height="10"></td>
    </tr>
    <tr>
      <td align="right" ><z:lang id="Order.CustomerName"/></td>
      <td width="30%"></td>
      <td align="right" ><z:lang id="Common.Mobile"></z:lang></td>
      <td width="30%"></td>
    </tr>
    <tr>
      <td align="right" ><z:lang id="Order.Address"></z:lang></td>
      <td colspan="3"></td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div id="SP1" class="z-contentBorder">
    		<div class="z-legend"><b><z:lang id="Order.CabinetMaterial"/></b></div>
			<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="formTable">
				<tr>
					<td width="12%" align="right"><z:lang id="Order.FloorMaterial"/></td>
					<td width="14%"></td>
					<td width="10%" align="right"><z:lang id="Order.ShutterMaterial"/></td>
					<td width="14%"></td>
					<td width="12%" align="right"><z:lang id="Order.CabinetCabinetMaterial"/></td>
					<td width="14%"></td>
					<td width="10%" align="right"><z:lang id="Order.SeePlateMaterial"/></td>
					<td width="14%"></td>
				</tr>
				<tr>
					<td align="right"><z:lang id="Order.MesaColor"/></td>
					<td></td>
					<td align="right"><z:lang id="Order.DoorColor"/></td>
					<td></td>
					<td align="right"><z:lang id="Order.FloorCabinetMaterial"/></td>
					<td></td>
					<td align="right"><z:lang id="Order.TopSealingMaterial"/></td>
					<td></td>
				</tr>
				<tr>
					<td align="right"><z:lang id="Order.MesaBlock"/></td>
					<td></td>
					<td align="right"><z:lang id="Order.OtherMaterial"/></td>
					<td></td>
					<td align="right"><z:lang id="Order.OtherMaterial"/></td>
					<td></td>
					<td align="right"><z:lang id="Order.Handle"/></td>
					<td></td>
				</tr>
			</table>
			</div>
    	</td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div id="SP1" class="z-contentBorder">
    			<div class="z-legend"><b><z:lang id="Order.TotalPurchasesOfProducts"/></b></div>
				<table id="gridTable">
				</table>
			</div>
    	</td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div>
    			<font size="14"><b><z:lang id="Order.TotalPrice"/>:<div id="allPrice" style="display:inline-block"></div></b></font>
			</div>
    	</td>
    </tr>
 </table>
</body>
</html>