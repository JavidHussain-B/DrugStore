package com.xplorethis.dao;

import com.xplorethis.entity.MenuEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.exception.ApplicationDBException;

public interface LoginDAO {

	UserEntity authenticateUser(String userName, String password);

	MenuEntity getMenus(int groupId) throws ApplicationDBException;

	MenuEntity getSubMenus(int groupId, int parentId) throws ApplicationDBException;

}
