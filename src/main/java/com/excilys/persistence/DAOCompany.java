package com.excilys.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.model.Pagination;


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
	private final static String countAllCompaniesQuery = "SELECT COUNT(id) AS rowcount FROM company";
	private final static String getPageCompaniesQuery = "SELECT id, name FROM company ORDER BY id LIMIT ?, ?";
	
	private  ArrayList<Company> storeCompaniesFromRequest(ResultSet resSet) throws SQLException{
		ArrayList<Company> res = new ArrayList<Company>();
		while(resSet.next()) {
			Company company = new Company.CompanyBuilder().setId(resSet.getLong("company.id")).setName(resSet.getString("company.name")).build();
			res.add(company);
		}
		return res;
	}
	
	
	public ArrayList<Company> getCompanies() {
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		try (PreparedStatement pstmCompany =Connecticut.conn.prepareStatement(listCompany);){
			ResultSet resCompany = pstmCompany.executeQuery();
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
	
	public Company getCompanybyId(long id) {

		try (PreparedStatement pstmCompanyDetail =Connecticut.conn.prepareStatement(companyById);){
			pstmCompanyDetail.setLong(1,id);
			ResultSet resCompany = pstmCompanyDetail.executeQuery();
			while (resCompany.next()) {
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
	
	public int countAllCompanies() {
		try(PreparedStatement stmt = Connecticut.conn.prepareStatement(countAllCompaniesQuery);){
			ResultSet res1 = stmt.executeQuery();
			if(res1.next()) {return res1.getInt("rowcount");}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Company> getPageCompaniesRequest(Pagination page) {
		ArrayList<Company> res = new ArrayList<Company>();
		try(PreparedStatement stmt = Connecticut.conn.prepareStatement(getPageCompaniesQuery);){
			stmt.setInt(1, page.getActualPageNb()*page.getPageSize());
			stmt.setInt(2, page.getPageSize());
			ResultSet res1 = stmt.executeQuery();
			res = storeCompaniesFromRequest(res1);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return res;
	}
	public void deleteCompany(int intId) {
		try (PreparedStatement pstmt = Connecticut.conn.prepareStatement(SQLRequests.DELETECOMPANY.getQuery())) {
			pstmt.setLong(1, intId);
			pstmt.executeUpdate();
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
	}
}
