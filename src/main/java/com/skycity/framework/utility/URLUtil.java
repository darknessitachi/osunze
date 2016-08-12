package com.skycity.framework.utility;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class URLUtil{
	
	/**
	 * 返回classes的绝对路径
	 * @return String
	 */
	public static String getClassesPath(){
		return getWEBINFPath() + "/classes/";
	}
	
	/**
	 * 返回当前应用到WEB-INF的绝对路径
	 * @return String
	 */
	public static String getWEBINFPath(){
		try {
			URL url = URLUtil.class.getClassLoader().getResource("com/skycity/framework/utility/URLUtil.class");
			if(url == null){
				LogUtil.error("URLUtil.getWEBINFPath() failed!");
				return "";
			}
			String path = URLDecoder.decode(url.getPath(), System.getProperty("file.encoding"));
            if (path.indexOf("WEB-INF") >= 0) {
                path = path.substring(0, path.lastIndexOf("WEB-INF") + 7);
            }
            return path;
		} catch (UnsupportedEncodingException e) {
			LogUtil.error("URLUtil.getWEBINFPath() failed!");
		}
		return "";
	}
}
