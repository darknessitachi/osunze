<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script language="javascript">
$(document).ready(function(){
	var height=$(window).height();
	$('#gridTable').datagrid({
        iconCls:'icon-ok',//图标 
        width: '100%', 
        height: height, 
        nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
        striped : true,//设置为true将交替显示行背景
        border: true, 
        collapsible:false,//是否可折叠的 
        url:'queryList.action', 
        loadMsg : '数据装载中......',
        idField:'id', 
        singleSelect:false,	//为true时只能选择单行
        fitColumns:true,	//允许表格自动缩放，以适应父容器
        pagination:true,	//分页控件 
        pageSize: 20,		//每页显示的记录条数，默认为10 
        pageList: [10,20,50,100],	//可以设置每页记录条数的列表 
        frozenColumns : [[{
           field : 'ck',
           checkbox : true
       	}]],
       	columns:[[
       	       {title:'<z:lang id="Resources.Name"/>',field:'name',width:100},
       	       {title:'资源KEY',field:'resKey',width:100},
       	       {title:'资源描述',field:'description',width:100}
       	]]
    }); 
	var p = $('#gridTable').datagrid('getPager'); 
    $(p).pagination({ 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页 共{pages}页', 
        displayMsg: ''
    }); 
    
    $('#gridTable2').datagrid({
        iconCls:'icon-ok',//图标 
        width: '100%', 
        height: height, 
        nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
        striped : true,//设置为true将交替显示行背景
        border: true, 
        collapsible:false,//是否可折叠的 
        url:'${ctx}/Menu/queryRefResList.action?menuId='+${id}, 
        idField:'id', 
        singleSelect:false,	//为true时只能选择单行
        fitColumns:true,	//允许表格自动缩放，以适应父容器
        pagination:true,	//分页控件 
        pageSize: 20,		//每页显示的记录条数，默认为10 
        pageList: [10,20,50,100],	//可以设置每页记录条数的列表 
        frozenColumns : [[{
           field : 'ck',
           checkbox : true
       	}]],
       	columns:[[
       	       {title:'<z:lang id="Resources.Name"/>',field:'name',width:100},
       	       {title:'资源KEY',field:'resKey',width:100},
       	       {title:'资源描述',field:'description',width:100}
       	]]
    }); 
	var p = $('#gridTable2').datagrid('getPager'); 
    $(p).pagination({ 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页 共{pages}页', 
        displayMsg: ''
    }); 
});
function add(){
	var selr=$('#gridTable').datagrid("getSelections");
	if(!selr||selr.length==0){
		Dialog.alert("<z:lang id='Framework.EasyUI.PleaseSelectFirst'>请先选择要操作的数据!</z:lang>");
		return;
	}
	for (var i = 0; i < selr.length; i++) { 
		var row = selr[i];
		$("#gridTable2").datagrid("insertRow",{index:0,row:row});
	}
}

function remove(){
	
}
function getSelectedIds(){
	return getSelected('id');
}
function getSelectedNames(){
	return getSelected('name');
}

function getSelected(column){
	var selr= $('#gridTable2').datagrid("getSelections");
	if(!selr||selr.length==0){
		alert("<z:lang id='Framework.EasyUI.PleaseSelectFirst'>请先选择要操作的数据!</z:lang>");
		return;
	}
	var arr = [];
	for (var i = 0; i < selr.length; i++) { 
		var row = selr[i];
		arr.push(row[column]);
	}
	return arr.join();
}
function commitSearch(){
	
}
</script>
</head>
<body class="z-body-detail">
  <input type="hidden" id="UserNames" name="UserNames" value="${SelectedUsers}"/>
  <table width="100%" id="js_layoutTable" border="0" cellspacing="0"
	cellpadding="0" height="*" class="js_layoutTable">
    <tr>
      <td height="33">
          <img src="../Icons/icon021a2.png" /><a href="#" onClick="add()"><z:lang id="Resources.AddSelectedResource"/></a>
          <img src="../Icons/icon021a3.png" /><z:lang id="Resources.RemoveSelectedResource"/>
        </td>
    </tr>
    <tr>
      <td height="33"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td style="padding-left:8px" width="55%"> <z:lang id="Resources.Name"></z:lang>：
              <input type='text' id="SearchContent" style="width:120px;" placeholder="<z:lang id='Resources.SearchTip'></z:lang>">
              <input type="button" onclick="commitSearch()" value="<z:lang id='Common.Search'></z:lang>" /></td>
            <td width="45%"><div class="z-legend"><b><z:lang id="Resources.SelectedResourceList"></z:lang></b></div></td></tr></table>
          </tr>
          <tr>
            <td height="*"><table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" style="table-layout:fixed;">
                <tr>
                  <td width="50%" height="100%" valign="top" style="padding-top:0px;padding-left:6px;padding-right:3px;padding-bottom:6px;">
                      <table id="gridTable" width="100%" cellpadding="2" cellspacing="0" class="z-datagrid">
     				  </table>
                   </td>
                  <td width="50%" height="100%" valign="top" style="padding-top:0px;padding-right:6px;padding-left:3px;padding-bottom:6px;">
                  		<table id="gridTable2" width="100%" cellpadding="2" cellspacing="0" class="z-datagrid">
     					</table>
                  </td>
              </tr>
            </table></td>
          </tr>
        </table>
</body>
</html>
