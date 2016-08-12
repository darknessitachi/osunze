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
	$('#gridTable').treegrid({
        width: '100%', 
        height: height, 
        nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
        striped : true,//设置为true将交替显示行背景
        border: true, 
        collapsible:false,//是否可折叠的 
      	url:'${ctx}/resources/queryList.do',
        loadMsg : '数据装载中......',
        idField:'id',
        treeField:'name',
        singleSelect:true,	//为true时只能选择单行
        fitColumns:true,	//允许表格自动缩放，以适应父容器
        pagination:true,	//分页控件 
        rownumbers:false,	//行号 
        pageSize: 20,		//每页显示的记录条数，默认为10 
        pageList: [10,20,50,100],	//可以设置每页记录条数的列表 
        remoteSort : false,
       /* frozenColumns : [[{
           field : 'ck',
           checkbox : true
       	}]], */
       	columns:[[ 
       	       {title:'<z:lang id="Resources.Name"/>',field:'name',width:100},
       	       {title:'<z:lang id="Resources.URL"/>',field:'resUrl',width:100},
       	       {title:'<z:lang id="Resources.Desc"/>',field:'description',width:100}
       	]],
       	onBeforeLoad: function(row,param){
			if (!row) {	// load top level rows
				param["id"] = 0;	// set id=0, indicate to load new page rows
			}
		}
    }); 
	var p = $('#gridTable').treegrid('getPager'); 
    $(p).pagination({ 
        beforePageText: '<z:lang id="Platform.Pagination.BeforePageText"/>',//页数文本框前显示的汉字 
        afterPageText: '<z:lang id="Platform.Pagination.afterPageText"/>', 
        displayMsg: '<z:lang id="Platform.Pagination.displayMsg"/>'
    }); 
});

function zDel(){
	var selr=$('#gridTable').treegrid("getSelections");
	if(!selr||selr.length==0){
		Dialog.alert("<z:lang id='Common.PleaseSelectOneRowAtLeast'/>");
		return;
	}
	var ids = selr[0].id;
	Dialog.confirm("确定删除记录么?",function(){
		var dc = {};
		dc["IDs"]=ids;
		Server.sendRequest("resources/delResource.do", dc, function(response) {
			if(response.Status==1){
				MsgPop(response.Message);
				$("#gridTable").treegrid("reload");
			}
		});
	});
}
function add(){
	var diag = new Dialog();
	diag.width = 400;
	diag.height = 150;
	diag.title = "<z:lang id='Resources.New'/>";
	diag.url = "${ctx}/resources/toAdd.do";
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
	$("#gridTable").treegrid("reload");
}
function doSearch() {
	var resourceName = $('#SearchResource').val();
	$('#gridTable').treegrid('load',{name:resourceName});  
}
function edit() {
	var selr = $('#gridTable').treegrid("getSelections");
	if (!selr || selr.length != 1) {
		Dialog.alert("<z:lang id='Common.PleaseSelectOnlyOneRecord'/>");
		return;
	}
	var ids = selr[0].id;
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
			$("#gridTable").treegrid("reload"); 
		} 
	});
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
