package com.excilys.controller;

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
	DTOComputer computerDTO;
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
	public ModelAndView addComputer(
			@RequestParam(value = "computerName") String computerName,
			@RequestParam(value = "introduced") String introduced,
			@RequestParam(value = "discontinued") String discontinued,
			@RequestParam(value = "companyId") String companyId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/Dashboard");
		String companyName = companyService.getCompanyById(companyId).toString();
		computerDTO = new DTOComputer.DTOComputerBuilder().setDiscontinued(discontinued).setIntroduced(introduced)
				.setName(computerName).setCompany_Id(companyId).setCompany_Name(companyName).build();
		computerService.createComputer(computerDTO);
		return modelAndView;



	}
}
