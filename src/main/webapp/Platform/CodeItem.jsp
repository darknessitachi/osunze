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
	var height = $(window).height() - 80;
	$('#gridTable').datagrid({
		width : '98%',
		height : height,
		nowrap : true,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的 
		url : '${ctx}/Code/queryList.do?parentCode=${codeType}',
		loadMsg : '<z:lang id="Platform.loadDataMsg"/>',
		singleSelect : false, 
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
			title : '<z:lang id="Code.ItemValue"/>',
			field : 'codeType',
			width : 100
		}, {
			title : '<z:lang id="Code.ItemName"/>',
			field : 'codeName',
			width : 100
		}, {
			title : '<z:lang id="Code.CodeType"/>',
			field : 'parentCodeName',
			width : 100
		}, {
			title : '<z:lang id="Common.Memo"/>',
			field : 'codeMemo',
			width : 100
		} ] ]
	});
	var p = $('#gridTable').datagrid('getPager');
	$(p).pagination({
		beforePageText : '第',//页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});
function add(){
	var diag = new Dialog();
	diag.width = 450;
	diag.height = 150;
	diag.title = "<z:lang id='Code.addCodeItem'></z:lang>";
	diag.url = "${ctx}/Platform/CodeDialog.jsp";
	diag.onLoad = function(){
		$DW.$('#codeType').focus();
		$DW.$("#parentCode").val('${codeType}');
		$DW.$("#codeTypeTxt").text("<z:lang id='Code.ItemValue'/>");
		$DW.$("#codeNameTxt").text("<z:lang id='Code.ItemName'/>");
		$DW.$("#operator").val("add");
	};
	diag.onOk = doSave;
	diag.show();
}
function edit(){
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
	diag.url = "${ctx}/Code/updCode.do?codeType="+ids+"&parentCode=${codeType}";
	diag.onLoad = function() {
		$DW.$("#codeTypeTxt").text("<z:lang id='Code.ItemValue'/>");
		$DW.$("#codeNameTxt").text("<z:lang id='Code.ItemName'/>");
		$DW.$("#codeType").prop('disabled',true);
		$DW.$('#codeType').focus();
	}
	diag.onOk = doSave;
	diag.show();
}

function doSave() {
	if ($DW.Verify.hasError()) {
		return;
	}
	var dc = $DW.Form.getData("form2");
	Server.sendRequest("Code/saveCode.do", dc, function(response) {
		Dialog.alert(response.Message, function() {
			if (response.Status == 1) {
				$D.close();
				$("#gridTable").datagrid("reload");
			}
		}, response.Status);
	});
}

function del(){
	var selr = $('#gridTable').datagrid("getSelections");
	if (!selr || selr.length == 0) {
		Dialog.alert("<z:lang id='Common.PleaseSelectOneRowAtLeast'/>");
		return;
	}
	var ids = "";
	for (var i = 0; i < selr.length; i++) {
		var row = selr[i];
		if(i==selr.length-1){
			ids+=selr[i].codeType;
		}else{
			ids+=selr[i].codeType+",";
		}
	}
	Dialog.confirm("<z:lang id="Common.ConfirmDelete"/>", function() {
		var dc = {};
		dc["IDs"]=ids;
		dc["parentCode"]='${codeType}';
		Server.sendRequest("Code/delCode.do", dc, function(response) {
			Dialog.alert(response.Message, function() {
				if (response.Status == 1) {
					$("#gridTable").datagrid("reload");
				}
			}, response.Status);
		});
	});
}
</script>
</head>
<body class="z-body-detail">
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
  <tr><td><z:toolbar>
        		<z:button id="ButtonAdd" onClick="add()"  theme="flat"><img src="../Icons/add.png" /><z:lang id="Common.Add">添加</z:lang></z:button> 
				<z:button id="ButtonEdit" onClick="edit()" theme="flat"><img src="../Icons/update.png" /><z:lang id="Common.Modify">修改</z:lang></z:button> 
				<z:button id="ButtonDel" onClick="del()" theme="flat"><img src="../Icons/delete.png" /><z:lang id="Common.Delete">删除</z:lang></z:button>
		  </z:toolbar>
    </td>
  </tr>
  <tr valign="top">
    <td height="*" style="padding:7px;">
    	<div id="SP1" class="z-overflowPanel" style="height:100%;">
			<table id="gridTable">
			</table>
		</div>
	</td>
  </tr>
</table>
</body>
</html>