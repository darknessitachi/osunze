<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="Code.Title"></z:lang></title>
<script language="javascript">
	function editPermission(value, rowData, rowIndex) {
		 return "<a href='javascript:showOrderInfo("+rowData["id"]+")'><z:lang id='Order.ShowOrderDetail'/></a>";
	}
	$(document).ready(function() {
		var editRow = undefined;
		var height = $(window).height() - 80;
		$('#gridTable').datagrid({
			width : '98%',
			height : height,
			nowrap : true,
			striped : true,
			border : true,
			collapsible : false,//是否可折叠的 
			url : '${ctx}/Order.do',
			loadMsg : '<z:lang id="Platform.loadDataMsg"/>',
			singleSelect : true, 
			fitColumns : true, 
			pagination : true,
			rownumbers : true,
			idField:'id', 
			pageSize : 10, 
			pageList : [ 10, 25, 50, 100 ],  
			remoteSort : false,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			columns : [[
			    {title : '<z:lang id="Order.CustomerName"/>',field : 'customer.name',width:20,align:'center',formatter:function(value,row,index){
			    	return row.customer.name;
			    }}, 
			    {title : '<z:lang id="Common.Mobile"/>',field : 'customer.mobile',width:30,align:'center',formatter:function(value,row,index){
			    	return row.customer.mobile;
			    }},
			    {title : '<z:lang id="Order.OrderDate"/>',field : 'orderDate',width:20,align:'center',editor:'text'},
			    {title : '<z:lang id="Order.OrderStatus"/>',field : 'orderStatus',width:20,align:'center',editor:'text'},
			    {title : '<z:lang id="Order.TotalPrice"/>',field : 'orderTotalPrice',width:20,align:'center',editor:'text',formatter:fmoney},
			    {title : '<z:lang id="Common.Manager"/>',field : 'customer',width:90,align:'center',editor:'text',formatter: editPermission}
			 ]]
		});
		var p = $('#gridTable').datagrid('getPager');
		$(p).pagination({
			beforePageText: '<z:lang id="Platform.Pagination.BeforePageText"/>',//页数文本框前显示的汉字 
	        afterPageText: '<z:lang id="Platform.Pagination.afterPageText"/>', 
	        displayMsg: '<z:lang id="Platform.Pagination.displayMsg"/>'
		});
	});
	function edit() {
		var selr = $('#gridTable').datagrid("getSelections");
		if (selr.length != 1) {
			Dialog.alert("<z:lang id='Common.PleaseSelectOnlyOneRecord'/>");
			return;
		}
		var ids = [];
		for (var i = 0; i < selr.length; i++) {
			var row = selr[i];
			ids.push(selr[i].id);
		}
		var diag = new Dialog();
		diag.width = 450;
		diag.height = 180;
		diag.title = "<z:lang id='Supplier.UpdateSupplier'/>";
		diag.url = "${ctx}/Supplier/update.do?id="+ids;
		diag.onLoad = function() {
		}
		diag.onOk = doSave;
		diag.show();
	}
	function add() {
		var diag = new Dialog();
		diag.width = 880;
		diag.height = 580;
		diag.title = "<z:lang id='Order.AddSale'/>";
		diag.url = "${ctx}/Order/editUI.do";
		diag.onLoad = function() {
		};
		diag.onOk = doSave;
		diag.show();
	}

	function doSave() {
		if ($DW.Verify.hasError()) {
			return;
		}
		var dc = $DW.Form.getData("form2");
		var rows =$DW.$("#gridTable").datagrid("getRows");
		var opts = $DW.$("#gridTable").datagrid('getColumnFields'); //这是获取到所有的FIELD
		var orderDetails=new Array();
		var customer={};
		customer["name"]=$DW.$V("customer.name");
		customer["mobile"]=$DW.$V("customer.mobile");
		customer["address"]=$DW.$V("customer.address");
		for(var i=0;i<rows.length;i++){
			$DW.$("#gridTable").datagrid('endEdit', i);
			var detail = {};
			for(j=0;j<opts.length;j++){
				var col = $DW.$("#gridTable").datagrid("getColumnOption",opts[j]);
				detail[opts[j]]=rows[i][col.field];
			}
			orderDetails.push(detail);
		}
		dc["customer"]=customer;
		dc["orderDetail"]=orderDetails;
		Server.sendRequest("Order/save.do", dc, function(response) {
			MsgPop(response.Message);
			if(response.Status==1){
				$D.close();
				$("#gridTable").datagrid("reload");
			}
		});
	}
	function zDel() {
		var selr = $('#gridTable').datagrid("getSelections");
		if (!selr || selr.length == 0) {
			Dialog.alert("<z:lang id='Common.PleaseSelectOneRowAtLeast'/>");
			return;
		}
		var ids = [];
		for (var i = 0; i < selr.length; i++) {
			var row = selr[i];
			ids.push(selr[i].id);
		}
		Dialog.confirm("<z:lang id="Common.ConfirmDelete"/>", function() {
			var dc = new DataCollection();
			dc.add("IDs", ids);
			Server.sendRequest("Supplier/delete.do", dc, function(response) {
				if(response.Status==1){
					MsgPop(response.Message);
					$("#gridTable").datagrid("reload");
				}
			});
		});
	}
	function refresh() {
		$("#gridTable").datagrid("reload");
	}

	document.onkeydown = function(event) {
		event = getEvent(event);
		if (event.keyCode == 13) {
			var ele = event.srcElement || event.target;
			if (ele.id == 'SearchText' || ele.id == 'Submitbutton') {
				doSearch();
			}
		}
	}
	function doSearch() {
		var serchText = $('#SearchText').val();
		$('#gridTable').datagrid('load',{qrText:serchText});
	}
	
	function showOrderInfo(id){
		var diag = new Dialog({
			title:"<z:lang id='Order.ShowOrderDetail'/>",
			url:"${ctx}/Order/showOrderInfo.do?id="+id,
			width:880,
			height:580
		});
		diag.onOk =function(){
			diag.close();
		};
		diag.show();
	}
</script>
</head>
<body class="z-body-detail">
	<table width="100%" id="js_layoutTable" border="0" cellspacing="0"
		cellpadding="0" height="*" class="js_layoutTable">
		<tr>
			<td><z:toolbar>
					<z:button id="ButtonAdd" onClick="add()" theme="flat">
						<img src="../Icons/add.png" />
						<z:lang id="Common.New" />
					</z:button>
					<z:button id="ButtonEdit" onClick="edit()" theme="flat">
						<img src="../Icons/update.png" />
						<z:lang id="Common.Modify" />
					</z:button>
					<z:button id="btnDel" onClick="zDel()" theme="flat">
						<img src="../Icons/delete.png" />
						<z:lang id="Common.Delete"></z:lang>
					</z:button>
					<z:button id="btnRefresh" onClick="refresh()" theme="flat">
						<img src="../Icons/refresh.png" />
						<z:lang id="Common.Refresh"></z:lang>
					</z:button>
				</z:toolbar>
				<div class="gradient" style="padding: 5px 12px;">
					<div style="float: right; white-space: nowrap;">
						<input type="text" name="SearchText" id="SearchText"
							placeholder="<z:lang id='Supplier.SearchTip' />" style="width: 200px" onclick="this.select()">
						<input type="button" name="Submitbutton" id="Submitbutton"
							value="<z:lang id='Common.Search' />" onClick="doSearch()">
					</div>
				</div></td>
		</tr>
		<tr valign="top">
			<td height="*" style="padding: 7px;">
				<div class="z-overflowPanel" style="height: 100%;">
					<table id="gridTable">
					</table>
			</td>
		</tr>
	</table>
</body>
</html>
