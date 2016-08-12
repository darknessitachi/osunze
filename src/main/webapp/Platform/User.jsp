<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="User.Title">用户管理</z:lang></title>
<link href="../Style/Default.css" rel="stylesheet" type="text/css">
<script language="javascript">
$(document).ready(function() {
	var mainheight = document.documentElement.clientHeight;
	var otherpm = 100;
	var w = $("#bottom_div").width();
	var gh = mainheight - otherpm;
	$("#gridTable").jqGrid({
		url:'${ctx}/User/UserList.action',
		datatype: "json",
		width: w-12,
		height: gh,
		colNames:['ID','登录名','昵称','性别'],
		colModel:[
			{name:'id',index:'id',width:100,hidden:true}, 
			{name:'userName',index:'userName',width:60,search:true,align:"center"}, 
			{name:'userNickname',index:'userNickname',align:"center",width:50},
			{name:'userSex',index:'userSex',search:false}    
		],
		rowNum:15,
        rowList:[10, 15, 20, 25, 40, 100],
        pager: '#gridPager',
        prmNames:{rows:"pageSize",page:"pageIndex"},
        jsonReader: {
        	root: "result",
     		repeatitems : false,
     		id: "0" 
    	},
        sortable : true, 
        multiselect: true,
        viewrecords: true,
        sortname: 'userid',
        sortorder: "asc"
	});
});
</script>
<body>
<div>
		<img src="../Icons/icon021a2.png"/><z:lang id="Common.New">新建</z:lang>
	    <img src="../Icons/icon021a4.png"/><z:lang id="Common.Edit">修改</z:lang>
	    <img src="../Icons/icon021a9.png"/><z:lang id="User.Disable">停用</z:lang>
	    <img src="../Icons/icon021a8.png"/><z:lang id="User.Enable">启用</z:lang>
	    <img src="../Icons/icon021a3.png"/><z:lang id="Common.Delete">删除</z:lang>
	    <img src="../Icons/icon021a6.png"/><z:lang id="User.ChangePassword">修改密码</z:lang>
</div>
<div id="bottom_div">
     <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>
</body>
</html>
