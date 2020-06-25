package com.excilys.persistence;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

@Repository
public class DAOComputer {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	private DAOCompany daoCompany;
	private MapperComputer computerMapper = new MapperComputer(daoCompany);

	public DAOComputer(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final int ASC = 1;

	public void addComputer(Computer computer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", computer.getName())
				.addValue("2", computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null)
				.addValue("3", computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null)
				.addValue("4", computer.getCompany().getId());
		namedParameterJdbcTemplate.update(SQLRequests.ADDCOMPUTER.getQuery(), namedParameters);
	}

	public void deleteComputer(long intId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", intId);
		namedParameterJdbcTemplate.update(SQLRequests.DELETECOMPUTER.getQuery(), namedParameters);
	}

	public void updateComputer(Computer computer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", computer.getName())
				.addValue("2", computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null)
				.addValue("3", computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null)
				.addValue("4", computer.getCompany().getId()).addValue("5", computer.getId());

		namedParameterJdbcTemplate.update(SQLRequests.UPDATECOMPUTER.getQuery(), namedParameters);
	}

	public Optional<Computer> getComputerDetail(long id) {
		Computer computer = new Computer();
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", id);
		computer = namedParameterJdbcTemplate.queryForObject(SQLRequests.COMPUTERBYID.getQuery(), namedParameters,
				this.computerMapper);
		return Optional.of(computer);
	}

	public List<Computer> getComputers() {
		return this.namedParameterJdbcTemplate.query(SQLRequests.LISTCOMPUTER.getQuery(), this.computerMapper);
	}

	public int countAllComputer() {
		return jdbcTemplate.queryForObject(SQLRequests.COUNTALLCOMPUTERQUERY.getQuery(), Integer.class);
	}

	public List<Computer> getPageComputersRequest(Pagination page) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("1", page.getActualPageNb() * page.getPageSize()).addValue("2", page.getPageSize());
		return namedParameterJdbcTemplate.query(SQLRequests.GETPAGECOMPUTERQUERY.getQuery(), namedParameters,
				this.computerMapper);
	}

	public List<Computer> getPageComputerNameSearched(String search, Pagination page) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", "%" + search + "%")
				.addValue("2", "%" + search + "%").addValue("3", page.getPageSize() * page.getActualPageNb())
				.addValue("4", page.getPageSize());
		return namedParameterJdbcTemplate.query(SQLRequests.GETPAGECOMPUTERNAMESIZESEARCHED.getQuery(), namedParameters,
				this.computerMapper);
	}

	public List<Computer> getSearchedComputers(String search) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", "%" + search + "%").addValue("2",
				"%" + search + "%");
		return namedParameterJdbcTemplate.query(SQLRequests.GETPAGECOMPUTERNAMESEARCHED.getQuery(), namedParameters,
				this.computerMapper);
	}

	public List<Computer> getPageComputerOrderByName(String order, int direction, Pagination page) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("1", page.getActualPageNb() * page.getPageSize()).addValue("2", page.getPageSize());
		if (order.equals("computer.name")) {
			if (direction == ASC) {
				return namedParameterJdbcTemplate.query(SQLRequests.SORTPAGECOMPUTERASC.getQuery(), namedParameters,
						this.computerMapper);
			} else {
				return namedParameterJdbcTemplate.query(SQLRequests.SORTPAGECOMPUTERDESC.getQuery(), namedParameters,
						this.computerMapper);
			}
		} else {
			if (direction == ASC) {
				return namedParameterJdbcTemplate.query(SQLRequests.SORTPAGECOMPANYASC.getQuery(), namedParameters,
						this.computerMapper);
			} else {
				return namedParameterJdbcTemplate.query(SQLRequests.SORTPAGECOMPANYDESC.getQuery(), namedParameters,
						this.computerMapper);
			}
		}

	}

	public List<Computer> getComputerIdByCompany(long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("1", id);
		return namedParameterJdbcTemplate.query(SQLRequests.COMPUTERBYIDBYCOMPANY.getQuery(), namedParameters,
				this.computerMapper);
	}

}
