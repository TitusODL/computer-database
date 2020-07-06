package com.excilys.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.DTOCompany;
import com.excilys.dto.DTOComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping(value = "/AddComputer")
public class AddComputerController {
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;

	@GetMapping
	public ModelAndView getComputer() throws SQLException {

		ModelAndView modelAndView = new ModelAndView("addComputer");
		List<DTOCompany> companysDTO = new ArrayList<DTOCompany>();
		companysDTO = companyService.listCompanies();
		modelAndView.addObject("companysDTO", companysDTO);
		return modelAndView;

	}

	@PostMapping
	public ModelAndView addComputer(@RequestParam(required = false, value = "id") String id,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "introduced") String introduced,
			@RequestParam(required = false, value = "discontinued") String discontinued,
			@RequestParam(required = false, value = "company_id") String company_id) throws SQLException {
		DTOComputer dto = new DTOComputer.DTOComputerBuilder().setCompany_Id(company_id).setDiscontinued(discontinued)
				.setIntroduced(introduced).setName(name).build();
		System.out.println(dto);
		computerService.addComputer(dto);
		return new ModelAndView("redirect:/Dashboard");

	}
}
