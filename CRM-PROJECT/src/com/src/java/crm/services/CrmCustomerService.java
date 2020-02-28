package com.src.java.crm.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.src.java.crm.dao.CustomerDAO;
import com.src.java.crm.dao.UserDAO;
import com.src.java.crm.dto.Customer;
import com.src.java.crm.dto.User;
import com.src.java.crm.exceptions.ServiceException;
import com.src.java.crm.utilities.DBUtil;


public class CrmCustomerService extends AbstractGPSUserService {
	/**
	 * This is a Customer registration service
	 * 
	 * @param user
	 * @param pd
	 */
	public Customer fetchProfileDetails(Long userId) {
		Customer cust = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			//Create object of Customer DAO & call respective method
			CustomerDAO customerDAO = new CustomerDAO(con); 
			cust=customerDAO.getCustomerById(userId);
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue to validate CRM User, Please contact admin");
		}
		return cust;
	}
	
	public void updateProfile(Customer cust) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			//Create object of Customer DAO & call updateCustomer(cust)
			CustomerDAO customerDAO = new CustomerDAO(con);
			con.setAutoCommit(false);
			customerDAO.updateCustomer(cust);
			con.commit();
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue to update profile, Please contact admin");
		}
	}
	
	public void registerCustomer(User user, Customer cust) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				UserDAO userDAO = new UserDAO(con);
				CustomerDAO customerDAO = new CustomerDAO(con);

				if (!userDAO.isUserExists(user.getLoginId())) {
					userDAO.saveUser(user);
					customerDAO.saveCustomer(cust);
					con.commit();
				} else {
					throw new ServiceException("Username already exist");
				}
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to handle registeration process, Please contact admin");
		}
	}
	
	public void registerEmployee(User user, Customer cust) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				UserDAO userDAO = new UserDAO(con);

				if (!userDAO.isUserExists(user.getLoginId())) {
					userDAO.saveUser(user);
					con.commit();
				} else {
					throw new ServiceException("Username already exist");
				}
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to handle registeration process, Please contact admin");
		}
	}
	
	
	public void registerSales(User user, Customer cust) {
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			try {
				con.setAutoCommit(false);
				UserDAO userDAO = new UserDAO(con);

				if (!userDAO.isUserExists(user.getLoginId())) {
					userDAO.saveUser(user);
					con.commit();
				} else {
					throw new ServiceException("Username already exist");
				}
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			throw new ServiceException("Internal issue to handle registeration process, Please contact admin");
		}
	}	
}
