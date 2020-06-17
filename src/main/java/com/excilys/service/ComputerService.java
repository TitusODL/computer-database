package com.excilys.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.DAOComputer;

public class ComputerService {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	public Optional<Computer> getComputerDetail(long id) throws SQLException {
		return DAOComputer.getInstance().getComputerDetail(id);
	}

	public List<Computer> getComputers() throws SQLException {
		return DAOComputer.getInstance().getComputers();
	}

	public void createComputer(Computer computer) throws SQLException {
		DAOComputer.getInstance().addComputer(computer);
	}

	public int countAllComputer() {
		return DAOComputer.getInstance().countAllComputer();
	}

	public List<Computer> getPageComputer(Pagination page) {
		return DAOComputer.getInstance().getPageComputersRequest(page);
	}

	public List<Computer> getPageByNameSearched(String search, Pagination page) {
		return DAOComputer.getInstance().getPageComputerNameSearched(search, page);
	}

	public List<Computer> getSearchedComputers(String search) {
		return DAOComputer.getInstance().getSearchedComputers(search);
	}

	public List<Computer> getComputersbyOrder(String order,int direction,Pagination page) {
		return DAOComputer.getInstance().getPageComputerOrderByName(order,direction,page);
	}

	CompanyService companyService = new CompanyService();

	public List<Computer> getAllComputers() {
		return DAOComputer.getInstance().getComputers();
	}

	public Optional<Computer> getComputerById(String id) {
		long compId = Long.parseLong(id);
		Optional<Computer> computer = DAOComputer.getInstance().getComputerDetail(compId);
		if (!computer.isPresent()) {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
		return computer;
	}

	public void createComputer(DTOComputer DTOComputer) {
		boolean name = verifierNom(DTOComputer);
		boolean date = verifierDate(DTOComputer);

		if (name && date) {
			Computer computer = MapperComputer.dtoToComputer(DTOComputer);
			DAOComputer.getInstance().addComputer(computer);
		}
	}

	public void updateComputer(DTOComputer DTOComputer) {
		DTOComputer newDTOComputer = new DTOComputer.DTOComputerBuilder().build();
		newDTOComputer.setId(DTOComputer.getId());
		Optional<Computer> oldComputer = getComputerById(DTOComputer.getId());
		DTOComputer oldDTOComputer = MapperComputer.computerToDto(oldComputer.get());
		updateName(DTOComputer, oldDTOComputer, newDTOComputer);
		updateIntroduced(DTOComputer, oldDTOComputer, newDTOComputer);
		updateDiscontinued(DTOComputer, oldDTOComputer, newDTOComputer);
		boolean date = verifierDate(DTOComputer);
		updateCompany(DTOComputer, oldDTOComputer, newDTOComputer);

		logger.info(newDTOComputer.toString());

		if (date) {
			Computer computer = MapperComputer.dtoToComputer(newDTOComputer);
			computer.setId(Long.parseLong(DTOComputer.getId()));
			DAOComputer.getInstance().updateComputer(computer);
		}
	}

	public void deleteComputer(long id) {
		Optional<Computer> computer = DAOComputer.getInstance().getComputerDetail(id);
		if (computer.isPresent()) {
			DAOComputer.getInstance().deleteComputer(id);
			logger.info(computer.get().toString());
			logger.info("Computer deleted");
		} else {
			logger.info("No Computer on this ID");
		}
	}

	private boolean verifierDate(DTOComputer DTOComputer) {
		boolean ordreDate = false;
		boolean dateIntroduced = Validators.verifyDateUserInput(DTOComputer.getIntroduced());
		boolean dateDiscontinued = Validators.verifyDateUserInput(DTOComputer.getDiscontinued());
		if (dateIntroduced && dateDiscontinued) {
			ordreDate = Validators.verifierDateOrdre(DTOComputer.getIntroduced(), DTOComputer.getDiscontinued());
			if (!ordreDate) {
				logger.info("Non Valid Date!");
			}
		}
		if (dateIntroduced && dateDiscontinued && ordreDate) {
			return true;
		} else
			return false;
	}

	private boolean verifierNom(DTOComputer DTOComputer) {
		boolean name = false;
		if (DTOComputer.getName().isEmpty()) {
			logger.info("Name required");
		} else {
			name = true;
		}
		return name;
	}

	private void updateName(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.getName().isEmpty()) {
			String oui = newDTOComputer.getName(); 
			oldDTOComputer.setName(oui);

		} else {
			newDTOComputer.setName(DTOComputer.getName());
		}
	}

	private void updateIntroduced(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.getIntroduced().isEmpty()) {
			String oui = newDTOComputer.getIntroduced(); 
			oldDTOComputer.setIntroduced(oui);
		} else {
			newDTOComputer.setIntroduced(DTOComputer.getIntroduced());
		}
	}

	private void updateDiscontinued(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.getDiscontinued().isEmpty()) {
			String oui = newDTOComputer.getDiscontinued(); 
			oldDTOComputer.setDiscontinued(oui);
		} else {
			newDTOComputer.setDiscontinued(DTOComputer.getDiscontinued());
		}
	}

	private void updateCompany(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.getCompany_id().isEmpty() || DTOComputer.getCompany_name().isEmpty()) {
			newDTOComputer.setCompany_id(oldDTOComputer.getCompany_id());
			newDTOComputer.setCompany_name(oldDTOComputer.getCompany_name());
		} else {
			newDTOComputer.setCompany_id(DTOComputer.getCompany_id());
			newDTOComputer.setCompany_name(DTOComputer.getCompany_name());
		}
	}
}
