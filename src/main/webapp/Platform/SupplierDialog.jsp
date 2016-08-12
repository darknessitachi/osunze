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
<form id="supplier"  method="post">
<input type="hidden" id="id" value="${entity.id }"/>
<table align="center" cellpadding="2" cellspacing="0" width="99%">
    <tr>
      <td height="20" align="right" width="40%"><z:lang id="Supplier.Name"/>：</td>
      <td><input value="${entity.name }" type="text" class="input1" id="name" verify="NotNull"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Supplier.Contacts"/>：</td>
      <td><input value="${entity.contacts }" type="text" class="input1" id="contacts"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Supplier.Phone"/>：</td>
      <td><input value="${entity.phone }" type="text" class="input1" id="phone" verify="CnPhone&&NotNull"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Supplier.Email"/>：</td>
      <td><input value="${entity.email }" type="text" class="input1" id="email"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Common.QQ"/>：</td>
      <td><input value="${entity.qq }" type="text" class="input1" id="qq"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Supplier.WebSite"/>：</td>
      <td><input value="${entity.webSite }" type="text" class="input1" id="webSite"/></td>
    </tr>
    <tr>
      <td height="20" align="right"><z:lang id="Supplier.Address"/>：</td>
      <td><input value="${entity.address }" type="text" class="input1" id="address"/></td>
    </tr>
</table>
</form>
</body>
</html>