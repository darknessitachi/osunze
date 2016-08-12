package com.skycity.framework.utility;

import java.util.List;

/**
 * @author Administrator
 * 权限计算帮助类
 */
public class RightsHelper {
	/*
	 *判断小的集合里面是否包含targetRights
	 */
	public static boolean testRights(List<Integer> auths,int targetRights){
		if(ObjectUtil.isNull(auths))
			return false;
		return auths.contains(targetRights);
	}
	
}
