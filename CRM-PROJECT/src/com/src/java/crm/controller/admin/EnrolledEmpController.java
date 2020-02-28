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

import com.src.java.crm.dto.User;
import com.src.java.crm.services.CrmAdminService;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/enrolemp.do")
public class EnrolledEmpController extends HttpServlet {
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		ServletContext context = rq.getServletContext();
		List<User> enrList = (List<User>) context.getAttribute("EnrolledEmployeeList");

		String id = rq.getParameter("userId");
		if (id != null && id.trim().length() > 0) {
			int tid = Integer.parseInt(id);

			CrmAdminService service = new CrmAdminService();
			service.removeUserById(tid);
			for (User s : enrList) {
				if (s.getUserId() == tid) {
					enrList.remove(s);
					context.setAttribute("EnrolledEmployeeList", enrList);
					rq.setAttribute("sMsg", "User deleted successfully");
					break;
				}
			}
		}

		rq.setAttribute("EnrolledEmployeeList", enrList);
		RequestDispatcher rd = rq.getRequestDispatcher("enrolledEmp.jsp");
		rd.forward(rq, rs);
	}
}
