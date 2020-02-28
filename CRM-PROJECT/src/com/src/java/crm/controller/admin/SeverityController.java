package com.src.java.crm.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.java.crm.dto.Severity;
import com.src.java.crm.services.CrmAdminService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/severity.do")
public class SeverityController extends HttpServlet {
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		ServletContext context = rq.getServletContext();
		List<Severity> severityList = (List<Severity>) context.getAttribute("SeverityList");

		String id = rq.getParameter("id");
		if (id != null && id.trim().length() > 0) {
			int tid = Integer.parseInt(id);
		
			CrmAdminService service = new CrmAdminService();
			service.removeSeverity(tid);
			for (Severity s : severityList) {
				if (s.getId() == tid) {
					severityList.remove(s);
					context.setAttribute("SeverityList", severityList);
					rq.setAttribute("sMsg", "Severity deleted successfully");
					break;
				}
			}
		}

		rq.setAttribute("SeverityList", severityList);
		RequestDispatcher rd = rq.getRequestDispatcher("severity.jsp");
		rd.forward(rq, rs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String name = rq.getParameter("sevDescription");

		ServletContext context = rq.getServletContext();
		List<Severity> severityList = (List<Severity>) context.getAttribute("SeverityList");

		boolean found = false;
		for (Severity s : severityList) {
			if (s.getDescription().equalsIgnoreCase(name)) {
				found = true;
				rq.setAttribute("eMsg", "Severity already exists.");
				break;
			}
		}

		if (!found) {
			CrmAdminService service = new CrmAdminService();
			Severity newS = service.saveAndGetSeverity(name);
			severityList.add(newS);
			context.setAttribute("SeverityList", severityList);
			rq.setAttribute("sMsg", "Severity added successfully");
		}

		rq.setAttribute("SeverityList", severityList);
		RequestDispatcher rd = rq.getRequestDispatcher("severity.jsp");
		rd.forward(rq, rs);
	}
}
