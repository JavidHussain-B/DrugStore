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

import com.xplorethis.dao.ApplicationDAO;
import com.xplorethis.entity.BillStatementEntity;
import com.xplorethis.entity.DrugEntity;
import com.xplorethis.entity.PurchasedDrugsEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.exception.ApplicationDBException;
import com.xplorethis.util.ApplicationConstants;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.util.DBUtil;
import com.xplorethis.vo.PurchasedDrugsVO;

public class ApplicationDAOImpl implements ApplicationDAO {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ApplicationDAOImpl.class);
	
	@Override
	public List<DrugEntity> getAvailableDrugs() throws ApplicationDBException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		List<DrugEntity> drugList = null;
		try {
			drugList = new ArrayList<>();
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			ps = conn.prepareStatement("select * from DS_DRUG");
			rs = ps.executeQuery();
			DrugEntity drug = null;
			while(rs.next()) {
				drug = new DrugEntity();
				drug.setDrugId(rs.getInt("DRUG_ID"));
				drug.setDescription(rs.getString("DESCRIPTION"));
				drug.setAvailableQty(rs.getInt("AVAILABLE_QUANTITY"));
				drug.setUnitRate(rs.getDouble("UNIT_RATE"));
				drugList.add(drug);
			}
		} catch(Exception e) {
			logger.error("Error occured while getting drugs list ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return drugList;
	}

	@Override
	public void addDrug(List<DrugEntity> list) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO DS_DRUG(DESCRIPTION,AVAILABLE_QUANTITY,QUANTITY_PER_UNIT,UNIT_RATE,CREATED_BY,CREATED_DATE) VALUES (?,?,?,?,?,?)");
			for(DrugEntity drug : list) {
				ps.setString(1, drug.getDescription());
				ps.setInt(2, drug.getAvailableQty());
				ps.setInt(3, drug.getQtyPerUnit());
				ps.setDouble(4, drug.getUnitRate());
				ps.setString(5, drug.getCreatedBy());
				ps.setTimestamp(6, (Timestamp) drug.getCreatedDate());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting adding drug ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}

	@Override
	public void updateDrugs(List<DrugEntity> list) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			String sql = "UPDATE DS_DRUG SET DESCRIPTION = ?, AVAILABLE_QUANTITY = ?, QUANTITY_PER_UNIT = ?, UNIT_RATE = ?, "
					+ "LAST_MODIFIED_BY = ?, LAST_MODIFIED_TIME = ? WHERE DRUG_ID IN (?)";
			ps = conn.prepareStatement(sql);
			for(DrugEntity drug : list) {
				ps.setString(1, drug.getDescription());
				ps.setInt(2, drug.getAvailableQty());
				ps.setInt(3, drug.getQtyPerUnit());
				ps.setDouble(4, drug.getUnitRate());
				ps.setString(5, drug.getLastUpdatedBy());
				ps.setTimestamp(6, (Timestamp) drug.getLastUpdatedDate());
				ps.setInt(7, drug.getDrugId());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting updating drug ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}
	
	public void purchasedDrugs(List<PurchasedDrugsEntity> list) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO DS_PURCHASED_DRUGS(DRUG_ID, PURCHASED_BY, DATE_OF_PURCHASE, STATUS, COMMENTS, PURCHASED_QUANTITY)"
					+ " VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			for(PurchasedDrugsEntity drug : list) {
				ps.setInt(1, drug.getDrugId());
				ps.setInt(2, drug.getPurchasedBy());
				ps.setTimestamp(3, (Timestamp) drug.getPurchasedDate());
				ps.setString(4, drug.getStatus());
				ps.setString(5, drug.getComments());
				ps.setInt(6, drug.getPurchasedQty());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting purchasing drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}
	
	public void addDrug2Cart(PurchasedDrugsEntity drug) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO DS_PURCHASED_DRUGS(DRUG_ID, PURCHASED_BY, DATE_OF_PURCHASE, STATUS, COMMENTS, PURCHASED_QUANTITY)"
					+ " VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, drug.getDrugId());
			ps.setInt(2, drug.getPurchasedBy());
			ps.setTimestamp(3, (Timestamp) drug.getPurchasedDate());
			ps.setString(4, drug.getStatus());
			ps.setString(5, drug.getComments());
			ps.setInt(6, drug.getPurchasedQty());
			ps.addBatch();
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting adding drugs 2 cart ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}
	
	public void approvePurchasedDrugs(List<PurchasedDrugsEntity> list) throws ApplicationDBException {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			conn.setAutoCommit(false);
			String sql = "UPDATE DS_PURCHASED_DRUGS SET STATUS = ?, COMMENTS = ? WHERE PURCHASED_ID = ?";
			ps = conn.prepareStatement(sql);
			for(PurchasedDrugsEntity drug : list) {
				ps.setString(1, drug.getStatus());
				ps.setString(2, drug.getComments());
				ps.setInt(3, drug.getPurchasedId());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch(Exception e) {
			logger.error("Error occured while getting approvePurchasedDrugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, null, ps, st);
		}
	}
	
	public List<PurchasedDrugsEntity> getDrugsStatus(PurchasedDrugsVO pvo, int userId) throws ApplicationDBException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		List<PurchasedDrugsEntity> drugList = null;
		try {
			drugList = new ArrayList<>();
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			String sql = "SELECT PURCHASED_ID, DRUG_ID, PURCHASED_BY, (SELECT USER_NAME FROM DS_USERS WHERE USER_ID = PURCHASED_BY) AS USER_NAME,"
					+ " DATE_OF_PURCHASE, STATUS, COMMENTS, PURCHASED_QUANTITY FROM DS_PURCHASED_DRUGS WHERE STATUS = ?";
			if(userId != 1) {
				sql = " AND PURCHASED_BY = ?";
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, pvo.getStatus());
			if(userId != 1) {
				ps.setInt(2, userId);
			}
			rs = ps.executeQuery();
			PurchasedDrugsEntity drug = null;
			UserEntity user = null;
			while(rs.next()) {
				drug = new PurchasedDrugsEntity();
				user = new UserEntity();
				drug.setPurchasedId(rs.getInt("PURCHASED_ID"));
				drug.setDrugId(rs.getInt("DRUG_ID"));
				drug.setPurchasedBy(rs.getInt("PURCHASED_BY"));
				user.setUserName(rs.getString("USER_NAME"));
				drug.setUser(user);
				drug.setPurchasedDate(rs.getTimestamp("DATE_OF_PURCHASE"));
				drug.setStatus(rs.getString("STATUS"));
				drug.setComments(rs.getString("COMMENTS"));
				drug.setPurchasedQty(rs.getInt("PURCHASED_QUANTITY"));
				drugList.add(drug);
			}
		} catch(Exception e) {
			logger.error("Error occured while getting getDrugsStatus ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return drugList;
	}
	
	public List<PurchasedDrugsEntity> getPurchasedDrugsList(String purchasedId) throws ApplicationDBException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		List<PurchasedDrugsEntity> drugList = null;
		try {
			drugList = new ArrayList<>();
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			String sql = "SELECT PURCHASED_ID, DRUG_ID, PURCHASED_BY, (SELECT USER_NAME FROM DS_USERS WHERE USER_ID = PURCHASED_BY) AS USER_NAME,"
					+ " DATE_OF_PURCHASE, STATUS, COMMENTS, PURCHASED_QUANTITY FROM DS_PURCHASED_DRUGS WHERE STATUS = ? AND PURCHASED_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "D");
			ps.setInt(2, Integer.parseInt(purchasedId));
			rs = ps.executeQuery();
			PurchasedDrugsEntity drug = null;
			UserEntity user = null;
			while(rs.next()) {
				drug = new PurchasedDrugsEntity();
				user = new UserEntity();
				drug.setPurchasedId(rs.getInt("PURCHASED_ID"));
				drug.setDrugId(rs.getInt("DRUG_ID"));
				drug.setPurchasedBy(rs.getInt("PURCHASED_BY"));
				user.setUserName(rs.getString("USER_NAME"));
				drug.setUser(user);
				drug.setPurchasedDate(rs.getTimestamp("DATE_OF_PURCHASE"));
				drug.setStatus(rs.getString("STATUS"));
				drug.setComments(rs.getString("COMMENTS"));
				drug.setPurchasedQty(rs.getInt("PURCHASED_QUANTITY"));
				drugList.add(drug);
			}
		} catch(Exception e) {
			logger.error("Error occured while getting getDrugsStatus ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
		return drugList;
	}
	
	public void generateBill(BillStatementEntity bill) throws ApplicationDBException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getJdbcOdbcConnection(null, null);
			String sql = "INSERT INTO DS_BILL_STATEMENT(PURCHASED_ID,AMOUNT) VALUES (?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bill.getPurchasedId());
			ps.setDouble(2, bill.getAmount());
			rs = ps.executeQuery();
		} catch(Exception e) {
			logger.error("Error occured while getting generateBill ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationDBException(e.toString(),ApplicationConstants.STR_REF+ new Date().getTime());
		} finally {
			DBUtil.closeConnection(conn, rs, ps, st);
		}
	}
		
}
