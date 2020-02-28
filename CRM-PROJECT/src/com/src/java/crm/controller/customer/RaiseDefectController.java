package com.src.java.crm.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.src.java.crm.dto.DesTable;
import com.src.java.crm.dto.CrmModuleVersionDetail;
import com.src.java.crm.dto.User;
import com.src.java.crm.services.CrmUserService;
import com.src.java.crm.utilities.DefectStatusConstant;
import com.src.java.crm.utilities.DefectTypeConstant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/raiseDefect.do")
public class RaiseDefectController extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		int moduleId = Integer.parseInt(rq.getParameter("moduleId"));
		
		ServletContext context = rq.getServletContext();
		List<CrmModuleVersionDetail> moduleVersionDetalList = (List<CrmModuleVersionDetail>) context.getAttribute("ModuleVersionDetalList");
		
		List<Float> versionList = new ArrayList<Float>();
		
		for(CrmModuleVersionDetail gmvd : moduleVersionDetalList) {
			if(gmvd.getModuleId() == moduleId) {
				versionList.add(gmvd.getVersion());
			}
		}
		
		String gson = new Gson().toJson(versionList);

		rs.setContentType("application/json");
		rs.setCharacterEncoding("UTF-8");
		rs.getWriter().write(gson);
	}
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String nextPage = "";
		HttpSession session = rq.getSession(false);
		User user = (User) session.getAttribute("UserDetails");

		DesTable dt = new DesTable();
		dt.setUserId(user.getUserId());
		dt.setDesc(rq.getParameter("description"));
		dt.setType(DefectTypeConstant.DEFECT);
		dt.setStrp(rq.getParameter("str"));
		dt.setSevId(Integer.parseInt(rq.getParameter("severity")));
		dt.setCreationDate(new Date());
		dt.setAssignedTo(Long.parseLong(rq.getParameter("assignedTo")));
		dt.setStatus(DefectStatusConstant.SUBMITTED);
		dt.setReleaseVersionId(Integer.parseInt(rq.getParameter("releaseversion")));
		dt.setModuleNameId(Integer.parseInt(rq.getParameter("modulename")));
		dt.setModuleVersionId(Float.parseFloat(rq.getParameter("moduleversion")));

		try {
			CrmUserService gpsUserService = new CrmUserService();
			gpsUserService.raiseNewDefect(dt);
			
			rq.setAttribute("msg", "Defect created Successfully");
			nextPage = "index.jsp";
		} catch (RuntimeException rt) { // exception handle
			rt.printStackTrace();
			rq.setAttribute("eMsg", rt.getMessage());
			nextPage = "raiseDefect";
			rq.setAttribute("DefectDetail", dt);

		}

		// RequestDispacher with proper message
		RequestDispatcher rd = rq.getRequestDispatcher(nextPage);
		rd.forward(rq, rs);

	}

}
