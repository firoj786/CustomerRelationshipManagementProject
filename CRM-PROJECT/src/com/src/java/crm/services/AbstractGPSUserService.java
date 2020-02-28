package com.src.java.crm.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.src.java.crm.dao.CommentDAO;
import com.src.java.crm.dao.DesTableDAO;
import com.src.java.crm.dao.UserDAO;
import com.src.java.crm.dto.DesTable;
import com.src.java.crm.dto.SearchCriteria;
import com.src.java.crm.dto.User;
import com.src.java.crm.exceptions.ServiceException;
import com.src.java.crm.utilities.DBUtil;

/**
 * This service is to validate CRM User, Register new User & Logout Operation
 * 
 * @author user
 *
 */
public abstract class AbstractGPSUserService {
	public final User validateCRMUser(String loginId, String pass, int roleId) {
		Connection con = DBUtil.getInstance().getDbConnection();
		UserDAO userDAO = new UserDAO(con);
		User user = userDAO.validateUser(loginId, pass, roleId);
		
		if (user == null) {
			throw new ServiceException("Username / Password mismatched.");
		}
		return user;
	}
	
	/**
	 * This method applicable except Admin role
	 * 
	 */
	public DesTable getDefectDetailById(int defectId) {
		DesTable defect = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			DesTableDAO dtDAO = new DesTableDAO(con);
			CommentDAO cDAO = new CommentDAO(con);
			
			defect = dtDAO.getDefectById(defectId);
			defect.setComments(cDAO.getAllCommentByDefectId(defectId));
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue fetch open defect list, Please contact admin");
		}
		return defect;
	}
	
	/**
	 * This method applicable except Admin role
	 * 
	 */
	public void updateDefect(DesTable dt) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			con.setAutoCommit(false);
			DesTableDAO dtdao = new DesTableDAO(con);
			dtdao.updateDefect(dt);
			CommentDAO cDao = new CommentDAO(con);
			cDao.addComment(dt.getComment());
			
			con.commit();
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue to update profile, Please contact admin");
		}
	}
	
	/**
	 * This method applicable except Admin role
	 * 
	 */
	public List<DesTable> searchAllDefectByCriteria(SearchCriteria sc) {
		List<DesTable> defectList = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			DesTableDAO dtDAO = new DesTableDAO(con);			
			defectList = dtDAO.getDefectBySearchCritera(sc);
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue search defect by criteria, Please contact admin");
		}
		return defectList;
	}
	
	public void updatePassword(String pass, Long id) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			
			UserDAO userDao = new UserDAO(con);
			con.setAutoCommit(false);
			userDao.updatePassword(pass, id);
			con.commit();
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue to update password, Please contact admin");
		}
	}
	
}
	