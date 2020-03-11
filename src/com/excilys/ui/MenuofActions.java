package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.mapper.MapperComputer;
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
		System.out.println("You choose the detail of a computer \n Please enter the ID of the computer :");
		int choice2 = scan.nextInt();
		Optional<Computer> chosenComputer = daocomputer.getComputerDetail(choice2);
		if ( chosenComputer.isPresent() ) {
			System.out.println(chosenComputer.get().toString());
		}
		else {
			System.out.println("Id not found!");
		}
	}
	public void updateComputer() throws SQLException {
		System.out.println("You choose to update a computer :");
		System.out.println("Enter ID to modify :");
        int updId = scan.nextInt();
        scan.nextLine();
        Optional<Computer> optionDetails = DAOComputer.getInstance().getComputerDetail(updId);
        System.out.println(optionDetails.toString());
        Computer computer = new Computer();
		System.out.println("Enter new name");
		computer.setName(scan.nextLine());
		System.out.println("Enter new introduction date (yyyy-MM-dd):");
		computer.setIntroduced((MapperComputer.transString(scan.nextLine())));
		System.out.println("Enter new date of Termination (yyyy-MM-dd):");
		computer.setDiscontinued((MapperComputer.transString(scan.nextLine())));
		System.out.println("Enter new company ID:");
		Company company = daocompany.getCompanybyId(scan.nextInt());
		computer.setCompany(company);
		daocomputer.updateComputer(computer,updId);
		
	}
	
	public void createComputer() throws SQLException {
		Computer computer = new Computer();
		System.out.println("Enter name");
		computer.setName(scan.nextLine());
		System.out.println("Introduction date (yyyy-MM-dd):");
		computer.setIntroduced((MapperComputer.transString(scan.nextLine())));
		System.out.println("Date of Termination (yyyy-MM-dd)");
		computer.setDiscontinued((MapperComputer.transString(scan.nextLine())));
		System.out.println("The ID of the Company");
		Company company = daocompany.getCompanybyId(scan.nextInt());
		computer.setCompany(company);
		daocomputer.addComputer(computer);

	}
    public void deleteComputer() throws SQLException {
        System.out.println("Entrer un ID");
         int suppId = scan.nextInt();
         daocomputer.deleteComputer(suppId);
    }


	public void listCompanies() throws SQLException {
		DAOCompany shcompany = new DAOCompany();
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		listCompanies = shcompany.getCompany();
		System.out.println("You choose the list of companies :");
		for (Company hm : listCompanies) {
			System.out.println(hm.toString());
		}
	}
	public void listComputers() throws SQLException {
		DAOComputer shcomputer = new DAOComputer();
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		listComputers = shcomputer.getComputer();
		System.out.println("You choose the list of computers :");
		for (Computer pc : listComputers) {
			System.out.println(pc.toString());
		}
	}
}

