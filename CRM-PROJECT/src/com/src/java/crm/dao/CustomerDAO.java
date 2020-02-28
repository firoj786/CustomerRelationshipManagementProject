package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.Customer;
import com.src.java.crm.exceptions.DAOException;
import com.src.java.crm.exceptions.SessionExpiredException;

public class CustomerDAO {
	private Connection con;

	public CustomerDAO(Connection con) {
		this.con = con;
	}

	public Customer getCustomerById(Long id) {
		Customer cust = null;
		try {
			String sql = "select * from customer where userId = ? and isDeleted = 0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				cust = new Customer();
				cust.setAddress(rs.getString("address"));
				cust.setCity(rs.getString("city"));
				cust.setCompany(rs.getString("company"));
				cust.setCountry(rs.getString("country"));
				cust.setPincode(rs.getString("pincode"));
				cust.setPrContact(rs.getInt("prContact"));
				cust.setSecContact(rs.getInt("secContact"));
				cust.setUserId(rs.getLong("userId"));
				cust.setDeleted(rs.getBoolean("isDeleted"));
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to validate login, Please contact admin");
		}
		if (cust == null) {
			throw new SessionExpiredException("Session expired, Login again");
		} else if (cust.isDeleted()) {
			throw new DAOException("Login is inActive, Wait or Contact admin");
		}
		return cust;
	}

	public void updateCustomer(Customer cust) {
		try {
			String sql = "update customer set company = ?, prContact = ?, secContact = ?, address = ?, city = ?, country = ?, pincode = ? where userId = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cust.getCompany());
			pstmt.setLong(2, cust.getPrContact());
			pstmt.setLong(3, cust.getSecContact());
			pstmt.setString(4, cust.getAddress());
			pstmt.setString(5, cust.getCity());
			pstmt.setString(6, cust.getCountry());
			pstmt.setString(7, cust.getPincode());
			pstmt.setLong(8, cust.getUserId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to update Customer, Please contact admin");
		}
	}

	public Customer fetchCustomerById(int userId) {
		Customer cust = null;
		ResultSet rs = null;
		try {

			String sql = "select * from customer where userId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cust = new Customer();
				cust.setDeleted(rs.getBoolean("isDeleted"));
				cust.setAddress(rs.getString("address"));
				cust.setCity(rs.getString("city"));
				cust.setCompany(rs.getString("company"));
				cust.setCountry(rs.getString("country"));
				cust.setPincode(rs.getString("pincode"));
				cust.setPrContact(rs.getInt("prContact"));
				cust.setSecContact(rs.getInt("secContact"));
				cust.setUserId(rs.getLong("userId"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}
	
	public void saveCustomer(Customer customer) {
		//write db code to insert Customer into customer table
		String sql = "insert into Customer (company, prContact, secContact,address,city,country,pincode,userId) values(?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getCompany());
			pstmt.setLong(2, customer.getPrContact());
			pstmt.setLong(3, customer.getSecContact());
			pstmt.setString(4, customer.getAddress());
			pstmt.setString(5, customer.getCity());
			pstmt.setString(6, customer.getCountry());
			pstmt.setString(7, customer.getPincode());
			pstmt.setLong(8, customer.getUserId());

			pstmt.executeUpdate();
			// Transaction ended
		} catch (SQLException e) {
			throw new DAOException("Problem to add Personal Detail while adding new User, Please contact admin");
		} 
	}
	
	public List<Customer> fetchTop10CustomerDetails() {
		List<Customer> custList = new ArrayList<Customer>();
		ResultSet rs = null;
		try {

			String sql = "select * from customer where priority between 1 and 10";
			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Customer cust = null;
			while (rs.next()) {
				cust = new Customer();
				cust.setDeleted(rs.getBoolean("isDeleted"));
				cust.setAddress(rs.getString("address"));
				cust.setCity(rs.getString("city"));
				cust.setCompany(rs.getString("company"));
				cust.setCountry(rs.getString("country"));
				cust.setPincode(rs.getString("pincode"));
				cust.setPrContact(rs.getInt("prContact"));
				cust.setSecContact(rs.getInt("secContact"));
				cust.setUserId(rs.getLong("userId"));
				cust.setPriority(rs.getInt("priority"));
				
				custList.add(cust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custList;
	}
}
