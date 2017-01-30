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
import com.xplorethis.service.AdminService;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.vo.MenuVO;
import com.xplorethis.vo.ResponseVO;
import com.xplorethis.vo.UserGroupVO;
import com.xplorethis.vo.UserVO;

@Controller
public class AdminController {

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/addUserGroup", method = RequestMethod.POST)
	public @ResponseBody ResponseVO addUserGroup(@RequestBody UserGroupVO vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			adminService.addUserGroup(vo, uVO.getUserName());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting addUserGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/updateUserGroup", method = RequestMethod.POST)
	public @ResponseBody ResponseVO updateUserGroup(@RequestBody UserGroupVO vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			adminService.updateUserGroup(vo, uVO.getUserName());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting updateUserGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/getAllUserGroups", method = RequestMethod.GET)
	public @ResponseBody List<UserGroupVO> getAllUserGroups() {
		List<UserGroupVO> list = null;
		try {
			list = adminService.getAllUserGroups();
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting getAllUserGroups ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return list;
	}
	
	@RequestMapping(value = "/getAllUsers4aGroup", method = RequestMethod.GET)
	public @ResponseBody List<UserVO> getAllUsers4aGroup(HttpServletRequest request) {
		List<UserVO> list = null;
		try {
			list = adminService.getAllUsers4aGroup(request.getParameter("GroupId"));
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting getAllUsers4aGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return list;
	}
	
	@RequestMapping(value = "/addUsers", method = RequestMethod.POST)
	public @ResponseBody ResponseVO addUsers(@RequestBody List<UserVO> vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			adminService.addUsers(vo, uVO.getUserName());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting addUsers ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/UpdateUsers", method = RequestMethod.POST)
	public @ResponseBody ResponseVO UpdateUsers(@RequestBody List<UserVO> vo, HttpServletRequest request) {
		ResponseVO res = new ResponseVO();
		try {
			UserVO uVO = (UserVO) request.getSession().getAttribute("LOGGEDIN_USER");
			adminService.UpdateUsers(vo, uVO.getUserName());
			res.setResponseCode("success");
		} catch(ApplicationServiceException e) {
			res.setResponseCode("fail");
			res.setResponseDesc(e.getErrorCode()+" :: " +e.getErrorMessage());
			logger.error("Error occured while getting UpdateUsers ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return res;
	}
	
	@RequestMapping(value = "/getAllActionsItems", method = RequestMethod.GET)
	public @ResponseBody List<MenuVO> getAllActionsItems(HttpServletRequest request) {
		List<MenuVO> list = null;
		try {
			list = adminService.getAllActionsItems(request.getParameter("GroupId"));
		} catch(ApplicationServiceException e) {
			logger.error("Error occured while getting getAllActionsItems ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return list;
	}

}