package com.excilys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.DTOCompany;
import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperDTOCompany;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping(value = "/")
public class AddComputerController {
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	
	@GetMapping(value = "AddComputer")
	public ModelAndView addcomputer()
	{

		ModelAndView modelAndView = new ModelAndView("addComputer");
		List<DTOCompany> companysDTO = new ArrayList<DTOCompany>();
		companysDTO = MapperDTOCompany.listCompanyToDto(companyService.getAllCompanies());
		modelAndView.addObject("companysDTO", companysDTO);
		return modelAndView;

	}
	@PostMapping(value="/AddComputer")
	public ModelAndView addComputer(DTOComputer dtoComputer) {
	
			ModelAndView modelAndView = new ModelAndView("redirect:/Dashboard");
			computerService.createComputer(dtoComputer);
			return modelAndView;
		
		}



	}

