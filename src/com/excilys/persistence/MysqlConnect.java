package com.excilys.persistence;

import java.sql.*;
import java.sql.DriverManager;


public class MysqlConnect {
    public Connection conn;
    private Statement statement;
    public static MysqlConnect db;
    String url= "jdbc:mysql://localhost:3306/";
    String dbName = "computer-database-db";
    String driver = "com.mysql.cj.jdbc.Driver";
    String userName = "admincdb";
    String password = "qwerty1234";
    private MysqlConnect() {

        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("lol");
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized MysqlConnect getDbCon() {
        if ( db == null ) {
            db = new MysqlConnect();
        }
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
        
        
    }
 
}