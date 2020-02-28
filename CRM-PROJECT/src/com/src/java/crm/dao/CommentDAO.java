package com.src.java.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.src.java.crm.dto.Comment;
import com.src.java.crm.dto.DesTable;
import com.src.java.crm.exceptions.DAOException;
import com.src.java.crm.utilities.DefectStatusConstant;
import com.src.java.crm.utilities.DefectTypeConstant;

public class CommentDAO {
	private Connection con;

	public CommentDAO(Connection con) {
		this.con = con;
	}

	public List<Comment> getAllCommentByDefectId(int defectId) {
		List<Comment> comments = new ArrayList<Comment>();
		try {
			String sql = "select * from comment c, user u where c.desId = ?  and c.isDeleted = 0 and c.commentedBy = u.userid";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, defectId);

			ResultSet rs = pstmt.executeQuery();

			Comment comment = null;
			while (rs.next()) {
				comment = new Comment();
				comment.setAssignedTo(rs.getLong("assignedTo"));
				comment.setCommentedBy(rs.getLong("commentedBy"));
				comment.setCommentedOn(rs.getDate("commentedOn"));
				comment.setDefectId(defectId);
				comment.setDeleted(false);
				comment.setDescription(rs.getString("description"));
				comment.setCommentGivenByName(rs.getString("fName") + " " + rs.getString("lName"));
				comment.setId(rs.getInt("id"));

				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new DAOException(
					"Internal Server issue to get all comments [" + defectId + "] defect, Please contact admin");
		}
		return comments;
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

	public void addComment(Comment c) {
		try {
			String sql = "insert into comment (desId, commentedOn, commentedBy, description, assignedTo) values (?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c.getDefectId());
			pstmt.setDate(2, new java.sql.Date(c.getCommentedOn().getTime()));
			pstmt.setLong(3, c.getCommentedBy());
			pstmt.setString(4, c.getDescription());
			pstmt.setLong(5, c.getAssignedTo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Internal Server issue to add defect comment, Please contact admin");
		}
	}

}
