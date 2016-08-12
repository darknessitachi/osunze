<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="Include/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title><z:lang id="Platform.AppName"/></title>
<script language="javascript">
Logout = function() {
	Dialog.confirm("<z:lang id='Application.LogoutConfirm'/>", function() {
		var dc = new DataCollection();
		Server.sendRequest("Login/logout.do", dc, function(response) {
			if(response&&response.Status==0){
				Dialog.alert(response.Message);
			}else{
				window.location.href = "${ctx}/Login.jsp";
			}
		});
	});
};
$(document).ready(function(){
	var timer;
	var $skinSelector=$('#skinSelector');
	$skinSelector.hover(function(){
		clearTimeout(timer);
		$('#skinsMenu').css('left',$skinSelector.offset().left).show();
	},function(){
		timer=setTimeout(function(){$('#skinsMenu').hide();},300);
	})
	$('#skinsMenu').hover(function(){
		clearTimeout(timer);
	},function(){
		timer=setTimeout(function(){$('#skinsMenu').hide();},300);
	});
})
function changePassword(){
	var diag = new Dialog();
	diag.width = 450;
	diag.height = 150;
	diag.title = "<z:lang id='Application.ChangePassword'></z:lang>";
	diag.url = "Platform/ChangePasswordDialog.jsp";
	diag.onLoad = function(){
		$DW.$('#OldPassword').focus();
	};
	diag.onOk = doUpdate;
	diag.show();
}
function doUpdate(){
	if($DW.Verify.hasError()){
		return;
	}
	var dc = $DW.Form.getData("changePassword");
	Server.sendRequest("Login/changePassword.do",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
			}	
		},response.Status);
	});
} 
function changeSkin(skin){
	Cookie.set("zcms_skin", skin, 60*60*24*100);
	Config.skin=skin;
	window.location.reload();
}
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true" scroll="no">

<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
    <img src="images/loading.gif" align="absmiddle" /> <z:lang id="Platform.loadPageMsg"/></div>
</div>

<div id="skinsMenu" class="z-layer z-menu z-hidden" style="z-index: 10200; position: absolute; left: 1035px; top: 25px;">
	<a class="z-btn z-btn-inmenu" onClick="changeSkin('zvingClassic')" href="javascript:void(0)"><z:lang id="Platform.BlueTheme"/></a>
	<a class="z-btn z-btn-inmenu" onClick="changeSkin('zvingGreen')" href="javascript:void(0)"><z:lang id="Platform.GreenTheme"/></a>
	<a class="z-btn z-btn-inmenu" onClick="changeSkin('zvingDeep')" href="javascript:void(0)"><z:lang id="Platform.DarkTheme"/></a>
	<a class="z-btn z-btn-inmenu" onClick="changeSkin('zvingRed')" href="javascript:void(0)"><z:lang id="Platform.RedTheme"/></a>
	<a class="z-btn z-btn-inmenu" onClick="changeSkin('zvingPurple')" href="javascript:void(0)"><z:lang id="Platform.PurpleTheme"/></a>
</div>
<div region="north" split="true" border="false" style="overflow: hidden; height: 60px;
       line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体" class="bluebg">
       <span style="float:right; padding-right:20px;" class="head">
       	<div class="loginInfo">
       		<span class="loginInfo_welcom"><z:lang id="Application.CurrentUser"></z:lang>:<b><shiro:principal/></b> 
          	<a href="javascript:void(0);" onClick="getMessage()" title="<z:lang id='Application.Message' />">
          	<img src="${ctx }/Framework/Images/blank.gif" class="icon icon_msg"><span id="MsgCount">0</span></a>
          		| <a href="javascript:void(0);" id="skinSelector" title="<z:lang id='Platform.Theme'/>"><img src="${ctx }/Icons/changestyle.gif" class="icon icon_msg"></a>
          		| <a href="javascript:void(0);" onclick="changePassword()"><z:lang id="Application.ChangePassword"/></a>
          		| <a href="javascript:void(0);" onclick="Logout()"><z:lang id="Application.Logout"/></a>
           	</span>
         </div>
       </span>
       <span style="padding-left:10px; font-size: 16px; ">
       		<img id="logo" src="${ctx}/Product/Images/logo_en.png"/>
            <div style="display:none; float:left; background:url(${ctx}/Platform/Images/selectsitebg.gif) no-repeat right top; color:#666666; padding:6px 23px 0 10px; margin-bottom:-2px;">
            </div>
       </span>
</div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2;">
        <div class="footer" align="right">QQ:20880488、31680122、34561089</div>
    </div>
    <div region="west" split="true" title="<z:lang id='Platform.NaviMenu'/>" style="width:180px;" id="west">
		<div id="_sideNavigation">
			<!--  导航内容 -->
			<ul id="_Child_Menu_1" class="sideNavigation">
				<%-- <li><a class="" rel="${ctx}/Platform/SystemInfo.jsp"><b><img src="${ctx}/Icons/icon040a6.png" class="icon"><z:lang id="SysInfo.Title"/></b></a></li>
				<li><a class="" rel="${ctx}/Platform/Resources.jsp"><b><img src="${ctx}/Icons/icon040a6.png" class="icon"><z:lang id="Resources.Manager"/></b></a></li> --%>
				<%-- 
				<li><a class="" rel="${ctx}/Platform/Menu.jsp"><b><img src="Icons/icon040a6.png" class="icon"><z:lang id="Menu.Title"/></b></a></li>
				<li><a class="" rel="${ctx}/Platform/Schedule.jsp"><b><img src="Icons/icon040a6.png" class="icon"><z:lang id="Cron.Title"/></b></a></li>
				 --%>
				<%-- <li><a class="" rel="${ctx}/Platform/Code.jsp"><b><img src="${ctx}/Icons/icon040a6.png" class="icon"><z:lang id="Code.Title"/></b></a></li>
				<li><a class="" rel="${ctx}/Platform/Supplier.jsp"><b><img src="${ctx}/Icons/icon040a6.png" class="icon"><z:lang id="Supplier.Title"/></b></a></li>
				<li><a class="" rel="${ctx}/Platform/Goods.jsp"><b><img src="${ctx}/Icons/icon040a6.png" class="icon"><z:lang id="Goods.Title"/></b></a></li> --%>
				<%-- <li><a class="" rel="${ctx}/Platform/Stock.jsp"><b><img src="${ctx}/Icons/icon040a6.png" class="icon"><z:lang id="Stock.Title"/></b></a></li> --%>
				<%-- <li><a class="" rel="${ctx}/Platform/Sale.jsp"><b><img src="${ctx}/Icons/icon040a6.png" class="icon"><z:lang id="Sale.Title"/></b></a></li> --%>
				<c:forEach var="menu" items="${menuList}">
					<li><a class="" rel="${ctx }/${menu.resUrl}"><b><img src="${ctx}/Icons/icon040a6.png" class="icon">${menu.name }</b></a></li>
				</c:forEach>
				
			</ul>
		</div>
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden" class="bluebg">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="<z:lang id='Platform.WelcomeUse'/>" style="padding:20px;overflow:hidden; color:red; " >
				<iframe id='_mainFrame_0' name="_mainFrame_0" frameborder="0" width="100%" height="100%" src='about:blank' scrolling="auto" style="left:-22in;top:-11in;" allowtransparency="true"></iframe>
			</div>
		</div>
    </div>
</body>
</html>	