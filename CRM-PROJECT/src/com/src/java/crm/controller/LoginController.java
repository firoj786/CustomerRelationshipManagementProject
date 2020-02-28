package com.src.java.crm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.java.crm.dto.User;
import com.src.java.crm.services.CrmUserService;

@WebServlet(urlPatterns = "/validateLogin")
public class LoginController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nextPage = "";
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		String loginId = req.getParameter("username");
		String password = req.getParameter("password");

		CrmUserService gPSUserService = new CrmUserService();
		User user = null;
		try {
			user = gPSUserService.validateCRMUser(loginId, password, roleId);
			HttpSession session = req.getSession(true);
			session.setAttribute("UserDetails", user);
			nextPage = "index.jsp";
		} catch (RuntimeException rt) {
			rt.printStackTrace();
			req.setAttribute("eMsg", rt.getMessage());
			req.setAttribute("loginId", loginId);
			switch (roleId) {
			case 1:
				nextPage = "adminLogin";
				break;
			case 2:
				nextPage = "customerLogin";
				break;
			// TODO
			}
		}

		RequestDispatcher rd = req.getRequestDispatcher(nextPage);
		rd.forward(req, resp);
	}
}
