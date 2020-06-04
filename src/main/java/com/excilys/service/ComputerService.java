package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.DAOComputer;

public class ComputerService {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	public Optional<Computer> getComputerDetail(int id) throws SQLException {
		return DAOComputer.getInstance().getComputerDetail(id);
	}
	public ArrayList<Computer> getComputers() throws SQLException {
		return DAOComputer.getInstance().getComputers();
	}

	public void createComputer(Computer computer) throws SQLException {
		DAOComputer.getInstance().addComputer(computer);
		
	}

	public void updateComputer(Computer computer , long intId) throws SQLException {

		Optional<Computer> compudp = DAOComputer.getInstance().getComputerDetail(intId);
		if(compudp.isPresent()) {
			DAOComputer.getInstance().updateComputer(computer,  intId);
			logger.info("Computer updated");
		}
		else {
			logger.info("ID not matching with a computer");
		}
	}


	public void deleteComputer(int id) throws SQLException {
		Optional<Computer> computer = DAOComputer.getInstance().getComputerDetail(id);
		if(computer.isPresent()) {
			DAOComputer.getInstance().deleteComputer(id);
			logger.info(computer.get().toString());
			logger.info("Computer deleted");
		}
		else {
			logger.info("ID not matching with a computer");
		}
	}

	public int countAllComputer() {
		return DAOComputer.getInstance().countAllComputer();
	}

	public ArrayList<Computer> getPageComputer(Pagination page){
		return DAOComputer.getInstance().getPageComputersRequest(page);
	}
}


