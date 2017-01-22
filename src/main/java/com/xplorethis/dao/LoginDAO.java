package com.xplorethis.dao;

import com.xplorethis.entity.UserEntity;

public interface LoginDAO {

	UserEntity authenticateUser(String userName, String password);

}
