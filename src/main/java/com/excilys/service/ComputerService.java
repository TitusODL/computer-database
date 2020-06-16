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

	public Optional<Computer> getComputerDetail(int id) throws SQLException {
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

	public List<Computer> getPageByName(String search, Pagination page) {
		return DAOComputer.getInstance().getPageComputerName(search, page);
	}

	public List<Computer> getSearchedComputers(String search) {
		return DAOComputer.getInstance().getSearchedComputers(search);
	}

	public List<Computer> getComputersbyOrder(String order,int direction,Pagination page) throws SQLException {
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
		newDTOComputer.setId(DTOComputer.id);
		Optional<Computer> oldComputer = getComputerById(DTOComputer.id);
		DTOComputer oldDTOComputer = MapperComputer.computerToDto(oldComputer.get());
		updateName(DTOComputer, oldDTOComputer, newDTOComputer);
		updateIntroduced(DTOComputer, oldDTOComputer, newDTOComputer);
		updateDiscontinued(DTOComputer, oldDTOComputer, newDTOComputer);
		boolean date = verifierDate(DTOComputer);
		updateCompany(DTOComputer, oldDTOComputer, newDTOComputer);

		logger.info(newDTOComputer.toString());

		if (date) {
			Computer computer = MapperComputer.dtoToComputer(newDTOComputer);
			computer.setId(Long.parseLong(DTOComputer.id));
			DAOComputer.getInstance().updateComputer(computer);
		}
	}

	public void deleteComputer(int id) {
		Optional<Computer> computer = DAOComputer.getInstance().getComputerDetail(id);
		if (computer.isPresent()) {
			DAOComputer.getInstance().deleteComputer(id);
			logger.info(computer.get().toString());
			logger.info("Computer deleted");
		} else {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
	}

	private boolean verifierDate(DTOComputer DTOComputer) {
		boolean ordreDate = false;
		boolean dateIntroduced = Validators.verifyDateUserInput(DTOComputer.introduced);
		boolean dateDiscontinued = Validators.verifyDateUserInput(DTOComputer.discontinued);
		if (dateIntroduced && dateDiscontinued) {
			ordreDate = Validators.verifierDateOrdre(DTOComputer.introduced, DTOComputer.discontinued);
			if (!ordreDate) {
				logger.info("Date non valide !");
			}
		}
		if (dateIntroduced && dateDiscontinued && ordreDate) {
			return true;
		} else
			return false;
	}

	private boolean verifierNom(DTOComputer DTOComputer) {
		boolean name = false;
		if (DTOComputer.name.isEmpty()) {
			logger.info("Nom requis");
		} else {
			name = true;
		}
		return name;
	}

	private void updateName(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.name.isEmpty()) {
			newDTOComputer.name = oldDTOComputer.name;
		} else {
			newDTOComputer.setName(DTOComputer.name);
		}
	}

	private void updateIntroduced(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.introduced.isEmpty()) {
			newDTOComputer.introduced = oldDTOComputer.introduced;
		} else {
			newDTOComputer.setIntroduced(DTOComputer.introduced);
		}
	}

	private void updateDiscontinued(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.discontinued.isEmpty()) {
			newDTOComputer.discontinued = oldDTOComputer.discontinued;
		} else {
			newDTOComputer.setDiscontinued(DTOComputer.discontinued);
		}
	}

	private void updateCompany(DTOComputer DTOComputer, DTOComputer oldDTOComputer, DTOComputer newDTOComputer) {
		if (DTOComputer.company_id.isEmpty() || DTOComputer.company_name.isEmpty()) {
			newDTOComputer.setCompany_id(oldDTOComputer.company_id);
			newDTOComputer.setCompany_name(oldDTOComputer.company_name);
		} else {
			newDTOComputer.setCompany_id(DTOComputer.company_id);
			newDTOComputer.setCompany_name(DTOComputer.company_name);
		}
	}
}
