package com.xplorethis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.service.ApplicationService;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.vo.DrugVO;
import com.xplorethis.vo.PurchasedDrugsVO;
import com.xplorethis.vo.ResponseVO;
import com.xplorethis.vo.UserVO;

@Controller
public class ApplicationController {

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(ApplicationController.class);
	
	@Autowired
	ApplicationService appService;
	
	@RequestMapping(value = "/getAvailableDrugs", method = RequestMethod.GET)
	public @ResponseBody List<DrugVO> getAvailableDrugs() {
		List<DrugVO> list = null;
		try {
			list = appService.getAvailableDrugs();
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting AvailableDrugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return list;
	}
	
	@RequestMapping(value = "/addDrug", method = RequestMethod.POST)
	public @ResponseBody ResponseVO addDrug(@RequestBody List<DrugVO> vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			appService.addDrug(vo, uVO.getUserName());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting adding drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/updateDrugs", method = RequestMethod.POST)
	public @ResponseBody ResponseVO updateDrugs(@RequestBody List<DrugVO> vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			appService.updateDrugs(vo, uVO.getUserName());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting adding drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/purchasedDrugs", method = RequestMethod.POST)
	public @ResponseBody ResponseVO purchasedDrugs(@RequestBody List<PurchasedDrugsVO> vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			appService.purchasedDrugs(vo, uVO.getUserId());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting adding drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/addDrug2Cart", method = RequestMethod.POST)
	public @ResponseBody ResponseVO addDrug2Cart(@RequestBody PurchasedDrugsVO vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			appService.addDrug2Cart(vo, uVO.getUserId());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting adding drugs 2 cart ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/approvePurchasedDrugs", method = RequestMethod.POST)
	public @ResponseBody ResponseVO approvePurchasedDrugs(@RequestBody List<PurchasedDrugsVO> vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			appService.approvePurchasedDrugs(vo, uVO.getUserId());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting adding drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/getDrugsStatus", method = RequestMethod.POST)
	public @ResponseBody List<PurchasedDrugsVO> getDrugsStatus(@RequestBody PurchasedDrugsVO vo, HttpServletRequest request) {
		List<PurchasedDrugsVO> list = null;
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			list = appService.getDrugsStatus(vo, uVO.getUserId());
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting adding drugs ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return list;
	}
	
	@RequestMapping(value = "/generateBill", method = RequestMethod.POST)
	public @ResponseBody void generateBill(HttpServletRequest request) {
		try {
			appService.generateBill(request.getParameter("purchasedId"));
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting generateBill ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
	}
	
}