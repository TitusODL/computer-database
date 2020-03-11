
package com.excilys.ui;

public enum MenuofEntries {

	LISTCOMPUTERS, LISTCOMPANIES, COMPUTERDETAILS, UPDATECOMPUTER, ADDCOMPUTER, DELETECOMPUTER, QUIT, EXIT;

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
			return QUIT;
		default:
			return EXIT;

		}

	}

}

