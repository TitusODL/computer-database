package com.excilys.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;
import com.excilys.persistence.DAOComputer;

public class CompanyService {
	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

	public Company getCompanyById(String companyID) {
		try {
			long compId = Long.parseLong(companyID);
			Company company = DAOCompany.getInstance().getCompanybyId(compId);
			return company;
		} catch (NumberFormatException NFexception) {
			NFexception.printStackTrace();
		}
		return null;
	}

	public List<Company> getAllCompanies() {
		return DAOCompany.getInstance().getCompanies();
	}

	public void deleteCompany(long id) {
		ComputerService cs = new ComputerService();
		Company company = DAOCompany.getInstance().getCompanybyId(id);
		List<Computer> list = new ArrayList<Computer>();
		list = DAOComputer.getInstance().getComputerIdByCompany(id);
		if (company != null) {
			for (Computer c : list) {
				cs.deleteComputer(c.getId());
			}
			DAOCompany.getInstance().deleteCompany(id);
			logger.info(list.size() + " Computers deleted");
			logger.info(company.toString()+" Company deleted");
		} else {
			logger.info("Aucune compagnie ne correspond à cet ID");
		}
	}
}
