package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.CrmRelease;
import com.src.java.crm.exceptions.DAOException;

public class CrmReleaseDAO {
	private Connection con;

	public CrmReleaseDAO(Connection con) {
		this.con = con;
	}

	public List<CrmRelease> getGpsSimReleases() {
		List<CrmRelease> simReleaseList = new ArrayList<CrmRelease>();
		
		CrmRelease gpsSim = null;
		try {
			String sql = "select * from gpssim_releases order by version";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				gpsSim = new CrmRelease();
				gpsSim.setId(rs.getInt("id"));
				gpsSim.setVersion(rs.getFloat("version"));
				gpsSim.setDate(rs.getDate("release_date"));
				
				simReleaseList.add(gpsSim);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to all gps sim releases, Please contact admin");
		}
		
		return simReleaseList;
	}
}
