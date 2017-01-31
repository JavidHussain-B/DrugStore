package com.xplorethis.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.xplorethis.dao.LoginDAO;
import com.xplorethis.entity.MenuEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.exception.ApplicationDBException;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.util.DBUtil;

public class LoginDAOImpl implements LoginDAO {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(LoginDAOImpl.class);
	
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
	
	public List<MenuEntity> getMenus(int groupId) throws ApplicationDBException {
		MenuEntity menu = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		List<MenuEntity> list = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			ps = conn.prepareStatement("select m.MENU_ID, m.DESCRIPTION, m.PARENT_SCREEN_ID, m.SEQUENCE_NO from DS_USER_MENU_ACCESS u, DS_MENU m where u.MENU_ID = m.MENU_ID AND m.PARENT_SCREEN_ID = 0 AND u.GROUP_ID = ? ORDER BY m.SEQUENCE_NO");
			ps.setInt(1, groupId);
			rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				menu = new MenuEntity();
				menu.setMenuId(rs.getInt("MENU_ID"));
				menu.setDescription(rs.getString("DESCRIPTION"));
				menu.setParentScreenInt(rs.getInt("PARENT_SCREEN_ID"));
				menu.setSequenceNo(rs.getInt("SEQUENCE_NO"));
				list.add(menu);
			}
		} catch(Exception e) {
			logger.error("Error occured while getMenus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return list;
	}
	
	public List<MenuEntity> getSubMenus(int groupId, int parentId) throws ApplicationDBException {
		MenuEntity menu = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		List<MenuEntity> list = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			ps = conn.prepareStatement("select m.MENU_ID, m.DESCRIPTION, m.PARENT_SCREEN_ID, m.SEQUENCE_NO from DS_USER_MENU_ACCESS u, DS_MENU m where u.MENU_ID = m.MENU_ID AND m.PARENT_SCREEN_ID = ? AND u.GROUP_ID = ? ORDER BY m.SEQUENCE_NO");
			ps.setInt(1, parentId);
			ps.setInt(2, groupId);
			rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				menu = new MenuEntity();
				menu.setMenuId(rs.getInt("MENU_ID"));
				menu.setDescription(rs.getString("DESCRIPTION"));
				menu.setParentScreenInt(rs.getInt("PARENT_SCREEN_ID"));
				menu.setSequenceNo(rs.getInt("SEQUENCE_NO"));
				list.add(menu);
			}
		} catch(Exception e) {
			logger.error("Error occured while getSubMenus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return list;
	}
	
}
