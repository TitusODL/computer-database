package com.excilys.ui;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOCompany;
import com.excilys.persistence.DAOComputer;
import com.excilys.persistence.MysqlConnect;


public class MenuofActions {

	private static volatile MenuofActions instance = null;
	MysqlConnect msc = MysqlConnect.getDbCon();


	public final static MenuofActions getInstance() {
		if (MenuofActions.instance == null) {
			synchronized (DAOComputer.class) {
				if (MenuofActions.instance == null) {
					MenuofActions.instance = new MenuofActions();
				}
			}
		}
		return MenuofActions.instance;
	}


	public Scanner scan = new Scanner(System.in);
	DAOComputer daocomputer = new DAOComputer();
	DAOCompany daocompany = new DAOCompany();


	public void showDetails() throws SQLException {
		System.out.println("You choose the detail of a computer \n Please enter the id of the computer :");
		int choice2 = scan.nextInt();
		Optional<Computer> chosenComputer = daocomputer.getComputerDetail(choice2);

		if ( chosenComputer.isPresent() ) {
			System.out.println(chosenComputer.get().toString());
		}

		else {
			System.out.println("Id not found!");
		}
	}

	public void createComputer() throws SQLException {
		Computer computer = new Computer();
		System.out.println("Enter name");
		computer.setName(scan.nextLine());
		System.out.println("Introduction date (yyyy-MM-dd):");
		computer.setIntroduced((scan.nextLine()));
		System.out.println("Date of Termination (yyyy-MM-dd)");
		computer.setDiscontinued((scan.nextLine()));
		System.out.println("The ID of the Company");
		Company company = daocompany.getCompanybyId(scan.nextInt());
		computer.setCompany(company.getId());
		daocomputer.addComputer(computer);

	}


}

