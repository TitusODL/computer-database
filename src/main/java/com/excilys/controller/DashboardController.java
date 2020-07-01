package com.excilys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.DTOComputer;
import com.excilys.model.Pagination;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping(value = "/")

public class DashboardController {

	private int nbRows;
	private int pageMax;
	private int dir;
	public ComputerService computerService;

	public DashboardController(ComputerService computerService) {
		this.computerService = computerService;

	}

	@GetMapping(value = "Dashboard")
	public ModelAndView dashboard(@RequestParam(required = false, value = "pageNum") Integer pageNum,
			@RequestParam(required = false, value = "pageTaille") String pageTaille,
			@RequestParam(required = false, value = "search") String search,
			@RequestParam(required = false, value = "order") String order,
			@RequestParam(required = false, value = "direction") String direction) {
		List<DTOComputer> computerListPage = new ArrayList<DTOComputer>();
		ModelAndView modelAndView = new ModelAndView("dashboard");
		nbRows = computerService.countAllComputer();
		Pagination page = new Pagination(nbRows, Integer.parseInt(pageTaille == null ? pageTaille = "10" : pageTaille));
		page.getActualPageNb();

//			if (pageNum != null) {
//				;
//			}
		page.setActualPageNb(pageNum);
		if (pageTaille != null) {
			int pageT = Integer.parseInt(pageTaille);
			page.setPageSize(pageT);
			pageMax = nbRows / pageT;
		}
		computerListPage = paramPage(search, order, direction, page);
		addObjectParam(pageNum, pageTaille, search, order, direction, computerListPage, modelAndView);

		return modelAndView;
	}

	private void addObjectParam(Integer pageNum, String pageTaille, String search, String order, String direction,
			List<DTOComputer> computerListPage, ModelAndView modelAndView) {
		modelAndView.addObject("search", search);
		modelAndView.addObject("order", order);
		modelAndView.addObject("nbRows", nbRows);
		modelAndView.addObject("pageMax", pageMax);
		modelAndView.addObject("direction", dir);
		modelAndView.addObject("pageTaille", pageTaille);
		modelAndView.addObject("computerListPage", computerListPage);
		modelAndView.addObject("pageNum", pageNum);
	}

	private List<DTOComputer> paramPage(String search, String order, String direction, Pagination page) {
		List<DTOComputer> computerListPage;
		if (search != null && (order == null || order.isEmpty())) {
			computerListPage = computerService.getPageByNameSearched(search, page);
			nbRows = computerService.getSearchedComputers(search).size();
		} else if (order != null && (search == null || search.isEmpty())) {
			dir = Integer.parseInt(direction) % 2;
			computerListPage = computerService.getComputersbyOrder(order, dir, page);
		} else {
			computerListPage = computerService.getPageComputer(page);
		}
		return computerListPage;
	}

	@PostMapping(value = "/deleteComputer")
	public ModelAndView deleteComputer(@RequestParam(value = "selection") String selection) {

		ModelAndView modelAndView = new ModelAndView("redirect:/Dashboard");
		List<String> computers = Arrays.asList(selection.split(","));
		for (String s : computers) {
			computerService.deleteComputer(Integer.parseInt(s));
		}

		return modelAndView;
	}

}