package com.src.java.crm.controller.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import model.User;
import com.src.java.crm.dto.Customer;
import com.src.java.crm.dto.User;
import com.src.java.crm.exceptions.SessionExpiredException;
import com.src.java.crm.services.CrmCustomerService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/updateCustomerProfile")
public class UpdateProfileController extends HttpServlet {
	/**
	 * Get customer based on Id from session to display customer data into JSP
	 */
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		Customer cust = null;
		String nextPage = null;
		try {
			HttpSession session = rq.getSession(false);
			User user = (User) session.getAttribute("UserDetails");

			CrmCustomerService gPSCustomerService = new CrmCustomerService();
			cust = gPSCustomerService.fetchProfileDetails(user.getUserId());

			nextPage = "updateProfile";
			rq.setAttribute("CustomerDetails", cust);
		} catch (RuntimeException rt) {
			if(rt instanceof SessionExpiredException) {
				nextPage = "customerLogin";
			} else {
				nextPage = "index.jsp";
			}
			rt.printStackTrace();
			rq.setAttribute("eMsg", rt.getMessage());
			
		}
		RequestDispatcher rd = rq.getRequestDispatcher(nextPage);
		rd.forward(rq, rs);
	}

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String nextPage = null;
		Customer cust = new Customer();
		cust.setCompany(rq.getParameter("cumpanyname"));
		cust.setPrContact(Integer.parseInt(rq.getParameter("prcontact")));
		cust.setSecContact(Integer.parseInt(rq.getParameter("sccontact")));
		cust.setAddress(rq.getParameter("address"));
		cust.setCountry(rq.getParameter("country"));
		cust.setCity(rq.getParameter("city"));
		cust.setPincode(rq.getParameter("pincode"));

		HttpSession session = rq.getSession(false);
		User user = (User) session.getAttribute("UserDetails");
		cust.setUserId(user.getUserId());
		
		try {
			CrmCustomerService gPSCustomerService = new CrmCustomerService();
			gPSCustomerService.updateProfile(cust);

			rq.setAttribute("msg", "Profile Updated Successfully");
			nextPage = "index.jsp";
		} catch (RuntimeException rt) {
			rt.printStackTrace();
			rq.setAttribute("eMsg", rt.getMessage());
			rq.setAttribute("CustomerDetails", cust);
			nextPage = "updateProfile";
		}
		RequestDispatcher rd = rq.getRequestDispatcher(nextPage);
		rd.forward(rq, rs);
	}

	// Fetch User detail from HttpSession -> Create CustomerService Object and
	// call respective method

	// Bind Customer to request attribute
	// RD to updateProfile.jsp

	// Define doPost method
	// Populate Customer using req.getParameter
	// Fetch User from HttpSession then User Id
	// Set user Id into Customer pojo
	// Create CustomerService Object and call respective method
	// Bind Success Message for Profile update
	// RD to Home Page
}
