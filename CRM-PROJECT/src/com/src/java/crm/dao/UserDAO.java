package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.User;
import com.src.java.crm.exceptions.DAOException;
import com.src.java.crm.exceptions.SessionExpiredException;

public class UserDAO {
	private Connection con;

	public UserDAO(Connection con) {
		this.con = con;
	}

	public User getUserById(int id) {
		User user = null;
		try {
			String sql = "select * from user where userId = ? and isDeleted = 0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getLong("userId"));
				user.setRoleId(rs.getInt("roleId"));
				user.setPassword(rs.getString("password"));
				user.setLoginId(rs.getString("loginId"));
				user.setLname(rs.getString("fName"));
				user.setLname(rs.getString("lName"));
				user.setEmailId(rs.getString("emailId"));
				user.setActive(rs.getBoolean("isActive"));
				user.setDeleted(rs.getBoolean("isDeleted"));
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to validate login, Please contact admin");
		}
		if (user == null) {
			throw new SessionExpiredException("Session expired, Login again");
		} else if (user.isDeleted()) {
			throw new DAOException("Login is inActive, Wait or Contact admin");
		}
		return user;
	}

	public List<User> getUsersByRoleId(int roleId) {
		List<User> userList = new ArrayList<User>();
		User user = null;
		try {
			String sql = "select * from user where roleId = ? and isDeleted = 0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, roleId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getLong("userId"));
				user.setRoleId(rs.getInt("roleId"));
				user.setPassword(rs.getString("password"));
				user.setLoginId(rs.getString("loginId"));
				user.setFname(rs.getString("fName"));
				user.setLname(rs.getString("lName"));
				user.setEmailId(rs.getString("emailId"));
				user.setActive(rs.getBoolean("isActive"));
				user.setDeleted(rs.getBoolean("isDeleted"));

				userList.add(user);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to fetch user list, Please contact admin");
		}

		return userList;
	}

	public User validateUser(String loginId, String pass, int roleId) {
		User user = null;
		try {
			String sql = "select * from User where loginId = ? and password = ? and roleId = ? and isDeleted = 0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, pass);
			pstmt.setInt(3, roleId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setActive(rs.getBoolean("isActive"));
				user.setDeleted(false);
				user.setFname(rs.getString("fName"));
				user.setLname(rs.getString("lName"));
				user.setLoginId(loginId);
				user.setPassword(pass);
				user.setRoleId(roleId);
				user.setUserId(rs.getLong("userId"));
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to validate login, Please contact admin");
		}
		if (user == null) {
			throw new DAOException("Invalid LoginId / Password");
		} else if (!user.isActive()) {
			throw new DAOException("Login not activated, Wait or Contact admin");
		}
		return user;
	}

	public boolean isUserExists(String loginId) {
		boolean found = false;

		String sql = "select * from user where loginId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				found = true;
			}
		} catch (SQLException e) {
			throw new DAOException("Internal issue to check user, Please contact admin");
		}
		return found;
	}

	public void saveUser(User user) {
		// write db code to insert User into user table
		String sql = "insert into User (loginId, emailId, password,fName,lName, roleId, userId) values(?, ?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getEmailId());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getFname());
			pstmt.setString(5, user.getLname());
			pstmt.setInt(6, user.getRoleId());
			pstmt.setLong(7, user.getUserId());

			pstmt.executeUpdate();
			// Transaction ended
		} catch (SQLException e) {
			throw new DAOException("Internal issue to register, Please contact admin");
		}
	}
	
	public void updatePassword(String oldPass, Long loginId) {
		// write db code to insert User into user table
		String sql = "update User set password = ? where userId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oldPass);
			pstmt.setLong(2, loginId);

			pstmt.executeUpdate();
			// Transaction ended
		} catch (SQLException e) {
			throw new DAOException("Internal issue to update Password, Please contact admin");
		}
	}
}
