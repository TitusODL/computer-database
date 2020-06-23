package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.dto.DTOCompany;
import com.excilys.model.Company;
import com.excilys.persistence.DAOCompany;
@Component
public class MapperCompany {
	
	DAOCompany daoCompany;
	public MapperCompany(DAOCompany daoCompany) {
	this.daoCompany = daoCompany;
}

	public Company dtoToCompany(DTOCompany DTOCompany){
		long companyId = Long.parseLong(DTOCompany.getId());
		Company optCompany = daoCompany.getCompanybyId(companyId).get();

		Company company = new Company.CompanyBuilder()
				.setId(companyId)
				.setName(optCompany.getName())
				.build();

		return company;
	}

	public static DTOCompany companyToDto(Company company) {
		DTOCompany DTOCompany = new DTOCompany.DTOCompanyBuilder()
				.setId(String.valueOf(company.getId()))
				.setName(company.getName()).build();

		return DTOCompany;
	}
	public static List<DTOCompany> listCompanyToDto(List<Company> CompanyList){
		List<DTOCompany> DTOCompanyList = new ArrayList<DTOCompany>();
		
		for(int i=0; i<CompanyList.size(); i++) {
			DTOCompanyList.add(companyToDto(CompanyList.get(i)));
		}
		
		return DTOCompanyList;
	}
	public List<Company> listDtoToCompany(List<DTOCompany> DTOCompanyList){
		List<Company> CompanyList = new ArrayList<Company>();
		
		for(int i=0; i<CompanyList.size(); i++) {
			CompanyList.add(dtoToCompany(DTOCompanyList.get(i)));
		}
		return CompanyList;
	}
}
