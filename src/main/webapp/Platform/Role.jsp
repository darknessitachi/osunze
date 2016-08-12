<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="User.Title">资源管理</z:lang></title>
<script language="javascript">
$(document).ready(function(){
	var height = $(window).height() - 80;
	function editPermission(value, rowData, rowIndex) {
		 return "<a href='javascript:editRights("+rowData["roleId"]+")'>权限设置</a>";
	}
	$('#gridTable').datagrid({
        width: '100%', 
      /*   title:'aa', */
        height: height, 
        nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
        striped : true,//设置为true将交替显示行背景
        border: true, 
        collapsible:false,//是否可折叠的 
        url:'${ctx}/roles/queryList.do', 
        //sortName: 'code', 
        //sortOrder: 'desc', 
        loadMsg : '数据装载中......',
        idField:'id', 
        singleSelect:true,	//为true时只能选择单行
        fitColumns:true,	//允许表格自动缩放，以适应父容器
        pagination:true,	//分页控件 
        rownumbers:true,	//行号 
        pageSize: 20,		//每页显示的记录条数，默认为10 
        pageList: [10,20,50,100],	//可以设置每页记录条数的列表 
        remoteSort : false,
        frozenColumns : [[{
           field : 'ck',
           checkbox : true
       	}]],
       	columns:[[
       	       {title:'<z:lang id="Role.Name"/>',field:'name',width:100},
       	       {title:'<z:lang id="Resources.URL"/>',field:'resUrl',width:100},
       	       {title:'<z:lang id="Role.Assignpermissions"/>',align:'center',field:'description',width:100,formatter: editPermission}
       	]]
    }); 
	var p = $('#gridTable').datagrid('getPager'); 
    $(p).pagination({ 
        beforePageText: '<z:lang id="Platform.Pagination.BeforePageText"/>',//页数文本框前显示的汉字 
        afterPageText: '<z:lang id="Platform.Pagination.afterPageText"/>', 
        displayMsg: '<z:lang id="Platform.Pagination.displayMsg"/>'
    }); 
});

function zDel(){
	var selr=$('#gridTable').datagrid("getSelections");
	if(!selr||selr.length==0){
		Dialog.alert("<z:lang id='Common.PleaseSelectOneRowAtLeast'/>");
		return;
	}
	var ids = [];
	for (var i = 0; i < selr.length; i++) { 
		var row = selr[i];
		ids.push(selr[i].id);
	}
	Dialog.confirm("确定删除记录么?",function(){
		var dc = new DataCollection();
		dc.add("IDs",ids);
		Server.sendRequest("resources/delResource.do", dc, function(response) {
			if(response.Status==1){
				MsgPop(response.Message);
				$("#gridTable").datagrid("reload");
			}
		});
	});
}
function add(){
	var diag = new Dialog();
	diag.width = 400;
	diag.height = 150;
	diag.title = "<z:lang id='Resources.New'></z:lang>";
	diag.url = "ResourcesDialog.jsp";
	diag.onLoad = function(){
	};
	diag.onOk = doSave;
	diag.show();
}
document.onkeydown = function(event) {
	event = getEvent(event);
	if (event.keyCode == 13) {
		var ele = event.srcElement || event.target;
		if (ele.id == 'SearchResource' || ele.id == 'Submitbutton') {
			doSearch();
		}
	}
}
function refresh() {
	$("#gridTable").datagrid("reload");
}
function doSearch() {
	var resourceName = $('#SearchResource').val();
	$('#gridTable').datagrid('load',{name:resourceName});  
}
function edit() {
	var selr = $('#gridTable').datagrid("getSelections");
	if (!selr || selr.length != 1) {
		Dialog.alert("<z:lang id='Common.PleaseSelectOnlyOneRecord'/>");
		return;
	}
	var ids = [];
	for (var i = 0; i < selr.length; i++) {
		var row = selr[i];
		ids.push(selr[i].id);
	}
	var diag = new Dialog();
	diag.width = 400;
	diag.height = 150;
	diag.title = "<z:lang id='Resources.Update'/>";
	diag.url = "${ctx}/resources/update.do?id="+ids;
	diag.onLoad = function() {
	}
	diag.onOk = doSave;
	diag.show();
}
function doSave(){
	var dc = $DW.Form.getData('resources');
	Server.sendRequest("resources/save.do", dc, function(response) {
		if(response.Status==1){
			MsgPop(response.Message);
			$D.close();
			$("#gridTable").datagrid("reload"); 
		} 
	});
}
function editRights(roleId){
	var diag = new Dialog();
	diag.width = 400;
	diag.height = 150;
	diag.title = "<z:lang id='Role.RoleMenuManager'/>";
	diag.url = "${ctx}/roles/auth.do?roleId="+roleId;
	diag.onLoad = function() {
	}
	diag.onOk = function(){
		var roleId = $DW.$V("roleId");
		var menuIds = $DW.$V("menuIds");
		var dc = {};
		dc["menuIds"] = menuIds;
		dc["roleId"] = roleId;
		Server.sendRequest("roles/auth/save.do",dc,function(response){
			if(response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert(response.Message);
				$D.close();
			}
		});
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
						<input type="text" name="SearchResource" id="SearchResource"
							placeholder="<z:lang id='Resources.SearchTip' />" style="width: 200px" onclick="this.select()">
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
