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
	$("input[name=sex]:eq(0)").attr("checked",'checked');
});
</script>
</head>
<body class="dialogBody">
<form:form action="" id="form2" method="post" commandName="potentialCustomer">
<form:hidden path="id"/>
<table align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td height="30" align="right"><z:lang id="Customer.name"/>：</td>
      <td><form:input path="name" verify="NotNull"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Common.Sex"/>：</td>
      <td><form:radiobuttons path="sex" items="${sexList}" itemLabel="codeName" itemValue="codeType"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Common.Mobile"/>：</td>
      <td><form:input path="mobile" verify="CnPhone&&NotNull"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Common.Email"/>：</td>
      <td><form:input path="email"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Common.WebChat"/>：</td>
      <td><form:input path="webChat"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Customer.comefrom"/>：</td>
      <td><form:input path="comeFrom"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Customer.visitDate"/>：</td>
      <td><form:input path="visitDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" cssClass="Wdate"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Common.Address"/>：</td>
      <td><form:input path="address"/></td>
    </tr>
    <tr>
      <td height="30" align="right"><z:lang id="Common.Description"/>：</td>
      <td><form:input path="description"/></td>
    </tr>
</table>
</form:form>
</body>
</html>