package com.xplorethis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

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
	public List<MenuVO> getMenus(int groupId) throws ApplicationServiceException {
		List<MenuVO> list = null;
		try {
			List<MenuEntity> menusList = loginDAO.getMenus(groupId);
			if(menusList != null && !menusList.isEmpty()) {
				list = new ArrayList<>();
				MenuVO vo = null;
				for(MenuEntity menu : menusList) {
					vo = new MenuVO();
					vo.setMenuId(menu.getMenuId());
					vo.setDescription(menu.getDescription());
					vo.setParentScreenInt(menu.getParentScreenInt());
					vo.setSequenceNo(menu.getSequenceNo());
					list.add(vo);
				}
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getting menus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return list;
	}
	
	public List<MenuVO> getSubMenus(int groupId, int parentId) throws ApplicationServiceException {
		List<MenuVO> list = null;
		try {
			List<MenuEntity> menusList = loginDAO.getSubMenus(groupId, parentId);
			if(menusList != null && !menusList.isEmpty()) {
				list = new ArrayList<>();
				MenuVO vo = null;
				for(MenuEntity menu : menusList) {
					vo = new MenuVO();
					vo.setMenuId(menu.getMenuId());
					vo.setDescription(menu.getDescription());
					vo.setParentScreenInt(menu.getParentScreenInt());
					vo.setSequenceNo(menu.getSequenceNo());
					list.add(vo);
				}
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getting submenus ::: " + ApplicationUtil.getExceptionStackTrace(e));
		}
		return list;
	}
	
}
