//package com.excilys.persistence;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@PropertySource("classpath:/hikari.properties")
//
//public class HikariCPConnect implements AutoCloseable {
//
//	static Connection conn;
//	private static HikariConfig config = new HikariConfig("/hikari.properties");
//	private static HikariDataSource ds;
//
//	@Bean
//	public static DataSource hikariDataSource() {
//		ds = new HikariDataSource(config);
//		return ds;
//	}
//	
//	public Connection getConnection() {
//		try {
//			 if ( conn == null )
//			conn = hikariDataSource().getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	@Override
//	public void close() throws Exception {
//		conn.close();
//		ds.close();
//
//	}
//}
