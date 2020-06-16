package com.excilys.persistence;

public enum SQLRequests {

	LISTCOMPUTER("SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company.id =company_id ;"),
	
	COMPUTERBYID( "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id =company.id WHERE computer.id =?; "),
	
	ADDCOMPUTER ( "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);"),
	
	DELETECOMPUTER( "DELETE FROM computer WHERE id = ?"),
	
	DELETECOMPANY( "DELETE FROM company WHERE id = ?"),
	
	UPDATECOMPUTER( "UPDATE computer SET computer.name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE computer.id= ?;"),
	
	COUNTALLCOMPUTERQUERY( "SELECT COUNT(id) as \"Rows\" FROM computer"),
	
	GETPAGECOMPUTERQUERY("SELECT computer.name, computer.id, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT ?, ?;"),
	
	GETPAGECOMPUTERORDERBYNAME( "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id ORDER BY  LIMIT ?,?;"),
	
	GETPAGECOMPUTERNAMESIZESEARCHED ("SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? LIMIT ?,?;"),
	
	GETPAGECOMPUTERNAMESEARCHED( "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?;"),
	
	SORTPAGECOMPUTERASC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name ASC LIMIT ?,?;"),
	
	SORTPAGECOMPANYASC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name  FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name ASC LIMIT ?,?;"),
	
	SORTPAGECOMPUTERDESC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name DESC LIMIT ?,?;"),
	
	SORTPAGECOMPANYDESC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name DESC LIMIT ?,?;"),

	COMPUTERBYIDBYCOMPANY( "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id =company.id WHERE company.id =?; ");
	
	private String query;

	SQLRequests(String query) {
		this.query = query;
	}

	public String getQuery() {
		return this.query;
	}

}
