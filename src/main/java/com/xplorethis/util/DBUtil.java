package com.xplorethis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * The Class DBUtil.
 */
public class DBUtil {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(DBUtil.class);

	/**
	 * Close connection.
	 *
	 * @param con
	 *            the con
	 * @param rs
	 *            the rs
	 * @param ps
	 *            the ps
	 */
	public static void closeConnection(Connection con, ResultSet rs, PreparedStatement ps, Statement st) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Error occured while closing the ResultSet. Error Message::" + e.getMessage());
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("Error occured while closing the PreparedStatement. Error Message::" + e.getMessage());
			}
		}
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				logger.error("Error occured while closing the connection. Error Message::" + e.getMessage());
			}
		}
		if (st != null) {
			try {
				st.close();
				st = null;
			} catch (SQLException e) {
				logger.error("Error occured while closing the connection. Error Message::" + e.getMessage());
			}
		}
	}

	public static Connection getJdbcOdbcConnection(String username, String password) {
		Connection conn = null;
		String URL = "jdbc:odbc:drugStoreDSN";
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection(URL, username, password);
		} catch (SQLException e) {
			logger.error("Got error while creating JDBC ODBC connection." + ApplicationUtil.getExceptionStackTrace(e));
		} catch (Exception e) {
			logger.error("Got error while creating JDBC ODBC connection." + ApplicationUtil.getExceptionStackTrace(e));
		}
		return conn;
	}

}
