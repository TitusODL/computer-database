package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;
import com.excilys.persistence.DAOComputer;
import com.excilys.ui.MenuofEntries;

public class Main {

	public static void main(String[] args) throws SQLException {

		

		DAOCompany shcompany = new DAOCompany();
		DAOComputer shcomputer = new DAOComputer();
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		listComputers = shcomputer.getComputer();
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		listCompanies = shcompany.getCompany();
		System.out.println("Choose one of the options below :");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create a computer");
		System.out.println("5 - Update a computer");
		System.out.println("6 - Delete a computer");
		System.out.println("7 - Quit");
		Scanner key = new Scanner(System.in);
		int choice = key.nextInt();
		switch(MenuofEntries.entry(choice)) {
		case LISTCOMPUTERS : 
			System.out.println("You choose the list of computers :");
			for (Computer pc : listComputers) {
				System.out.println(pc.toString());
			}
			break;
		case LISTCOMPANIES: 

			System.out.println("You choose the list of companies :");
			for (Company hm : listCompanies) {
				System.out.println(hm.toString());
			}
			break;

		case COMPUTERDETAILS :
			System.out.println("You choose the detail of a computer \n Please enter the id of the computer :");
			Scanner key2 =new Scanner(System.in);
			int choice2 = key2.nextInt();
			Optional<Computer> chosenComputer = shcomputer.getComputerDetail(choice2);
			
			if ( chosenComputer.isPresent() ) {
				System.out.println(chosenComputer.get().toString());
			}

			else {
				System.out.println("Id not found!");
			}
			break;

		case ADDCOMPUTER : 
			//Computer computer = new Computer(575,"test",null,null,5);
			//DAOComputer.getInstance().addComputer(computer);
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
