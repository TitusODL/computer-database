package com.excilys.controller;

import java.sql.SQLException;
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
@RequestMapping(value = "/EditComputer")

public class EditComputerController {
	@Autowired
	ComputerService serviceComputer;

	@Autowired
	CompanyService serviceCompany;

	@GetMapping
	public ModelAndView editcomputer(@RequestParam(required = false, value = "computerId") String computerId)
			throws SQLException {
		ModelAndView modelAndView = new ModelAndView("editComputer");

		List<DTOCompany> companies = serviceCompany.listCompanies();
		DTOComputer computer = serviceComputer.getComputerById(computerId).get();
		System.out.println(computer);

		modelAndView.addObject("listCompanyDTO", companies);
		modelAndView.addObject("computerDTO", computer);
		return modelAndView;
	}

	@PostMapping
	public ModelAndView updateComputer(DTOComputer dtoComputer) {
		serviceComputer.updateComputer(dtoComputer);
		return new ModelAndView("redirect:/Dashboard");
	}

}
