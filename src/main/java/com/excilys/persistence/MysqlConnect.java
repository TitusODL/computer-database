package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;


public class MysqlConnect implements AutoCloseable {
	
	private static volatile MysqlConnect instance = null;
    public static Connection conn;
    public static MysqlConnect db;
    String url= "jdbc:mysql://localhost:3306/";
    String dbName = "computer-database-db";
    String timeZoneErr = "? useUnicode=true & useJDBCCompliantTimezoneShift=true & useLegacyDatetimeCode=false & serverTimezone=UTC";
    String driver = "com.mysql.cj.jdbc.Driver";
    String userName = "admincdb";
    String password = "qwerty1234";
    
	
	public final static MysqlConnect getInstance() {
		if (MysqlConnect.instance == null) {
			synchronized (MysqlConnect.class) {
				if (MysqlConnect.instance == null) {
					MysqlConnect.instance = new MysqlConnect();
								}
			}
		}
		return MysqlConnect.instance;
	}
	
    private MysqlConnect() {

        try {
            Class.forName(driver).newInstance();
            conn = (Connection)DriverManager.getConnection(url+dbName+timeZoneErr,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
   
    public static synchronized MysqlConnect getDbCon() {
        if ( db == null ) {
            db = new MysqlConnect();
        }
        return db;
 
    }


	@Override
	public void close() throws Exception {
		conn.close();
		db.close();
		
	}
 
}
