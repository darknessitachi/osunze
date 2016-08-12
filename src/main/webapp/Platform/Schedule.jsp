<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="Cron.List"/></title>
<script language="javascript">
	$(document).ready(function() {
		var height = $(window).height() - 48;
		$('#gridTable').datagrid({
			width : '100%',
			height : height,
			nowrap : true,
			striped : true,
			border : true,
			collapsible : false,//是否可折叠的 
			url : '${ctx}/Schedule/queryList.action',
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
				title : '<z:lang id="Cron.Type"/>',
				field : 'typeCode',
				width : 30
			}, {
				title : '<z:lang id="Cron.Name"/>',
				field : 'cronExpression',
				width : 20
			}, {
				title : '<z:lang id="Cron.Status"/>',
				field : 'isUsing',
				width : 10
			}, {
				title : '<z:lang id="Cron.NextExecuteTime"/>',
				field : 'codeMemo',
				width : 20
			}, {
				title : '<z:lang id="Cron.Description"/>',
				field : 'description',
				width : 20
			} ] ]
		});
		var p = $('#gridTable').datagrid('getPager');
		$(p).pagination({
			beforePageText: '<z:lang id="Platform.Pagination.BeforePageText"/>',//页数文本框前显示的汉字 
	        afterPageText: '<z:lang id="Platform.Pagination.afterPageText"/>', 
	        displayMsg: '<z:lang id="Platform.Pagination.displayMsg"/>'
		});
	});
function add(){
	var diag = new Dialog();
	diag.width = 600;
	diag.height = 300;
	diag.title = "<z:lang id='Cron.New'/>";
	diag.ShowMessageRow = true;
	diag.messageTitle = "<z:lang id='Cron.New'/>";
	diag.message = "<z:lang id='Cron.NewMessage'/>";
	diag.url = "scheduleDialog.jsp";
	diag.onLoad = function(){
	};
	diag.onOk = save;
	diag.show();
}

function save(){
	var dc = $DW.Form.getData("form2");
	if($DW.Verify.hasError()){
		return;
}
	Server.sendRequest("Schedule.save",dc,function(response){
		if(response.Status==1){
			Dialog.alert(response.Message);
			$D.close();
			DataGrid.setParam("dg1",Constant.PageIndex,0);
		    DataGrid.loadData("dg1");
		}else{
			Dialog.warn(response.Message);
		}
	});
}

function edit(dr){
	if(!dr){
		var dt = DataGrid.getSelectedData("dg1");
		if(dt.getRowCount()==0){
			Dialog.alert("<z:lang id='Framework.DataGrid.PleaseSelectFirst'>请先选择要编辑的行!</z:lang>");
			return;
		}
		dr = dt.getDataRow(0);
	}
	var diag = new Dialog();
	diag.width = 600;
	diag.height = 300;
	diag.title = "<z:lang id='Cron.Edit'>修改定时计划</z:lang>";
	diag.ShowMessageRow = true;
	diag.messageTitle = "<z:lang id='Cron.Edit'>修改定时计划</z:lang>";
	diag.message = "<z:lang id='Cron.NewMessage'>选择可执行任务，并设置执行计划</z:lang>";
	diag.url = "scheduleDialog.zhtml?ID="+dr.get("ID");
	diag.onLoad = function(){
		$DW.SourceID = dr.get("SourceID");
		if(dr.get("PlanType")=="Period"){
			var expr = dr.get("CronExpression");
			var arr = expr.split(" ");
			if(arr[0].indexOf("/")>0){
				$DW.$S('#PeriodType',"Minute");
				$DW.$S('#Period',arr[0].substring(arr[0].indexOf("/")+1));
			}
			if(arr[1].indexOf("/")>0){
				$DW.$S('#PeriodType',"Hour");
				$DW.$S('#Period',arr[1].substring(arr[1].indexOf("/")+1));
			}
			if(arr[2].indexOf("/")>0){
				$DW.$S('#PeriodType',"Day");
				$DW.$S('#Period',arr[2].substring(arr[2].indexOf("/")+1));
			}
			if(arr[3].indexOf("/")>0){
				$DW.$S('#PeriodType',"Month");
				$DW.$S('#Period',arr[3].substring(arr[3].indexOf("/")+1));
			}
			if(arr[4].indexOf("/")>0){
				$DW.$S('#PeriodType',"Year");
				$DW.$S('#Period',arr[4].substring(arr[4].indexOf("/")+1));
			}
		}		
		$DW.onPlanChange();
		$DW.$('#TypeCode').disable();
		$DW.$('#SourceID').disable();
	}
	if(Application.hasPriv("Platform.Schedule.Edit")){
		diag.onOk = save;
	}else{
		diag.cancelText="<z:lang id="Common.Close">关闭</z:lang>";
		diag.onCancel=function(){$DW.close();};
	}
	diag.show();
}

function execute(){
	var dt = DataGrid.getSelectedData("dg1");
	if(dt.getRowCount()==0){
		Dialog.alert("<z:lang id='Framework.DataGrid.PleaseSelectFirst'>请先选择要编辑的行!</z:lang>");
		return;
	}
	dr = dt.getDataRow(0);
	var dc = new DataCollection();
	dc.add("ID",dr.get("ID"));
	Server.sendRequest("Schedule.execute",dc,function(response){
		Dialog.alert(response.Message,null,response.Status);
	});
}

function del(){
	var arr = DataGrid.getSelectedValue("dg1");
	if(!arr||arr.length==0){
		Dialog.alert("<z:lang id='Framework.DataGrid.PleaseSelectFirst'>请先选择要编辑的行!</z:lang>");
		return;
	}
	Dialog.confirm("<z:lang id="Common.ConfirmDelete">确认删除</z:lang>？",function(){
		var dc = new DataCollection();	
		dc.add("IDs",arr.join());
		Server.sendRequest("Schedule.del",dc,function(response){
			Dialog.alert(response.Message,null,response.Status);
			if(response.Status==1){
				DataGrid.setParam("dg1",Constant.PageIndex,0);
        		DataGrid.clearAllSelected('dg1');
				DataGrid.loadData("dg1");
			}
		});
	});
} 
</script>
</head>
<body class="z-body-detail">
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
  <tr>
	<td>
	<z:toolbar>
		<z:button onClick="add()" theme="flat"><img src="../Icons/add.png" /><z:lang id="Common.New">新建</z:lang></z:button>
		<z:button onClick="edit()" theme="flat"> <img src="../Icons/update.png" /><z:lang id="Common.Modify">修改</z:lang></z:button>
		<z:button onClick="del()" theme="flat"> <img src="../Icons/delete.png" /><z:lang id="Common.Delete">删除</z:lang></z:button>
		<z:button onClick="execute()" theme="flat"> <img src="../Icons/ManualClock.png" /><z:lang id="Cron.Manual" /></z:button>
	</z:toolbar>
	</td>
  </tr>
  <tr valign="top">
    <td height="*" style="padding:7px;">
		<table id="gridTable">
		</table>
	</td>
  </tr>
</table>
</body>
</html>
