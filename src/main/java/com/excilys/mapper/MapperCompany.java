package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.dto.DTOCompany;
import com.excilys.model.Company;

import com.excilys.persistence.DAOCompany;

public class MapperCompany {

	public static Company dtoToCompany(DTOCompany DTOCompany){
		long companyId = Long.parseLong(DTOCompany.id);
		Company optCompany = DAOCompany.getInstance().getCompanybyId(companyId);

		Company company = new Company.CompanyBuilder()
				.setId(companyId)
				.setName(optCompany.name)
				.build();

		return company;
	}

	public static DTOCompany companyToDto(Company company) {
		DTOCompany DTOCompany = new DTOCompany.DTOCompanyBuilder()
				.setId(String.valueOf(company.id))
				.setName(company.name).build();

		return DTOCompany;
	}
	public static List<DTOCompany> listCompanyToDto(List<Company> CompanyList){
		List<DTOCompany> DTOCompanyList = new ArrayList<DTOCompany>();
		
		for(int i=0; i<CompanyList.size(); i++) {
			DTOCompanyList.add(companyToDto(CompanyList.get(i)));
		}
		
		return DTOCompanyList;
	}
	public static List<Company> listDtoToCompany(List<DTOCompany> DTOCompanyList){
		List<Company> CompanyList = new ArrayList<Company>();
		
		for(int i=0; i<CompanyList.size(); i++) {
			CompanyList.add(dtoToCompany(DTOCompanyList.get(i)));
		}
		return CompanyList;
	}
}
