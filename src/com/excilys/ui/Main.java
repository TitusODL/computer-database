package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;
import com.excilys.persistence.DAOComputer;
import com.excilys.persistence.MysqlConnect;
import com.excilys.ui.MenuofEntries;

public class Main {

	public static void main(String[] args) throws SQLException {

		
		System.out.println("Choose one of the options below :");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create a computer");
		System.out.println("5 - Update a computer");
		System.out.println("6 - Delete a computer");
		System.out.println("7 - Quit");
		DAOCompany shcompany = new DAOCompany();
		DAOComputer shcomputer = new DAOComputer();
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		listComputers = shcomputer.getComputer();
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		listCompanies = shcompany.getCompany();

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
			
			MenuofActions.getInstance().showDetails();
			break;

		case ADDCOMPUTER : 
			Company company = new Company();
			Computer computer = new Computer(275,"test",null,null,company);
			DAOComputer.getInstance().addComputer(computer);
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
