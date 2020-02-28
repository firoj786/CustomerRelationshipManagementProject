package com.src.java.crm.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.src.java.crm.dao.CrmModuleDAO;
import com.src.java.crm.dao.EnrolledEmpDAO;
import com.src.java.crm.dao.SeverityDAO;
import com.src.java.crm.dao.UserDAO;
import com.src.java.crm.dto.CrmModule;
import com.src.java.crm.dto.Severity;
import com.src.java.crm.dto.User;
import com.src.java.crm.exceptions.ServiceException;
import com.src.java.crm.utilities.DBUtil;

/**
 * This service is admin activities
 * 
 * @author user
 *
 */
public class CrmAdminService extends AbstractGPSUserService {
	public Severity saveAndGetSeverity(String sevDescription) {
		Severity sev = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				SeverityDAO sDAO = new SeverityDAO(con);
				sDAO.addSeverity(sevDescription);
				con.commit();

				sev = sDAO.getSeverityByName(sevDescription);

			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to save and get severity");
		}

		return sev;
	}	
	
	public CrmModule saveAndGetModule(String sevDescription) {
		CrmModule mod = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				CrmModuleDAO mDAO = new CrmModuleDAO(con);
				mDAO.addModule(sevDescription);
				con.commit();

				mod = mDAO.getModuleByName(sevDescription);

			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to save and get module");
		}

		return mod;
	}

	public void removeSeverity(int tid) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				SeverityDAO mDAO = new SeverityDAO(con);
				mDAO.deleteSeverityById(tid);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to delete severity");
		}		
	}
	
	public void removeModule(int tid) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				CrmModuleDAO mDAO = new CrmModuleDAO(con);
				mDAO.deleteModuleById(tid);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to delete module");
		}		
	}

	public User saveAndGetUser(String sevUser) {
		User user = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				UserDAO uDAO = new UserDAO(con);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to save and get severity");
		}

		return user;
	}	
	
	public void removeUserById(int uid) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				EnrolledEmpDAO eDAO=new EnrolledEmpDAO(con);
				eDAO.deleteUserById(uid);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to delete User");
		}		
	}
}
