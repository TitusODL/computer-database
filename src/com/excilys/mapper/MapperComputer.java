package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class MapperComputer { 

	public static Computer ComputerDetailMapper(ResultSet resComputer) throws SQLException {


		long computerId = (resComputer.getLong("computer.id"));
		String computerName = (resComputer.getString("computer.name"));
		LocalDate introduced = (resComputer.getDate("computer.introduced") != null ? resComputer.getDate("computer.introduced").toLocalDate() : null);
		LocalDate discontinued = (resComputer.getDate("computer.discontinued") != null ? resComputer.getDate("computer.discontinued").toLocalDate() : null);
		Long companyId = (resComputer.getLong("company_id"));
		String companyName = (resComputer.getString("company.name"));
		Company company = new Company.CompanyBuilder().setId(companyId).setName(companyName).build();
		Computer computer = new Computer.Builder().setId(computerId).setName(computerName).setIntroducedDate(introduced)
										 .setDiscontinuedDate(discontinued).setCompany(company).build();
		return computer;
	}
	public static LocalDate transString(String entry) {
		if(entry.isEmpty()) {
			return null;
		}
		else{ 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(entry, formatter);
			return localDate;
		}

	}

}   

