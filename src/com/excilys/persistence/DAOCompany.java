package com.excilys.persistence;

import java.sql.*;
import java.util.ArrayList;

import com.excilys.model.Company;


public class DAOCompany  {

	private static volatile DAOCompany instance = null;

	public final static DAOCompany getInstance() {
		if (DAOCompany.instance == null) {
			synchronized (DAOCompany.class) {
				if (DAOCompany.instance == null) {
					DAOCompany.instance = new DAOCompany();
					DAOCompany.instance = new DAOCompany();
				}
			}
		}
		return DAOCompany.instance;
	}
	
	MysqlConnect msc = MysqlConnect.getDbCon();	
	private final static String requete = "SELECT id, name FROM company";
	
	public ArrayList<Company> getCompany() throws SQLException{
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		ResultSet listCompany =msc.query(requete);
	while (listCompany.next()) {
		long id = listCompany.getLong("id");
		String name  = listCompany.getString( "name"); 
		Company hm = new Company(id,name);
		listCompanies.add(hm);
	}
	return listCompanies;
	
	}
	
	}
//	MysqlConnect msc = MysqlConnect.getDbCon();	
//	public void getCompany() throws SQLException{
//		
//	String requete = "SELECT company.id, company.name FROM company";
//	ResultSet listCompanies =msc.query(requete);
//	while (listCompanies.next()) {
//		System.out.println(listCompanies.getString("id") + " | " + listCompanies.getString( "name"));
//	}
		
	
	

//
//	try{
//		stmt = connect.preparedStatement();
//		résultats = stmt.executeQuery(requete);
//	} 
//	catch (SQLException e) {
//		System.out.println(e.getMessage());
//	}
