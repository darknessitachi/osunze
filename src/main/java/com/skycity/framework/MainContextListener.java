package com.skycity.framework;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/***
 * 启动时扫描别名文件
 * 扫描系统配置文件
 * ClassName:MainContextListener
 * Date:     2014-11-13 下午10:32:38
 * @author   YingBo.Dai 
 * @version  1.0.0
 * @see
 */ 
public class MainContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	//容器初始化
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		sc.log("-----开始初始化-----");
		Config.configMap.put("System.ContainerInfo", sc.getServerInfo());		//容器信息
		Config.configMap.put("App.ContextPath",sc.getContextPath());			//上下文相对路径
//		try {
			Config.init();			//配置文件初始化
//			AliasLoader.load();		//扫描系统别名
//		} catch (Throwable t) {
//			t.printStackTrace();
//		}
		sc.log("-----初始化完成-----");
	}
}
