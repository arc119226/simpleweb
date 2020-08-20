package com.hoyetec.api.core.service;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
*****************************************************************************
*
*    Page/Class Name: UserService.java
*              Title: user 商業邏輯類
*        Description: user 商業邏輯類
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月25日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月25日
*             Author: Arc Liu  
*
*****************************************************************************
 */
@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private KeycloakService keycloakService;
	
	/**
	 * 用戶註冊
	 * key
	 * 	username
	 * 	password
	 *  ...//TODO
	 * @return
	 */
	public Optional<Map<String,Object>> register(Map<String,Object> registerInfo){
		logger.info("start");
		return Optional.empty();
	}
	/**
	 * 用戶登入
	 * @param username
	 * @param password
	 * @return
	 */
	public Optional<Map<String,Object>> login(Map<String,Object> loginInfo){
		logger.info("start");
		return Optional.empty();
	}
	
	/**
	 *用戶登出 
	 * @param logoutInfo
	 * @return
	 */
	public Optional<Map<String,Object>> logout(Map<String,Object> logoutInfo){
		logger.info("start");
		return Optional.empty();
	}
	
	/**
	 *   發送重設密碼簡訊
	 * @param phoneNo
	 * @return
	 */
	public Optional<Map<String,Object>> sendResetPasswordByPhone(Map<String,Object> phoneInfo){
		logger.info("start");
		return Optional.empty();
	}
	
	/**
	 *發送重設密碼email 
	 * @param email
	 * @return
	 */
	public Optional<Map<String,Object>> sendResetPasswordByEmail(Map<String,Object> emailInfo){
		logger.info("start");
		return Optional.empty();
	}
	
}
