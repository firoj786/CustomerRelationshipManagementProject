package com.src.java.crm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.java.crm.dto.DesTable;
import com.src.java.crm.services.CrmUserService;

@WebServlet(urlPatterns = "/defectDetail")
public class DefectDetailController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String def = req.getParameter("defectId");
		if(def == null) {
			def = (String) req.getAttribute("defectId");
		}
		int defectId = Integer.parseInt(def);
		CrmUserService gpsUserService = new CrmUserService();
		
		DesTable defectDetails = gpsUserService.getDefectDetailById(defectId);
		req.setAttribute("DefectDetails", defectDetails);
		RequestDispatcher rd = req.getRequestDispatcher("viewDefectDetail.jsp");
		rd.forward(req, resp);
	}
}
