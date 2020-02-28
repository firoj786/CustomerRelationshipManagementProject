package com.src.java.crm.dto;

import java.util.Date;

public class CrmModuleVersionDetail {
	private int id;
	private int moduleId;
	private float version;
	private Date releatseDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public Date getReleatseDate() {
		return releatseDate;
	}

	public void setReleatseDate(Date releatseDate) {
		this.releatseDate = releatseDate;
	}
}
