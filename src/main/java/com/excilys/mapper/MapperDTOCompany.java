package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.dto.DTOCompany;
import com.excilys.model.Company;

@Component
public class MapperDTOCompany {
	public MapperDTOCompany() {
		// TODO Auto-generated constructor stub
	}
	private static final Logger logger = LoggerFactory.getLogger(MapperDTOCompany.class);
	public Company dtoToCompany(DTOCompany dtoCompany){
		Company company = new Company.CompanyBuilder()
				.setId(Long.parseLong(dtoCompany.getId()))
				.setName(dtoCompany.getName())
				.build();
		return company;
	}

	public DTOCompany companyToDto(Company company) {
		DTOCompany DTOCompany = new DTOCompany.DTOCompanyBuilder()
				.setId(String.valueOf(company.getId()))
				.setName(company.getName()).build();
		return DTOCompany;
	}
	public List<DTOCompany> listCompanyToDto(List<Company> CompanyList){
		logger.info("CompanyList converted!");
		return CompanyList.stream().map(company -> companyToDto(company)).collect(Collectors.toList());
		
	}
	public List<Company> listDtoToCompany(List<DTOCompany> DTOCompanyList){
		logger.info("CompanyDTOList converted!");
		return DTOCompanyList.stream().map(comp -> dtoToCompany(comp)).collect(Collectors.toList());
	}
}