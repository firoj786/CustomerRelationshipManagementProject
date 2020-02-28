package com.src.java.crm.dto;

import java.util.Date;

public class Comment {
	private int id;
	private int defectId;
	private Date commentedOn;
	private Long commentedBy;
	private transient String commentGivenByName; 
	private String description;
	private Long assignedTo;
	private boolean deleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDefectId() {
		return defectId;
	}

	public void setDefectId(int defectId) {
		this.defectId = defectId;
	}

	public Date getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}

	public Long getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(Long commentedBy) {
		this.commentedBy = commentedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getCommentGivenByName() {
		return commentGivenByName;
	}

	public void setCommentGivenByName(String commentGivenByName) {
		this.commentGivenByName = commentGivenByName;
	}	
}
