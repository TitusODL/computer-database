package com.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;

@Service
public class CompanyService {
	
	DAOCompany daoCompany;
	ComputerService serviceComputer;
	
public CompanyService(DAOCompany daoCompany, ComputerService serviceComputer) {
		this.daoCompany = daoCompany;
		this.serviceComputer = serviceComputer;
	}

	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

	public Optional<Company> getCompanyById(String companyID) {
		try {
			long compId = Long.parseLong(companyID);
			Optional<Company> company = daoCompany.getCompanybyId(compId);
			return company;
			
		} catch (NumberFormatException NFexception) {
			NFexception.printStackTrace();
		}
		
		return Optional.ofNullable(null);
	}

	public List<Company> getAllCompanies() {
		return daoCompany.getCompanies();
	}

	public void deleteCompany(long id) {
		
	//	ComputerService cs = new ComputerService();
		Optional<Company> company = daoCompany.getCompanybyId(id);
		List<Computer> list = new ArrayList<Computer>();
		list = serviceComputer.getComputerIdByCompany(id);
		if (company != null) {
			for (Computer c : list) {
				serviceComputer.deleteComputer(c.getId());
			}
			daoCompany.deleteCompany(id);
			logger.info(list.size() + " Computers deleted");
			logger.info(company.toString()+" Company deleted");
		} else {
			logger.info("Aucune compagnie ne correspond à cet ID");
		}
	}
}
