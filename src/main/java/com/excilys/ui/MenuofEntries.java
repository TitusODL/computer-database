
package com.excilys.ui;

public enum MenuofEntries {

	LISTCOMPUTERS, 
	LISTCOMPANIES,
	COMPUTERDETAILS,
	UPDATECOMPUTER,
	ADDCOMPUTER, 
	DELETECOMPUTER, 
	PAGINATION,
	DELETECOMPANY,
	QUIT;

	public static MenuofEntries entry(int choice) {
		switch (choice) {

		case 1:
			return LISTCOMPUTERS;
		case 2:
			return LISTCOMPANIES;
		case 3:
			return COMPUTERDETAILS;
		case 4:
			return ADDCOMPUTER;
		case 5:
			return DELETECOMPUTER ;
		case 6:
			return UPDATECOMPUTER;
		case 7:
			return PAGINATION;
		case 8:
			return DELETECOMPANY;
		case 9:
			return QUIT;
		default:
			return QUIT;

		}

	}

}

