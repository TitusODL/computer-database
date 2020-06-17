package com.excilys.ui;

import java.sql.SQLException;

import com.excilys.persistence.Connecticut;

public class Main {

	public static void main(String[] args) throws SQLException {
		
	
		Connecticut.getDbCon();
		MenuCLI.CliMenu();
	
		
	}
	
}
