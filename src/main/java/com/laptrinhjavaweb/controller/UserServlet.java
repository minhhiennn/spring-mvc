package com.laptrinhjavaweb.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laptrinhjavaweb.entity.UserDAO;
import com.laptrinhjavaweb.model.User;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String iAction = request.getParameter("action");
		if (iAction != null && !iAction.equals("")) {
			if (iAction.equals("Login")) {
				try {
					login(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		UserDAO dao = new UserDAO();
		String ID_User = request.getParameter("ID_User");
		String password = request.getParameter("password");
		User user = dao.check(ID_User, password);
		if (user != null) {
			session.setAttribute("currentUser", user);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
