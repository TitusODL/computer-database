package com.excilys.persistence;

import java.sql.*;

public class DAOCompany  {
	MysqlConnect msc = MysqlConnect.getDbCon();	
	String requete = "SELECT company.id, company.name FROM company";
	
	public ResultSet getCompany(String requete) throws SQLException{
	ResultSet listeEntreprise =msc.query(requete);
	System.out.println(requete);
	return listeEntreprise;
		
	}
	

//
//	try{
//		stmt = connect.preparedStatement();
//		résultats = stmt.executeQuery(requete);
//	} 
//	catch (SQLException e) {
//		System.out.println(e.getMessage());
//	}
}
