package com.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ClinicFilter
 */
@WebFilter("/ClinicFilter")
public class AuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Inside destroy filter");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		PrintWriter out = res.getWriter();
		String driver = "com.mysql.jdbc.Driver";
		String dburl = "jdbc:mysql://localhost:3306/test";
		String dbuser = "mysql";
		String dbpass = "mysql";

		try {
			Class.forName(driver);
			// com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			// DriverManager.registerDriver(driver);
			Connection conn = DriverManager
					.getConnection(dburl, dbuser, dbpass);
			Statement stmt = conn.createStatement();
			String sql;
			String id = req.getParameter("id");
			sql = "SELECT username, role FROM registration WHERE id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("username");
				String role = rs.getString("role");
				if (role.equals("Patient")){
				out.println("<h1>" + "Id " + id + " is registered as patient under the name of " + name + " and has access to hospital services." + "</h1>");
				chain.doFilter(req, res);
				}
			} else {
				out.println("<h1>" + "Id " + id + " is not registered"
						+ "</h1>");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Inside init filter");
	}

}
