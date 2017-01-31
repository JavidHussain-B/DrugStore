package com.xplorethis.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.xplorethis.dao.AdminDAO;
import com.xplorethis.entity.MenuEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.entity.UserGroupEntity;
import com.xplorethis.exception.ApplicationDBException;
import com.xplorethis.util.ApplicationConstants;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.util.DBUtil;

public class AdminDAOImpl implements AdminDAO {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);
	
	public void addUserGroup(UserGroupEntity userGrp) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO DS_USER_GROUP(GROUP_DESCRIPTION,GROUP_NAME,CREATED_BY,CREATED_DATE) VALUES (?,?,?,?)");
			ps.setString(1, userGrp.getGroupDesc());
			ps.setString(2, userGrp.getGroupName());
			ps.setString(3, userGrp.getCreatedBy());
			ps.setTimestamp(4, (Timestamp) userGrp.getCreatedDate());
			ps.addBatch();
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting addUserGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}

	public void updateUserGroup(UserGroupEntity userGrp) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			String sql = "UPDATE DS_USER_GROUP SET GROUP_DESCRIPTION = ?, GROUP_NAME = ?, LAST_UPDATED_BY = ?, LAST_UPDATED_DATE = ? "
					+ "WHERE GROUP_ID IN (?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userGrp.getGroupDesc());
			ps.setString(2, userGrp.getGroupName());
			ps.setString(3, userGrp.getCreatedBy());
			ps.setTimestamp(4, (Timestamp) userGrp.getCreatedDate());
			ps.setInt(5, userGrp.getGroupId());
			ps.addBatch();
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting updating user group ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}
	
	public List<UserGroupEntity> getAllUserGroups() throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UserGroupEntity> list = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			String sql = "SELECT * FROM DS_USER_GROUP";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<>();
			UserGroupEntity grp = null;
			while(rs.next()) {
				grp = new UserGroupEntity();
				grp.setGroupId(rs.getInt("GROUP_ID"));
				grp.setGroupName(rs.getString("GROUP_NAME"));
				grp.setGroupDesc(rs.getString("GROUP_DESCRIPTION"));
				grp.setCreatedBy(rs.getString("CREATED_BY"));
				list.add(grp);
			}
		} catch(Exception e) {
			logger.error("Error occured while getting getAllUserGroups ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return list;
	}
	
	public List<UserEntity> getAllUsers4aGroup(String groupId) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UserEntity> list = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			String sql = "SELECT * FROM DS_USERS WHERE GROUP_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupId));
			rs = ps.executeQuery();
			list = new ArrayList<>();
			UserEntity user = null;
			while(rs.next()) {
				user = new UserEntity();
				user.setUserId(rs.getInt("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setEmail(rs.getString("EMAIL_ADDRESS"));
				user.setPhoneNo(rs.getString("PHONE_NUMBER"));
				user.setGroupId(rs.getInt("GROUP_ID"));
				user.setPassword(rs.getString("PASSWORD"));
				list.add(user);
			}
		} catch(Exception e) {
			logger.error("Error occured while getting getAllUsers4aGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return list;
	}
	
	public void addUsers(List<UserEntity> usersList) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO DS_USERS(USER_NAME,EMAIL_ADDRESS,PHONE_NUMBER,GROUP_ID,CREATED_BY,PASSWORD,CREATED_DATE) VALUES (?,?,?,?,?,?,?)");
			for(UserEntity user : usersList) {
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPhoneNo());
				ps.setInt(4, user.getGroupId());
				ps.setString(5, user.getCreatedBy());
				ps.setString(6, user.getPassword());
				ps.setTimestamp(7, (Timestamp) user.getCreatedDate());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting addUsers ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}
	
	public void UpdateUsers(List<UserEntity> usersList) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("UPDATE DS_USERS SET EMAIL_ADDRESS = ?, PHONE_NUMBER = ?, GROUP_ID = ?, LAST_UPDATED_BY = ?, PASSWORD = ?, LAST_UPDATED_DATE = ? WHERE USER_ID = ? AND USER_NAME = ?");
			for(UserEntity user : usersList) {
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPhoneNo());
				ps.setInt(3, user.getGroupId());
				ps.setString(4, user.getCreatedBy());
				ps.setString(5, user.getPassword());
				ps.setTimestamp(6, (Timestamp) user.getCreatedDate());
				ps.setInt(7, user.getUserId());
				ps.setString(8, user.getUserName());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting UpdateUsers ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}
	
	public List<MenuEntity> getAllActionsItems(String groupId) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MenuEntity> list = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			String sql = "select m.DESCRIPTION from DS_MENU m, DS_USER_MENU_ACCESS u where u.MENU_ID = m.MENU_ID and u.GROUP_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupId));
			rs = ps.executeQuery();
			list = new ArrayList<>();
			MenuEntity menu = null;
			while(rs.next()) {
				menu = new MenuEntity();
				menu.setDescription(rs.getString("DESCRIPTION"));
				list.add(menu);
			}
		} catch(Exception e) {
			logger.error("Error occured while getting getAllActionsItems ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return list;
	}
	
}
