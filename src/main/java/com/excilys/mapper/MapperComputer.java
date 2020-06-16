package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.dto.DTOComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;
import com.excilys.service.Validators;

public class MapperComputer { 

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

	public static Computer dtoToComputer(DTOComputer DTOComputer) throws NumberFormatException{
		Computer computer = new Computer.Builder().setName(DTOComputer.name)
				.setIntroduced(transString(DTOComputer.introduced))
				.setDiscontinued(transString(DTOComputer.discontinued))
				.setCompany(DAOCompany.getInstance().getCompanybyId(Long.parseLong(DTOComputer.company_id)))
				.build();
		return computer;
	}

	public static DTOComputer computerToDto(Computer computer) {

		DTOComputer DTOComputer = new DTOComputer.DTOComputerBuilder().setId(String.valueOf(computer.id))
				.setName(computer.name).setIntroduced(String.valueOf(computer.introduced))
				.setDiscontinued(String.valueOf(computer.discontinued))
				.setCompany_Id(String.valueOf(computer.company.id))
				.setCompany_Name(String.valueOf(computer.company.name))
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

	public static List<Computer> listDtoToComputer(List<DTOComputer> DTOComputerList){
		List<Computer> computerList = new ArrayList<Computer>();

		for(int i=0; i<computerList.size(); i++) {
			computerList.add(dtoToComputer(DTOComputerList.get(i)));
		}

		return computerList;
	}
	public static LocalDate transString(String entry) {
		boolean format = Validators.verifyDateUserInput(entry);
		if(entry == null || entry.isEmpty()) 
		{
			return null;
		}
		else if(entry.equals("null")) 
		{
			return null;
		}
		else if (!format){ 
			return null;
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(entry, formatter);
			return localDate;

		}
	}
}
