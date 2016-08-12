<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script>
Page.onReady(function(){
	$("#name").focus();
});
</script>
</head>
<body class="dialogBody">
<form id="resources"  method="post">
<input type="hidden" id="id" value="${resource.id }"/>
<table align="center" cellpadding="2" cellspacing="0">
	<tr>
		<td><z:lang id="Menu.ParentMenu"/></td>
		<td>
			<select name="pId" id="pId" onchange="setMUR()">
				<option value="0"><z:lang id="Menu.TopMenu"/></option>
				<c:forEach items="${rootMenus}" var="menu">
					<option value="${menu.id }">${menu.name }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
    <tr>
      <td height="30" align="right"><z:lang id="Resources.Name"/>：</td>
      <td><input value="${resource.name }" type="text" class="input1" id="name"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Resources.URL"/>：</td>
      <td><input value="${resource.resUrl }" type="text" class="input1" id="resUrl"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Resources.Desc"/>：</td>
      <td><input value="${resource.description }" type="text" class="input1" id="description"/></td>
    </tr>
</table>
</form>
</body>
</html>