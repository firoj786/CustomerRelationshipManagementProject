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

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/changePassword")
public class ChangePasswordController extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String nextPage = null;
		String eMsg = null;
		
		HttpSession session = rq.getSession(false);
		User user = (User) session.getAttribute("UserDetails");
		
		String oPass = rq.getParameter("oldPassword");
		String nPass = rq.getParameter("newPassword");
		String cPass = rq.getParameter("cpassword");
		
		eMsg = nPass.equals(cPass) ? null : "New Password & Confirm Password mismatched";
		eMsg = oPass.equals(user.getPassword()) ? null : "Old Password & New Password mismatched";
		
		if(eMsg != null) {
			nextPage = "changePassword.do";
			rq.setAttribute("eMsg", eMsg);
		} else {
			try {
				CrmUserService gPSUserService = new CrmUserService();
				gPSUserService.updatePassword(nPass, user.getUserId());
				
				user.setPassword(nPass);
				session.setAttribute("UserDetails", user);

				rq.setAttribute("msg", "Password Updated Successfully");
				nextPage = "index.jsp";
			} catch (RuntimeException rt) {
				rt.printStackTrace();
				rq.setAttribute("eMsg", rt.getMessage());
				nextPage = "changePassword.do";
			}
		}
		RequestDispatcher rd = rq.getRequestDispatcher(nextPage);
		rd.forward(rq, rs);
	}
}
