package com.xplorethis.dao;

import java.util.List;

import com.xplorethis.entity.MenuEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.entity.UserGroupEntity;
import com.xplorethis.exception.ApplicationDBException;

public interface AdminDAO {

	void addUserGroup(UserGroupEntity userGrp) throws ApplicationDBException;

	void updateUserGroup(UserGroupEntity userGrp) throws ApplicationDBException;

	List<UserGroupEntity> getAllUserGroups() throws ApplicationDBException;

	List<UserEntity> getAllUsers4aGroup(String groupId) throws ApplicationDBException;

	void addUsers(List<UserEntity> user) throws ApplicationDBException;

	void UpdateUsers(List<UserEntity> user) throws ApplicationDBException;

	List<MenuEntity> getAllActionsItems(String groupId) throws ApplicationDBException;
	
}
