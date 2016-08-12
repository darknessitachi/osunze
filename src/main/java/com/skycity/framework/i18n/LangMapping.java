package com.skycity.framework.i18n;

import com.skycity.framework.User;
import com.skycity.framework.User.UserData;
import com.skycity.framework.collection.Mapx;
import com.skycity.framework.utility.StringUtil;


public class LangMapping {
    
    protected Mapx<String, String> mapping = null;
    
    protected String DefaultLanguage = "zh-cn";
    
    protected Mapx<String, String> languageMap;
    
    private static LangMapping instance = null;
    
    private static Object mutex = new Object();
    
    private static long lastTime = 0L;
    
    public static LangMapping getInstance() {
        if ((lastTime == 0L) || System.currentTimeMillis() - lastTime > 3000L) {
            synchronized (mutex) {
                if ((lastTime == 0L)  && System.currentTimeMillis() - lastTime > 3000L) {
                    instance = LangLoader.loadMapping();
                    lastTime = System.currentTimeMillis();
                }
            }
        }
        return instance;
    }
    
    public Mapx<String, String> getLanguageMap() {
        return this.languageMap;
    }
    
    public String getDefaultLanguage() {
        return this.DefaultLanguage;
    }
    
    public String getValue(String lang, String key) {
        key = filterKey(key);
        String str = (String) this.mapping.get(lang + "." + key);
        if ((str == null) && (!lang.equals(this.DefaultLanguage))) {
            str = (String) this.mapping.get(this.DefaultLanguage + "." + key);
        }
        return str;
    }
    
    public static String get(String lang, String key) {
        return getInstance().getValue(lang, key);
    }
    
    public static String get(String key) {
        return getInstance().getValue(key);
    }
    
    public String getValue(String key) {
        key = filterKey(key);
        UserData u = User.getCurrent();
        String lang = u.getLanguage();
        if(StringUtil.isEmpty(lang)) {
            lang = this.DefaultLanguage;
        }
        return getValue(lang, key);
    }
    
    public void put(String key, String str) {
        key = filterKey(key);
        this.mapping.put(key, str);
    }
    
    private String filterKey(String key) {
        if (key == null) {
            return key;
        }
        if ((key.startsWith("@{")) && (key.endsWith("}"))) {
            return key.substring(2, key.length() - 1);
        }
        return key;
    }
}
