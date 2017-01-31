package com.xplorethis.service;

import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.vo.MenuVO;
import com.xplorethis.vo.UserVO;

public interface LoginService {

	UserVO authenticateUser(String userName, String password);

	MenuVO getMenus(int groupId) throws ApplicationServiceException;

	MenuVO getSubMenus(int groupId, int parseInt) throws ApplicationServiceException;

}
