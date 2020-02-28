package com.src.java.crm.dto;

import java.util.Date;
import java.util.List;

import com.src.java.crm.utilities.DefectStatusConstant;
import com.src.java.crm.utilities.DefectTypeConstant;

public class DesTable {
	private int id;
	private Long userId;
	transient private String userFullName;
	private String desc;
	private DefectTypeConstant type;
	private String strp;
	private int sevId;
	transient private String sevDescription;
	private Date creationDate;
	private Long assignedTo;
	private DefectStatusConstant status;
	private Date reopenDate;
	private float releaseVersionId;
	private int moduleNameId;
	private float moduleVersionId;
	private float fixedInVersionId;
	private Date resolutionDate;
	private String resolution;
	private boolean deleted;
	private Comment comment;
	private List<Comment> comments;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DefectTypeConstant getType() {
		return type;
	}

	public void setType(DefectTypeConstant type) {
		this.type = type;
	}

	public String getStrp() {
		return strp;
	}

	public void setStrp(String strp) {
		this.strp = strp;
	}

	public int getSevId() {
		return sevId;
	}

	public void setSevId(int sevId) {
		this.sevId = sevId;
	}	

	public String getSevDescription() {
		return sevDescription;
	}

	public void setSevDescription(String sevDescription) {
		this.sevDescription = sevDescription;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	public DefectStatusConstant getStatus() {
		return status;
	}

	public void setStatus(DefectStatusConstant status) {
		this.status = status;
	}

	public Date getReopenDate() {
		return reopenDate;
	}

	public void setReopenDate(Date reopenDate) {
		this.reopenDate = reopenDate;
	}

	public float getReleaseVersionId() {
		return releaseVersionId;
	}

	public void setReleaseVersionId(float releaseVersionId) {
		this.releaseVersionId = releaseVersionId;
	}

	public int getModuleNameId() {
		return moduleNameId;
	}

	public void setModuleNameId(int moduleNameId) {
		this.moduleNameId = moduleNameId;
	}

	public float getModuleVersionId() {
		return moduleVersionId;
	}

	public void setModuleVersionId(float moduleVersionId) {
		this.moduleVersionId = moduleVersionId;
	}

	public float getFixedInVersionId() {
		return fixedInVersionId;
	}

	public void setFixedInVersionId(float fixedInVersionId) {
		this.fixedInVersionId = fixedInVersionId;
	}

	public Date getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}	
}
