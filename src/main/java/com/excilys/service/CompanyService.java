package com.excilys.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.persistence.DAOCompany;

public class CompanyService {

	
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
}

