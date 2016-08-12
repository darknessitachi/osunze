package com.skycity.framework.utility;

import java.lang.reflect.Array;

/**
 * 
 * @author YingBo
 *
 */
public class ObjectUtil {

	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		if ((obj instanceof String)) {
			return "".equals(obj);
		}
		if ((obj instanceof Number)) {
			return ((Number) obj).doubleValue() == 0.0D;
		}
		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		return false;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean in(Object[] args) {
		if ((args == null) || (args.length < 2)) {
			return false;
		}
		Object arg1 = args[0];
		for (int i = 1; i < args.length; i++) {
			if (arg1 == null) {
				if (args[i] == null)
					return true;
			} else {
				if (arg1.equals(args[i])) {
					return true;
				}

				if ((!arg1.getClass().isArray())
						&& (args[i].getClass().isArray())) {
					for (Object obj : (Object[]) args[i]) {
						if (arg1.equals(obj)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
