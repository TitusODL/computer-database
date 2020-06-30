package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.dto.DTOComputer;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;

@Component

public class MapperDTOComputer {
	
	DAOCompany daoCompany;
	public MapperDTOComputer(DAOCompany daoCompany) {
		this.daoCompany =daoCompany;
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
}
