(function() {
	var z = window.Zving;
	//$T, $N, $V, $S, $NV, $NS, $F简短别名都在这儿配置
	z.$G = z.getDom;
	z.$T = z.getByTag;
	z.$N = z.getByName;
	if (z.Node) {
		z.$V = z.Node.getValue;
		z.$S = z.Node.setValue;
	}
	z.$NV = z.getValues;
	z.$NS = z.setValues;
	z.$F = z.getForm;
	if (!z.Config.namespace) {
		return;
	}
	if (z.Config.namespace === 'window') { //当配置项namespace为window时，把Zving下的所有对象复制到window下
		var p, skipExtra = [];
		for (p in z) {
			if (typeof(z[p]) === 'undefined' || /^(id)$/.test(p)) {
				continue;
			}
			if (typeof(window[p]) !== 'undefined') {
				if (!/^(\$|Object|Class|Array|Date|Function|String|Node|Event|JSON|Storage)$/.test(p)) {
					skipExtra.push('Retouch.js -- ' + p + ' existed in window');
				} else if (/^(Object|Class|Array|Date|Function|String|Node|Event|JSON|Storage)$/.test(p) && typeof(z[p]) === 'object') { //如果Zving下对象和原生对象重名，则复制对象下的属性/方法
					z.extraIf(window[p], z[p]);
				}
			} else {
				try {
					window[p] = z[p];
				} catch (ex) {
					throw ex;
				}
			}
		}
		if (skipExtra.length && z.Console) {
			z.Console.silence = true; //Console的静默模式，暂存log，不直接显示在页面上
			z.Console.error(skipExtra.join('<br>'));
		}
	} else if (z.Config.namespace !== 'Zving') {
		window[z.Config.namespace] = z;
	}

	if (typeof console == "undefined") {
		window.console = {};
		window.console.log = function() {};
	}
	if(!window.console.dir){
		window.console.dir = function(obj) {
			if(!/object/.test(Object.prototype.toString.call(obj))){
				return console.log('console can`t dir the arg');
			}
			for(var key in obj){
				console.log(key,': ',obj[key]);
			}
		};
	}
	if (z.Config.ignoreErrors == 'true') { //忽略脚本错误
		window.onerror = function() {
			return false;
		};
	}

	/*在页面关闭时清除elCache，释放内存*/
	$(window).on('unload', function() {

		if (z.ComponentManager) {
			z.ComponentManager._unload(); //销毁所有js组件实例
		}
		if (z.EventManager) {
			z.EventManager._unload(); //清除Zving.elCache中存储的事件，释放内存
		}
		if (z.AllDocumentsEvent) {
			z.AllDocumentsEvent._unload();
		}
		if (z.Node) {
			z.Node._unload(); //清除Zving.elCache中页面元素附加的方法，释放内存
		}
		for (var eid in z.elCache) {
			if (z.elCache.hasOwnProperty(eid)) {
				delete z.elCache[eid].data;
				delete z.elCache[eid];
			}
		}
		z.elCache = {};
	});

	var skin = z.Cookie.get("zcms_skin");
	if(skin){
		z.Config.skin= skin;
		z.importCSS('../Style/'+skin+'/Default.css');
	}

})();
Zving.Skin={};
Zving.Skin.Supported='zvingClassic,zvingGreen,zvingDeep,zvingPurple,zvingRed';

Zving.Lang = Zving.Lang || {};
Zving.Lang.Supported = "en,zh-cn,zh-tw";
Zving.Lang.DefaultLanguage = "zh-cn";
