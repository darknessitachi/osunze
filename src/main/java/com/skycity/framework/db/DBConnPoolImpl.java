package com.skycity.framework.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.skycity.framework.Config;
import com.skycity.framework.collection.Mapx;
import com.skycity.framework.utility.StringUtil;


public class DBConnPoolImpl {
    
    private static DBConnConfig dcc = new DBConnConfig();
    
    private static Connection conn;
    
    public static Connection getInstance(){
    	try {
    		init(dcc,Config.getConfigMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return conn;
    }
    
    public static void close() {
        if (conn == null) {
            return;
        }else{
        	try {
				conn.close();
	        	conn=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    public static void init(DBConnConfig dcc, Mapx<String, String> map) throws Exception {
        dcc.ConnectionURL = map.getString("DB.url");
        dcc.DBUserName = map.getString("DB.username");
        dcc.DBPassword = map.getString("DB.password");
        if (StringUtil.isEmpty(dcc.ConnectionURL)) {
	        if ((dcc.DBUserName == null) || (dcc.DBUserName.equals(""))) {
	            throw new RuntimeException("DB.UserName not found");
	        }
	        if (dcc.DBPassword == null) {
	            throw new RuntimeException("DB.Password not found");
	        }
        }
        createConnection(dcc);
    }

    
    public static void createConnection(DBConnConfig dbcc) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(dbcc.ConnectionURL, dbcc.DBUserName, dbcc.DBPassword);
        Statement stmt = conn.createStatement(1005, 1008);
        String charset = dbcc.Charset;
        if (StringUtil.isEmpty(charset)) {
            charset = "UTF-8";
        }
        stmt.execute("SET NAMES '" + charset.replaceAll("\\-", "").toLowerCase() + "'");
        stmt.close();
    }
    
    public void executeSql(String sql){
//    	try {
//			Statement stmt = conn.createStatement(1005, 1008);
//			ResultSet rs = stmt.executeQuery(sql);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
    }
}
