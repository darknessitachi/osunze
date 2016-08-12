package com.skycity.framework.utility;

import org.apache.log4j.Logger;

/**
 * 日志类，用于扩展，错误或者警告信息可以写入数据库
 * @author YingBo
 *
 */
public class LogUtil {
    private static Logger logger = Logger.getLogger(LogUtil.class);
    
    public static void info(Object obj) {
         logger.info("info:" + obj);
    }
    
    public static void debug(Object obj) {
        logger.debug("debug:"+obj);
    }
    
    public static void warn(Object obj) {
    	logger.warn("warn"+obj);
    }
    
    public static void error(Object obj) {
    	logger.error("error:" + obj);
    }
}