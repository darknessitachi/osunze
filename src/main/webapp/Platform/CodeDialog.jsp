<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script>
</script>
</head>
<body class="dialogBody">
<form:form action="" id="form2" method="post">
<input type="hidden" id="parentCode" value="${code.parentCode}" />
<input type="hidden" id="operator" value=""/>
<table width="100%" cellpadding="2" cellspacing="0">
	<tr>
     <tr>
      <td width="30%" height="10" align="right" ></td>
      <td height="10"></td>
    </tr>
    <tr>
      <td align="right" ><div id="codeTypeTxt"></div></td>
      <td width="70%"><input value="${code.codeType}"  type="text" class="input1" id="codeType" size=20 verify="<z:lang id="Platform.Verify.OnlyNumberOrLetter"></z:lang>|Regex=[a-zA-Z0-9]+&&NotNull"/></td>
    </tr>
    <tr>
      <td align="right" ><div id="codeNameTxt"></div></td>
      <td><input type="text" value="${code.codeName }" class="input1" id="codeName" size=20 verify="NotNull"/></td>
    </tr>
    <tr>
      <td align="right" ><z:lang id="Common.Memo"></z:lang></td>
      <td><input value="${code.codeMemo}" type="text"  class="input1" id="codeMemo" size=20/></td>
    </tr>
 </table>
</form:form>
</body>
</html>