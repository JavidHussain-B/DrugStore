package com.xplorethis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xplorethis.dao.ApplicationDAO;
import com.xplorethis.entity.BillStatementEntity;
import com.xplorethis.entity.DrugEntity;
import com.xplorethis.entity.PurchasedDrugsEntity;
import com.xplorethis.exception.ApplicationDBException;
import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.service.ApplicationService;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.vo.DrugVO;
import com.xplorethis.vo.PurchasedDrugsVO;

/**
 * The Class ApplicationServiceImpl.
 */
public class ApplicationServiceImpl implements ApplicationService {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ApplicationServiceImpl.class);
	
	@Autowired
	ApplicationDAO appDAO;

	@Override
	public List<DrugVO> getAvailableDrugs() throws ApplicationServiceException {
		List<DrugVO> drugsList = null;
		try {
			List<DrugEntity> list = appDAO.getAvailableDrugs();
			DrugVO vo = null;
			drugsList = new ArrayList<>();
			if(list != null && !list.isEmpty()) {
				for(DrugEntity de : list) {
					vo = new DrugVO();
					vo.setDrugId(de.getDrugId());
					vo.setDescription(de.getDescription());
					vo.setAvailableQty(de.getAvailableQty());
					vo.setQtyPerUnit(de.getQtyPerUnit());
					vo.setUnitRate(de.getUnitRate());
					drugsList.add(vo);
				}
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getAvailableDrugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
		return drugsList;
	}

	@Override
	public void addDrug(List<DrugVO> dvo, String userName) throws ApplicationServiceException {
		try {
			DrugEntity drug = null;
			List<DrugEntity> list = new ArrayList<>();
			for(DrugVO vo : dvo) {
				drug = new DrugEntity();
				drug.setDescription(vo.getDescription());
				drug.setAvailableQty(vo.getAvailableQty());
				drug.setQtyPerUnit(vo.getQtyPerUnit());
				drug.setUnitRate(vo.getUnitRate());
				drug.setCreatedBy(vo.getCreatedBy());
				drug.setCreatedDate(ApplicationUtil.getCurrentTimeStamp());
			}
			appDAO.addDrug(list);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while add Drug ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}

	@Override
	public void updateDrugs(List<DrugVO> dvo, String userName) throws ApplicationServiceException {
		try {
			DrugEntity drug = null;
			List<DrugEntity> list = new ArrayList<>();
			for(DrugVO vo : dvo) {
				drug = new DrugEntity();
				drug.setDrugId(vo.getDrugId());
				drug.setDescription(vo.getDescription());
				drug.setAvailableQty(vo.getAvailableQty());
				drug.setQtyPerUnit(vo.getQtyPerUnit());
				drug.setUnitRate(vo.getUnitRate());
				drug.setLastUpdatedBy(vo.getCreatedBy());
				drug.setLastUpdatedDate(ApplicationUtil.getCurrentTimeStamp());
			}
			appDAO.updateDrugs(list);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while updating Drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}
	
	public void purchasedDrugs(List<PurchasedDrugsVO> pvo, int userId) throws ApplicationServiceException {
		try {
			PurchasedDrugsEntity drug = null;
			List<PurchasedDrugsEntity> list = new ArrayList<>();
			for(PurchasedDrugsVO vo : pvo) {
				drug = new PurchasedDrugsEntity();
				drug.setDrugId(vo.getDrugId());
				drug.setPurchasedBy(userId);
				drug.setPurchasedDate(ApplicationUtil.getCurrentTimeStamp());
				drug.setStatus("P");
				drug.setComments("");
				drug.setPurchasedQty(vo.getPurchasedQty());
			}
			appDAO.purchasedDrugs(list);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while purchasing Drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}
	
	public void addDrug2Cart(PurchasedDrugsVO vo, int userId) throws ApplicationServiceException {
		try {
			PurchasedDrugsEntity drug = new PurchasedDrugsEntity();
			drug.setDrugId(vo.getDrugId());
			drug.setPurchasedBy(userId);
			drug.setPurchasedDate(ApplicationUtil.getCurrentTimeStamp());
			drug.setStatus("D");
			drug.setComments("");
			drug.setPurchasedQty(vo.getPurchasedQty());
			appDAO.addDrug2Cart(drug);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while adding Drugs 2 cart ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}

	@Override
	public void approvePurchasedDrugs(List<PurchasedDrugsVO> pvo, int userId) throws ApplicationServiceException {
		try {
			PurchasedDrugsEntity drug = null;
			List<PurchasedDrugsEntity> list = new ArrayList<>();
			for(PurchasedDrugsVO vo : pvo) {
				drug = new PurchasedDrugsEntity();
				drug.setPurchasedId(vo.getPurchasedId());
				drug.setDrugId(vo.getDrugId());
				drug.setPurchasedBy(userId);
				drug.setPurchasedDate(ApplicationUtil.getCurrentTimeStamp());
				drug.setStatus(vo.getStatus());
				drug.setComments(vo.getComments());
				drug.setPurchasedQty(vo.getPurchasedQty());
			}
			appDAO.approvePurchasedDrugs(list);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while approvePurchasedDrugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}
	
	public List<PurchasedDrugsVO> getDrugsStatus(PurchasedDrugsVO pvo, int userId) throws ApplicationServiceException {
		List<PurchasedDrugsVO> list = null;
		try {
			PurchasedDrugsVO drug = null;
			List<PurchasedDrugsEntity> drugsList = appDAO.getDrugsStatus(pvo, userId);
			if(drugsList != null && !drugsList.isEmpty()) {
				list = new ArrayList<>();
				for(PurchasedDrugsEntity vo : drugsList) {
					drug = new PurchasedDrugsVO();
					drug.setPurchasedId(vo.getPurchasedId());
					drug.setDrugId(vo.getDrugId());
					drug.setPurchasedBy(vo.getPurchasedBy());
					drug.setUserName(vo.getUser().getUserName());
					drug.setPurchasedDate(vo.getPurchasedDate());
					drug.setStatus(vo.getStatus());
					drug.setComments(vo.getComments());
					drug.setPurchasedQty(vo.getPurchasedQty());
					list.add(drug);
				}
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getDrugsStatus ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
		return list;
	}
	
	public void generateBill(String purchasedId) throws ApplicationServiceException {
		try {
			List<PurchasedDrugsEntity> drugsList = appDAO.getPurchasedDrugsList(purchasedId);
			BillStatementEntity bill = new BillStatementEntity();
			bill.setPurchasedId(Integer.parseInt(purchasedId));
			double amount = 0;
			for(PurchasedDrugsEntity drug : drugsList) {
				amount += drug.getAmount();
			}
			bill.setAmount(amount);
			appDAO.generateBill(bill);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while generateBill ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}

}
