package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connecticut implements AutoCloseable  {
	
		private static Properties connectionProperties;
	    public static Connection conn;
	    public static Connecticut db;
	    private static String url;
	    private String userName;
	    private String password;
	    private static final String Localisation = "mysql.properties";
		

	    private Connecticut() {
	    	connectionProperties = new Properties();
	        try {
//	        	FileInputStream stream = new FileInputStream("src/main/ressources/mysql.properties");
//	        	connectionProperties.load(stream);
	        	connectionProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(Localisation));
	        	url = connectionProperties.getProperty("url");
	        	userName =connectionProperties.getProperty("userName");
	        	password =connectionProperties.getProperty("password");
	        	 Class.forName(connectionProperties.getProperty("driver"));
	             conn = DriverManager.getConnection(url, userName, password);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	   
	    public static synchronized Connecticut getDbCon() {
	        if ( db == null ) {
	            db = new Connecticut();
	        }
	        return db;
	 
	    }


		@Override
		public void close() throws Exception {
			conn.close();
			db.close();
			
		}
	 
	}

