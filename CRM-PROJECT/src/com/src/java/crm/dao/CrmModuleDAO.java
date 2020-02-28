package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.CrmModule;
import com.src.java.crm.dto.Severity;
import com.src.java.crm.exceptions.DAOException;

public class CrmModuleDAO {
	private Connection con;

	public CrmModuleDAO(Connection con) {
		this.con = con;
	}

	public List<CrmModule> getAllModule() {
		List<CrmModule> moduleList = new ArrayList<CrmModule>();

		CrmModule module = null;
		try {
			String sql = "select * from gpsmodules order by id";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				module = new CrmModule();
				module.setId(rs.getInt("id"));
				module.setDescription(rs.getString("description"));

				moduleList.add(module);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to all module, Please contact admin");
		}

		return moduleList;
	}

	public void addSeverity(String sevDescription) {
		String sql = "insert into gpsmodules (description) values(?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sevDescription);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Problem to add Severity");
		}
	}
	
	public void deleteModuleById(int id) {
		String sql = "delete from gpsmodules where id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Problem to delete GPS module");
		}
	}
	
	public CrmModule getModuleByName(String name) {
		CrmModule module = null;
		try {
			String sql = "select * from gpsmodules where description = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				module = new CrmModule();
				module.setId(rs.getInt("id"));
				module.setDescription(name);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to fetch severity, Please contact admin");
		}

		return module;
	}
	
	public void addModule(String sevDescription) {
		String sql = "insert into gpsmodules (description, version) values(?, -1.0)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sevDescription);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Problem to add Module");
		}
	}
	
}
