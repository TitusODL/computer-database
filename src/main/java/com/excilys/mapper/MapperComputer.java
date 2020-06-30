package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class MapperComputer implements RowMapper<Computer>{ 
	

	public static Computer ComputerDetailMapper(ResultSet resComputer) throws SQLException {

		long computerId = (resComputer.getLong("computer.id"));
		String computerName = (resComputer.getString("computer.name"));
		LocalDate introduced = (resComputer.getDate("computer.introduced") != null ? resComputer.getDate("computer.introduced").toLocalDate() : null);
		LocalDate discontinued = (resComputer.getDate("computer.discontinued") != null ? resComputer.getDate("computer.discontinued").toLocalDate() : null);
		Long companyId = (resComputer.getLong("company_id"));
		String companyName = (resComputer.getString("company.name"));
		Company company = new Company.CompanyBuilder().setId(companyId).setName(companyName).build();
		Computer computer = new Computer.Builder().setId(computerId).setName(computerName).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();

		return computer;
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ComputerDetailMapper(rs);
	}
	

}
