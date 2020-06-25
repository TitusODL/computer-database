package com.excilys.servlet;

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
import com.excilys.mapper.MapperCompany;
import com.excilys.mapper.MapperComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping(value = "/")

public class EditComputerServlet {
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
			listCompanyDTO = MapperCompany.listCompanyToDto(serviceCompany.getAllCompanies());
			computerDTO = MapperComputer.computerToDto(serviceComputer.getComputerById(computerId).get());
			modelAndView.addObject("listCompanyDTO", listCompanyDTO);
			modelAndView.addObject("computerDTO",computerDTO);
			return modelAndView;
	}

	@PostMapping(value="EditComputer")
	public ModelAndView deleteComputer(
			@RequestParam(value = "computerId") String computerId,
			@RequestParam(value = "computerName") String computerName,
			@RequestParam(value = "introduced") String introduced,
			@RequestParam(value = "discontinued") String discontinued,
			@RequestParam(value = "companyId") String companyId) {

		ModelAndView modelAndView = new ModelAndView("redirect:/Dashboard");
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
		return modelAndView;
	}

}
