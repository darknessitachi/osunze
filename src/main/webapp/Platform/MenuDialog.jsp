<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script language="javascript">
function selectUser(id) {
	var diag = new Dialog();
	diag.width = 800;
	diag.height = 450;
	diag.title = "<z:lang id='Menu.refResource'/>";
	var selId = $V("id");
	diag.url = "${ctx}/resources/resourceSelectDialog.action?id="+selId;
	diag.onOk = function(){
		var names = $DW.getSelectedNames();
		var ids = $DW.getSelectedIds();
		$S(id, ids);
		$S(id+"Name", names);
		$D.close();	
	};
	diag.show();
}
</script>
</head>
<body class="dialogBody">
<form id="form2">
<table width="100%" height="100%">
	<tr>
		<td valign="middle"><br>
		<table width="95%" height="97" align="center" style="padding: 4px" border="1" borderColor="#eeeeee">
			<tr>
				<td align="right" bgcolor="#F7F7F7"><strong> <z:lang id="Menu.ParentMenu"></z:lang></strong></td>
				<td>
					<input name="id"  type="hidden" value="${menu.id }" id="id" />
					<input name="parentId"  type="hidden" value="${menu.parentId }" id="parentId" />
					${menu.parent.name}
				</td>
			</tr>
			<tr>
				<td align="right" bgcolor="#F7F7F7"><strong> <z:lang id="Menu.Name"></z:lang></strong></td>
				<td width="75%"><input name="name"  type="text" value="${menu.name }" class="input1" id="name"  verify="NotNull" /></td>
			</tr>
			<tr>
				<td align="right" bgcolor="#F7F7F7"><strong> <z:lang id="Menu.refResource"></z:lang></strong></td>
				<td width="75%" height="101">
					<input type="hidden" name="refRes" id="refRes" value="${menu.refRes }">
					<textarea style="height:100px; width:240px;" id="refResName" readonly="true">${refResName}</textarea>
					<a href="javascript:void(0);" onclick="selectUser('refRes');"><z:lang id="Common.Add"/></a>
				</td>
			</tr>
			<tr>
				<td align="right" bgcolor="#F7F7F7"><strong> <z:lang
					id="Common.Memo"></z:lang></strong></td>
				<td><input name="description"  type="text" value="${menu.description }" class="input1" id="description"/></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>