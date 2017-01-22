package com.xplorethis.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.xplorethis.dao.LoginDAO;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.serviceImpl.LoginServiceImpl;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.util.DBUtil;

public class LoginDAOImpl implements LoginDAO {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	public UserEntity authenticateUser(String userName, String password) {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		UserEntity user = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			ps = conn.prepareStatement("select * from DS_USERS where USER_NAME = ? and password = ?");
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new UserEntity();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setGroupId(rs.getInt("GROUP_ID"));
				user.setEmail(rs.getString("EMAIL_ADDRESS"));
				user.setPhoneNo(rs.getString("PHONE_NUMBER"));
				user.setPassword(rs.getString("PASSWORD"));
			}
		} catch(Exception e) {
			logger.error("Error occured while authenticating User ::: " + ApplicationUtil.getExceptionStackTrace(e));
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return user;
	}

	
}
