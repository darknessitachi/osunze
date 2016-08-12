<!-- 用户自己修改密码使用的对话框 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/Include/taglibs.jsp"%>
<%@ taglib uri="skycity" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<link href="../Style/Default.css" rel="stylesheet" type="text/css"/>
<script src="../Framework/Main.js"></script>
</head>
<body>
  <form id="changePassword">
  <table width="100%" height="100%" border="0">
  <tr>
    <td valign="middle" style="text-align:center"><table width="100%" >
      <tr>
      	
        <td width="35%" height="35" align="right">
          <z:lang id="Common.OldPassword">旧密码</z:lang>:</td>
        <td align="left">
	        <input type="hidden" name="userName" id="userName" value="<z:currentUserName/>" />
	        <input name="OldPassword"  type="password" value="" class="input1" id="OldPassword"  verify="NotNull" />
        </td>
      </tr>
      <tr >
        <td height="35" align="right">
         <z:lang id="Common.NewPassword">新密码</z:lang>:</td>
        <td align="left"><input name="Password" type="password" class="input1" id="Password" verify="NotNull&&(<z:lang id="Member.PasswordRule">必须是5-18位的字符。</z:lang>)|Regex=^[\s\S]{5,18}$" /></td>
      </tr>
      <tr >
        <td height="35" align="right">
         <z:lang id="Common.ConfirmNewPassword">重复新密码</z:lang>:</td>
        <td align="left"><input name="ConfirmPassword" type="password" class="input1" id="ConfirmPassword" verify="<z:lang id="User.ConfirmPassword">两次输入密码不相同，请重新输入</z:lang>|Script=$('#Password').val()==$('#ConfirmPassword').val()" /></td>
      </tr>
    </table></td>
  </tr>
</table>
  </form>
</body>
</html>