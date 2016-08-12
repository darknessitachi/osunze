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
	var editRow = undefined;
	var supplierList = null;
	var unitList = null;
	$.ajax({    
        url:'${ctx}/Supplier/getAllList.do',    
        dataType : 'json',    
        type : 'POST',    
        async:false,  
        success: function (data){    
        	supplierList = data;  
        }    
  	});
	$.ajax({    
		url:'${ctx}/Code/getListByCodeName.do?name=Unit',
        dataType : 'json',    
        type : 'POST',    
        async:false,  
        success: function (data){    
        	unitList = data;  
        }    
  	});
	function supplierNameformatter(value, rowData, rowIndex) {
	    if (value == 0) {
	        return;
	    }
	    for (var i = 0; i < supplierList.length; i++) {
	        if (supplierList[i].id == value) {
	            return supplierList[i].name;
	        }
	    }
	}
	function unitformatter(value, rowData, rowIndex) {
	    if (value == 0) {
	        return;
	    }
	    for (var i = 0; i < unitList.length; i++) {
	        if (unitList[i].codeType == value) {
	            return unitList[i].codeName;
	        }
	    }
	}

});

function bindGridEvent(){
	var row = $('#gridTable').datagrid('getSelected');
	var rowIndex = $('#gridTable').datagrid('getRowIndex',row);
	var price = $('#gridTable').datagrid('getEditor', {'index':rowIndex,'field':'price'});
	var count = $('#gridTable').datagrid('getEditor', {'index':rowIndex,'field':'count'});
	var total = $('#gridTable').datagrid('getEditor', {'index':rowIndex,'field':'totalPrice'});
	var priceValue = $(price.target).val();
	var countValue = $(count.target).val();
	var totalValue = priceValue*parseFloat(countValue);
	$(total.target).numberbox('setValue',totalValue);
	
	var rows = $("#gridTable").datagrid("getRows"); //这段代码是获取当前页的所有行。
	/* for(var i=0;i<rows.length;i++)
	{
		alert(rows[i].totalPrice);
	} */
}
</script>
</head>
<body class="dialogBody">
<form:form action="" id="form2" method="post" commandName="order">
<input type="hidden" id="parentCode" value="${code.parentCode}" />
<input type="hidden" id="Operator" value=""/>
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
     <tr>
      <td colspan="4" height="10" align="right" ></td>
    </tr>
    <tr>
      <td align="right" width="20%"><z:lang id="Order.CustomerName"/></td>
      <td width="30%"><form:input path="customerName"/></td>
      <td align="right" width="20%"><z:lang id="Common.Mobile"></z:lang></td>
      <td><form:input path="mobile"/></td>
    </tr>
    <tr>
      <td align="right"><z:lang id="Order.Address"></z:lang></td>
      <td colspan="3"><form:input path="address" style="width:600px"/></td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div id="SP1" class="z-contentBorder">
    		<div class="z-legend"><b><z:lang id="Order.CabinetMaterial"/></b></div>
			<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="formTable">
				<tr>
					<td width="10%" align="right">
						<z:lang id="Order.FloorMaterial"/>
					</td>
					<td width="15%">
						
					</td>
					<td width="10%" align="right">
						<z:lang id="Order.ShutterMaterial"/>
					</td>
					<td width="15%">&nbsp;</td>
					<td width="12%" align="right">
						<z:lang id="Order.CabinetCabinetMaterial"/>
					</td>
					<td width="15%">
					</td>
					<td width="10%" align="right">
						<z:lang id="Order.SeePlateMaterial"/>
					</td>
					<td width="15%">
					</td>
				</tr>
				<tr>
					<td width="10%" align="right">
						<z:lang id="Order.MesaColor"/>
					</td>
					<td width="15%">
						
					</td>
					<td width="10%" align="right">
						<z:lang id="Order.DoorColor"/>
					</td>
					<td width="15%">&nbsp;</td>
					<td width="12%" align="right">
						<z:lang id="Order.FloorCabinetMaterial"/>
					</td>
					<td width="15%">
					</td>
					<td width="10%" align="right">
						<z:lang id="Order.TopSealingMaterial"/>
					</td>
					<td width="15%">
					</td>
				</tr>
				<tr>
					<td width="10%" align="right">
						<z:lang id="Order.MesaBlock"/>
					</td>
					<td width="15%">
						
					</td>
					<td width="10%" align="right">
						<z:lang id="Order.OtherMaterial"/>
					</td>
					<td width="15%">&nbsp;</td>
					<td width="12%" align="right">
						<z:lang id="Order.OtherMaterial"/>
					</td>
					<td width="15%">
					</td>
					<td width="10%" align="right">
						<z:lang id="Order.Handle"/>
					</td>
					<td width="15%">
					</td>
				</tr>
			</table>
			</div>
    	</td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div id="SP1" class="z-contentBorder">
    		<div class="z-legend"><b><z:lang id="Order.TotalPurchasesOfProducts"/></b></div>
			<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="formTable">
				<tr>
					<td width="25%" align="center">
						<z:lang id="Order.Product"/>
					</td>
					<td width="10%" align="center">
						<z:lang id="Goods.Unit"/>
					</td>
					<td width="15%" align="center"><z:lang id="Order.Count"/></td>
					<td width="12%" align="center">
						<z:lang id="Order.Price"/>
					</td>
					<td width="15%" align="center"><z:lang id="Order.AmountOfPrice"/></td>
					<td width="25%" align="center">
						<z:lang id="Common.Memo"/>
					</td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.Cabinet"/></td>
					<td><z:lang id="Common.Mile"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.WallCupboard"/></td>
					<td><z:lang id="Common.Mile"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.Basin"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.Hydrovalve"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.PullBasket"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.SeasoningBasket"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.StainlessSteelBasin"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.High"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.SeeLight"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.TopSealingPlate"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.KitchenAppliances"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr align="center">
					<td><z:lang id="Order.WaterPurifier"/></td>
					<td><z:lang id="Common.Pieces"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			</div>
    	</td>
    </tr>
 </table>
</form:form>
</body>
</html>