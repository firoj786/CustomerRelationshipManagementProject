package com.src.java.crm.controller.employee;

import java.util.Comparator;

import com.src.java.crm.dto.DesTable;

public class DesTableComparatorByCustomerName implements Comparator<DesTable> {
	private int order;
	
	public DesTableComparatorByCustomerName(int order) {
		this.order = order;
	}
	
	@Override
	public int compare(DesTable dt1, DesTable dt2) {
		return order * dt1.getUserFullName().compareTo(dt2.getUserFullName());
	}

	public void setOrder(int order) {
		this.order = order;
	}	
}