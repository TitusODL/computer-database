package com.excilys.persistence;

import java.sql.*;
import java.util.ArrayList; 
import com.excilys.model.Computer;

public class DAOComputer {

		
		MysqlConnect msc = MysqlConnect.getDbCon();	
		private final static String requete = "SELECT id, name, introduced, discontinued, company_id FROM computer";
		
		public ArrayList<Computer> getComputer() throws SQLException{
			ArrayList<Computer> listComputers = new ArrayList<Computer>();
			ResultSet listComputer =msc.query(requete);
		while (listComputer.next()) {
			long id = listComputer.getLong("id");
			String name  = listComputer.getString( "name"); 
			Date introduced = listComputer.getDate("introduced");
			Date discontinued = listComputer.getDate( "discontinued");
			Long company_id = listComputer.getLong( "company_id");
			Computer pc = new Computer(id,name,introduced,discontinued,company_id);
			listComputers.add(pc);
		}
		return listComputers;
		
		}
}
