package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.User;
import com.src.java.crm.exceptions.DAOException;

public class EnrolledEmpDAO {
	private Connection con;

	public EnrolledEmpDAO(Connection con) {
		this.con = con;
	}

	public List<User> getAllUserList() {
		List<User> enrList = new ArrayList<User>();

		User user = null;
		try {
			String sql = "SELECT * FROM user where roleId!=1;";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getLong("userid"));
				user.setFname(rs.getString("fname"));
				user.setActive(rs.getBoolean("isActive"));

				enrList.add(user);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to all User, Please contact admin");
		}

		return enrList;
	}

	public void deleteUserById(int id) {
		String sql = "delete from user where userid = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Problem to delete User");
		}
	}

}
