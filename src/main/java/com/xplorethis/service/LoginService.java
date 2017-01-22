package com.xplorethis.service;

import com.xplorethis.vo.UserMenuAccessVO;
import com.xplorethis.vo.UserVO;

public interface LoginService {

	UserVO authenticateUser(String userName, String password);

	UserMenuAccessVO getMenus(String userName, int groupId);

}
