package com.skycity.framework.utility;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 字符串控制类
 * @author YingBo
 *
 */
public class StringUtil {
	
	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isNull(String str) {
		return (isEmpty(str)) || ("null".equals(str));
	}

	public static boolean isNotNull(String str) {
		return isNotEmpty(str) && (!"null".equals(str));
	}
	
	/**
	 * eg:
	 * str:a,b,c
	 * spliter:,
	 * return:new String[]{a,b,c}
	 * @param str
	 * @param spliter
	 * @return
	 */
	public static String[] splitEx(String str, String spliter) {
		String[] strs =str.split(spliter);
		return strs;
	}
	
	public static String singleQuote(String str,String spliter){
		String[] strs = splitEx(str,spliter);
		return Arrays.asList(strs).stream().collect(Collectors.joining(","));
	}
	
	public static float str2float(Object object){
		if(ObjectUtil.isNull(object)){
			return 0.0f;
		}else{
			return new Float((String)object);
		}
    }
}
