package com.xplorethis.service;

import java.util.List;

import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.vo.DrugVO;
import com.xplorethis.vo.PurchasedDrugsVO;

public interface ApplicationService {

	List<DrugVO> getAvailableDrugs() throws ApplicationServiceException;

	void addDrug(List<DrugVO> vo, String userName) throws ApplicationServiceException;

	void updateDrugs(List<DrugVO> vo, String userName) throws ApplicationServiceException;

	void purchasedDrugs(List<PurchasedDrugsVO> vo, int userId) throws ApplicationServiceException;

	void addDrug2Cart(PurchasedDrugsVO vo, int userId) throws ApplicationServiceException;

	void approvePurchasedDrugs(List<PurchasedDrugsVO> vo, int userId) throws ApplicationServiceException;

	List<PurchasedDrugsVO> getDrugsStatus(PurchasedDrugsVO vo, int userId) throws ApplicationServiceException;

	void generateBill(String parameter) throws ApplicationServiceException;

}
