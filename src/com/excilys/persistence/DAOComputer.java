package com.excilys.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.mapper.MapperComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

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
	
//	private List<Computer> getpagecomputer(ResultSet resSet) throws SQLException{
//		List<Computer> comp = new ArrayList<Computer>();
//		while(resSet.next()) {
//			
//			Company company = new Company.CompanyBuilder().setId(company.id).setName(company.name).build();
//			Computer computer = new Computer.Builder().setId(computer.id).setName(computer.name).setIntroducedDate(computer.introduced)
//					 .setDiscontinuedDate(computer.discontinued).setCompany(company).build();
////			Company company = new Company.CompanyBuilder().setId(resSet.getLong("company.id")).setName(resSet.getString("company.name")).build();
////			Computer computer = new Computer.Builder().setId(resSet.getLong("computer.id").setName(resSet.getString("computer.name").setIntroducedDate(resSet.getTimestamp("computer.introduced")
////					 .setDiscontinuedDate(resSet.getTimestamp("computer.discontinued").build();
//			
//			res.add(computer);
//			}
//		return res;
//	
//
//	}
	
//	private Optional<Computer> storeOneOrNoneComputerFromReq(ResultSet resSet) throws SQLException{
//		if(resSet.next()) {
//			Company company = new Company.CompanyBuilder().setId(resSet.getLong("company.id")).setName(resSet.getString("company.name")).build();
//			Computer computer = new Computer.Builder().setId(resSet.getLong("computer.id").setName(resSet.getString("computer.name").setIntroducedDate(resSet.getTimestamp("computer.introduced")
//					 .setDiscontinuedDate(resSet.getTimestamp("computer.discontinued").build();
//			return Optional.of(computer);
//		}
//		else {
//			return Optional.empty();
//		}
//	}

	private final static String listComputer = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company.id =company_id ;";
	private final static String computerById = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id =company.id WHERE computer.id =?; ";
	private final static String compAdd = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private final static String compDel = "DELETE FROM computer WHERE id = ?";
	private final static String updAdd = "UPDATE computer SET computer.name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE computer.id= ?;";
	private final static String countAllComputerQuery = "SELECT COUNT(id) FROM computer";
	private final static String getPageComputersQuery = "SELECT computer.name, computer.id, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT ?, ?";
												
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

	public ArrayList<Computer> getComputers() throws SQLException {
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
	
	public static int countAllComputer() {
		try(PreparedStatement stmt = MysqlConnect.conn.prepareStatement(countAllComputerQuery);){
			ResultSet res1 = stmt.executeQuery();
			if(res1.next()) {return res1.getInt("COUNT(id)");}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Computer> getPageComputersRequest(Pagination page) {
		ArrayList<Computer> res = new ArrayList<Computer>();
		try(PreparedStatement stmt = MysqlConnect.conn.prepareStatement(getPageComputersQuery);){
			stmt.setInt(1, page.getActualPageNb()*page.getPageSize());
			stmt.setInt(2, page.getPageSize());
			ResultSet res1 = stmt.executeQuery();
			while (res1.next()) {
			Computer computer = MapperComputer.ComputerDetailMapper(res1);
			res.add(computer);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return res;
	}


}
