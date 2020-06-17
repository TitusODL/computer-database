package com.excilys.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

public class DAOComputer {

	private static volatile DAOComputer instance = null;

	public final static DAOComputer getInstance() {
		if (DAOComputer.instance == null) {
			synchronized (DAOComputer.class) {
				if (DAOComputer.instance == null) {
					DAOComputer.instance = new DAOComputer();
				}
			}
		}
		return DAOComputer.instance;
	}
	
	private static final int ASC = 1;


	public void addComputer(Computer computer) {

		try (PreparedStatement preparedStatementAddComputer = Connecticut.conn.prepareStatement(SQLRequests.ADDCOMPUTER.getQuery())) {
			preparedStatementAddComputer.setString(1, computer.name);
			preparedStatementAddComputer.setDate(2,computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			preparedStatementAddComputer.setDate(3,computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			preparedStatementAddComputer.setLong(4, computer.company.id);
			preparedStatementAddComputer.executeUpdate();

		} catch (SQLException sqlexception) {
			System.out.println(sqlexception.getMessage());
		}
	}

	public void deleteComputer(long intId) {

		try (PreparedStatement pstmt = Connecticut.conn.prepareStatement(SQLRequests.DELETECOMPUTER.getQuery())) {
			pstmt.setLong(1, intId);
			pstmt.executeUpdate();
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
	}

	public void updateComputer(Computer computer) {

		try (PreparedStatement pstmt = Connecticut.conn.prepareStatement(SQLRequests.UPDATECOMPUTER.getQuery())) {
			pstmt.setString(1, computer.name);
			pstmt.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			pstmt.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			pstmt.setLong(4, computer.getCompany().getId());
			pstmt.setLong(5, computer.getId());
			pstmt.executeUpdate();
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
	}

	public Optional<Computer> getComputerDetail(long id) {
		Computer comput = new Computer();
		try (PreparedStatement pstmComputerDetail = Connecticut.conn
				.prepareStatement(SQLRequests.COMPUTERBYID.getQuery());) {
			pstmComputerDetail.setLong(1, id);
			ResultSet resComputer = pstmComputerDetail.executeQuery();
			if (resComputer.next()) {
				comput = MapperComputer.ComputerDetailMapper(resComputer);
			}
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
		return Optional.ofNullable(comput);
	}

	public List<Computer> getComputers() {
		List<Computer> listComputers = new ArrayList<Computer>();
		try (PreparedStatement pstmComputerDetail = Connecticut.conn
				.prepareStatement(SQLRequests.LISTCOMPUTER.getQuery());) {
			ResultSet resComputer = pstmComputerDetail.executeQuery();
			while (resComputer.next()) {
				Computer comp = MapperComputer.ComputerDetailMapper(resComputer);
				listComputers.add(comp);
			}
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
		return listComputers;
	}

	public int countAllComputer() {
		int nbRows = -1;
		try (PreparedStatement stmt = Connecticut.conn.prepareStatement(SQLRequests.COUNTALLCOMPUTERQUERY.getQuery());) {
			ResultSet res1 = stmt.executeQuery();
			if (res1.next()) {
				nbRows = res1.getInt("Rows");
			}
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
		return nbRows;

	}

	public List<Computer> getPageComputersRequest(Pagination page) {
		List<Computer> rescomputer = new ArrayList<Computer>();
		try (PreparedStatement stmt = Connecticut.conn.prepareStatement(SQLRequests.GETPAGECOMPUTERQUERY.getQuery());) {
			stmt.setInt(1, page.getActualPageNb() * page.getPageSize());
			stmt.setInt(2, page.getPageSize());
			ResultSet res1 = stmt.executeQuery();
			while (res1.next()) {
				Computer computer = MapperComputer.ComputerDetailMapper(res1);
				rescomputer.add(computer);
			}
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
		return rescomputer;
	}
	

	public List<Computer> getPageComputerNameSearched(String search, Pagination page) {

		List<Computer> computerlist = new ArrayList<Computer>();
		try (PreparedStatement stmtSelectPage = Connecticut.conn.prepareStatement(SQLRequests.GETPAGECOMPUTERNAMESIZESEARCHED.getQuery());) {
			stmtSelectPage.setString(1, "%" + search + "%");
			stmtSelectPage.setString(2, "%" + search + "%");
			stmtSelectPage.setInt(3, page.getPageSize() * page.getActualPageNb());
			stmtSelectPage.setInt(4, page.getPageSize());

			ResultSet resListcomputer = stmtSelectPage.executeQuery();
			while (resListcomputer.next()) {
				Computer computer = MapperComputer.ComputerDetailMapper(resListcomputer);
				computerlist.add(computer);
			}

		} catch (SQLException esql) {

			esql.printStackTrace();
		}
		return computerlist;

	}

	public List<Computer> getSearchedComputers(String search) {

		List<Computer> computerlist = new ArrayList<Computer>();
		try (PreparedStatement stmtSelectPage = Connecticut.conn.prepareStatement(SQLRequests.GETPAGECOMPUTERNAMESEARCHED.getQuery());) {
			stmtSelectPage.setString(1, "%" + search + "%");
			stmtSelectPage.setString(2, "%" + search + "%");

			ResultSet resListcomputer = stmtSelectPage.executeQuery();
			while (resListcomputer.next()) {
				Computer computer = MapperComputer.ComputerDetailMapper(resListcomputer);
				computerlist.add(computer);
			}

		} catch (SQLException esql) {

			esql.printStackTrace();
		}
		return computerlist;

	}

	public List<Computer> getPageComputerOrderByName(String order, int direction, Pagination page) {

		List<Computer> computerlist = new ArrayList<Computer>();
		PreparedStatement statementSelecPage;
		try {
			if (order.equals("computer.name")) {
				if (direction == ASC) {
					statementSelecPage = Connecticut.conn.prepareStatement(SQLRequests.SORTPAGECOMPUTERASC.getQuery());
				}					
				else {
					statementSelecPage = Connecticut.conn.prepareStatement(SQLRequests.SORTPAGECOMPUTERDESC.getQuery());
				}
			}	
			else {
				if (direction == ASC) {
					statementSelecPage = Connecticut.conn.prepareStatement(SQLRequests.SORTPAGECOMPANYASC.getQuery());
				} 
				else {
					statementSelecPage = Connecticut.conn.prepareStatement(SQLRequests.SORTPAGECOMPANYDESC.getQuery());
				}
			}
			statementSelecPage.setInt(1, page.getActualPageNb() * page.getPageSize());
			statementSelecPage.setInt(2, page.getPageSize());
			ResultSet resListecomputer = statementSelecPage.executeQuery();
			while (resListecomputer.next()) {
				Computer computer = MapperComputer.ComputerDetailMapper(resListecomputer);
				computerlist.add(computer);
			}
			
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
		return computerlist;
	}

	public List<Computer> getComputerIdByCompany(long id) {

		List<Computer> list = new ArrayList<Computer>();
		Computer comput = new Computer();
		try (PreparedStatement pstmComputerDetail = Connecticut.conn.prepareStatement(SQLRequests.COMPUTERBYIDBYCOMPANY.getQuery());) {
			pstmComputerDetail.setLong(1, id);
			ResultSet resComputer = pstmComputerDetail.executeQuery();
			while(resComputer.next()) {
				comput = MapperComputer.ComputerDetailMapper(resComputer);
				list.add(comput);
			}
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
		return list;
	}

}
