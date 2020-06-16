package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.DAOComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;


public class MenuofActions {

	private static volatile MenuofActions instance = null;


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
	ComputerService computerService =new ComputerService();
	CompanyService companyService =new CompanyService();

	public void showDetails() throws SQLException {
		System.out.println("You choose the detail of a computer \n Please enter the ID of the computer :");
		int choice2 = scan.nextInt();
		Optional<Computer> chosenComputer = computerService.getComputerDetail(choice2);
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
		Optional<Computer> optionDetails = computerService.getComputerDetail(updId);
		System.out.println(optionDetails.toString());
		DTOComputer computer = new DTOComputer.DTOComputerBuilder().build();
		computer.setId(String.valueOf(updId));
		System.out.println("Enter new name");
		computer.setName(scan.nextLine());
		System.out.println("Enter new introduction date (yyyy-MM-dd):");
		computer.setIntroduced(scan.nextLine());
		System.out.println("Enter new date of Termination (yyyy-MM-dd):");
		computer.setDiscontinued(scan.nextLine());
		System.out.println("Enter new company ID:");
		Company company = companyService.getCompanyById(scan.nextLine());
		computer.setCompany_id(String.valueOf(company.id));
		computer.setCompany_name(company.name);
		computerService.updateComputer(computer);

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
		Company company = companyService.getCompanyById(scan.nextLine());
		computer.setCompany(company);
		computerService.createComputer(computer);

	}
	public void deleteComputer() throws SQLException {
		System.out.println("Entrer un ID");
		int suppId = scan.nextInt();
		
		computerService.deleteComputer(suppId);
	}
	public void deleteCompany() throws SQLException {
		System.out.println("Entrer un ID");
		int suppId = scan.nextInt();
		companyService.deleteCompany(suppId);
	}


	public void listCompanies() throws SQLException {

		List<Company> listCompanies = new ArrayList<Company>();
		listCompanies = companyService.getAllCompanies();
		System.out.println("You choose the list of companies :");
		for (Company hm : listCompanies) {
			System.out.println(hm.toString());
		}
	}
	public void listComputers() throws SQLException {
		List<Computer> listComputers = new ArrayList<Computer>();
		listComputers = computerService.getComputers();
		System.out.println("You choose the list of computers :");
		for (Computer pc : listComputers) {
			System.out.println(pc.toString());
		}
	}


	public void  displayPage() {
		Pagination page = new Pagination(DAOComputer.getInstance().countAllComputer(), 20);
		List<Computer> pageComputer =new ArrayList<Computer>();
		pageComputer = computerService.getPageComputer(page);
		page.displayPageContent(pageComputer);
				
		boolean quit =true;
		while(quit){
			System.out.println("*------------------------------------------------------------------*");
			System.out.format("page %d / %d | next page : N | prev page : P | quit : Q \n", page.getActualPageNb(), page.getMaxPages());
			String line = this.scan.nextLine();
			switch(line) {
			case "n" :
				page.nextPage();
				pageComputer = computerService.getPageComputer(page);
				page.displayPageContent(pageComputer);
				break;
			case "p" :
				page.PrevPage();
				pageComputer = computerService.getPageComputer(page);
				page.displayPageContent(pageComputer);
				break;
			case "q" :
				quit =false;
				break;
			default :
				System.out.println("Wrong entry, please use a proposed answer");
			}
		}
	}
}

