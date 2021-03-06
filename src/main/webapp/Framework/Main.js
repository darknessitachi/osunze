/*
	Zving JS Library
	Copyright (c) 2011, zving.com Inc. All rights reserved.
	http://www.zving.com
*/

(function() {
	var _Zving = window.Zving;
	//window.undefined = window.undefined;
	var scripts = document.getElementsByTagName('script'),
		script = scripts[scripts.length - 1],
		jspath = script.hasAttribute ? script.src : script.getAttribute('src', 4); //ie下通过getAttribute('src', 4)才能获取全路径
	var uri2varName = '_' + document.URL.split("#")[0].split("=")[0].replace(location.protocol + '//' + location.host, '').replace(/[^A-Za-z0-9\/]/g, '').replace(/\//g, '_');
	//将URI处理为符合变量命名规则的字符串，可作前缀用于创建各页面不同复的命名空间或对象
/*
	<script src="main.js" config="context:frontend">
	配置项说明:
	context:frontend|backend 默认为后台
	namespace:符合变量命名规则的字符串；当值为‘window’时为特殊情况，即复制Zving下所有对象到window下，用于省略根命名空间引用对象。
	debug:yes|no是否开启调试，默认为no
*/
	var z = {
		version: '0.2',
		JSLIBPATH: jspath.substr(0, jspath.lastIndexOf('/') + 1),
		Config: {
			namespace: 'window',
			context: 'backend',
			debug: 'no',
			skin: 'default'
		}
	};

	if (_Zving && _Zving.version === z.version && _Zving.JSLIBPATH === z.JSLIBPATH) {
		return; //防止重复加载
	} else {
		window.Zving = z;
	}
    z.startTime= +new Date();
	z.CONTEXTPATH = z.JSLIBPATH.replace(/\/[^\/]+\/?$/, '/');
	z.pageId = uri2varName;

	/**浏览器判断**/
	var ua = navigator.userAgent.toLowerCase();
	z.isQuirks = document.compatMode === 'BackCompat';
	z.isStrict = document.compatMode === 'CSS1Compat';
	z.isWindows = /windows|win32/.test(ua);
	z.isMac = /macintosh|mac os/.test(ua);
	z.isLinux = /linux/.test(ua);
	z.isWebKit = /webkit/.test(ua);
	z.isChrome = /chrome/.test(ua);
	z.isSafari = /safari/.test(ua) && !z.isChrome;
	z.isGecko = /gecko/.test(ua); //包括firefox
	z.isFirefox = /firefox/.test(ua);
	z.isOpera = /opera/.test(ua) && !! window.opera;
	z.isIE = /msie/.test(ua) && !z.isOpera;
	z.ieVersion = z.isIE ? parseFloat(/msie ([\w.]+)/.exec(ua)[1]) : null;
	z.isIE9 = /msie (7|8|9)/.test(ua) && !! window.performance;
	//z.isMaxthon = /maxthon/i.test(navigator.userAgent)
	//z.is360se = /360se/i.test(navigator.userAgent)
	if (z.isIE9) {
		z.ieVersion = 9;
	}
	z.isIE8 = /msie (7|8)/.test(ua) && !! window.XDomainRequest; //在ie兼容模式下ua会返回msie 7
	if (z.isIE8) {
		z.ieVersion = 8;
	}
	z.isIE7 = /msie 7/.test(ua) && !window.XDomainRequest;
	z.isIE6 = z.isIE && !window.XMLHttpRequest;
	z.isMobile = ('createTouch' in document) && !('onmousemove' in document.documentElement) || /(iphone|ipad|ipod|android)/.test(ua);
	z.isBorderBox = z.isIE && z.isQuirks;
	if (z.isIE) {
		try {
			//document.documentElement.addBehavior("#default#userdata");
			document.execCommand("BackgroundImageCache", false, true);
		} catch (e) {}
	}
	document.head = document.getElementsByTagName('head')[0] || document.documentElement;

    z.restricted = false;//对父窗口的访问是否受限

	z.getRootWin = z.getTopLevelWindow = function() {
		var pw = window;
		while (pw != pw.parent) {
			try {
				var pd = pw.parent.document; //在没权限访问parent中的对象时会出错
				var all = pd.getElementsByTagName('*');
			} catch (ex) {
                z.restricted=true;
				return pw;
			}
			if (typeof pw.parent.Zving != 'object') {
				return pw;
			}
			pw = pw.parent;
		}
		return pw;
	};
	z.rootWin = z.getRootWin();
	z.rootDoc = z.rootWin.document;


	z.extra = function(obj, srcObj, defaults) { //复制对象srcObj的成员到对象obj
		if (!obj) {
			obj = {};
		}
		if (defaults) {
			z.extra(obj, defaults);
		}
		if (obj && srcObj && typeof srcObj === 'object') {
			var p;
			for (p in srcObj) {
				if (srcObj.hasOwnProperty(p)) {
					obj[p] = srcObj[p];
				}
			}
		}
		return obj;
	};

	z.extraIf = function(obj, srcObj) {
		if (!obj) {
			obj = {};
		}
		var p;
		for (p in srcObj) {
			if (typeof(obj[p]) === 'undefined') {
				obj[p] = srcObj[p];
			}
		}
		return obj;
	};

	/**	从script标签的config(自定义)属性内读取框架全局配置 **/
	var config = {},
		confStr = script.getAttribute('config'),
		i = 0;
	if (confStr) {
		var split1 = confStr.split(','),
			split2;
		for (i = 0; i < split1.length; i++) {
			if (split1[i] && /^\w+\:\w+$/.test(split1[i])) {
				split2 = split1[i].split(':');
				config[split2[0]] = split2[1];
			}
		}
	}
	z.extra(z.Config, config);

	/**
	 异步加载脚本
	 url:js文件路径，相对于引用js框架的页面，如果要从js框架根目录开始引用需自行加上z.JSLIBPATH
	 onsuccess:js文件加载后的回调函数
	 **/
	z.loadJS = z.loadJs = function(url, onsuccess) {
		var head = document.getElementsByTagName('head')[0] || document.documentElement,
			script = document.createElement('script'),
			done = false;
		script.src = url;

		script.onerror = script.onload = script.onreadystatechange = function() {
			if (!done && (!this.readyState || this.readyState === "loaded" || this.readyState === "complete")) {
				done = true;
				if (onsuccess) {
					onsuccess();
				}
				script.onerror = script.onload = script.onreadystatechange = null;
				head.removeChild(script);
			}
		};
		head.appendChild(script);
	};
	/**
	 加载脚本
	 url:js文件路径，因有加z.PATH，所以路径是相对于js框架根目录开始
	 **/
	z.importJS = z.importJs = function(url) {
		if (!/^\/|^\w+\:\/\//.test(url)) {
			url = z.JSLIBPATH + url;
		}
		if (!document.body || document.readyState == 'loading') {
			document.write('<script type="text/javascript" src="' + url + '"><\/script>');
		} else {
			z.loadJS(url);
		}

	};
	/**
	 * 加载jsonp脚本
	 * @method loadJsonp
	 * @static
	 * @param { String } url Javascript文件路径
	 * @param { Function } onsuccess (Optional) jsonp的回调函数
	 * @param { Option } options (Optional) 配置选项，目前除支持loadJs对应的参数外，还支持：
	 {RegExp} callbackReplacer (Optional) 回调函数的匹配正则。默认是：/%callbackfun%/ig；如果url里没找到匹配，则会添加“callback=%callbackfun%”在url后面
	 */
	z.loadJsonp = (function() {
		var seq = +(new Date());
		return function(url, onsuccess, options) {
			var funName = "z_jsonp" + seq++,
				callbackReplacer = options.callbackReplacer || /%callbackfun%/ig;
			window[funName] = function(data) {
				if (onsuccess) {
					onsuccess(data);
				}
				window[funName] = null;
			};
			if (callbackReplacer.test(url)) {
				url = url.replace(callbackReplacer, funName);
			} else {
				url += (/\?/.test(url) ? "&" : "?") + "callback=" + funName;
			}
			z.loadJs(url, null);
		};
	}());
	/**
	 异步加载CSS文件
	 url:css文件路径，相对于引用js框架的页面，如果要从js框架根目录开始引用需自行加上z.JSLIBPATH
	 **/
	z.loadCSS = z.loadCss = function(url) {
		var head = document.getElementsByTagName('head')[0] || document.documentElement;
		if (z.isIE) {
			document.createStyleSheet(url);
		} else {
			var e = document.createElement('link');
			e.rel = 'stylesheet';
			e.type = 'text/css';
			e.href = url;
			head.appendChild(e);
		}
	};
	/**
	 加载CSS文件
	 url:css文件路径，因有加z.PATH，所以路径是相对于js框架根目录开始
	 **/
	z.importCSS = z.importCss = function(url) {
		if (!/^\/|^\w+\:\/\//.test(url)) {
			url = z.JSLIBPATH + url;
		}
		if (!document.body || document.readyState == 'loading') {
			document.write('<link rel="stylesheet" type="text/css" href="' + url + '" />');
		} else {
			z.loadCSS(url);
		}

	};
    /**
     * 添加向页面内添加一个style标签，并添加规则
     * @param styleElId
     * @param cssStr
     */
    z.addStyle = function(styleElId, cssStr){
        if(!cssStr){
            cssStr=styleElId;
            styleElId= 'style'+(1 + z.startTime);
        }
        /* 如果是一个不含{}的字符串，则认为是要载入外部的css */
        if(cssStr.indexOf('{') <1 && cssStr.indexOf('}') <1 && /^\S$/.test(cssStr)){
            return z.loadCSS(cssStr);
        }
        var styleEl=document.getElementById(styleElId);
        if(!styleEl){
            styleEl = document.createElement('style');
            styleEl.type = 'text/css';
            styleEl.id=styleElId;
            document.getElementsByTagName('head')[0].appendChild(styleEl);
        }
        if(styleEl.styleSheet){
            styleEl.styleSheet.cssText += cssStr;
        }else{
            cssStr=document.createTextNode(cssStr);
            styleEl.appendChild(cssStr);
        }
        return styleEl;
    };
    
	if (!/^(http:\/\/develophost)/.test(location.href)){
		z.importJs('jquery.min.js');
		z.importJs('Core.min.js');
		z.importJs('components_frontend.min.js');
		if (z.Config.context === 'backend') {
			z.importJs('components_backend.min.js');
		}
	}
	z.importJs('Retouch.js');
	if (z.Config.context === 'backend') {
		z.importJs('../Application.js');
	}
})();