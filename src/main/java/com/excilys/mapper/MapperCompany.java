package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;
@Component
public class MapperCompany  implements RowMapper<Company> {
	

	public static Optional<Company> companyMapper(ResultSet CompanyResultSet) throws SQLException {
		long companyid = CompanyResultSet.getLong("id");
		String name = CompanyResultSet.getString("name");
		
		Company company = new Company.CompanyBuilder().setId(companyid).setName(name).build();
		return Optional.ofNullable(company);
	}


	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return companyMapper(rs).get();
	}
}
