package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.persistence.DAOCompany;

public class CompanyService {
	public ArrayList<Company> getCompany() throws SQLException {
		return DAOCompany.getInstance().getCompanies();
	}

	public Company getCompanybyId(long id) throws SQLException {
		return DAOCompany.getInstance().getCompanybyId(id);
	}
}

