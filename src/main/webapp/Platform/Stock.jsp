<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="Code.Title"></z:lang></title>
<script language="javascript">
	$(document).ready(function() {
		var height = $(window).height() - 80;
		$('#gridTable').datagrid({
			width : '98%',
			height : height,
			nowrap : true,
			striped : true,
			border : true,
			collapsible : false,//是否可折叠的 
			url : '',
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
			    {title : '供应商名称',field : 'supplierName',width : 30},
			    {title : '进货时间',field : 'brand',width : 20}, 
			    {title : '进货总价',field : 'goodType',width : 20},
			    {title : '是否结清',field : 'model',width : 30},
			    {title : '已付款项',field : 'level',width : 30}
			]]
		});
		var p = $('#gridTable').datagrid('getPager');
		$(p).pagination({
			beforePageText: '<z:lang id="Platform.Pagination.BeforePageText"/>',//页数文本框前显示的汉字 
	        afterPageText: '<z:lang id="Platform.Pagination.afterPageText"/>', 
	        displayMsg: '<z:lang id="Platform.Pagination.displayMsg"/>'
		});
		
		$('#gridTable2').datagrid({
			width : '98%',
			height : height,
			nowrap : true,
			striped : true,
			border : true,
			collapsible : false,//是否可折叠的 
			url : '',
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
			    {title : '商品名称',field : 'supplierName',width : 30},
			    {title : '进货价格',field : 'brand',width : 20}, 
			    {title : '进货数量',field : 'goodType',width : 20},
			    {title : '同批次赠送量',field : 'model',width : 30}
			]]
		});
		var p2 = $('#gridTable2').datagrid('getPager');
		$(p2).pagination({
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
		diag.height = 340;
		diag.title = "<z:lang id='Goods.UpdateGoods'/>";
		diag.url = "${ctx}/Goods/editUI.action?id="+ids;
		diag.onLoad = function() {
		}
		diag.onOk = doSave;
		diag.show();
	}
	function add() {
		var diag = new Dialog();
		diag.width = 450;
		diag.height = 340;
		diag.title = "<z:lang id='Goods.AddGoods'/>";
		diag.url = "${ctx}/Goods/editUI.action";
		diag.onLoad = function() {
		};
		diag.onOk = doSave;
		diag.show();
	}

	function doSave() {
		if ($DW.Verify.hasError()) {
			return;
		}
		var dc = $DW.Form.getData("supplier");
		Server.sendRequest("Goods/save.action", dc, function(response) {
			MsgPop(response.Message);
			if(response.Status==1){
				$D.close();
				$("")
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
			Server.sendRequest("Goods/delete.action", dc, function(response) {
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
</script>
</head>
<body class="z-body-detail">
	<table width="100%" id="js_layoutTable" border="0" cellspacing="0"
		cellpadding="0" height="*" class="js_layoutTable">
		<tr>
			<td colspan="2"><z:toolbar>
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
							placeholder="<z:lang id='Goods.SearchTip' />" style="width: 200px" onclick="this.select()">
						<input type="button" name="Submitbutton" id="Submitbutton"
							value="<z:lang id='Common.Search' />" onClick="doSearch()">
					</div>
				</div></td>
		</tr>
		<tr valign="top">
			<td height="*" style="padding: 3px;">
				<div class="z-overflowPanel" style="height: 100%;">
					<table id="gridTable">
					</table>
				</div>
			</td>
			<td height="*" style="padding: 3px;">
				<div class="z-overflowPanel" style="height: 100%;">
					<table id="gridTable2">
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
