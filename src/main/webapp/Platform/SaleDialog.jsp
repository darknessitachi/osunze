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
		url:'${ctx}/Code/getListByCodeName.do?name=OrderProduct',  
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
	        if (supplierList[i].codeType == value) {
	            return supplierList[i].codeName;
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

	var height = $(window).height() - 280;
	$('#gridTable').datagrid({
		width : '98%',
		height : height,
		nowrap : true,
		striped : true,
		border : true,
		url : '',
		loadMsg : '<z:lang id="Platform.loadDataMsg"/>',
		singleSelect : true, 
		fitColumns : true, 
		pagination : false,
		rownumbers : true,
		idField:'name', 
		toolbar: [{
            text: '添加明细', iconCls: 'icon-add', handler: function () {
                if (editRow != undefined) {
                    $("#gridTable").datagrid('endEdit', editRow);
                }
                if (editRow == undefined) {
                    $("#gridTable").datagrid('insertRow', {
                        index: 0,
                        row: {}
                    });
                    $('#gridTable').datagrid('selectRow',0);
                    $("#gridTable").datagrid('beginEdit', 0);
                    editRow = 0;
                }
            }
        }],
		columns : [[
		    {title:'<z:lang id="Order.Product"/>',field:'goodsName',width:40,align:"center",
		    	formatter: supplierNameformatter,
		    	editor:{
		    		type:'combobox',
		    		options:{
			    		data :supplierList,
			    		valueField: 'codeType',    
		                textField: 'codeName',
		    		    required:true
			    	}
		    	}
		    }, 
		    {title:'<z:lang id="Goods.Unit"/>',field:'unit',align:"center",width:20,disabled:false,formatter: unitformatter,editor:{type:'combobox',options:{
		    	data :unitList,
	    	 	valueField: 'codeType',    
                textField: 'codeName',required:true}
		    }},
		    {title:'<z:lang id="Order.Count"/>',field:'count',align:"center",width:20,editor:{type:'numberbox',options:{precision:2,required:true,onChange:function(){
		    	bindGridEvent();
		    }}}}, 
		    {title:'<z:lang id="Order.Price"/>',field:'price',align:"center",width:40,editor:{type:'numberbox',options:{precision:2,required:true,onChange:function(){
		    	bindGridEvent();
		    }}}}, 
		    {title:'<z:lang id="Order.AmountOfPrice"/>',field:'amountOfPrice',align:"center",width:30,editor:{type:'numberbox',options:{precision:2}}}
		 ]],
		onBeforeEdit:function(index,row){
	        row.editing = true;
	        $('#gridTable').datagrid('refreshRow', index);
	    },
	    onAfterEdit:function(index,row){
	    	editRow = undefined;
	    },
	    onClickRow : function (rowIndex) {  
            if (editRow != rowIndex) {  
                $('#gridTable').datagrid('endEdit', editRow);  
                $('#gridTable').datagrid('beginEdit', rowIndex);  
            }  
            editRow = rowIndex;  
        }  
	});
});

function bindGridEvent(){
	var row = $('#gridTable').datagrid('getSelected');
	var rowIndex = $('#gridTable').datagrid('getRowIndex',row);
	var price = $('#gridTable').datagrid('getEditor', {'index':rowIndex,'field':'price'});
	var count = $('#gridTable').datagrid('getEditor', {'index':rowIndex,'field':'count'});
	var total = $('#gridTable').datagrid('getEditor', {'index':rowIndex,'field':'amountOfPrice'});
	var priceValue = $(price.target).val();
	var countValue = $(count.target).val();
	var totalValue = priceValue*parseFloat(countValue);
	$(total.target).numberbox('setValue',totalValue);
	
	var rows = $("#gridTable").datagrid("getRows"); //这段代码是获取当前页的所有行。
	var allPrice=totalValue;
	for(var i=0;i<rows.length;i++)
	{
		if(rows[i].amountOfPrice!=undefined && i!=rowIndex){
			allPrice+=parseInt(rows[i].amountOfPrice);
		}
	} 
	$("#orderTotalPrice").val(allPrice);
	TransformNumberIntoCHN(allPrice,"allPrice");
}
</script>
</head>
<body class="dialogBody">
<form:form action="" id="form2" method="post" commandName="order">
<table width="100%" cellpadding="2" cellspacing="0">
	<form:hidden path="orderTotalPrice"/>
    <tr>
      <td align="right" width="20%"><z:lang id="Order.CustomerName"/></td>
      <td width="30%"><form:input path="customer.name" verify="NotNull"/></td>
      <td align="right" ><z:lang id="Common.Mobile"></z:lang></td>
      <td width="30%"><form:input path="customer.mobile" verify="CnPhone&&NotNull"/></td>
    </tr>
    <tr>
      <td align="right" ><z:lang id="Order.Address"></z:lang></td>
      <td colspan="3"><form:input path="customer.address" style="width:600px" verify="NotNull"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><z:lang id="Order.OrderDate"/></td>
      <td width="30%">
      	<form:input path="orderDate" verify="NotNull" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%ld'})" cssClass="Wdate"/>
      </td>
      <td align="right" ><z:lang id="Order.OrderCharge"></z:lang></td>
      <td width="30%">
      	<form:select path="orderCharge" >
      		<form:option value="">--请选择--</form:option>
      		<form:options items="${orderChargeList}" itemLabel="codeName" itemValue="codeType"/>
      	</form:select>
      </td>
    </tr>
    <tr>
    	<td colspan="4">
    		<div id="SP1" class="z-contentBorder">
    		<div class="z-legend"><b><z:lang id="Order.CabinetMaterial"/></b></div>
			<table width="100%" cellpadding="0" cellspacing="0" bordercolor="#eeeeee" class="formTable">
				<tr>
					<td width="12%" align="right"><z:lang id="Order.FloorMaterial"/></td>
					<td width="14%"><form:input path="floorMaterial"/></td>
					<td width="10%" align="right"><z:lang id="Order.ShutterMaterial"/></td>
					<td width="14%"><form:input path="shutterMaterial"/></td>
					<td width="12%" align="right"><z:lang id="Order.CabinetCabinetMaterial"/></td>
					<td width="14%"><form:input path="cabinetCabinetMaterial"/></td>
					<td width="10%" align="right"><z:lang id="Order.SeePlateMaterial"/></td>
					<td width="14%"><form:input path="seePlateMaterial"/></td>
				</tr>
				<tr>
					<td align="right"><z:lang id="Order.MesaColor"/></td>
					<td><form:input path="mesaColor"/></td>
					<td align="right"><z:lang id="Order.DoorColor"/></td>
					<td><form:input path="doorColor"/></td>
					<td align="right"><z:lang id="Order.FloorCabinetMaterial"/></td>
					<td><form:input path="floorCabinetMaterial"/></td>
					<td align="right"><z:lang id="Order.TopSealingMaterial"/></td>
					<td><form:input path="topSealingMaterial"/></td>
				</tr>
				<tr>
					<td align="right"><z:lang id="Order.MesaBlock"/></td>
					<td><form:input path="mesaBlock"/></td>
					<td align="right"><z:lang id="Order.OtherMaterial"/></td>
					<td><form:input path="otherMaterial"/></td>
					<td align="right"><z:lang id="Order.OtherMaterial"/></td>
					<td><form:input path="otherMaterial2"/></td>
					<td align="right"><z:lang id="Order.Handle"/></td>
					<td><form:input path="handle"/></td>
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
</form:form>
</body>
</html>