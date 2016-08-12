(function() {
	Application = {};
	var StartTime;
	var $navMenus;

	/* 初始化导航菜单 */
	Application.initNavMenu = function(nav, sideNav) {
		$navMenus = $(nav).find('a');
		var $childNavMenus = $(sideNav).find('ul.sideNavigation'); 
		/* 给横向菜单注册onclick方法 */
		$navMenus.each(function(i) {
			this._index=i;
			var myChildMenu = Zving.getDom('_Child' + this.id); 
			if(myChildMenu){
				myChildMenu._index=this._index;
			}
			this.onclick = function() {
				var self = this;
				$childNavMenus.hide();
				var url = self.getAttribute('url');
				var myChildMenu = Zving.getDom('_Child' + self.id);
				$navMenus.removeClass('current');
				$(self).addClass('current');
				Cookie.set('lastNavStatus', self.id);
				var iframes=$('#_framesWrap iframe:not(#_mainFrame_'+self._index+')');
				iframes.each(function(i){
					try {
						this.style.width = Zving.getDom('_framesWrap').clientWidth + 'px';
						this.style.height = Zving.getDom('_framesWrap').clientHeight + 'px';
					} catch (e) {
						//在ie下iframe不可见时，设置宽高会出错？？？（待证实）
					}
					this.style.position = 'absolute';
				});
				var targetIframe=Zving.getDom('_mainFrame_'+self._index);
				if(!targetIframe){
					targetIframe=Dom.createNodeByHtml('<iframe id="_mainFrame_'+self._index+'" name="_mainFrame_'+self._index+'" frameborder="0" width="100%" height="100%" src="about:blank" scrolling="auto" style="position:absolute;left:-22in;top:-11in;" allowtransparency="true"></iframe>');
					targetIframe.appendTo(Zving.getDom('_framesWrap'))
				}
				targetIframe.style.position='static';
				targetIframe.style.width = "100%";
				targetIframe.style.height = "100%";	
				
				if (!url) {
					$('#leftColumnWrap').show();
					self._childMenu = myChildMenu; // 给菜单添加childMenu属性
					myChildMenu.onShow();
				} else {
					$('#leftColumnWrap').hide();
					if (!/^#/.test(url)) {
						if(!targetIframe.src.endsWith(url)){
							targetIframe.src = url;
							var menuid = self.getAttribute('menuid')
							var dc = new DataCollection();
							dc.add("MenuID",menuid);
							//Server.sendRequest("Application.changeMenuClickCount",dc);
						}
						window.location.hash = self.id.substring("_Menu_".length);
					}
					Zving.preventEvent();
				}
			};
		}); /* 给左侧菜单注册onclick方法 */
		$childNavMenus.each(function(i) {
			var childNavMenu=this;
			var $links = $(childNavMenu).find('a');
			childNavMenu.onShow = function() {
				$(childNavMenu).show();
				if (childNavMenu._activeMenu) {
					childNavMenu._activeMenu.onclick();
				} else {
					if ($links[0]) {
						$links[0].onclick();
					} else {
						Zving.getDom('_MainArea').src = 'about:blank';
					}
				}
			}
			$links.each(function(j) {
				this.setAttribute('target','_mainFrame_'+childNavMenu._index);
				this.onclick = function(e) {
					var url = $(this).attr("href");
					var content = $(this).text();
					var target =  $(this).attr("target");
					var targetIframe=Zving.getDom(target);
//					Application.addTab(content,url);
				}
			})
		})
	};
	Application.clickNavMenu = function(navMenuTag, childLinkIndex, flag) { // flag仅在回退/前进时置为true
		if (navMenuTag) {
			var ele = Zving.getDom('_Menu_'+navMenuTag);
			if (ele) {
				ele.onclick();
			} else {
				if ($navMenus[0]) {
					$navMenus[0].onclick();
				}
			}
		}
		if (childLinkIndex) {
			var p = Zving.getDom('_Child_Menu_' + navMenuTag);
			if (!p) {
				return;
			}
			var $links = $(p).find('a');
			if ($links[childLinkIndex - 1]) {
				$links.get(childLinkIndex - 1).onclick();
			}
		}
	}
	window.attachEvent('onunload', function() {
		if (window.location.hash) {
			var initdata = location.hash.match(/(\w+)(-(\d+))?/);
			if (initdata && initdata.length) {
				var navMenuTag = initdata[1],
					childLinkIndex = initdata[3];
				Cookie.set('lastNavStatus', navMenuTag + '-' + childLinkIndex);
			}
		}
	})
	$(document).ready(function(){
		$('#loading-mask').fadeOut();
		var nav = $("#_Navigation");
		var sideNav = $("#_sideNavigation");
		if (nav && sideNav) {
			$navMenus = $(nav).find('a');
			Application.initNavMenu(nav, sideNav); // 导航菜单初始化
			var h = window.location.hash || Cookie.get('lastNavStatus');
			if (h) {
				if (h.startsWith("#")) {
					h = h.substring(1);
				}
				var index = h.lastIndexOf("-");
				if (index > 0) {
					Application.clickNavMenu(h.substring(0, index), h.substring(index + 1));
				} else {
					Application.clickNavMenu(h);
				}
			} else {
				$navMenus[0].onclick();
				if ($navMenus[0]._childMenu) {
					$navMenus[0]._childMenu.getElementsByTagName('A')[0].fireEvent('onclick');
				}
			}
		}
	});
})();