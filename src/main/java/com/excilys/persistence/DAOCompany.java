package com.excilys.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.excilys.model.Company;
import com.excilys.model.Pagination;


@Repository
public class DAOCompany  {

	HikariCPConnect conn;
	public DAOCompany(HikariCPConnect conn) {
		this.conn = conn;
	}
	
	private  List<Company> storeCompaniesFromRequest(ResultSet resSet) throws SQLException{
		List<Company> res = new ArrayList<Company>();
		while(resSet.next()) {
			Company company = new Company.CompanyBuilder().setId(resSet.getLong("company.id")).setName(resSet.getString("company.name")).build();
			res.add(company);
		}
		return res;
	}
	
	
	public List<Company> getCompanies() {
		List<Company> listCompanies = new ArrayList<Company>();
		try (PreparedStatement pstmCompany =conn.getConnection().prepareStatement(SQLRequests.COMPANYLIST.getQuery());){
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
	
	public Optional<Company >getCompanybyId(long id) {

		try (PreparedStatement pstmCompanyDetail =conn.getConnection().prepareStatement(SQLRequests.COMPANYBYID.getQuery());){
			pstmCompanyDetail.setLong(1,id);
			ResultSet resCompany = pstmCompanyDetail.executeQuery();
			while (resCompany.next()) {
				long companyId = resCompany.getLong("company.id");
				String companyName = resCompany.getString("company.name");
				Company hm = new Company(companyId, companyName);
				return Optional.of(hm);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return Optional.ofNullable(null);


	}
	
	public int countAllCompanies() {
		try(PreparedStatement stmt = conn.getConnection().prepareStatement(SQLRequests.COUNTALLCOMPANIESQUERY.getQuery());){
			ResultSet res1 = stmt.executeQuery();
			if(res1.next()) {return res1.getInt("rowcount");}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Company> getPageCompaniesRequest(Pagination page) {
		List<Company> res = new ArrayList<Company>();
		try(PreparedStatement stmt = conn.getConnection().prepareStatement(SQLRequests.GETPAGECOMPANIESQUERY.getQuery());){
			stmt.setInt(1, page.getActualPageNb()*page.getPageSize());
			stmt.setInt(2, page.getPageSize());
			ResultSet res1 = stmt.executeQuery();
			res = storeCompaniesFromRequest(res1);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return res;
	}
	
	public void deleteCompany(long intId) {
		try (PreparedStatement pstmt = conn.getConnection().prepareStatement(SQLRequests.DELETECOMPANY.getQuery())) {
			pstmt.setLong(1, intId);
			pstmt.executeUpdate();
		} catch (SQLException esql) {
			esql.printStackTrace();
		}
	}
}
