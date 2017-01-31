package com.xplorethis.service;

import java.util.List;

import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.vo.MenuVO;
import com.xplorethis.vo.UserVO;

public interface LoginService {

	UserVO authenticateUser(String userName, String password);

	List<MenuVO> getMenus(int groupId) throws ApplicationServiceException;

	List<MenuVO> getSubMenus(int groupId, int parseInt) throws ApplicationServiceException;

}
