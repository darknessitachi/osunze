<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="User.Title">用户管理</z:lang></title>
<script language="javascript">
$(document).ready(function() {
	$("#gcttree").tree({
		url : "${ctx}/Menu/showMenu.action", // 默认加载的目录
		onBeforeExpand : function(node) {
			if (node) {
				$('#gcttree').tree('options').url = "${ctx}/Menu/showMenu.action?id="+ node.id;
			}
		},
		onContextMenu: function(e, node){
	        e.preventDefault();	//阻止缺省的右键菜单
	        $(this).tree('select',node.target);
	        $('#menu').menu('show', {
	            left: e.pageX,
	            top: e.pageY,
	            onClick:function(action){
	            	if(node.id==1 && action.id!="addSubMenu"){
	            		Dialog.alert("不能对根节点进行该操作");
	            	}else{
	            		if(action.id=="addMenu"){
	            			addMenu(node.pid);
	            		}else if(action.id=="addSubMenu"){
	            			addMenu(node.id);
	            		}else if(action.id=="updMenu"){
	            			updateMenu(node.id);
	            		}
	            		else if(action.id=="delMenu"){
	            			Dialog.confirm("<z:lang id='Common.ConfirmDelete'></z:lang>",function(){
		            			delMenu(node.id);
	            			});
		            	}
	            	}
	            }
	        });
	    }
	});
	function addMenu(id){
		var diag = new Dialog();
		diag.width = 450;
		diag.height = 250;
		diag.title = "<z:lang id='Menu.addMenu'></z:lang>";
		diag.url = "${ctx}/Menu/addUI.action?parentId="+id;
		diag.onLoad = function(){
			$DW.$('#name').focus();
		};
		diag.onOk = doUpdate;
		diag.show();
	}
	function updateMenu(id){
		var diag = new Dialog();
		diag.width = 450;
		diag.height = 250;
		diag.title = "<z:lang id='Menu.updateMenu'></z:lang>";
		diag.url = "${ctx}/Menu/updateUI.action?id="+id;
		diag.onLoad = function(){
			$DW.$('#name').focus();
		};
		diag.onOk = doUpdate;
		diag.show();
	}
	function delMenu(id){
		var dc = new DataCollection();
		dc.add("id",id);
		Server.sendRequest("Menu/delMenu.action",dc,function(response){
			if(response&&response.Status==0){
				Dialog.alert(response.Message);
			}else{
				Dialog.alert(response.Message);
				$("#gcttree").tree('remove',$("#gcttree").tree('getSelected').target); 
			}
		});
	}
	
	function doUpdate(){
		if($DW.Verify.hasError()){
			return;
		}
		var dc = $DW.Form.getData("form2");
		Server.sendRequest("Menu/addMenu.action",dc,function(response){
			$D.close();
		});
	}
})
</script>
</head>
<body class="z-body-detail">
	<div style="left: 20px; top: 20px">
		<ul id="gcttree">
		</ul>
	</div>
	
<div id="menu" class="easyui-menu" style="width:150px;">
    <div id="addMenu">新增同级菜单</div>
    <div id="addSubMenu">新增下级菜单</div>
    <div class="menu-sep"></div>
    <div id="updMenu">修改当前菜单</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-save'" id="delMenu">删除当前菜单</div>
</div>
</body>
</html>