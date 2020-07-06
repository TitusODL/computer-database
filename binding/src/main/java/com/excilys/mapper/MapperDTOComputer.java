package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.dto.DTOComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class MapperDTOComputer {
	private static final Logger logger = LoggerFactory.getLogger(MapperDTOComputer.class);

	public Computer dtoToComputer(DTOComputer dtoComputer) {
		Company company = new Company.CompanyBuilder().setId(Long.parseLong(dtoComputer.getCompany_id()))
				.setName(dtoComputer.getCompany_name()).build();
		Computer computer = new Computer.Builder()
				.setId(dtoComputer.getId() == null ? 0 : Long.parseLong(dtoComputer.getId()))
				.setName(dtoComputer.getName()).setIntroduced(MapperDate.ConvertDateString(dtoComputer.getIntroduced()))
				.setDiscontinued(MapperDate.ConvertDateString(dtoComputer.getDiscontinued())).setCompany(company)
				.build();
		return computer;
	}

	public DTOComputer computerToDto(Computer computer) {
		DTOComputer DTOComputer = new DTOComputer.DTOComputerBuilder().setId(String.valueOf(computer.getId()))
				.setName(computer.getName()).setIntroduced(String.valueOf(computer.getIntroduced()))
				.setDiscontinued(String.valueOf(computer.getDiscontinued()))
				.setCompany_Id(computer.getCompany() != null ? String.valueOf(computer.getCompany().getId()) : null)
				.setCompany_Name(computer.getCompany() != null ? String.valueOf(computer.getCompany().getName()) : "X")
				.build();
		return DTOComputer;
	}

	public List<DTOComputer> listComputerToDto(List<Computer> computerList) {
		logger.info("ComputerList converted!");
		return computerList.stream().map(computer -> computerToDto(computer)).collect(Collectors.toList());
		
	}

	public List<Computer> listDtoToComputer(List<DTOComputer> DTOComputerList) {
		logger.info("ComputerDTOList converted!");
		return DTOComputerList.stream().map(computer -> dtoToComputer(computer)).collect(Collectors.toList());

	}
}