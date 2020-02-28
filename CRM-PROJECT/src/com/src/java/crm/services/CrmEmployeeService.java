package com.src.java.crm.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.src.java.crm.dao.DesTableDAO;
import com.src.java.crm.dto.DesTable;
import com.src.java.crm.exceptions.ServiceException;
import com.src.java.crm.utilities.DBUtil;
import com.src.java.crm.utilities.DefectStatusConstant;


public class CrmEmployeeService extends AbstractGPSUserService {
	
	public List<DesTable> getAllOpenDefecList() {
		List<DesTable> defectList = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			DesTableDAO dtDAO = new DesTableDAO(con);
			defectList = dtDAO.getAllDefectByStatus(DefectStatusConstant.SUBMITTED);
			defectList.addAll(dtDAO.getAllDefectByStatus(DefectStatusConstant.IN_PROGRESS));
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue fetch open defect list, Please contact admin");
		}
		return defectList;
	}
	
	public List<DesTable> getAllClosedDefecList() {
		List<DesTable> defectList = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			DesTableDAO dtDAO = new DesTableDAO(con);
			defectList = dtDAO.getAllDefectByStatus(DefectStatusConstant.CLOSED);
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue fetch closed defect list, Please contact admin");
		}
		return defectList;
	}
	
	public List<DesTable> getAllDefecList() {
		List<DesTable> defectList = null;
		try (Connection con = DBUtil.getInstance().getDbConnection()) {
			DesTableDAO dtDAO = new DesTableDAO(con);
			defectList = dtDAO.getAllDefectByStatus(DefectStatusConstant.SUBMITTED);
			defectList.addAll(dtDAO.getAllDefectByStatus(DefectStatusConstant.IN_PROGRESS));
			defectList.addAll(dtDAO.getAllDefectByStatus(DefectStatusConstant.CLOSED));
		} catch (SQLException e) {
			throw new ServiceException("Internal Server issue fetch open defect list, Please contact admin");
		}
		return defectList;
	}
}
