package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.dto.DTOComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;

@Component
public class MapperComputer implements RowMapper<Computer>{ 
	
	DAOCompany daoCompany;
	public MapperComputer(DAOCompany daoCompany) {
		this.daoCompany =daoCompany;
	}
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

	public Computer dtoToComputer(DTOComputer DTOComputer) throws NumberFormatException{
		Computer computer = new Computer.Builder()
				.setName(DTOComputer.getName())
				.setIntroduced(MapperDate.ConvertDateString(DTOComputer.getIntroduced()))
				.setDiscontinued(MapperDate.ConvertDateString(DTOComputer.getDiscontinued()))
				//.setId(DTOCompany.getId())
				.setCompany(daoCompany.getCompanybyId(Long.parseLong(DTOComputer.getCompany_id())).get())
				.build();
		return computer;
	}

	public static DTOComputer computerToDto(Computer computer) {	
		DTOComputer DTOComputer = new DTOComputer.DTOComputerBuilder().setId(String.valueOf(computer.getId()))
				.setName(computer.getName()).setIntroduced(String.valueOf(computer.getIntroduced()))
				.setDiscontinued(String.valueOf(computer.getDiscontinued()))
				.setCompany_Id(String.valueOf(computer.getCompany().getId()))
				.setCompany_Name(String.valueOf(computer.getCompany().getName()))
				.build();
		return DTOComputer;
	}

	public static List<DTOComputer> listComputerToDto(List<Computer> computerList){
		List<DTOComputer> DTOComputerList = new ArrayList<DTOComputer>();

		for(int i=0; i<computerList.size(); i++) {
			DTOComputerList.add(computerToDto(computerList.get(i)));
		}

		return DTOComputerList;
	}

	public List<Computer> listDtoToComputer(List<DTOComputer> DTOComputerList){
		List<Computer> computerList = new ArrayList<Computer>();

		for(int i=0; i<computerList.size(); i++) {
			computerList.add(dtoToComputer(DTOComputerList.get(i)));
		}

		return computerList;
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ComputerDetailMapper(rs);
	}
	

}
