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
			url : '${ctx}/Supplier/queryList.do',
			loadMsg : '<z:lang id="Platform.loadDataMsg"/>',
			singleSelect : false, 
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
			    {title : '<z:lang id="Supplier.Name"/>',field : 'name',width : 50}, 
			    {title : '<z:lang id="Supplier.Contacts"/>',field : 'contacts',width : 30},
			    {title : '<z:lang id="Supplier.Phone"/>',field : 'phone',width : 40},
			    {title : '<z:lang id="Common.QQ"/>',field : 'qq',width : 30},
			    {title : '<z:lang id="Supplier.Email"/>',field : 'email',width : 40}, 
			    {title : '<z:lang id="Supplier.WebSite"/>',field : 'webSite',width : 60}, 
			    {title : '<z:lang id="Supplier.Address"/>',field : 'address',width : 90}
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
		diag.width = 450;
		diag.height = 180;
		diag.title = "<z:lang id='Supplier.AddSupplier'/>";
		diag.url = "SupplierDialog.jsp";
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
		Server.sendRequest("Supplier/save.do", dc, function(response) {
			if(response.Status==1){
				MsgPop(response.Message);
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
		var ids = "";
		for (var i = 0; i < selr.length; i++) {
			var row = selr[i];
			if(i==selr.length-1){
				ids+=selr[i].id;
			}else{
				ids+=selr[i].id+",";
			}
		}
		Dialog.confirm("<z:lang id="Common.ConfirmDelete"/>", function() {
			var dc = {};
			dc["IDs"]=ids;
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
