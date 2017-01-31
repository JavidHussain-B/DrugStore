package com.xplorethis.dao;

import java.util.List;

import com.xplorethis.entity.MenuEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.exception.ApplicationDBException;

public interface LoginDAO {

	UserEntity authenticateUser(String userName, String password);

	List<MenuEntity> getMenus(int groupId) throws ApplicationDBException;

	List<MenuEntity> getSubMenus(int groupId, int parentId) throws ApplicationDBException;

}
