package com.excilys.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns ="/EditComputer")

public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String EDITCOMPUTER = "/WEB-INF/views/editComputer.jsp";
 
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.getServletContext().getRequestDispatcher(EDITCOMPUTER).forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}