package com.src.java.crm.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.java.crm.controller.employee.DesTableComparatorByCustomerName;
import com.src.java.crm.dto.DesTable;
import com.src.java.crm.dto.SearchCriteria;
import com.src.java.crm.services.CrmEmployeeService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/searchDefectList")
public class SearchDefectController extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String keyword = rq.getParameter("keyword");
		
		SearchCriteria sc = new SearchCriteria();
		if(keyword != null && keyword.trim().length() > 0){
			sc.setKeyList(Arrays.asList(keyword.split(",")));
		}
		sc.setSeverityId(Integer.parseInt(rq.getParameter("severity")));
		
		List<DesTable> defectList = null;

		CrmEmployeeService gpsEmployeeService = new CrmEmployeeService();
		defectList = gpsEmployeeService.searchAllDefectByCriteria(sc);
		
		String order = (rq.getParameter("order") != null ? rq.getParameter("order") : "customer");
		String by = (rq.getParameter("by") != null ? rq.getParameter("by") : "asc");
		int orderVal = by.equals("asc") ? 1 : -1;
		
		switch (order) {
		case "customer":
			Collections.sort(defectList, new DesTableComparatorByCustomerName(orderVal));
			break;
		}

		// RequestDispacher with proper message
		RequestDispatcher rd = rq.getRequestDispatcher("viewDefectList.jsp");
		rq.setAttribute("type", "");
		rq.setAttribute("DefectList", defectList);
		rd.forward(rq, rs);
	}
}
