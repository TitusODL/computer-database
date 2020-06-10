package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.DTOCompany;
import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperCompany;
import com.excilys.model.Company;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet (urlPatterns ="/AddComputer")

public class AddComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ADDCOMPUTER = "/WEB-INF/views/addComputer.jsp";

	CompanyService serviceCompany =  new CompanyService();
	ComputerService serviceComputer =  new ComputerService();
	DTOComputer computerDTO;
	List<DTOCompany>companysDTO = new ArrayList<DTOCompany>();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			companysDTO = MapperCompany.listCompanyToDto(serviceCompany.getAllCompanies());
			
				request.setAttribute("companysDTO", companysDTO);
				request.getRequestDispatcher(ADDCOMPUTER).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued  = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		Company company = serviceCompany.getCompanyById(companyId);
		String companyName = company.getName();
		computerDTO = new DTOComputer.DTOComputerBuilder()
				.setDiscontinued(discontinued)
				.setIntroduced(introduced)
				.setName(computerName)
				.setCompany_Id(companyId)
				.setCompany_Name(companyName)
				.build();
	
		serviceComputer.createComputer(computerDTO);
			response.sendRedirect("Dashboard");


	}

}
