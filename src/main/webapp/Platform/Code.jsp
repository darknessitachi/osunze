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
			url : '${ctx}/Code/queryList.do',
			loadMsg : '<z:lang id="Platform.loadDataMsg"/>',
			singleSelect : true, 
			fitColumns : true, 
			pagination : true,
			rownumbers : true, 
			pageSize : 10, 
			pageList : [ 10, 25, 50, 100 ],  
			remoteSort : false,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			columns : [ [ {
				title : '<z:lang id="Code.CodeType"/>',
				field : 'codeType',
				width : 100
			}, {
				title : '<z:lang id="Code.CodeName"/>',
				field : 'codeName',
				width : 100
			}, {
				title : '<z:lang id="Common.Memo"/>',
				field : 'codeMemo',
				width : 100
			} ] ]
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
		if (!selr || selr.length != 1) {
			Dialog.alert("<z:lang id='Common.PleaseSelectOnlyOneRecord'/>");
			return;
		}
		var ids = [];
		for (var i = 0; i < selr.length; i++) {
			var row = selr[i];
			ids.push(selr[i].codeType);
		}
		var diag = new Dialog();
		diag.width = 450;
		diag.height = 150;
		diag.title = "<z:lang id='Code.UpdateCode'/>";
		diag.url = "${ctx}/Code/updCode.do?codeType="+ids;
		diag.onLoad = function() {
			$DW.$("#codeTypeTxt").text("<z:lang id='Code.CodeType'/>");
			$DW.$("#codeNameTxt").text("<z:lang id='Code.CodeName'/>");
			$DW.$("#codeType").prop('disabled',true);
			$DW.$('#codeType').focus();
		}
		diag.onOk = doSave;
		diag.show();
	}
	function add() {
		var diag = new Dialog();
		diag.width = 400;
		diag.height = 150;
		diag.title = "<z:lang id='Code.addCode'/>";
		diag.url = "CodeDialog.jsp?ParentCode=System&codeType=System";
		diag.onLoad = function() {
			$DW.$("#codeTypeTxt").text("<z:lang id='Code.CodeType'/>");
			$DW.$("#codeNameTxt").text("<z:lang id='Code.CodeName'/>");
			$DW.$("#operator	").val("add");
		 	$DW.$('#codeType').focus();
		};
		diag.onOk = doSave;
		diag.show();
	}

	function doSave() {
		if ($DW.Verify.hasError()) {
			return;
		}
		var dc = $DW.Form.getData("form2");
		Server.sendRequest("Code/saveCode.do", dc, function(response) {
			if(response.Status==1){
				MsgPop(response.Message);
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
		var ids = selr[0].codeType;
		Dialog.confirm("<z:lang id="Common.ConfirmDelete"/>", function() {
			var dc = {};
			dc["IDs"]=ids;
			Server.sendRequest("Code/delCode.do", dc, function(response) {
				MsgPop(response.Message);
				if(response.Status==1){
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
			if (ele.id == 'SearchCodeType' || ele.id == 'Submitbutton') {
				doSearch();
			}
		}
	}
	function doSearch() {
		var codeName = $('#SearchCodeType').val();
		$('#gridTable').datagrid('load',{qrCode:codeName});  
	}

	function codeList(codeType) {
		var diag = new Dialog();
		diag.width = 700;
		diag.height = 380;
		diag.title = "<z:lang id='Code.CodeType'></z:lang>";
		diag.url = "${ctx}/Code/codeItem.do?codeType=" + codeType;
		diag.show();
	}

	function editItems() {
		var selr = $('#gridTable').datagrid("getSelections");
		if (selr.length == 0) {
			Dialog.alert("<z:lang id='Common.PleaseSelectOnlyOneRecord'/>");
			return;
		}
		codeList(selr[0]["codeType"]);
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
					<z:button id="ButtonEdit" onClick="editItems()" theme="flat">
						<img src="../Icons/editsub.png" />
						<z:lang id="Code.EditList"/>
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
						<input type="text" name="SearchCodeType" id="SearchCodeType"
							placeholder="<z:lang id='Code.SearchTip' />" style="width: 200px" onclick="this.select()">
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
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
