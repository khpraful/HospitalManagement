package com.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */

public class HospitalFrontController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		String service = req.getParameter("service");
		out.println("<h1>" + "Service requested : " + service + "</h1>");
		DepartmentDispatcher dispatcher = new DepartmentDispatcher();
		boolean isServiceAvailable = dispatcher.DispatcherRequest(service);
		if (isServiceAvailable) {
			out.println("<h1>" + "Welcome to " + service + " Department"
					+ "</h1>");
		} else {
			out.println("<h1>" + "Sorry, requested service is not available"
					+ "</h1>");
		}

	}
}