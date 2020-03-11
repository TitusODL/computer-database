package com.excilys.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class DAOComputer {

	private static volatile DAOComputer instance = null;
	MysqlConnect msc = MysqlConnect.getDbCon();

	
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



	public void addComputer(Computer computer) throws SQLException {

		String compAdd = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);";

		try (PreparedStatement pstmt = MysqlConnect.conn.prepareStatement(compAdd)) {
			pstmt.setString(1, computer.name);
			pstmt.setDate(2, (Date) computer.introduced);
			pstmt.setDate(3, (Date) computer.discontinued);
			pstmt.setLong(4, computer.company.id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private final static String listComputer = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company.id";
	private final static String computerById = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id WHERE computer.id =?; ";
	public Optional<Computer> getComputerDetail(long id) throws SQLException {

		try (PreparedStatement pstmComputerDetail =MysqlConnect.conn.prepareStatement(computerById);){
			pstmComputerDetail.setLong(1,id);
			ResultSet resComputer = pstmComputerDetail.executeQuery();
			if (resComputer.first()) {
				long computerId = resComputer.getLong("computer.id");
				String computerName = resComputer.getString("computer.name");
				Date introduced = resComputer.getDate("introduced");
				Date discontinued = resComputer.getDate("discontinued");
				long companyId = resComputer.getLong("company_id");
				Company company = new Company();
				company = DAOCompany.getInstance().getCompanybyId(companyId);
				Computer pc = new Computer(computerId, computerName, introduced, discontinued, company);
				return Optional.of(pc);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();


	}

	public ArrayList<Computer> getComputer() throws SQLException {
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		try (PreparedStatement pstmComputerDetail =MysqlConnect.conn.prepareStatement(listComputer);){
			ResultSet resComputer = pstmComputerDetail.executeQuery();
			while (resComputer.next()) {
				long computerId = resComputer.getLong("computer.id");
				String computerName = resComputer.getString("computer.name");
				Date introduced = resComputer.getDate("introduced");
				Date discontinued = resComputer.getDate("discontinued");
				long companyId = resComputer.getLong("company_id");
				Company company = new Company();
				company = DAOCompany.getInstance().getCompanybyId(companyId);
				Computer pc = new Computer(computerId, computerName, introduced, discontinued,company);
				listComputers.add(pc);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listComputers;

	}
}
