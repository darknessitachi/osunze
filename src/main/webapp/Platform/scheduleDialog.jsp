<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/Include/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script>
function onPlanChange(){
	if($NV("PlanType")=="Period"){
		$("#Period").prop('disabled',false);
		$("#PeriodType").prop('disabled',false);
		$('#trCron').hide();
	}else{
		$("#Period").prop('disabled',true);
		$("#PeriodType").prop('disabled',true);
		$('#trCron').show();
	}
}
function onTypeChange(){//id参数仅供修改时初始化使用
	if($V('#TypeCode')){
		$("#SourceID").getComponent().setParam("TypeCode",$V("#TypeCode"));
		$("#SourceID").getComponent().loadData();
	}
}
</script>
</head>
<body>
<form id="form2">
 	<table width="100%" height="100%" border="0">
		<tr>
			<td>
			<table width="590" align="center" cellpadding="2" cellspacing="0">
				<tr>
					<td width="168" height="10"><input name=ID type="hidden" id="ID" value="${ID}"></td>
					<td width="412">
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><z:lang id="Cron.TaskType"/>：</td>
					<td>
						<select id="">
							<option value="1">系统任务</option>
						</select>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><z:lang id="Cron.SelectTask"/>：</td>
					<td>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"><z:lang id="Cron.StartTime"/>：</td>
					<td ><span> <input type="text" class="inputText" id="StartTime" value="${StartTime}"
						ztype="DateTime" size="30" verify="NotNull" /> </span></td>
				</tr>
				<tr>
					<td height="30" align="right"><z:lang id="Cron.Period"/>：</td>
					<td><input name="PlanType"
						type="radio" value="Period" onClick="onPlanChange();" checked><z:lang id="Cron.Per"/>
					<input name="Period" type="text" value="1" class="inputText"
						id="Period" size=3 verify="NotNull" />
						<select id="PeriodType">
							<option value="Minute"><z:lang id="Common.Minutes"/></option>
							<option value="Hour"><z:lang id="Common.Hours"/></option> 
							<option value="Day" selected><z:lang id="Common.Days"/></option>
							<option value="Month"><z:lang id="Common.Months"/></option>
						</select>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"  ></td>
					<td><label><input type="radio"
						name="PlanType" value="Cron" onClick="onPlanChange();"><z:lang id="Cron.UseCronExpr"/></label>
					<div id="trCron" style="display:none"><input
						name="CronExpression" type="text" value="${CronExpression}" class="inputText"
						id="CronExpression" size="40" verify="NotNull"
						condition="$NV('PlanType')=='Cron'" /></div>
					</td>
				</tr>
				<tr>
					<td height="30" align="right"  ><z:lang id="Common.Status"/>：</td>
					<td>
						<input type="radio" name="status" id="status" value="Y">是
						<input type="radio" name="status" id="status" value="N">否
					</td>
				</tr>
				<tr>
					<td height="30" align="right"  ><z:lang id="Common.Memo"/>：</td>
					<td><input name="Description" type="text"
						value="${Memo}" class="inputText" id="Description" size=40 /></td>
				</tr>
				<tr>
					<td height="30" align="right"  >&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
