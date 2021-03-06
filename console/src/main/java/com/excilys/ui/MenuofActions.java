package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.dto.DTOCompany;
import com.excilys.dto.DTOComputer;
import com.excilys.model.Pagination;
import com.excilys.persistence.DAOComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Component
public class MenuofActions {

	public Scanner scan = new Scanner(System.in);
	
	ComputerService computerService;
	CompanyService companyService;
	public MenuofActions(ComputerService computerService, CompanyService companyService, DAOComputer daoComputer) {
		this.computerService = computerService;
		this.companyService = companyService;
	}



	public void showDetails() throws SQLException {
		System.out.println("You choose the detail of a computer \n Please enter the ID of the computer :");
		String choice2 = scan.nextLine();
		Optional<DTOComputer> chosenComputer = computerService.getComputerById(choice2);
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
		String updId = scan.nextLine();
		scan.nextLine();
		Optional<DTOComputer> optionDetails = computerService.getComputerById(updId);
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
		DTOCompany company = companyService.getCompanyById(scan.nextLong()).get();
		computer.setCompany_id(String.valueOf(company.getId()));
		computer.setCompany_name(company.getName());
		computerService.updateComputer(computer);

	}

	public void createComputer() throws SQLException {
		DTOComputer dtocomputer = new DTOComputer.DTOComputerBuilder().build();

		System.out.println("Enter name");
		dtocomputer.setName(scan.nextLine());
		System.out.println("Introduction date (yyyy-MM-dd):");
		dtocomputer.setIntroduced((scan.nextLine()));
		System.out.println("Date of Termination (yyyy-MM-dd)");
		dtocomputer.setDiscontinued((scan.nextLine()));
		System.out.println("The ID of the Company");
		dtocomputer.setCompany_id(dtocomputer.getCompany_id());
		dtocomputer.setCompany_name(dtocomputer.getCompany_name());
		computerService.addComputer(dtocomputer);

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

		List<DTOCompany> listCompanies = new ArrayList<DTOCompany>();
		listCompanies = companyService.listCompanies();
		System.out.println("You choose the list of companies :");
		for (DTOCompany hm : listCompanies) {
			System.out.println(hm.toString());
		}
	}
	public void listComputers() throws SQLException {
		List<DTOComputer> listComputers = new ArrayList<DTOComputer>();
		listComputers = computerService.listComputers();
		System.out.println("You choose the list of computers :");
		for (DTOComputer pc : listComputers) {
			System.out.println(pc.toString());
		}
	}


	public void  displayPage() throws SQLException {
		Pagination page = new Pagination((int) computerService.countComputers(), 20);
		List<DTOComputer> pageComputer =new ArrayList<DTOComputer>();
		pageComputer = computerService.ComputersPage(page);
		page.displayPageContent(pageComputer);
				
		boolean quit =true;
		while(quit){
			System.out.println("*------------------------------------------------------------------*");
			System.out.format("page %d / %d | next page : N | prev page : P | quit : Q \n", page.getActualPageNb(), page.getMaxPages());
			String line = this.scan.nextLine();
			switch(line) {
			case "n" :
				page.nextPage();
				pageComputer = computerService.ComputersPage(page);
				page.displayPageContent(pageComputer);
				break;
			case "p" :
				page.PrevPage();
				pageComputer = computerService.ComputersPage(page);
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

