package com.excilys.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperDTOComputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.DAOComputer;
@Service
public class ComputerService {
	DAOComputer daoComputer;
	MapperDTOComputer mapperDTOComputer;
	

	public ComputerService(DAOComputer daoComputer,MapperDTOComputer mapperDTOComputer) {
		this.daoComputer = daoComputer;
		this.mapperDTOComputer = mapperDTOComputer;
	}
	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	public Optional<Computer> getComputerDetail(long id) throws SQLException {
		return daoComputer.getComputerDetail(id);
	}

	public List<Computer> getComputers() throws SQLException {
		return daoComputer.getComputers();
	}

	public int countAllComputer() {
		return daoComputer.countAllComputer();
	}

	public List<DTOComputer> getPageComputer(Pagination page) {
		
		return MapperDTOComputer.listComputerToDto(daoComputer.getPageComputersRequest(page));
	}

	public List<DTOComputer> getPageByNameSearched(String search, Pagination page) {
		return MapperDTOComputer.listComputerToDto(daoComputer.getPageComputerNameSearched(search, page));
	}
	public List<Computer> getComputerIdByCompany(long id){
		return daoComputer.getComputerIdByCompany(id);
	}

	public List<Computer> getSearchedComputers(String search) {
		return daoComputer.getSearchedComputers(search);
	}

	public List<DTOComputer> getComputersbyOrder(String order,int direction,Pagination page) {
		return MapperDTOComputer.listComputerToDto(daoComputer.getPageComputerOrderByName(order,direction,page));
	}

	public List<Computer> getAllComputers() {
		return daoComputer.getComputers();
	}

	public Optional<Computer> getComputerById(String id) {
		long compId = Long.parseLong(id);
		Optional<Computer> computer = daoComputer.getComputerDetail(compId);
		if (!computer.isPresent()) {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
		return computer;
	}

	public void createComputer(DTOComputer DTOComputer) {
		boolean name = verifierNom(DTOComputer);
		boolean date = verifierDate(DTOComputer);

		if (name && date) {
			Computer computer = mapperDTOComputer.dtoToComputer(DTOComputer);
			daoComputer.addComputer(computer);
		}
	}

	public void updateComputer(DTOComputer DTOComputer) {
		DTOComputer newDTOComputer = new DTOComputer.DTOComputerBuilder().build();
		newDTOComputer.setId(DTOComputer.getId());
		Optional<Computer> oldComputer = getComputerById(DTOComputer.getId());
		DTOComputer oldDTOComputer = MapperDTOComputer.computerToDto(oldComputer.get());
		updateName(DTOComputer, oldDTOComputer, newDTOComputer);
		updateIntroduced(DTOComputer, oldDTOComputer, newDTOComputer);
		updateDiscontinued(DTOComputer, oldDTOComputer, newDTOComputer);
		boolean date = verifierDate(DTOComputer);
		updateCompany(DTOComputer, oldDTOComputer, newDTOComputer);

		logger.info(newDTOComputer.toString());

		if (date) {
			Computer computer = mapperDTOComputer.dtoToComputer(newDTOComputer);
			computer.setId(Long.parseLong(DTOComputer.getId()));
			daoComputer.updateComputer(computer);
		}
	}

	public void deleteComputer(long id) {
		Optional<Computer> computer = daoComputer.getComputerDetail(id);
		if (computer.isPresent()) {
			daoComputer.deleteComputer(id);
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
			String fAlse = newDTOComputer.getDiscontinued(); 
			oldDTOComputer.setDiscontinued(fAlse);
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
