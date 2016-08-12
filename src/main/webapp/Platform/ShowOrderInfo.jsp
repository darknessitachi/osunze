<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script language="javascript">
$(document).ready(function() {
	TransformNumberIntoCHN(${order.orderTotalPrice},"allPrice");
});
</script>
</head>
<body class="dialogBody">
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
     <tr>
       <td width="30%" height="10" align="right" colspan="4"></td>
    </tr>
    <tr>
      <td align="right" width="20%"><z:lang id="Order.CustomerName"/>:</td>
      <td width="30%">${order.customer.name }</td>
      <td align="right" ><z:lang id="Common.Mobile"/>:</td>
      <td width="30%">${order.customer.mobile }</td>
    </tr>
    <tr>
      <td align="right" ><z:lang id="Order.Address"/>:</td>
      <td colspan="3">${order.customer.address }</td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div id="SP1" class="z-contentBorder">
    		<div class="z-legend"><b><z:lang id="Order.CabinetMaterial"/></b></div>
			<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="formTable">
				<tr>
					<td width="12%" align="right"><z:lang id="Order.FloorMaterial"/>:</td>
					<td width="14%">${order.floorMaterial }</td>
					<td width="10%" align="right"><z:lang id="Order.ShutterMaterial"/>:</td>
					<td width="14%">${order.shutterMaterial }</td>
					<td width="12%" align="right"><z:lang id="Order.CabinetCabinetMaterial"/>:</td>
					<td width="14%">${order.cabinetCabinetMaterial }</td>
					<td width="10%" align="right"><z:lang id="Order.SeePlateMaterial"/>:</td>
					<td width="14%">${order.seePlateMaterial }</td>
				</tr>
				<tr>
					<td align="right"><z:lang id="Order.MesaColor"/>:</td>
					<td>${order.mesaColor }</td>
					<td align="right"><z:lang id="Order.DoorColor"/>:</td>
					<td>${order.doorColor }</td>
					<td align="right"><z:lang id="Order.FloorCabinetMaterial"/>:</td>
					<td>${order.floorCabinetMaterial }</td>
					<td align="right"><z:lang id="Order.TopSealingMaterial"/>:</td>
					<td>${order.topSealingMaterial }</td>
				</tr>
				<tr>
					<td align="right"><z:lang id="Order.MesaBlock"/>:</td>
					<td>${order.mesaBlock }</td>
					<td align="right"><z:lang id="Order.OtherMaterial"/>:</td>
					<td>${order.otherMaterial }</td>
					<td align="right"><z:lang id="Order.OtherMaterial"/>:</td>
					<td>${order.otherMaterial2 }</td>
					<td align="right"><z:lang id="Order.Handle"/>:</td>
					<td>${order.handle }</td>
				</tr>
			</table>
			</div>
    	</td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div id="SP1" class="z-contentBorder">
    			<div class="z-legend"><b><z:lang id="Order.TotalPurchasesOfProducts"/></b></div>
				<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="z-datagrid"">
				<tr class="dataTableHead" align="center">
					<td colspan="2" ><z:lang id="Order.Product"/></td>
					<td width="10%"><z:lang id="Goods.Unit"/></td>
					<td width="14%"><z:lang id="Order.Count"/></td>
					<td colpspan="2"><z:lang id="Order.Price"/></td>
					<td colpspan="2"><z:lang id="Order.AmountOfPrice"/></td>
				</tr>
				<c:forEach items="${order.orderDetail }" var="item">
				<tr align="center">
					<td colspan="2" >${item.goodsName }</td>
					<td width="10%">${item.unit }</td>
					<td width="14%">${item.count }</td>
					<td colpspan="2">${item.price }</td>
					<td colpspan="2">${item.amountOfPrice }</td>
				</tr>
				</c:forEach>
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