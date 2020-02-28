package com.src.java.crm.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.src.java.crm.dao.DesTableDAO;
import com.src.java.crm.dto.DesTable;
import com.src.java.crm.exceptions.ServiceException;
import com.src.java.crm.utilities.DBUtil;

/**
 * This service is to validate CRM User, Register new User & Logout Operation
 * 
 * @author user
 *
 */
public class CrmUserService extends AbstractGPSUserService{
	/**
	 * Raise new defect used by Sales & Customer
	 */
	public void raiseNewDefect(DesTable desTable) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				DesTableDAO desTableDAO = new DesTableDAO(con);
				desTableDAO.saveDefect(desTable);				
				con.commit();			
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to raise new defect, Please contact Admin");
		}
	}
}
