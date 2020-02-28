package com.src.java.crm.controller.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.java.crm.dto.Comment;
import com.src.java.crm.dto.Customer;
import com.src.java.crm.dto.DesTable;
import com.src.java.crm.dto.User;
import com.src.java.crm.services.CrmEmployeeService;
import com.src.java.crm.utilities.DefectStatusConstant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/viewDefectList.do")
public class ViewAllDefectController extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String type = (rq.getParameter("type") != null ? rq.getParameter("type") : "open");
		String order = (rq.getParameter("order") != null ? rq.getParameter("order") : "customer");
		String by = (rq.getParameter("by") != null ? rq.getParameter("by") : "asc");
		String onlyForCustomer = rq.getParameter("onlyForCustomer");
		
		int orderVal = by.equals("asc") ? 1 : -1;

		List<DesTable> defectList = null;

		CrmEmployeeService gpsEmployeeService = new CrmEmployeeService();

		switch (type) {
		case "open":
			defectList = gpsEmployeeService.getAllOpenDefecList();
			break;
		case "all":
			defectList = gpsEmployeeService.getAllDefecList();
			break;
		case "closed":
			defectList = gpsEmployeeService.getAllClosedDefecList();
			break;
		case "priority":
			List<DesTable> tempList = gpsEmployeeService.getAllOpenDefecList();
			ServletContext context = rq.getServletContext();
			List<Customer> top10CustomerList = (List<Customer>) context.getAttribute("Top10CustomerList");

			defectList = new ArrayList<DesTable>();
			for (DesTable dt : tempList) {
				if (isTop10CustomerDefect(dt, top10CustomerList)) {
					defectList.add(dt);
				}
			}
			break;
		}
		
		List<DesTable> customerDefectList = null;
		if(onlyForCustomer != null && onlyForCustomer.trim().equals("true")) {
			customerDefectList = new ArrayList<DesTable>();
			HttpSession session = rq.getSession(false);
			User user = (User) session.getAttribute("UserDetails");
			for(DesTable dt : defectList) {
				if(dt.getUserId().compareTo(user.getUserId()) == 0) {
					customerDefectList.add(dt);
				}
			}
		}
		
		if(customerDefectList != null) {
			defectList = customerDefectList;
		}

		switch (order) {
		case "customer":
			Collections.sort(defectList, new DesTableComparatorByCustomerName(orderVal));
			break;
		}

		// RequestDispacher with proper message
		RequestDispatcher rd = rq.getRequestDispatcher("viewDefectList.jsp");
		rq.setAttribute("type", type);
		rq.setAttribute("DefectList", defectList);
		rd.forward(rq, rs);

	}

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		HttpSession sess = rq.getSession();
		User user = (User) sess.getAttribute("UserDetails");

		DesTable dt = new DesTable();
		dt.setAssignedTo(Long.parseLong(rq.getParameter("assignedTo")));
		dt.setId(Integer.parseInt(rq.getParameter("defectId")));
		dt.setComment(populateComment(rq, dt, user));
		dt.setFixedInVersionId(Float.parseFloat(rq.getParameter("fixedinversion")));
		dt.setSevId(Integer.parseInt(rq.getParameter("severity")));
		dt.setStatus(DefectStatusConstant.valueOf(rq.getParameter("status")));

		if (dt.getStatus().name().equals(DefectStatusConstant.CLOSED.name())) {
			dt.setResolutionDate(new Date());
			dt.setResolution(rq.getParameter("resolution"));
		} else if (dt.getStatus().name().equals(DefectStatusConstant.SUBMITTED.name())) {
			dt.setStatus(DefectStatusConstant.IN_PROGRESS);
		}
		
		CrmEmployeeService gpsEmployeeService = new CrmEmployeeService();
		gpsEmployeeService.updateDefect(dt);
		
		RequestDispatcher rd = rq.getRequestDispatcher("defectDetail");
		rq.setAttribute("defectId", dt.getId());
		rq.setAttribute("sMsg", "Defect updated Successfully...");
		rd.forward(rq, rs);
	}

	private Comment populateComment(HttpServletRequest rq, DesTable dt, User user) {
		Comment comment = new Comment();
		comment.setAssignedTo(dt.getAssignedTo());
		comment.setCommentedBy(user.getUserId());
		comment.setCommentedOn(new Date());
		comment.setDefectId(dt.getId());
		comment.setDescription(rq.getParameter("resolution"));

		return comment;
	}

	private boolean isTop10CustomerDefect(DesTable dt, List<Customer> top10CustomerList) {
		for (Customer cust : top10CustomerList) {
			if (dt.getUserId().compareTo(cust.getUserId()) == 0)
				return true;
		}
		return false;
	}
}
