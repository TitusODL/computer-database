package com.excilys.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.model.Computer;

public class DAOComputer {

	private static volatile DAOComputer instance = null;

	public final static DAOComputer getInstance() {
		if (DAOComputer.instance == null) {
			synchronized (DAOComputer.class) {
				if (DAOComputer.instance == null) {
					DAOComputer.instance = new DAOComputer();
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
			pstmt.setLong(4, computer.companyId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// try {
		// Statement st = MysqlConnect.conn.createStatement();
		// st.executeUpdate("INSERT INTO
		// computer(id,name,introduced,discontinued,company_id) VALUES(574,Lenovo Legion
		// Y530,2017-05-01,null,36)");
		//
		// MysqlConnect.conn.close();
		// }
		// catch (Exception e) {
		// System.out.println("Got an exception! ");
		// System.out.println(e.getMessage());
		// }

	}

	MysqlConnect msc = MysqlConnect.getDbCon();
	private final static String ListComputer = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company.id;";
	private final static String displayComputer = ListComputer + " WHERE id = ?;";

	public Optional<Computer> getComputerDetail(int key) throws SQLException {
		ResultSet resComputerDetail = msc.query(displayComputer);
		while (resComputerDetail.next()) {
			long id = resComputerDetail.getLong("id");
			String name = resComputerDetail.getString("name");
			Date introduced = resComputerDetail.getDate("introduced");
			Date discontinued = resComputerDetail.getDate("discontinued");
			Long company_id = resComputerDetail.getLong("company_id");
			String companyName = resComputerDetail.getString("company.name");
			Computer pc = new Computer(id, name, introduced, discontinued, company_id,companyName);
			return Optional.of(pc);
		}
		return Optional.empty();
	}

	public ArrayList<Computer> getComputer() throws SQLException {
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		ResultSet reslistComputer = msc.query(ListComputer);

		while (reslistComputer.next()) {
			long computerId = reslistComputer.getLong("computer.id");
			String computerName = reslistComputer.getString("computer.name");
			Date introduced = reslistComputer.getDate("introduced");
			Date discontinued = reslistComputer.getDate("discontinued");
			Long companyId = reslistComputer.getLong("company_id");
			String companyName = reslistComputer.getString("company.name");
			Computer pc = new Computer(computerId, computerName, introduced, discontinued, companyId,companyName);
			listComputers.add(pc);
		}
		return listComputers;

	}
}
