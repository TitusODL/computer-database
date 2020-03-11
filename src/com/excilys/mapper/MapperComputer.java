package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;
	 
	public class MapperComputer { 
	
		public static Computer getComputerResultSet(ResultSet resDetailcomputer) throws SQLException {
			
			Computer computer;
			long computerId = (resDetailcomputer.getLong("computer.id"));
			String computerName = (resDetailcomputer.getString("computer.name"));
			LocalDate introduced = (resDetailcomputer.getDate("computer.introduced") != null ? resDetailcomputer.getDate("computer.introduced").toLocalDate() : null);
			LocalDate discontinued = (resDetailcomputer.getDate("computer.discontinued") != null ? resDetailcomputer.getDate("computer.discontinued").toLocalDate() : null);
			Long companyId = (resDetailcomputer.getLong("company_id"));
			String companyName = (resDetailcomputer.getString("company.name"));

			Company company = new Company(companyId,companyName);
			computer = new Computer(computerId,computerName,introduced,discontinued,company);
			return computer;
	    }
	public static Computer getComputers(ResultSet resDetailcomputer) throws SQLException {
			
			long computerId = (resDetailcomputer.getLong("computer.id"));
			String computerName = (resDetailcomputer.getString("computer.name"));
			LocalDate introduced = (resDetailcomputer.getDate("computer.introduced") != null ? resDetailcomputer.getDate("computer.introduced").toLocalDate() : null);
			LocalDate discontinued = (resDetailcomputer.getDate("computer.discontinued") != null ? resDetailcomputer.getDate("computer.discontinued").toLocalDate() : null);
			Long companyId = (resDetailcomputer.getLong("company_id"));
			//String companyName = (resDetailcomputer.getString("company.name"));
			Company company = new Company();
			company = DAOCompany.getInstance().getCompanybyId(companyId);
			Computer pc = new Computer(computerId, computerName, introduced, discontinued,company);
			return pc;
	}
	
	public static LocalDate transString(String entry) {
		    LocalDate localDate = null;
	        DateTimeFormatter formatter = null;
	        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        localDate = LocalDate.parse(entry, formatter);
	        return localDate;
	}
	}   

