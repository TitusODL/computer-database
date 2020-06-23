package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.dto.DTOCompany;
import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperCompany;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@WebServlet(urlPatterns = "/AddComputer")

public class AddComputerServlet extends HttpServlet {
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	

	private static final long serialVersionUID = 1L;
	private static final String ADDCOMPUTER = "/WEB-INF/views/addComputer.jsp";


	DTOComputer computerDTO;
	List<DTOCompany> companysDTO = new ArrayList<DTOCompany>();

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		companysDTO = MapperCompany.listCompanyToDto(companyService.getAllCompanies());

		request.setAttribute("companysDTO", companysDTO);
		request.getRequestDispatcher(ADDCOMPUTER).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName = companyService.getCompanyById(companyId).toString();

		computerDTO = new DTOComputer.DTOComputerBuilder().setDiscontinued(discontinued).setIntroduced(introduced)
				.setName(computerName).setCompany_Id(companyId).setCompany_Name(companyName).build();

		computerService.createComputer(computerDTO);
		response.sendRedirect("Dashboard");

	}

}
