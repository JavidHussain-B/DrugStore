package com.xplorethis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xplorethis.dao.AdminDAO;
import com.xplorethis.entity.MenuEntity;
import com.xplorethis.entity.UserEntity;
import com.xplorethis.entity.UserGroupEntity;
import com.xplorethis.exception.ApplicationDBException;
import com.xplorethis.exception.ApplicationServiceException;
import com.xplorethis.service.AdminService;
import com.xplorethis.util.ApplicationUtil;
import com.xplorethis.vo.MenuVO;
import com.xplorethis.vo.UserGroupVO;
import com.xplorethis.vo.UserVO;

/**
 * The Class ApplicationServiceImpl.
 */
public class AdminServiceImpl implements AdminService {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	AdminDAO adminDAO;

	@Override
	public void addUserGroup(UserGroupVO vo, String userName) throws ApplicationServiceException {
		try {
			UserGroupEntity userGrp = prepareUserGroupEntity(vo, userName);
			adminDAO.addUserGroup(userGrp);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while addUserGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}

	private UserGroupEntity prepareUserGroupEntity(UserGroupVO vo, String userName) {
		UserGroupEntity userGrp = new UserGroupEntity();
		userGrp.setGroupName(vo.getGroupName());
		userGrp.setGroupDesc(vo.getGroupDesc());
		userGrp.setCreatedBy(userName);
		userGrp.setCreatedDate(ApplicationUtil.getCurrentTimeStamp());
		return userGrp;
	}
	
	public void updateUserGroup(UserGroupVO vo, String userName) throws ApplicationServiceException {
		try {
			UserGroupEntity userGrp = prepareUserGroupEntity(vo, userName);
			userGrp.setGroupId(vo.getGroupId());
			adminDAO.updateUserGroup(userGrp);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while updateUserGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}
	
	public List<UserGroupVO> getAllUserGroups() throws ApplicationServiceException {
		List<UserGroupVO> grpsList = null;
		try {
			List<UserGroupEntity> list = adminDAO.getAllUserGroups();
			if(list != null && !list.isEmpty()) {
				grpsList = new ArrayList<>();
				UserGroupVO vo = null;
				for(UserGroupEntity grp : list) {
					vo = new UserGroupVO();
					vo.setGroupId(grp.getGroupId());
					vo.setGroupDesc(grp.getGroupDesc());
					vo.setGroupName(grp.getGroupName());
					vo.setCreatedBy(grp.getCreatedBy());
					grpsList.add(vo);
				}
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getAllUserGroups ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
		return grpsList;
	}
	
	public List<UserVO> getAllUsers4aGroup(String groupId) throws ApplicationServiceException {
		List<UserVO> usersList = null;
		try {
			List<UserEntity> list = adminDAO.getAllUsers4aGroup(groupId);
			if(list != null && !list.isEmpty()) {
				usersList = new ArrayList<>();
				UserVO vo = null;
				for(UserEntity user : list) {
					vo = new UserVO();
					vo.setUserId(user.getUserId());
					vo.setUserName(user.getUserName());
					vo.setEmail(user.getEmail());
					vo.setPhoneNo(user.getPhoneNo());
					vo.setGroupId(user.getGroupId());
					vo.setPassword(user.getPassword());
					usersList.add(vo);
				}
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getAllUsers4aGroup ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
		return usersList;
	}

	public void addUsers(List<UserVO> vo, String userName) throws ApplicationServiceException {
		try {
			List<UserEntity> user = prepareUserEntity(vo, userName);
			adminDAO.addUsers(user);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while addUsers ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}

	private List<UserEntity> prepareUserEntity(List<UserVO> list, String userName) {
		List<UserEntity> usersList = new ArrayList<>();
		UserEntity user = null;
		for(UserVO vo : list) {
			user = new UserEntity();
			if(vo.getUserId() != 0) {
				user.setUserId(vo.getUserId());
			}
			user.setUserName(vo.getUserName());
			user.setEmail(vo.getEmail());
			user.setPhoneNo(vo.getPhoneNo());
			user.setPassword(vo.getPassword());
			user.setGroupId(vo.getGroupId());
			user.setCreatedBy(userName);
			user.setCreatedDate(ApplicationUtil.getCurrentTimeStamp());
			usersList.add(user);
		}
		return usersList;
	}
	
	public void UpdateUsers(List<UserVO> vo, String userName) throws ApplicationServiceException {
		try {
			List<UserEntity> user = prepareUserEntity(vo, userName);
			adminDAO.UpdateUsers(user);
		} catch(ApplicationDBException e) {
			logger.error("Error occured while UpdateUsers ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
	}
	
	public List<MenuVO> getAllActionsItems(String groupId) throws ApplicationServiceException {
		List<MenuVO> usersList = null;
		try {
			List<MenuEntity> list = adminDAO.getAllActionsItems(groupId);
			if(list != null && !list.isEmpty()) {
				usersList = new ArrayList<>();
				MenuVO vo = null;
				for(MenuEntity menu : list) {
					vo = new MenuVO();
					vo.setDescription(menu.getDescription());
					usersList.add(vo);
				}
			}
		} catch(ApplicationDBException e) {
			logger.error("Error occured while getAllActionsItems ::: " + ApplicationUtil.getExceptionStackTrace(e));
			throw new ApplicationServiceException(e.getErrorCode(),e.getMessage());
		}
		return usersList;
	}
	
}
