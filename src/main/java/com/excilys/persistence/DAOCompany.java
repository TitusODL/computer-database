package com.excilys.persistence;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.MapperCompany;
import com.excilys.model.Company;
import com.excilys.model.Pagination;

@Repository
public class DAOCompany {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	private MapperCompany mapperCompany = new MapperCompany();

	public DAOCompany(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Company> getCompanies() {
		return this.namedParameterJdbcTemplate.query(SQLRequests.COMPANYLIST.getQuery(), this.mapperCompany);
	}

	public Optional<Company> getCompanybyId(long id) {

		Company company = new Company();
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", id);
		company = namedParameterJdbcTemplate.queryForObject(SQLRequests.COMPANYBYID.getQuery(), namedParameters,
				this.mapperCompany);
		return Optional.of(company);
	}

	public int countAllCompanies() {
		return jdbcTemplate.queryForObject(SQLRequests.COUNTALLCOMPANIESQUERY.getQuery(), Integer.class);
	}

	public List<Company> getPageCompaniesRequest(Pagination page) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("1", page.getActualPageNb() * page.getPageSize()).addValue("2", page.getPageSize());
		return namedParameterJdbcTemplate.query(SQLRequests.GETPAGECOMPANIESQUERY.getQuery(), namedParameters,
				this.mapperCompany);
	}

	public void deleteCompany(long intId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", intId);
		namedParameterJdbcTemplate.update(SQLRequests.DELETECOMPANY.getQuery(), namedParameters);

	}
}
