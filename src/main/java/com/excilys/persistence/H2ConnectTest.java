//package com.excilys.persistence;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//
//public class H2ConnectTest implements AutoCloseable {
//	
//	private static volatile H2ConnectTest instance = null;
//    public static Connection conn;
//    public static H2ConnectTest db;
//
//	
//    String url= "jdbc:h2:mem:computer-database-db;INIT=RUNSCRIPT FROM 'src/test/ressources/H2TableCreation.sql'";
//    String driver = "org.h2.Driver";
//    String userName = "sa";
//    String password = "";  
//    
//	
//	public final static H2ConnectTest getInstance() {
//		if (H2ConnectTest.instance == null) {
//			synchronized (H2ConnectTest.class) {
//				if (H2ConnectTest.instance == null) {
//					H2ConnectTest.instance = new H2ConnectTest();
//								}
//			}
//		}
//		return H2ConnectTest.instance;
//	}
//	
//    private H2ConnectTest() {
//
//        try {
//        	Class.forName(driver).newInstance();
//        conn = (Connection)DriverManager.getConnection(url,userName,password);
//        }
//        catch (Exception sqle) {
//            sqle.printStackTrace();
//        }
//    }
//   
//    public static  H2ConnectTest getDbCon() {
//        if ( db == null ) {
//            db = new H2ConnectTest();
//        }
//        System.out.println("connected");
//        return db;
//
//    }
//    public static boolean ConnectionTest() {
//    	try  {
//    		H2ConnectTest.getDbCon();
//    		return true;
//    	}
//    	catch (Exception e){
//    		e.printStackTrace();
//    	}
//    	return false;
//    }
//
//	@Override
//	public void close() throws Exception {
//		conn.close();
//		db.close();
//		
//	}
// 
//}
