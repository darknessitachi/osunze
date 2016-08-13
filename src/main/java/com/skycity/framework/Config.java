package com.skycity.framework;

import com.skycity.framework.collection.Mapx;
import com.skycity.framework.utility.LogUtil;
import com.skycity.framework.utility.URLUtil;
import com.skycity.framework.utility.XMLLoader;

public class Config {
	protected static Mapx<String,String> configMap = new Mapx<String,String>();

	public static Mapx<String, String> getConfigMap() {
		return configMap;
	}
	private static XMLLoader loader = new XMLLoader();
	
	protected static void init(){
		if (!configMap.containsKey("System.JavaVersion")) {
			loader.load();
			LogUtil.info("加载系统信息");;
			configMap.put("System.OSUserLanguage",System.getProperty("user.language")); // 获取系统语言
			configMap.put("System.OSUserName", System.getProperty("user.name")); // 用户账户名称
			configMap.put("System.LineSeparator",System.getProperty("line.separator")); // 换行分隔符
			configMap.put("System.FileSeparator",System.getProperty("file.separator")); // 文件分隔符
			configMap.put("System.FileEncoding",System.getProperty("file.encoding")); // 获得字符编码
			configMap.put("App.ContextRealPath", getContextRealPath()); // 应用实际存放目录
			configMap.put("System.JavaVersion", System.getProperty("java.version")); // JAVA运行时环境版本
			configMap.put("System.JavaVendor", System.getProperty("java.vendor")); // JAVA运行时环境供应商
			configMap.put("System.JavaHome", System.getProperty("java.home")); // JAVA安装目录
			configMap.put("System.OSPatchLevel", System.getProperty("sun.os.patch.level")); // 系统补丁版本
			configMap.put("System.OSArch", System.getProperty("os.arch")); // 操作系统架构
			configMap.put("System.OSVersion", System.getProperty("os.version")); // 操作系统版本
			configMap.put("System.OSName", System.getProperty("os.name")); // 操作系统名称
			if ((System.getProperty("os.name").toLowerCase().indexOf("windows") > 0) // 如果版本为6.1，则系统名称为win7
					&& (System.getProperty("os.name").equals("6.1"))) {
				configMap.put("System.OSName", "Windows 7");
			}
			XMLLoader.NodeData[] datas = loader.getNodeDataList("framework.application.config");
			for(XMLLoader.NodeData data:datas){
				configMap.put("Config."+data.getAttributes().get("name"), data.getBody());
			}
//			
//			File dbFile = new File(URLUtil.getClassesPath()+"jdbc.properties");
//			Mapx<String, String> map = PropertiesUtil.read(dbFile);
//			Set<Entry<String, String>>  c= map.entrySet();
//			for(Iterator<Entry<String, String>> it= c.iterator();it.hasNext();){
//				Entry<String,String> entry=it.next();
//	            configMap.put("DB."+entry.getKey(), entry.getValue());
//	        }
		}
	}
	
	public static void loadConfig(){
		configMap.remove("System.JavaVersion");
		init();
	}
	
	public static void setString(String key,String value){
		configMap.put(key, value);
	}
	
	public static String getString(String key){
		return configMap.get(key);
	}
	
	public static String getContextRealPath() {
		String path = "";
		if (configMap != null) {
			path = configMap.getString("App.ContextRealPath");
		} else {
			path = URLUtil.getWEBINFPath();
			int index = path.indexOf("WEB-INF");
			if (index > 0) {
				path = path.substring(0, index);
			}
		}
		return path;
	}
	
	public static String getSystemOsName(){
		return configMap.get("System.OSName");
	}
	
}
