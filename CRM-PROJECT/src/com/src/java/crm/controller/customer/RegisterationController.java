package com.src.java.crm.controller.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.java.crm.dto.Customer;
import com.src.java.crm.dto.User;
import com.src.java.crm.services.CrmCustomerService;
import com.src.java.crm.utilities.CrmUtility;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/registerCustomerProfile")
public class RegisterationController extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// Create new user object and respective fetch all data from form
		// Create new customer object and respective fetch all data from form
		String nextPage = "";
		User user = populateUser(rq);
		Customer cust = populateCD(rq);
		cust.setUserId(user.getUserId());

		try {
			// Create object of GPSUserService and call
			CrmCustomerService gPSCustomerService = new CrmCustomerService();
			// registerCustomer(user, customer)
			gPSCustomerService.registerCustomer(user, cust);
			rq.setAttribute("msg", "Save Customer Successfully");
			
			String msgText = "Hi<br>Welcome to GPSSim product<br><br>Your LoginId [" + user.getLoginId() + "] and Password [" + user.getPassword() + "]";
			CrmUtility.sendMail(user.getEmailId(), "Congrats for your Registration at GSPSim product", msgText);
		} catch (RuntimeException rt) { // exception handle
			rt.printStackTrace();
			rq.setAttribute("eMsg", rt.getMessage());
			nextPage = "register";
			rq.setAttribute("CustomerDetail", cust);

		}
		// RequestDispacher with proper message
		RequestDispatcher rd = rq.getRequestDispatcher(nextPage);
		rq.setAttribute("UserDetail", user);
		rd.forward(rq, rs);

	}

	private Customer populateCD(HttpServletRequest rq) {
		Customer cust = new Customer();
		cust.setCompany(rq.getParameter("cumpanyname"));
		cust.setPrContact(Integer.parseInt(rq.getParameter("prcontact")));
		try {
			cust.setSecContact(Integer.parseInt(rq.getParameter("sccontact")));
		} catch (NumberFormatException e) {
		}
		cust.setAddress(rq.getParameter("address"));
		cust.setCountry(rq.getParameter("country"));
		cust.setCity(rq.getParameter("city"));
		cust.setPincode(rq.getParameter("pincode"));
		return cust;
	}

	private User populateUser(HttpServletRequest rq) {
		User user = new User();
		user.setUserId(System.currentTimeMillis()); 
		user.setLoginId(rq.getParameter("loginId"));
		user.setEmailId(rq.getParameter("emailId"));
		user.setPassword(rq.getParameter("password"));
		user.setFname(rq.getParameter("fname"));
		user.setLname(rq.getParameter("lname"));
		user.setRoleId(2);

		return user;
	}
}
