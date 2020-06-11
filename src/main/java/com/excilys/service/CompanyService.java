package com.excilys.service;

import java.util.List;

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

