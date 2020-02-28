package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.DesTable;
import com.src.java.crm.dto.SearchCriteria;
import com.src.java.crm.exceptions.DAOException;
import com.src.java.crm.utilities.DefectStatusConstant;
import com.src.java.crm.utilities.DefectTypeConstant;

public class DesTableDAO {
	private Connection con;

	public DesTableDAO(Connection con) {
		this.con = con;
	}

	public void saveDefect(DesTable dt) {
		String sql = "insert into destable (userId, description, type, str, severity, creationDate, assignedTo, "
				+ "status, releaseVersion, moduleId, moduleVersion) values(?,?,?,?,?,?,?,?,?,?,?)";

		try {

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dt.getUserId());
			pstmt.setString(2, dt.getDesc());
			pstmt.setString(3, dt.getType().name());
			pstmt.setString(4, dt.getStrp());
			pstmt.setInt(5, dt.getSevId());
			pstmt.setDate(6, new java.sql.Date(dt.getCreationDate().getTime()));
			pstmt.setLong(7, dt.getAssignedTo());
			pstmt.setString(8, dt.getStatus().name());

			pstmt.setFloat(9, dt.getReleaseVersionId());
			pstmt.setInt(10, dt.getModuleNameId());
			pstmt.setFloat(11, dt.getModuleVersionId());

			pstmt.executeUpdate();
			// Transaction ended
		} catch (SQLException e) {
			throw new DAOException("Problem to save new Defect details, Please contact admin");
		}
	}

	public List<DesTable> getAllDefectByStatus(DefectStatusConstant submitted) {
		List<DesTable> defectList = new ArrayList<DesTable>();
		try {
			String sql = "select d.id, d.userId, d.description, d.type, d.str, d.severity, d.creationDate, d.assignedTo, d.status, d.reopendedDate, d.releaseVersion, d.moduleId, d.moduleVersion, d.fixedInVersion, d.resolutionDate, d.resolution, d.isDeleted, "
					+ " u.fName, u.lName, s.description description1 from destable d, user u , severity s where u.userid = d.userid and d.status = ? and d.severity = s.id and d.isDeleted = 0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, submitted.name());

			ResultSet rs = pstmt.executeQuery();

			DesTable dt = null;
			while (rs.next()) {
				dt = new DesTable();
				dt.setAssignedTo(rs.getLong("assignedTo"));
				dt.setCreationDate(rs.getDate("creationDate"));
				dt.setDeleted(rs.getBoolean("isDeleted"));
				dt.setDesc(rs.getString("description"));
				dt.setFixedInVersionId(rs.getFloat("fixedInVersion"));
				dt.setId(rs.getInt("id"));
				dt.setModuleNameId(rs.getInt("moduleId"));
				dt.setReleaseVersionId(rs.getFloat("releaseVersion"));
				dt.setReopenDate(rs.getDate("reopendedDate"));
				dt.setResolution(rs.getString("resolution"));
				dt.setResolutionDate(rs.getDate("resolutionDate"));
				dt.setSevId(rs.getInt("severity"));
				dt.setSevDescription(rs.getString("description1"));
				dt.setStatus(DefectStatusConstant.valueOf(rs.getString("status")));
				dt.setStrp(rs.getString("str"));
				dt.setType(DefectTypeConstant.valueOf(rs.getString("type")));
				dt.setUserId(rs.getLong("userId"));
				dt.setUserFullName(rs.getString("fName") + " " + rs.getString("lName"));

				defectList.add(dt);
			}
		} catch (SQLException e) {
			throw new DAOException(
					"Internal Server issue to view all [" + submitted.name() + "] defect, Please contact admin");
		}
		return defectList;
	}

	public DesTable getDefectById(int defectId) {
		DesTable dt = null;
		try {
			String sql = "select d.id, d.userId, d.description, d.type, d.str, d.severity, d.creationDate, d.assignedTo, d.status, d.reopendedDate, d.releaseVersion, d.moduleId, d.moduleVersion, d.fixedInVersion, d.resolutionDate, d.resolution, d.isDeleted, "
					+ " u.fName, u.lName, s.description description1 from destable d, user u , severity s where u.userid = d.userid and d.id = ? and d.severity = s.id and d.isDeleted = 0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, defectId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				dt = new DesTable();
				dt.setAssignedTo(rs.getLong("assignedTo"));
				dt.setCreationDate(rs.getDate("creationDate"));
				dt.setDeleted(rs.getBoolean("isDeleted"));
				dt.setDesc(rs.getString("description"));
				dt.setFixedInVersionId(rs.getFloat("fixedInVersion"));
				dt.setId(rs.getInt("id"));
				dt.setModuleNameId(rs.getInt("moduleId"));
				dt.setReleaseVersionId(rs.getFloat("releaseVersion"));
				dt.setReopenDate(rs.getDate("reopendedDate"));
				dt.setResolution(rs.getString("resolution"));
				dt.setResolutionDate(rs.getDate("resolutionDate"));
				dt.setSevId(rs.getInt("severity"));
				dt.setSevDescription(rs.getString("description1"));
				dt.setStatus(DefectStatusConstant.valueOf(rs.getString("status")));
				dt.setStrp(rs.getString("str"));
				dt.setType(DefectTypeConstant.valueOf(rs.getString("type")));
				dt.setUserId(rs.getLong("userId"));
				dt.setUserFullName(rs.getString("fName") + " " + rs.getString("lName"));
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to defect id [" + defectId + "], Please contact admin");
		}
		return dt;
	}

	public List<DesTable> getDefectBySearchCritera(SearchCriteria criteria) {
		List<DesTable> defectList = new ArrayList<DesTable>();

		try {
			String bSql = "select d.id, d.userId, d.description, d.type, d.str, d.severity, d.creationDate, d.assignedTo, d.status, d.reopendedDate, "
					+ " d.releaseVersion, d.moduleId, d.moduleVersion, d.fixedInVersion, d.resolutionDate, d.resolution, d.isDeleted, u.fName, u.lName, "
					+ " s.description description1 from destable d, user u , severity s where u.userid = d.userid and d.severity = s.id and d.isDeleted = 0";

			StringBuilder sql = new StringBuilder(bSql);
			sql = buildDynamicSQL(sql, criteria);
			System.out.println("SQL:" + sql.toString());
			Statement pstmt = con.createStatement();

			ResultSet rs = pstmt.executeQuery(sql.toString());

			DesTable dt = null;
			while (rs.next()) {
				dt = new DesTable();
				dt.setAssignedTo(rs.getLong("assignedTo"));
				dt.setCreationDate(rs.getDate("creationDate"));
				dt.setDeleted(rs.getBoolean("isDeleted"));
				dt.setDesc(rs.getString("description"));
				dt.setFixedInVersionId(rs.getFloat("fixedInVersion"));
				dt.setId(rs.getInt("id"));
				dt.setModuleNameId(rs.getInt("moduleId"));
				dt.setReleaseVersionId(rs.getFloat("releaseVersion"));
				dt.setReopenDate(rs.getDate("reopendedDate"));
				dt.setResolution(rs.getString("resolution"));
				dt.setResolutionDate(rs.getDate("resolutionDate"));
				dt.setSevId(rs.getInt("severity"));
				dt.setSevDescription(rs.getString("description1"));
				dt.setStatus(DefectStatusConstant.valueOf(rs.getString("status")));
				dt.setStrp(rs.getString("str"));
				dt.setType(DefectTypeConstant.valueOf(rs.getString("type")));
				dt.setUserId(rs.getLong("userId"));
				dt.setUserFullName(rs.getString("fName") + " " + rs.getString("lName"));

				defectList.add(dt);
			}
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to search defect, Please contact admin");
		}
		return defectList;
	}

	public void updateDefect(DesTable dt) {
		try {
			String sql = "update destable set assignedTo = ?, fixedInVersion = ?, severity = ?, "
					+ "status = ?, resolutionDate = ?, resolution = ? where id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, dt.getAssignedTo());
			pstmt.setFloat(2, dt.getFixedInVersionId());
			pstmt.setInt(3, dt.getSevId());
			pstmt.setString(4, dt.getStatus().name());
			pstmt.setDate(5,
					dt.getResolutionDate() != null ? new java.sql.Date(dt.getResolutionDate().getTime()) : null);
			pstmt.setString(6, dt.getResolution());
			pstmt.setInt(7, dt.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to update defect, Please contact admin");
		}
	}

	private StringBuilder buildDynamicSQL(StringBuilder sql, SearchCriteria criteria) {
		if (criteria.getKeyList() != null && criteria.getKeyList().size() > 0) {
			sql.append(" and (");
			boolean flag = false;
			for (String key : criteria.getKeyList()) {
				if (!flag) {
					sql.append(" d.description like '%" + key + "%' OR d.str like '%" + key
							+ "%' OR d.resolution like '%" + key + "%'");
					flag = true;
				} else {
					sql.append(" OR d.description like '%" + key + "%' OR d.str like '%" + key
							+ "%' OR d.resolution like '%" + key + "%'");
				}
			}
			sql.append(")");
		}

		if (criteria.getSeverityId() != -1) {
			sql.append(" and severity = " + criteria.getSeverityId());
		}
		return sql;
	}

}
