package com.src.java.crm.dto;

import java.util.Date;
import java.util.List;

public class SearchCriteria {
	private List<String> keyList;
	private int severityId;
	private Date creationDate;
	private Long assingedTo;
	private boolean reOpened;
	private int moduleId;

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public int getSeverityId() {
		return severityId;
	}

	public void setSeverityId(int severityId) {
		this.severityId = severityId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getAssingedTo() {
		return assingedTo;
	}

	public void setAssingedTo(Long assingedTo) {
		this.assingedTo = assingedTo;
	}

	public boolean isReOpened() {
		return reOpened;
	}

	public void setReOpened(boolean reOpened) {
		this.reOpened = reOpened;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
}
