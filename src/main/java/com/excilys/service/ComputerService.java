package com.excilys.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperDTOComputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.DAOComputer;

@Service
public class ComputerService {
	DAOComputer daoComputer;
	MapperDTOComputer mapcomp = new MapperDTOComputer();
	private final static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	public ComputerService(DAOComputer daoComputer) {
		this.daoComputer = daoComputer;
	}

	public List<DTOComputer> listComputers() {
		return mapcomp.listComputerToDto(daoComputer.findAll());
	}

	public long countComputers() throws SQLException {

		return daoComputer.count();
	}

	public List<DTOComputer> ComputersPage(Pagination page) {

		Pageable page2 = PageRequest.of(page.getActualPageNb(), page.getPageSize());
		Page<Computer> computersPage = daoComputer.findAll(page2);
		return mapcomp.listComputerToDto(computersPage.getContent());

	}

	public List<DTOComputer> getComputerByName(String search, Pagination page) {
		return mapcomp.listComputerToDto(
				daoComputer.findByNameContaining(search, PageRequest.of(page.getActualPageNb(), page.getPageSize())));
	}

	public long countSearchedComputers(String search) {

		return daoComputer.countByNameContaining(search);
	}

	private static final int ASC = 1;

	public List<DTOComputer> orderBy(Pagination page, long dir, String order) {

		if (order.equals("computer.name")) {
			if (dir == ASC) {
				logger.info("Order by Computer name and ascending");
				Pageable page2 = PageRequest.of(page.getActualPageNb(), page.getPageSize(),
						Sort.by(Sort.Direction.ASC, "name"));
				Page<Computer> computersByName = daoComputer.findAll(page2);
				return mapcomp.listComputerToDto(computersByName.getContent());
				
			} else {
				logger.info("Order by Computer name and descending");
				Pageable page2 = PageRequest.of(page.getActualPageNb(), page.getPageSize(),
						Sort.by(Sort.Direction.DESC, "name"));
				Page<Computer> computersByName = daoComputer.findAll(page2);
				return mapcomp.listComputerToDto(computersByName.getContent());
			}
		} else {
			if (dir == ASC) {
				logger.info("Order by Company name and ascending");
				Pageable page2 = PageRequest.of(page.getActualPageNb(), page.getPageSize(),
						Sort.by(Sort.Direction.ASC, "company.name"));
				Page<Computer> computersByCompany = daoComputer.findAll(page2);
				return mapcomp.listComputerToDto(computersByCompany.getContent());
			} else {
				logger.info("Order by Company name and descending");
				Pageable page2 = PageRequest.of(page.getActualPageNb(), page.getPageSize(),
						Sort.by(Sort.Direction.DESC, "company.name"));
				Page<Computer> computersByCompany = daoComputer.findAll(page2);
				return mapcomp.listComputerToDto(computersByCompany.getContent());
			}
		}

	}

	public Optional<DTOComputer> getComputerById(String id) {

		return Optional.of(mapcomp.computerToDto(daoComputer.findById(Long.parseLong(id)).get()));
	}

	public void addComputer(DTOComputer dtocomputer) {
		boolean name = Validators.verifyName(dtocomputer);
		boolean date = Validators.verifyDate(dtocomputer);
		if (name && date) {
			Computer computer = mapcomp.dtoToComputer(dtocomputer);
			daoComputer.save(computer);
			logger.info("Computer added :" + computer.toString());
		}
	}

	public void updateComputer(DTOComputer dtocomputer) {
		boolean date = Validators.verifyDate(dtocomputer);
		if (date) {
			Computer computer = mapcomp.dtoToComputer(dtocomputer);
			daoComputer.save(computer);
			logger.info("Computer updated :" +computer.toString());
		}
	}

	public void deleteComputer(long id) {
		
		Optional<Computer> computer = daoComputer.findById(id);
		if (computer.isPresent()) {
			daoComputer.deleteById(id);
			logger.info(computer.toString());
			logger.info("Computer deleted!");
		} else {
			logger.info("ID not valid!");
		}
	}

}
