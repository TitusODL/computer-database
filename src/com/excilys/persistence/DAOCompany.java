package com.excilys.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;


public class DAOCompany  {

	private static volatile DAOCompany instance = null;

	
	public final static DAOCompany getInstance() {
		if (DAOCompany.instance == null) {
			synchronized (DAOCompany.class) {
				if (DAOCompany.instance == null) {
					DAOCompany.instance = new DAOCompany();
				}
			}
		}
		return DAOCompany.instance;
	}

	private final static String listCompany = "SELECT company.id, company.name FROM company;";
	private final static String companyById = "SELECT  company.id, company.name FROM company WHERE company.id =?; ";
	
	public ArrayList<Company> getCompany() throws SQLException{
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		try (PreparedStatement pstmCompany =MysqlConnect.conn.prepareStatement(listCompany);){
			ResultSet resCompany = pstmCompany.executeQuery();
			System.out.println("yoo");
			while (resCompany.next()) {
				long companyId = resCompany.getLong("company.id");
				String companyName = resCompany.getString("company.name");
				Company hm = new Company(companyId, companyName);
				listCompanies.add(hm);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listCompanies;

	}
	public Company getCompanybyId(long id) throws SQLException {

		try (PreparedStatement pstmCompanyDetail =MysqlConnect.conn.prepareStatement(companyById);){
			pstmCompanyDetail.setLong(1,id);
			ResultSet resCompany = pstmCompanyDetail.executeQuery();
			while (resCompany.first()) {
				long companyId = resCompany.getLong("company.id");
				String companyName = resCompany.getString("company.name");
				Company hm = new Company(companyId, companyName);
				return hm;
			}
		
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;


	}

}
