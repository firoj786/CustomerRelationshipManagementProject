package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.Severity;
import com.src.java.crm.exceptions.DAOException;

public class SeverityDAO {
	private Connection con;

	public SeverityDAO(Connection con) {
		this.con = con;
	}

	public List<Severity> getAllSeverity() {
		List<Severity> severityList = new ArrayList<Severity>();

		Severity severity = null;
		try {
			String sql = "select * from severity order by id";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				severity = new Severity();
				severity.setId(rs.getInt("id"));
				severity.setDescription(rs.getString("description"));

				severityList.add(severity);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to all severity, Please contact admin");
		}

		return severityList;
	}

	public void addSeverity(String sevDescription) {
		String sql = "insert into Severity (description) values(?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sevDescription);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Problem to add Severity");
		}
	}
	
	public void deleteSeverityById(int id) {
		String sql = "delete from Severity where id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Problem to delete Severity");
		}
	}
	
	public Severity getSeverityByName(String name) {
		Severity severity = null;
		try {
			String sql = "select * from severity where description = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				severity = new Severity();
				severity.setId(rs.getInt("id"));
				
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to fetch severity, Please contact admin");
		}

		return severity;
	}
	
}
