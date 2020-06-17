package com.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPConnect implements AutoCloseable{

    private static HikariConfig config = new HikariConfig("/hikari.properties");
    private static HikariDataSource ds = new HikariDataSource(config);
    

    private HikariCPConnect() {

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

	@Override
	public void close() throws Exception {
			ds.close();
		
	}
}

