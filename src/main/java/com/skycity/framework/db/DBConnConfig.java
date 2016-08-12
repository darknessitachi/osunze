package com.skycity.framework.db;

public class DBConnConfig {
    
    public static final String ORACLE = "ORACLE";
    
    public static final String DB2 = "DB2";
    
    public static final String MYSQL = "MYSQL";
    
    public String JNDIName = null;
    
    public boolean isJNDIPool = false;
    
    public int MaxConnCount = 1000;
    
    public int InitConnCount = 5;
    
    public int ConnCount;
    
    public int MaxConnUsingTime = 300000;
    
    public int RefershPeriod = 60000;
    
    public String DBType;
    
    public String DBServerAddress;
    
    public int DBPort;
    
    public String ConnectionURL;
    
    public String DBName;
    
    public String DBUserName;
    
    public String DBPassword;
    
    public String TestTable;
    
    public String PoolName;
    
    public String Charset;
    
    public boolean isLatin1Charset;
    
    public boolean isOracle() {
        return this.DBType.equalsIgnoreCase("ORACLE");
    }
    
    public boolean isDB2() {
        return this.DBType.equalsIgnoreCase("DB2");
    }
    
    public boolean isMysql() {
        return this.DBType.equalsIgnoreCase("MYSQL");
    }
    
}
