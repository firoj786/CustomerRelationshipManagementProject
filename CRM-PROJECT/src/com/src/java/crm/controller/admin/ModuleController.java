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

import com.src.java.crm.dto.CrmModule;
import com.src.java.crm.services.CrmAdminService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/GpsModule.do")
public class ModuleController extends HttpServlet {
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		ServletContext context = rq.getServletContext();
		List<CrmModule> moduleList = (List<CrmModule>) context.getAttribute("GpsModuleList");

		String id = rq.getParameter("id");

		if (id != null && id.trim().length() > 0) {
			int tid = Integer.parseInt(id);

			CrmAdminService service = new CrmAdminService();
			service.removeModule(tid);
			for (CrmModule s : moduleList) {
				if (s.getId() == tid) {
					moduleList.remove(s);
					context.setAttribute("GpsModuleList", moduleList);
					rq.setAttribute("sMsg", "Module deleted successfully");
					break;
				}
			}
		}

		rq.setAttribute("ModuleList", moduleList);
		RequestDispatcher rd = rq.getRequestDispatcher("module.jsp");
		rd.forward(rq, rs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String name = rq.getParameter("sevDescription");

		ServletContext context = rq.getServletContext();
		List<CrmModule> moduleList = (List<CrmModule>) context.getAttribute("GpsModuleList");

		boolean found = false;
		for (CrmModule s : moduleList) {
			if (s.getDescription().equalsIgnoreCase(name)) {
				found = true;
				rq.setAttribute("eMsg", "GpsModule already exists.");
				break;
			}
		}

		if (!found) {
			CrmAdminService service = new CrmAdminService();
			CrmModule newG = service.saveAndGetModule(name);
			moduleList.add(newG);
			context.setAttribute("ModuleList", moduleList);
			rq.setAttribute("sMsg", "GpsModule added successfully");
		}

		rq.setAttribute("ModuleList", moduleList);
		RequestDispatcher rd = rq.getRequestDispatcher("module.jsp");
		rd.forward(rq, rs);
	}
}
