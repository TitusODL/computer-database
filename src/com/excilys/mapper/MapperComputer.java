package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.excilys.model.Company;
import com.excilys.model.Computer;
	 
	public class MapperComputer { 
	
		public static Computer getComputerResultSet(ResultSet resDetailcomputer) throws SQLException {
			Computer computer;
			long computerId = (resDetailcomputer.getLong("computer.id"));
			String computerName = (resDetailcomputer.getString("computer.name"));
			LocalDate introduced = (resDetailcomputer.getTimestamp("computer.introduced") != null ? resDetailcomputer.getTimestamp("computer.introduced").toLocalDate() : null);
			LocalDate discontinued = (resDetailcomputer.getTimestamp("computer.discontinued") != null ? resDetailcomputer.getTimestamp("computer.discontinued").toLocalDate() : null);
			Long companyId = (resDetailcomputer.getLong("company_id"));
			String companyName = (resDetailcomputer.getString("company.name"));

			Company company = new Company(companyId,companyName);
			computer = new Computer(computerId,computerName,introduced,discontinued,company);
			return computer;
	    }
	}   

