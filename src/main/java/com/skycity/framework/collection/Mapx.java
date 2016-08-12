package com.skycity.framework.collection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skycity.framework.utility.DateUtil;

public class Mapx<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = 201503241402L;

	public String getString(K key) {
		Object o = get(key);
		if (o == null) {
			return null;
		}
		return o.toString();
	}

	public int getInt(K key) {
		Object o = get(key);
		if ((o instanceof Number)) {
			return ((Number) o).intValue();
		}
		if (o != null) {
			try {
				return Integer.parseInt(o.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public long getLong(K key) {
		Object o = get(key);
		if ((o instanceof Number)) {
			return ((Number) o).longValue();
		}
		if (o != null) {
			try {
				return Long.parseLong(o.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0L;
	}

	public float getFloat(K key) {
		Object o = get(key);
		if ((o instanceof Number)) {
			return ((Number) o).floatValue();
		}
		if (o != null) {
			try {
				return Float.parseFloat(o.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0f;
	}

	public Date getDate(K key) {
		Object o = get(key);
		if ((o instanceof Date)) {
			return (Date) o;
		}
		if (o != null) {
			try {
				return DateUtil.parse(o.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void clear() {
		super.clear();
	}

	public static <K, V> Mapx<K, V> convertToMapx(
			Map<? extends K, ? extends V> map) {
		Mapx<K, V> mapx = new Mapx<K, V>();
		mapx.putAll(map);
		return mapx;
	}

	/**
	 * 将形如{"userName":"lyyb2001","password":"xiaobaihe","sex":"1"}这样的字段转换为MAPX结构
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Mapx<String, Object> toMap(String json) {
		ObjectMapper oa = new ObjectMapper();
		try {
			return oa.readValue(json,Mapx.class);
		} catch (IOException e) {
			throw new RuntimeException("不是JSON对象:" + json);
		}
	}
	
	public static List<Mapx<String,String>.KeyAndValue> turnToList(Mapx<String,Object> mapx){
		List<Mapx<String,String>.KeyAndValue> list = new ArrayList<Mapx<String,String>.KeyAndValue>();
		for (String k : mapx.keySet()) {
			Mapx<String,String>.KeyAndValue entity = new Mapx<String,String>().new KeyAndValue();
			entity.setKey(k);
			entity.setValue((String)mapx.get(k));
			list.add(entity);
		}
		return list;
	}

	public class KeyAndValue {
		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
