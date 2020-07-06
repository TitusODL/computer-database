package com.excilys.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuCLI {
	
	public static <T> void displayArrayList(List<T> list) {
		for(T o : list) {
			System.out.println(o.toString());
		}
	}
	MenuofActions menuofActions;

	public MenuCLI(MenuofActions menuofActions) {
		this.menuofActions = menuofActions;
	}
	public void CliMenu() throws SQLException {
		System.out.println("***********************************************************************************************************************");
		System.out.println("Choose one of the options below :");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create a computer");
		System.out.println("5 - Delete a computer");
		System.out.println("6 - Update a computer");
		System.out.println("7 - Pagination");
		System.out.println("8 - Delete a company");
		System.out.println("9 - Quit");
		System.out.println("***********************************************************************************************************************");

		try (Scanner key = new Scanner(System.in)) {
			int choice = key.nextInt();

			switch(MenuofEntries.entry(choice)) {
			//H2ConnectTest.getDbCon();
			case LISTCOMPUTERS : 
				menuofActions.listComputers();
				break;
			case LISTCOMPANIES: 
				menuofActions.listCompanies();
				break;

			case COMPUTERDETAILS :
				menuofActions.showDetails();
				break;

			case ADDCOMPUTER : 
				menuofActions.createComputer();
				break;

			case DELETECOMPUTER: 
				menuofActions.deleteComputer();
				break;

			case UPDATECOMPUTER: 
				menuofActions.updateComputer();
				break;
				
			case PAGINATION: 
				menuofActions.displayPage();
				break;
			
			case DELETECOMPANY: 
				menuofActions.deleteCompany();
				break;

			case QUIT:
				System.out.println("Game over");
				break;

			default:
				break;

			}
		}
	}
}
