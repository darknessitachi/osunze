<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="Include/taglibs.jsp"%>
<html style="overflow: hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>${AppName}</title>
<script>
var lastWinWidth=0;
EventManager.onWindowResize(function(){
	winWidth=$(window).width();
	if(lastWinWidth<1000 && (winWidth>1000 || winWidth-lastWinWidth>50)){
		location.reload();
	}
});

var TimeoutID = 0;

//获取短消息
function getNewMessageCount(){
	var dc = new DataCollection();
	dc.add("SessionID","${SessionID}");
	Server.sendRequest("Message.getNewMessage",dc,function(response){
		var count = response.Count;
		$('#MsgCount').html(count);
		if(response.PopFlag=="Y"){
			MsgPop(response.Message,null,450,null,30);
		}
		if(response.LogoutFlag!="Y"){
			TimeoutID = setTimeout(getNewMessageCount, 1000*30);
		}
	},null,true,'json');//true表示不需要session，否则页面打开后即会永不失效
}

function stopGetMessage(){
	clearTimeout(TimeoutID);
}

//定位到短消息菜单
function getMessage(){
	var diag = new Dialog("<z:lang id='Platform.ShortMessageList'>短消息列表</z:lang>","platform/message/message.zhtml",900,500);
	diag.show();
}

Tab=Zving.TabPage;

function onLanguageChange(strLang){
	var dc = new DataCollection();
	dc.add("Language",strLang?strLang:$('#Language').val());
	Server.sendRequest("Application.changeLanguage",dc,function(response){
		window.location.reload();
	});
}

Application.logout = function() {
	Dialog.confirm("<z:lang id='Application.LogoutConfirm' />", function() {
		Server.sendRequest("Application.logout");
	});
};
function changePassword() {
	var d = new Dialog("chnagePwdDiag");
	d.width = 460;
	d.height = 160;
	d.title = "<z:lang id='Application.ChangePassword' />";
	d.url = CONTEXTPATH + "platform/changePasswordDialog.zhtml";
	d.onOk = function() {
		if ($DW.Verify.hasError()) {
			return;
		}
		var dc = $DW.Form.getData("FChangePassword");
		Server.sendRequest("Application.changePassword", dc, function(response) {
			if (response.Status == 1) {
				MsgPop(response.Message);
				$D.close();
			} else {
				Dialog.warn(response.Message);
			}
		});
	};
	d.onLoad = function() {
		$DW.$('#OldPassword').focus();
	};
	d.show();
};
function pngfix(img){
	if(Zving.isIE6&&/\.png$/i.test(img.src)){
		var imgsrc=img.src;
		img.width=img.offsetWidth;
		img.height=img.offsetHeight;
		img.src='framework/images/blank.gif';
		img.style.filter='progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod="scale", src="'+imgsrc+'")';
	}
}

function onSiteChange(){
	var dc = new DataCollection();
	dc.add("SiteID",$V('Site'));
	Server.sendRequest("Site.changeSite",dc,function(){
		window.location.reload();
	});
}

function preview(link){
	var url="ContentCore/site/preview?ID=${SiteID}";
	link.target='_blank'
	link.href=url;
	return true;
}
function gotoEditWorkSpace(){
	$G('_Menu_ContentCore.EditWorkSpace').onclick();
}

function selectUser() {
	var diag = new Dialog("SelectUser");
	diag.width = 600;
	diag.height = 320;
	diag.title = "<z:lang id='Branch.SelectUser'>选择用户</z:lang>";
	diag.url = "platform/entrustUserSelectDialog.zhtml";
	diag.onOk = function(){
		var arr = $DW.DataGrid.getSelectedValue("dg2");
		if(!arr||arr.length==0){
			Dialog.alert("<z:lang id="Priv.SelectUserFirst">请先选择用户!</z:lang>");
			return;
		}
		var userName = arr[0];
		var dc = new DataCollection();
		dc.add("UserName",userName);
		Server.sendRequest("Application.addEntrust",dc,function(response){
			if(response.Status == 1){
				MsgPop(response.Message);
				$D.close();
				window.location.reload();
			}else{
				Dialog.alert(response.Message);
			}
		});
	};
	diag.show();
}

function cancelEntrust(){
	Dialog.confirm("<z:lang id="Platform.ConfirmCancelEntrust">确认取消委托？</z:lang>", function() {
		Server.sendRequest("Application.cancelEntrust",null,function(response){
			if(response.Status == 1){
				MsgPop(response.Message);
				window.location.reload();
			}else{
				Dialog.alert(response.Message);
			}
		});
	});
}

function changeLoginUser(){
	var diag = new Dialog("ChangeLoginUser");
	diag.width = 450;
	diag.height = 200;
	diag.title = "<z:lang id='Branch.SelectUser'>选择用户</z:lang>";
	diag.url = "platform/changeLoginUserDialog.zhtml";
	diag.onOk = function(){
		var arr = $DW.DataGrid.getSelectedValue("dg3");
		if(!arr||arr.length==0){
			Dialog.alert("<z:lang id="Priv.SelectUserFirst">请先选择用户!</z:lang>");
			return;
		}
		window.location = Server.ContextPath+"changeaccount?UserName="+arr[0];
	};
	diag.show();
}
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
</script>
<style>
body {
	background-color:#DBE8F4;
	min-width:1000px;
	min-height:400px;
}
.no_text_btn .z-btn b { padding-right:0;}
#oneKeyReprintTool_div { position:absolute; left:100px; top:100px;}
</style>
<!--[if lte IE 6]>
<style>
body{
	width:expression(document.documentElement.clientWidth < 1000? "1000px" : "auto");
	height:expression(document.documentElement.clientHeight < 400? "400px" : "auto");
}
</style>
<![endif]-->
<!--[if !IE]><!-->
<style>
#MsgCount{ font-size:9px; font-family:Tahoma, Geneva, sans-serif; display:inline-block; text-align:center; line-height:11px; padding:0 4px 0 3px; border-radius:10px; height:11px; min-width:6px; border:2px solid rgba(255,255,255,0.9); color:#fff; background-color:#F30; box-shadow:1px 1px 2px rgba(0,0,0,0.5); position:relative; left:-5px; top:-5px;}
</style>
<!--<![endif]-->
</head>
<body class="z-body-index language-zh_cn" id="application">
<table id="js_layoutTable" width="100%" height="*" border="0" cellspacing="0" cellpadding="0" class="js_layoutTable">
  <tr>
    <td height="60"><table id="_TableHeader" width="100%" border="0" cellpadding="0"
		cellspacing="0" class="bluebg">
        <tr>
          <td width="217" height="60" valign="middle"><img id="logo" src="${ctx}/Product/Images/logo_en.png" onload="pngfix(this);" />
            <div style="display:none; float:left; background:url(platform/images/selectsitebg.gif) no-repeat right top; color:#666666; padding:6px 23px 0 10px; margin-bottom:-2px;">
            	</div></td>
          <td><div class="loginInfo"><span class="loginInfo_welcom"><z:lang id="Application.CurrentUser">当前用户</z:lang>:<b><shiro:principal/></b> 
          	<a href="javascript:void(0);" onClick="getMessage()" title="<z:lang id='Application.Message' />"><img src="${ctx}/Framework/Images/blank.gif" class="icon icon_msg"><span id="MsgCount">10</span></a>
          		<a href="javascript:void(0);" onClick="selectUser();return false;" title="个人设置"><img src="${ctx }/Icons/icon023a1.gif"></a>
          		 <a href="javascript:void(0);" onclick="Logout()"><z:lang id="Application.Logout">退出登录</z:lang></a>
			 				| <span>
				 					<%-- <z:select id="Site" listURL="contentcore/siteSelectDialog.zhtml" autowidth="true" 
				 										listWidth="250" listHeight="300" style="height:16px;" value="${SiteID}"
				 										valueText="${SiteName}" onchange="onSiteChange()" /> --%>
			 					</span>
           	</span></div>
              	
                	<div id="_Navigation" class="navigation">
                		<!-- <a id='_Menu_Workspace.MainMenu' menuid="Workspace.MainMenu" onclick='return false;' url="" hidefocus='true'><b>我的工作台</b></a>
                		<a id='_Menu_ContentCore.EditWorkSpace' menuid="ContentCore.EditWorkSpace" onclick='return false;' url="" hidefocus='true'><b>文档列表</b></a> -->
                		<a id='_Menu_Platform.System' menuid="Platform.System" onclick='return false;' url="" hidefocus='true'><b>系统管理</b></a>
                	</div>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="*" class="mainArea">
    	<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable" style="table-layout:auto\9;*table-layout:fixed;"><!-- ie8下tableLayout为fixed的表格，就算是隐藏单元格也占有宽度，所以这个表格在ie8下tableLayout设为auto，使可以正常隐藏侧栏 -->
        <tr valign="top">
          <td width="160" height="100%" class="leftColumnWrap" id="leftColumnWrap"><table width="100%" id="leftColumn" border="0" cellspacing="0" cellpadding="0" height="100%" class="">
               <tr>
                <td height="33"><div class="z-toolbar no_text_btn">
              <div class="z-toolbar-ct">
                <div class="z-toolbar-overflow">
                  <div class="z-toolbar-nowrap">
                  <%-- <z:button type="link" target="_blank" href="site/preview?ID=${SiteID}" theme="flat"><img src="icons/icon040a15.png" title="<z:lang id='Contentcore.SitePreview'>站点预览</z:lang>" /></z:button>
                   --%><img src="${ctx}/Icons/icon040a6.png" title="文档工作台" />
                </div>
                </div>
              </div>
            </div></td>
              </tr>
              <tr valign="top">
                <td height="*">
                	<div id="_sideNavigation">
               			<ul id="_Child_Menu_Workspace.MainMenu" class="sideNavigation" style="display:none">
               				<c:forEach var="menu" items="${menuList}">
								<li><a href="${ctx }/${menu.resUrl}"><b><%-- <img src="${ctx}/Icons/icon040a6.png" class="icon"> --%>${menu.name }</b></a></li>
							</c:forEach>
						</ul>
						<ul id="_Child_Menu_ContentCore.EditWorkSpace" class="sideNavigation" style="display:none">
							<li><a href="adf"><b><img src="${ctx}/Icons/icon040a6.png" class="icon">$asdf</b></a></li>
						</ul>
						<ul id="_Child_Menu_Platform.System" class="sideNavigation" style="display:none">
                  			<c:forEach var="menu" items="${menuList}">
								<li><a href="${ctx }/${menu.resUrl}"><b><%-- <img src="${ctx}/Icons/icon040a6.png" class="icon"> --%>${menu.name }</b></a></li>
							</c:forEach>
                  		</ul>
                	<%-- <z:list method="Application.bindMainMenus">
                		<ul id="_Child_Menu_${ID}" class="sideNavigation" style="display:none">
                			<z:list method="Application.bindChildMenus">
                  			<li><a class="<z:if condition='${List.HasChild}' out='hasChild hasChild_expand'/>" href="${URL}" menuid="${ID}"><b>${Icon}${Name}</b></a>
                  				<z:if condition="${List.HasChild}">
              						<ul>
	                  					<z:list method="Application.bindChildMenus">
		                    				<li><a href="${URL}" menuid="${ID}"><b>${Icon}${Name}</b></a></li>
	                  					</z:list>
	                    			</ul>
                  				</z:if>
                  			</li>
                  		</z:list>
                  	</ul>
                  </z:list> --%>
                  </div>
                </td>
              </tr>
          </table></td>
          <td id="rightColumnWrap"><div id="_framesWrap" style="position:relative; height:100%">
          	<a id="toggleLeftColumn"  href="#;" class="toggleBtn-left"></a>
          	<iframe id='_mainFrame_0' name="_mainFrame_0" frameborder="0" width="100%" height="100%" src='about:blank' scrolling="auto" style="left:-22in;top:-11in;" allowtransparency="true"></iframe>
          	</div></td>
        </tr>
      </table></td>
  </tr>
</table>

<script>

$('#toggleLeftColumn').on('click',function(){
	if($(this).hasClass('toggleBtn-left')){
		$('#leftColumnWrap').hide();
		$G('splitter1')&& $('#splitter1').hide();
		$(this).removeClass('toggleBtn-left');
		$(this).addClass('toggleBtn-right');
	}else{
		$('#leftColumnWrap').show();
		$G('splitter1')&& $('#splitter1').show();
		$(this).removeClass('toggleBtn-right');
		$(this).addClass('toggleBtn-left');
	}
})
</script>
</body>
</html>