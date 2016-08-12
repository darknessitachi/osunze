package com.skycity.framework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.skycity.framework.collection.Mapx;

/**
 * 属性文件读取类
 * 
 * @author YingBo
 *
 */
public class PropertiesUtil {

	public static Mapx<String, String> read(String fileName) {
		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
			return read(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Mapx<String, String> read(File f) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			return read(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static Mapx<String, String> read(InputStream is) {
		String text = FileUtil.readText(is, "UTF-8");
		String[] arr = text.split("\n");
		Mapx<String, String> map = new Mapx<String, String>();
		Arrays.asList(arr).stream().filter(s->StringUtil.isNotNull(s) && !s.startsWith("#")).forEach(s->{
			int index = s.indexOf("=");
			if(index>=0){
				String k = s.substring(0,index).trim();
				String v = s.substring(index+1);
				map.put(k,v);
			}
		});
		return map;
	}
}
