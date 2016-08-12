package com.skycity.framework.i18n;

public class LangUtil {
	public static String get(String key){
		return LangMapping.get(key);
	}
	
	public static String get(String key,String language){
		return LangMapping.get(language, key);
	}
}
