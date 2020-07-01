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
import com.excilys.mapper.MapperDTOComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping(value = "/")

public class EditComputerController {
	@Autowired
	ComputerService serviceComputer;
	
	@Autowired
	CompanyService serviceCompany;
	DTOComputer computerDTO;
	List<DTOCompany> listCompanyDTO = new ArrayList<DTOCompany>();
	
	@GetMapping(value = "EditComputer")
	public ModelAndView editcomputer(
			@RequestParam(required = false, value = "computerId") String computerId) {
			ModelAndView modelAndView = new ModelAndView("editComputer");
			listCompanyDTO = MapperDTOCompany.listCompanyToDto(serviceCompany.getAllCompanies());
			computerDTO = MapperDTOComputer.computerToDto(serviceComputer.getComputerById(computerId).get());
			modelAndView.addObject("listCompanyDTO", listCompanyDTO);
			modelAndView.addObject("computerDTO",computerDTO);
			return modelAndView;
	}

	@PostMapping(value="/EditComputer")
	public ModelAndView deleteComputer(DTOComputer dtoComputer) {
		ModelAndView modelAndView = new ModelAndView("redirect:/Dashboard");
		serviceComputer.updateComputer(dtoComputer);
		return modelAndView;
	}

}
