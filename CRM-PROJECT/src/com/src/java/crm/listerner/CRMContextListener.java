package com.src.java.crm.listerner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.src.java.crm.dao.CustomerDAO;
import com.src.java.crm.dao.EnrolledEmpDAO;
import com.src.java.crm.dao.CrmModuleDAO;
import com.src.java.crm.dao.CrmModuleVersionDetailDAO;
import com.src.java.crm.dao.CrmReleaseDAO;
import com.src.java.crm.dao.SeverityDAO;
import com.src.java.crm.dao.UserDAO;
import com.src.java.crm.dto.Customer;
import com.src.java.crm.dto.CrmModule;
import com.src.java.crm.dto.CrmModuleVersionDetail;
import com.src.java.crm.dto.CrmRelease;
import com.src.java.crm.dto.Severity;
import com.src.java.crm.dto.User;
import com.src.java.crm.utilities.DBUtil;

@WebListener
public class CRMContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			
			SeverityDAO severityDAO = new SeverityDAO(con);
			CrmModuleDAO gpsModuleDAO = new CrmModuleDAO(con);
			CrmReleaseDAO gpsSimReleaseDAO = new CrmReleaseDAO(con);
			CrmModuleVersionDetailDAO gpsModuleVersionDetailDAO = new CrmModuleVersionDetailDAO(con);
			UserDAO userdao = new UserDAO(con);
			CustomerDAO custDao = new CustomerDAO(con);
			EnrolledEmpDAO enEDAO=new EnrolledEmpDAO(con);
			
			List<Severity> severityList = severityDAO.getAllSeverity();
			List<CrmModule> moduleList = gpsModuleDAO.getAllModule();
			List<User> enrList=enEDAO.getAllUserList();
			List<CrmRelease> simReleaseList = gpsSimReleaseDAO.getGpsSimReleases();
			List<User> userList = userdao.getUsersByRoleId(3);
			userList.addAll(userdao.getUsersByRoleId(4));
			List<User> customerList = userdao.getUsersByRoleId(2);
			List<User> employeeList = userdao.getUsersByRoleId(3);
			
			//List<CrmModuleVersionDetail> moduleVersionDetalList = gpsModuleVersionDetailDAO.getAllModules();
			List<Customer> top10CustomerList = custDao.fetchTop10CustomerDetails();
			
			ServletContext context = sce.getServletContext();
			context.setAttribute("SeverityList", severityList);
			context.setAttribute("GpsModuleList", moduleList);
			context.setAttribute("GpsSimReleaseList", simReleaseList);
			context.setAttribute("EmployeeAndSalesList", userList);
			context.setAttribute("CustomerList", customerList);
			context.setAttribute("EmployeeList", employeeList);
			//context.setAttribute("ModuleVersionDetalList", moduleVersionDetalList);
			context.setAttribute("Top10CustomerList", top10CustomerList);
			context.setAttribute("EnrolledEmployeeList", enrList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
