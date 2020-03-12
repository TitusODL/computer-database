package com.excilys.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.mapper.MapperComputer;
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

	private final static String listComputer = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company.id =company_id ;";
	private final static String computerById = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id =company.id WHERE computer.id =?; ";
	private final static String compAdd = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private final static String compDel = "DELETE FROM computer WHERE id = ?";
	private final static String updAdd = "UPDATE computer SET computer.name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE computer.id= ?;";
												//"SELECT pc.name, pc.id, pc.introduced, pc.discontinued, pc.company_id, comp.name FROM computer AS pc LEFT JOIN company AS comp ON comp.id = pc.company_id;";

	public void addComputer(Computer computer) throws SQLException {

		try (PreparedStatement pstmt = MysqlConnect.conn.prepareStatement(compAdd)) {
			pstmt.setString(1, computer.name);
			pstmt.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			pstmt.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			pstmt.setLong(4, computer.company.id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteComputer(int intId) throws SQLException {

		try (PreparedStatement pstmt = MysqlConnect.conn.prepareStatement(compDel)) {
			pstmt.setLong(1, intId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void updateComputer(Computer computer,long intId) throws SQLException {


		try (PreparedStatement pstmt = MysqlConnect.conn.prepareStatement(updAdd)) {
			pstmt.setString(1, computer.name);
			pstmt.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			pstmt.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			pstmt.setLong(4, computer.getCompany().getId());
			pstmt.setLong(5,intId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Optional<Computer> getComputerDetail(long id) throws SQLException {
		Computer comput = null;
		try (PreparedStatement pstmComputerDetail =MysqlConnect.conn.prepareStatement(computerById);){
			pstmComputerDetail.setLong(1,id);
			ResultSet resComputer = pstmComputerDetail.executeQuery();
			if (resComputer.first()) {
				comput = MapperComputer.ComputerDetailMapper(resComputer);
			}

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return Optional.ofNullable(comput);
	}

	public ArrayList<Computer> getComputer() throws SQLException {
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		try (PreparedStatement pstmComputerDetail =MysqlConnect.conn.prepareStatement(listComputer);){
			ResultSet resComputer = pstmComputerDetail.executeQuery();
			while (resComputer.next()) {
				Computer comp = MapperComputer.ComputerDetailMapper(resComputer);
				listComputers.add(comp);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listComputers;
	}
}
