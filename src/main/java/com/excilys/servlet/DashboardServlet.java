package com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		order = request.getParameter("order");
		search = request.getParameter("search");

		if (search != null && (order == null || order.isEmpty())) {
			computerListPage = computerService.getPageByNameSearched(search, page);
			nbRows = computerService.getSearchedComputers(search).size();
		} else if (order != null && (search == null || search.isEmpty())) {
			try {
				direction = Integer.parseInt(request.getParameter("direction")) % 2;
				computerListPage = computerService.getComputersbyOrder(order, direction, page);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			computerListPage = computerService.getPageComputer(page);

		}

		request.setAttribute("order", order);
		request.setAttribute("search", search);
		request.setAttribute("nbRows", nbRows);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageMax", pageMax);
		request.setAttribute("direction", direction);
		request.setAttribute("computerListPage", computerListPage);
		request.getRequestDispatcher(DASHBOARD).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComputerService computerService = new ComputerService();
		String[] computers = request.getParameter("selection").split(",");
		for (String s : computers) {
			computerService.deleteComputer(Integer.parseInt(s));
		}
		response.sendRedirect("Dashboard");
	}

}
