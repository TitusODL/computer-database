package com.excilys.ui;

import java.sql.SQLException;
import java.util.Scanner;
import com.excilys.ui.MenuofEntries;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Choose one of the options below :");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create a computer");
		System.out.println("5 - Delete a computer");
		System.out.println("6 - Update a computer");
		System.out.println("7 - Quit");
	
		Scanner key = new Scanner(System.in);
		int choice = key.nextInt();
		switch(MenuofEntries.entry(choice)) {
		case LISTCOMPUTERS : 
			MenuofActions.getInstance().listComputers();
			break;
		case LISTCOMPANIES: 
			
			MenuofActions.getInstance().listCompanies();
			break;

		case COMPUTERDETAILS :
			
			MenuofActions.getInstance().showDetails();
			break;

		case ADDCOMPUTER : 
			MenuofActions.getInstance().createComputer();
			break;
		
		case DELETECOMPUTER: 
			// code block
			break;
	
		case UPDATECOMPUTER: 
			// code block
			break;
				
		case QUIT:

			break;

		default:
			System.out.println("Game over");
		}

	}


}
