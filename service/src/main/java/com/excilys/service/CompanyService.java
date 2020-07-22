package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.dto.DTOCompany;
import com.excilys.mapper.MapperDTOCompany;
import com.excilys.model.Company;
import com.excilys.persistence.DAOCompany;

@Service
public class CompanyService {
	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	private DAOCompany daoCompany;
	MapperDTOCompany mapcompany = new MapperDTOCompany();
	public CompanyService(DAOCompany daoCompany) {
		this.daoCompany = daoCompany;
	}

	public Optional<DTOCompany> getCompanyById(long id) {
		try {
			Optional<Company> company = daoCompany.findById(id);
			if (!company.isPresent()) {
				logger.info("No company corresponds to this ID!");
			}
			return Optional.of(mapcompany.companyToDto(daoCompany.findById(null).get()));
		
		} catch (NumberFormatException NFexception) {
			NFexception.printStackTrace();
		} 
		return Optional.empty();
	}
		
	

	public List<DTOCompany> listCompanies() {

		return mapcompany.listCompanyToDto((List<Company>) daoCompany.findAll());
	}

	public void deleteCompany(long id){

		daoCompany.deleteById(id);
	}
}

