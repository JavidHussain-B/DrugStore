package com.xplorethis.service;

import java.util.List;

import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.vo.MenuVO;
import com.xplorethis.vo.UserGroupVO;
import com.xplorethis.vo.UserVO;

public interface AdminService {

	void addUserGroup(UserGroupVO vo, String userName) throws ApplicationServiceException;

	void updateUserGroup(UserGroupVO vo, String userName) throws ApplicationServiceException;

	List<UserGroupVO> getAllUserGroups() throws ApplicationServiceException;

	List<UserVO> getAllUsers4aGroup(String parameter) throws ApplicationServiceException;

	void addUsers(List<UserVO> vo, String userName) throws ApplicationServiceException;

	void UpdateUsers(List<UserVO> vo, String userName) throws ApplicationServiceException;

	List<MenuVO> getAllActionsItems(String parameter) throws ApplicationServiceException;

}
