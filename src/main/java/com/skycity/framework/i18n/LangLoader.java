package com.skycity.framework.i18n;

import java.io.File;

import com.skycity.framework.collection.Mapx;
import com.skycity.framework.utility.PropertiesUtil;
import com.skycity.framework.utility.URLUtil;

/**
 * 国际化语言加载
 * @author YingBo
 *
 */
public class LangLoader {

	public static LangMapping loadMapping() {
		Mapx<String, String> mapping = new Mapx<String, String>();
		LangMapping lm = new LangMapping();
		lm.mapping = mapping;
		loadFromClasses(URLUtil.getClassesPath() + "/lang", lm);
		return lm;
	}

	private static void loadFromClasses(String path, LangMapping lm) {
		if (!new File(path).exists()) {
			return;
		}
		try {
			File[] fs = new File(path).listFiles();
			for (File f : fs) {
				if (f.isDirectory()) {
					File[] fs2 = f.listFiles();
					for (File f2 : fs2) {
						if ((f2.isFile()) && (f2.getName().toLowerCase().endsWith(".i18n"))) {
							Mapx<String, String> map = PropertiesUtil.read(f2);
							for (String key : map.keySet()) {
								String lang = f2.getName().substring(0,f2.getName().lastIndexOf("."));
								lm.mapping.put(lang + "." + key,map.getString(key));
							}
						}
					}
				}
			}
			File langFile = new File(path + "/lang.i18n");
			if (langFile.exists()) {
				Mapx<String, String> map = PropertiesUtil.read(langFile);
				lm.languageMap = ((Mapx<String, String>) map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
