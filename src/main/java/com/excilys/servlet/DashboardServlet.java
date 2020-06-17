package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.Connecticut;
import com.excilys.service.ComputerService;

@WebServlet(urlPatterns = "/Dashboard")

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	private int pageNum;
	private int nbRows = 0;
	private int pageTaille = 10;
	private int pageMax;
	private int direction = 1;

	List<Computer> computerListPage = new ArrayList<Computer>();
	Pagination page = new Pagination(nbRows, pageTaille);
	ComputerService computerService = new ComputerService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connecticut.getDbCon();
		nbRows = computerService.countAllComputer();
		pageNum = page.getActualPageNb();
		String search = null;
		String order = "";

		if (request.getParameter("pageNum") != null) {
			String s = request.getParameter("pageNum");
			pageNum = Integer.parseInt(s);
			page.setActualPageNb(pageNum);

		}

		if (request.getParameter("pageTaille") != null) {
			String s = request.getParameter("pageTaille");
			pageTaille = Integer.parseInt(s);
			page.setPageSize(pageTaille);
			pageMax = nbRows / pageTaille;
		}
		
		search = request.getParameter("search");
		order = request.getParameter("order");
		computerListPage = getPageFromParam(request, search, order);
		setAttribut(request, search, order,nbRows,pageNum,pageMax,direction,computerListPage);
		request.getRequestDispatcher(DASHBOARD).forward(request, response);
	}

	
	private void setAttribut(HttpServletRequest request, String search, String order, int nbRows, int pageNum, int pageMax, int direction, List<Computer> computerListPage) {
		request.setAttribute("order", order);
		request.setAttribute("search", search);
		request.setAttribute("nbRows", nbRows);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageMax", pageMax);
		request.setAttribute("direction", direction);
		request.setAttribute("computerListPage", computerListPage);
	}

	private List<Computer>  getPageFromParam(HttpServletRequest request, String search, String order) {
		
		if (search != null && (order == null || order.isEmpty())) 
		{
	
			computerListPage = computerService.getPageByNameSearched(search, page);
			nbRows = computerService.getSearchedComputers(search).size();
		} 
		else if (order != null && (search == null || search.isEmpty())) 
		{
			
			direction = Integer.parseInt(request.getParameter("direction")) % 2;
			computerListPage = computerService.getComputersbyOrder(order, direction, page);
		} 
		else
		{
			computerListPage = computerService.getPageComputer(page);
		}
		return computerListPage;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		ComputerService computerService = new ComputerService();
		String[] computers = request.getParameter("selection").split(",");
		for (String s : computers) {
			computerService.deleteComputer(Integer.parseInt(s));
		}
		response.sendRedirect("Dashboard");
	}

}
