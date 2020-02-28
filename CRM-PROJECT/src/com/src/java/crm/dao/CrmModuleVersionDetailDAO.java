package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.CrmModuleVersionDetail;
import com.src.java.crm.exceptions.DAOException;

public class CrmModuleVersionDetailDAO {
	private Connection con;

	public CrmModuleVersionDetailDAO(Connection con) {
		this.con = con;
	}

	public List<CrmModuleVersionDetail> getAllModules() {
		List<CrmModuleVersionDetail> verList = new ArrayList<CrmModuleVersionDetail>();
		
		CrmModuleVersionDetail moduleVersionDetail = null;
		try {
			String sql = "select * from gpsmodules_details order by version";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				moduleVersionDetail = new CrmModuleVersionDetail();
				moduleVersionDetail.setId(rs.getInt("id"));
				moduleVersionDetail.setModuleId(rs.getInt("moduleId"));
				moduleVersionDetail.setVersion(rs.getFloat("version"));
				moduleVersionDetail.setReleatseDate(rs.getDate("releaseDate"));
				
				verList.add(moduleVersionDetail);
			}
		} 
		catch (SQLException e) {
			throw new DAOException("Internal Server issue to verion details, Please contact admin");
		}
		
		return verList;
	}
}
