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
	$("#supplierId").focus();
});
</script>
</head>
<body class="dialogBody">
<form:form id="supplier"  method="post" commandName="goods">
<form:hidden path="id"/>
<table align="center" cellpadding="2" cellspacing="0" width="99%">
    <tr>
      <td height="20" align="right" width="30%"><z:lang id="Supplier.Name"/>：</td>
      <td>
      	<form:select path="supplierId">
      		<form:options items="${supplierList}" itemValue="id" itemLabel="name"/>	
      	</form:select>
      </td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Brand"/>：</td>
      <td>
      	<form:select path="brand">
      		<form:options items="${brandList}" itemValue="codeType" itemLabel="codeName"/>	
      	</form:select>
      </td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Type"/>：</td>
      <td>
      	<form:select path="goodType">
      		<form:options items="${goodsTypeList}" itemValue="codeType" itemLabel="codeName"/>	
      	</form:select>
      </td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Model"/>：</td>
      <td><form:input path="model"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Unit"/>：</td>
      <td>
      	<form:select path="unit">
      		<form:options items="${unitList}" itemValue="codeType" itemLabel="codeName"/>	
      	</form:select>
      </td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Origin"/>：</td>
      <td><form:input path="origin"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Color"/>：</td>
      <td><form:checkboxes path="colorList" items = "${colorList}" itemValue="codeType" itemLabel="codeName"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.BarCode"/>：</td>
      <td><form:input path="barcode"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Weight"/>：</td>
      <td><form:input path="weight"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Size"/>：</td>
      <td>
      	<form:input path="sizeLong" cssStyle="width:30px"/>*
      	<form:input path="sizeWidth" cssStyle="width:30px"/>*
      	<form:input path="sizeHeight" cssStyle="width:30px"/>
      	(<z:lang id="Common.Long"/>*<z:lang id="Common.Width"/>*<z:lang id="Common.Height"/>)</td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Material"/>：</td>
      <td><form:input path="material"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Level"/>：</td>
      <td>
      <form:select path="level">
      		<form:options items="${levelList}" itemValue="codeType" itemLabel="codeName"/>	
      	</form:select>
      </td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.RetailPrice"/>：</td>
      <td><form:input path="retailPrice"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Goods.Desc"/>：</td>
      <td><form:input path="description"/></td>
    </tr>
</table>
</form:form>
</body>
</html>