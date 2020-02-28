package com.src.java.crm.dto;

public class Customer {
	private Long userId;
	private String company;
	private long prContact;
	private long secContact;
	private String address;
	private String city;
	private String country;
	private String pincode;
	private boolean deleted;
	private int priority;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long getPrContact() {
		return prContact;
	}

	public void setPrContact(long prContact) {
		this.prContact = prContact;
	}

	public long getSecContact() {
		return secContact;
	}

	public void setSecContact(long secContact) {
		this.secContact = secContact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}	
}
