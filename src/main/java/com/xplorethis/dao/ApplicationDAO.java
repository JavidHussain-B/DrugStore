package com.xplorethis.dao;

import java.util.List;

import com.xplorethis.entity.BillStatementEntity;
import com.xplorethis.entity.DrugEntity;
import com.xplorethis.entity.PurchasedDrugsEntity;
import com.xplorethis.exception.ApplicationDBException;
import com.xplorethis.vo.PurchasedDrugsVO;

public interface ApplicationDAO {

	List<DrugEntity> getAvailableDrugs() throws ApplicationDBException;

	void addDrug(List<DrugEntity> list) throws ApplicationDBException;

	void updateDrugs(List<DrugEntity> list) throws ApplicationDBException;

	void purchasedDrugs(List<PurchasedDrugsEntity> list) throws ApplicationDBException;

	void addDrug2Cart(PurchasedDrugsEntity drug) throws ApplicationDBException;

	void approvePurchasedDrugs(List<PurchasedDrugsEntity> list) throws ApplicationDBException;

	List<PurchasedDrugsEntity> getDrugsStatus(PurchasedDrugsVO pvo, int userId) throws ApplicationDBException;

	List<PurchasedDrugsEntity> getPurchasedDrugsList(String purchasedId) throws ApplicationDBException;

	void generateBill(BillStatementEntity bill) throws ApplicationDBException;
	
}
