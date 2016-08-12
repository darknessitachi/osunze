package com.skycity.framework.utility;

import java.sql.Connection;

import com.skycity.framework.db.DBConnPoolImpl;



public class DBUtil {
    
    public static Connection getConnection(){
    	return DBConnPoolImpl.getInstance();
    }
    
    public static void closeConnection(){
    	DBConnPoolImpl.close();
    }
    
    public static void getCodeData(String code){
//    	Connection conn = getConnection();
//    	ResultSet rs= conn.createStatement(resultSetType, resultSetConcurrency)
    }
}
