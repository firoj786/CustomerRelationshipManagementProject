package com.src.java.crm.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.src.java.crm.exceptions.DatabaseConnectionException;

/**
 * Single Design Pattern
 * 
 * @author Firoj Khan
 *
 */
public final class DBUtil {
	private static final DBUtil dbUtil = new DBUtil();
	
	public static DBUtil getInstance() {
		return dbUtil;
	}
	
	public Connection getDbConnection() {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL("jdbc:mysql://localhost:3306/gpssim_crm");
		ds.setUser("root");
		ds.setPassword("root");
		Connection con = null;
		try {
			con = ds.getConnection();
			
			if(con == null) {
				throw new DatabaseConnectionException("Database is down, Please contact admin");
			}
		} catch (SQLException e) {
			throw new DatabaseConnectionException("Database is down, Please contact admin");
		}
		return con;
	}

	private DBUtil() {}
}
