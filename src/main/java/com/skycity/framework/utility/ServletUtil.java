package com.skycity.framework.utility;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.skycity.framework.collection.Mapx;

public class ServletUtil {
	public static Mapx<String, Object> getParameterMap(HttpServletRequest request) {
        Mapx<String, Object> map = new Mapx<String, Object>();
        Map<String, String[]> tmap = request.getParameterMap();
        tmap.forEach((key,value)->map.put(key, value[0]));
        return map;
    }
}
