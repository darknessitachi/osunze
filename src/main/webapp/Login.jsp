<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="skycity" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="/osunze"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="Platform.AppName"></z:lang></title>
<link rel="shortcut icon" href="Include/favicon.ico" type="image/x-icon" />
<link href="${ctx }/Style/Default.css" rel="stylesheet" type="text/css">
<style>
.loginBar {
	width: 345px;
	padding-top: 60px;
	padding-left: 50px;
	font-size: 14px;
}

.fieldWrap {
	text-align: left;
	width: 300px;
	height: 30px;
	padding-top: 10px;
	overflow: hidden;
}

.lable {
	padding-top: 3px;
	width: 90px;
	float: left;
	color: #333333;
	font-size: 14px;
	text-align: right;
}

.inputWrap {
	background: url(${ctx }/Platform/Images/loginInputWrapbg.gif) no-repeat left top;
	height: 28px;
	line-height: 16px;
	border: none;
	width: 200px;
	float: left;
}

.selectWrap {
	background: url(${ctx }/Platform/Images/loginSelectWrapbg.gif) no-repeat left
		top;
}

.welcome {
	color: #2477b3;
	font-family: "微软雅黑", Tahoma, sans-serif;
	font-size: 14px;
	font-weight: bold;
	padding: 6px 0;
	padding-top: 10px;
	padding-left: 20px;
	letter-spacing: 2px;
	text-align: center;
}

.copyright {
	padding-top: 10px;
	text-align: center;
}

img {
	vertical-align: middle;
}

input.inputText {
	background: transparent none;
	border: none;
	margin-top: 3px;
	_margin-top: 1px;
}

.inputWrap .inputText {
	width: 192px;
	height: 20px;
	font-size: 14px;
	padding-left: 6px;
	margin-top: 0px;
	padding-top: 4px;
}

input.inputTextHover {
	background: transparent none;
	border: none;
}

input.inputTextFocus {
	background: transparent none;
	border: none;
}

input[type="text"]:hover,input[type="password"]:hover {
	background: transparent none;
	border: none;
}

input[type="text"]:focus,input[type="password"]:focus {
	background: transparent none;
	border: none;
}

#loginBtnWrap {
	padding: 12px 0px 10px 130px;
	width: 100px;
}

#Language .inputText,#skin .inputText {
	background: transparent none;
	color: #369;
	border: 0;
}

#Language .arrowimg,#skin .arrowimg {
	background: url(${ctx}/Framework/Images/arrow_inCell.gif) no-repeat 0 0;
}

#loginBtn {
	background: url(${ctx}/Platform/Images/loginbtn.gif) no-repeat 0 0;
	width: 100px;
	height: 34px;
	color: #ffffff;
	font-size: 14px;
	line-height: 32px;
	text-align: center;
	word-spacing: 1px;
	letter-spacing: 6px;
}

.loginLogo {
	width: 266px;
	height: 45px;
	margin: 0 auto;
	background: url(${ctx}/Product/Images/logo_login.png);
	_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='Product/Images/logo_login.png',
		sizingMethod=  'scale' );
	_background: none;
}
</style>
<script src="${ctx}/Framework/Main.js"></script>
<script>
	function login() {
		if ($V('#UserName').trim() == "" || $V('#Password').trim() == "") {
			Dialog.alert("<z:lang id='Login.InputUserNameOrPassword'>请输入用户名和密码</z:lang>");
			return;
		}
		var dc = Form.getData("form1");
		Server.sendRequest("Login/login.do", dc, function(response) {
			if(response&&response.Status==0){
				Dialog.alert(response.Message);
			}else{
				window.location.href = "Login/admin/index.do";
			}
		});
	}
	function onLanguageChange() {
		var dc = new DataCollection();
		dc.add("Language", $V('Language'));
		/* Server.sendRequest("Login/changeLanguage.action", dc, function(response) {
			Cookie.set("Language", $V('Language'), 60 * 60 * 24 * 100);
			window.location.reload();
		}); */

	}

	function onSkinChange() {
		Cookie.set("zcms_skin", $V('skin'), 60 * 60 * 24 * 100);
		Config.skin = $V('skin');
		window.location.reload();
	}

	Page.onLoad(function() {
		$G('Password').onkeydown = function(event) {
			event = getEvent(event);
			if (event.keyCode == 13) {
				login();
			}
		}
		if (window.top.location != window.self.location) {
			window.top.location = window.self.location;
		} else {
			$('#UserName').focus();
			$S('#UserName', "admin");
			$S('#Password', "admin");
		}
		if (!Cookie.get("zcms_skin")) {
			Cookie.set("zcms_skin", $V('skin'), 60 * 60 * 24 * 100);
		} else {
			Selector.setValue('skin', Cookie.get("zcms_skin"), true);
			$S('skin', Cookie.get("zcms_skin"));
			/* $S('Language',Cookie.get("Language")); */
		}
	});
</script>
</head>
<body class="loginPageBody">
	<div class="LoginWrapbg">
		<div style="width: 100%; height: 50%; left: 0; top: 0; z-index: -1;"></div>
		<div style="width: 442px; height: 440px; margin: 0 auto; margin-top: -260px;">
			<div class="loginLogo"></div>
			<div class="welcome">
				<z:lang id="Platform.WelcomeLogin"></z:lang><z:lang id="Platform.AppName"></z:lang>
			</div>
			<div class="loginBar" id="loginBar">
				<form action="${ctx }/Login/login.do" id="form1" method="post">
					<div class="">
						<div class="fieldWrap">
							<div class="lable">
								<z:lang id="Common.UserName"/>：
							</div>
							<div class="inputWrap">
								<input name="UserName" type="text" class="inputText" id="UserName" onFocus="this.select();">
							</div>
						</div>
						<div class="fieldWrap">
							<div class="lable">
								<z:lang id="Common.Password"/>：
							</div>
							<div class="inputWrap">
								<input name="Password" type="password" class="inputText" id="Password" onfocus="this.select();">
							</div>
						</div>
						<div class="fieldWrap">
							<div class="lable">
								<z:lang id="Platform.Language"/>：
							</div>
							<div class="inputWrap selectWrap">
								<select name="Language" id="Language" style="height: 20px; width: 192px;" onchange="onLanguageChange()">
									<option value="zh-cn">中文简体</option>
									<option value="zh-tw">中文繁体</option>
									<option value="en">英语</option>
								</select>
							</div>
						</div>
						<div class="fieldWrap">
							<div class="lable">
								<z:lang id="Platform.Theme"/>：
							</div>
							<div class="inputWrap selectWrap">
								<select id="skin" name="skin" style="height: 20px; width: 192px;" onchange="onSkinChange()">
									<option value="zvingClassic"><z:lang id="Platform.BlueTheme"/></option>
									<option value="zvingGreen"><z:lang id="Platform.GreenTheme"/></option>
									<option value="zvingDeep"><z:lang id="Platform.DarkTheme"/></option>
									<option value="zvingRed"><z:lang id="Platform.RedTheme"/></option>
									<option value="zvingPurple"><z:lang id="Platform.PurpleTheme"/></option>
								</select>
							</div>
						</div>
						<div class="fieldWrap" id="loginBtnWrap">
							<a href="#;" class="inline-block" id="loginBtn" onClick="login(); return false;">
								<z:lang id="Platform.LoginButton"/>
							</a>
							<%-- <Input type="submit" value="<z:lang id='Platform.LoginButton'/>"> --%>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="copyright">
			<div class="center">
				<z:lang id="Login.Copyright"/>
			</div>
		</div>
	</div>
</body>
</html>