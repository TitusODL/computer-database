package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.DAOComputer;

public class ComputerService {
	
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
		DAOComputer.getInstance().updateComputer(computer,  intId);
	}
	
	public void deleteComputer(int id) throws SQLException {
		DAOComputer.getInstance().deleteComputer(id);
	}
	
	public int countAllComputer() {
		return DAOComputer.getInstance().countAllComputer();
	}
	
	public ArrayList<Computer> getPageComputer(Pagination page){
		return DAOComputer.getInstance().getPageComputersRequest(page);
	}
}


