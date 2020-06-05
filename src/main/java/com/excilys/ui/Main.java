package com.excilys.ui;

import java.sql.SQLException;

import com.excilys.persistence.Connecticut;
import com.excilys.persistence.H2ConnectTest;
import com.excilys.persistence.MysqlConnect;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		//H2ConnectTest.getDbCon();
		Connecticut.getDbCon();
		MenuCLI.CliMenu();
	}
	
}
