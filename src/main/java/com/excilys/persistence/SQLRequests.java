package com.excilys.persistence;

public enum SQLRequests {
///In DAOComputer////
	LISTCOMPUTER("SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company.id =company_id ;"),
	
	COMPUTERBYID( "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id =company.id WHERE computer.id =:1; "),
	
	ADDCOMPUTER ( "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(:1,:2,:3,:4);"),
	
	DELETECOMPUTER( "DELETE FROM computer WHERE id = :1"),
	
	DELETECOMPANY( "DELETE FROM company WHERE id = :1"),
	
	UPDATECOMPUTER( "UPDATE computer SET computer.name = :1, introduced = :2, discontinued = :3, company_id = :4 WHERE computer.id= :5;"),
	
	COUNTALLCOMPUTERQUERY( "SELECT COUNT(id) as \"Rows\" FROM computer"),
	
	GETPAGECOMPUTERQUERY("SELECT computer.name, computer.id, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT :1, :2;"),
	
	GETPAGECOMPUTERNAMESIZESEARCHED ("SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE :1 OR company.name LIKE :2 LIMIT :3,:4;"),
	
	GETPAGECOMPUTERNAMESEARCHED( "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE :1 OR company.name LIKE :2;"),
	
	SORTPAGECOMPUTERASC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name ASC LIMIT :1,:2;"),
	
	SORTPAGECOMPANYASC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name  FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name ASC LIMIT :1,:2;"),
	
	SORTPAGECOMPUTERDESC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.name DESC LIMIT :1,:2;"),
	
	SORTPAGECOMPANYDESC("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY company.name DESC LIMIT :1,:2;"),

	COMPUTERBYIDBYCOMPANY( "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id =company.id WHERE company.id =:1; "),
	///In DAOCompany///
	COMPANYLIST("SELECT company.id, company.name FROM company;"),
	
	COMPANYBYID("SELECT  company.id, company.name FROM company WHERE company.id =:1;"),
	
	COUNTALLCOMPANIESQUERY("SELECT COUNT(id) AS rowcount FROM company"),
	
	GETPAGECOMPANIESQUERY("SELECT id, name FROM company ORDER BY id LIMIT :1, :2");
	
	private String query;

	SQLRequests(String query) {
		this.query = query;
	}

	public String getQuery() {
		return this.query;
	}

}
