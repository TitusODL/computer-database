package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.persistence.DAOCompany;

public class CompanyService {
	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	public Company getCompanyById(String companyID){
		try {
			long compId = Long.parseLong(companyID);
			Company company = DAOCompany.getInstance().getCompanybyId(compId);
			return company;
		} catch (NumberFormatException NFexception) {
			NFexception.printStackTrace();
		} 
		return null;
	}

	public List<Company> getAllCompanies(){
		return DAOCompany.getInstance().getCompanies();
	}
	
	public void deleteCompany(int id) {
		Company company = DAOCompany.getInstance().getCompanybyId(id);
		if (company != null) {
			DAOCompany.getInstance().deleteCompany(id);
			logger.info(company.toString());
			logger.info("Company deleted");
		} else {
			logger.info("Aucune compagnie ne correspond à cet ID");
		}
	}
}

