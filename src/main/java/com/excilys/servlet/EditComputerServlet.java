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
import com.excilys.mapper.MapperComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@WebServlet (urlPatterns ="/EditComputer")

public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String EDITCOMPUTER = "/WEB-INF/views/editComputer.jsp";
	
	@Autowired
	ComputerService serviceComputer;
	
	@Autowired
	CompanyService serviceCompany;
	DTOComputer computerDTO;

	
	List<DTOCompany> listCompanyDTO = new ArrayList<DTOCompany>();
	
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			listCompanyDTO = MapperCompany.listCompanyToDto(serviceCompany.getAllCompanies());
			
			String computerId = request.getParameter("computerId");
			computerDTO = MapperComputer.computerToDto(serviceComputer.getComputerById(computerId).get());
			request.setAttribute("listCompanyDTO", listCompanyDTO);
			request.setAttribute("computerDTO", computerDTO);
			request.getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String computerId = request.getParameter("computerId");
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued  = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		String companyName = serviceCompany.getCompanyById(companyId).toString();
		
		computerDTO = new DTOComputer.DTOComputerBuilder()
				.setId(computerId)
				.setDiscontinued(discontinued)
				.setIntroduced(introduced)
				.setName(computerName)
				.setCompany_Id(companyId)
				.setCompany_Name(companyName)
				.build();
			serviceComputer.updateComputer(computerDTO);
			response.sendRedirect("Dashboard");
	}



}
