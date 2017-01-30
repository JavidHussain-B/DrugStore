package com.xplorethis.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xplorethis.dao.LoginDAO;
import com.xplorethis.entity.MenuEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.exception.ApplicationDBException;
import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.service.LoginService;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.vo.MenuVO;
import com.xplorethis.vo.UserVO;

public class LoginServiceImpl implements LoginService {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	LoginDAO loginDAO;
	
	public UserVO authenticateUser(String userName, String password) {
		UserVO vo = null;
		try {
			UserEntity user = loginDAO.authenticateUser(userName, password);
			if(user != null) {
				vo = new UserVO();
				vo.setGroupId(user.getGroupId());
				vo.setUserName(user.getUserName());
				vo.setEmail(user.getEmail());
				vo.setPassword(user.getPassword());
				vo.setPhoneNo(user.getPhoneNo());
				vo.setUserId(user.getUserId());
			}
		} catch(Exception e) {
			logger.error("Error occured while authenticating User ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return vo;
	}

	@Override
	public MenuVO getMenus(int groupId) throws ApplicationServiceException {
		MenuVO vo = null;
		try {
			MenuEntity menu = loginDAO.getMenus(groupId);
			if(menu != null) {
				vo = new MenuVO();
				vo.setMenuId(menu.getMenuId());
				vo.setDescription(menu.getDescription());
				vo.setParentScreenInt(menu.getParentScreenInt());
				vo.setSequenceNo(menu.getSequenceNo());
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getting menus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return vo;
	}
	
	public MenuVO getSubMenus(int groupId, int parentId) throws ApplicationServiceException {
		MenuVO vo = null;
		try {
			MenuEntity menu = loginDAO.getSubMenus(groupId, parentId);
			if(menu != null) {
				vo = new MenuVO();
				vo.setMenuId(menu.getMenuId());
				vo.setDescription(menu.getDescription());
				vo.setParentScreenInt(menu.getParentScreenInt());
				vo.setSequenceNo(menu.getSequenceNo());
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getting sub menus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return vo;
	}
	
}
