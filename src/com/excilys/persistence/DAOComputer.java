package com.excilys.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.model.Computer;

public class DAOComputer {

		
		MysqlConnect msc = MysqlConnect.getDbCon();	
		private final static String request = "SELECT id, name, introduced, discontinued, company_id FROM computer";
		private final static String request2 = request + " WHERE id = ";
		
		public Optional<Computer> getComputerDetail(int key) throws SQLException {
			ResultSet ComputerDetail =msc.query(request2 + key);
			while (ComputerDetail.next()) {
				long id = ComputerDetail.getLong("id");
				String name  = ComputerDetail.getString( "name"); 
				Date introduced = ComputerDetail.getDate("introduced");
				Date discontinued = ComputerDetail.getDate( "discontinued");
				Long company_id = ComputerDetail.getLong( "company_id");
				Computer pc = new Computer(id,name,introduced,discontinued,company_id);
				return Optional.of(pc);
			}
			return Optional.empty();
		}
		
		
		public ArrayList<Computer> getComputer() throws SQLException{
			ArrayList<Computer> listComputers = new ArrayList<Computer>();
			ResultSet listComputer =msc.query(request);

			
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
