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

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nextPage = "";
		
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("UserDetails");
		
		if(user != null) {
			switch(user.getRoleId()) {
			case 1:
				nextPage = "adminLogin";
				break;
			case 2:
				nextPage = "customerLogin";
				break;
			case 3:
				nextPage = "employeeLogin";
				break;
			case 4:
				nextPage = "salesLogin";
			}
			req.setAttribute("sMsg", "Successfully Logout");
			session.setAttribute("UserDetails", null);
			session.invalidate();
		} else {
			req.setAttribute("eMsg", "Session Expired");
		}		
		
		RequestDispatcher rd = req.getRequestDispatcher(nextPage);
		rd.forward(req, resp);
	}
}
