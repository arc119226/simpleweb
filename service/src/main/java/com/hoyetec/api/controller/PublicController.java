package com.hoyetec.api.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoyetec.api.core.service.UserService;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: PublicController.java
*              Title: 公開訪問
*        Description: header 不需要帶 token
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月25日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月25日
*             Author: Arc Liu  
*
*****************************************************************************
 */
@RestController
@RequestMapping("/public")
public class PublicController {

	private final Logger logger = LoggerFactory.getLogger(PublicController.class);

	@Autowired UserService userService;
	
	@GetMapping("/hello")
	public @ResponseBody String hello(Principal principal) {
		return "hello";
	}
	
	/**
	 * 註冊帳號帳號
	 * 輸入帳號密碼
	 * @return 註冊成功或失敗結果訊息
	 * @throws Exception 
	 */
	@PostMapping(value = "/register",produces="application/json")
	public @ResponseBody ResponseEntity<?> register(@RequestBody Map<String, Object> obj){
		if(obj.get("username")!=null && obj.get("password")!=null) {
			Optional<Map<String,Object>> optionResult = this.userService.register(obj);
			if(optionResult.isPresent()) {
				return ResponseEntity.ok(optionResult.get());
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("status", "fail");
		resultMap.put("reason", "param error");
		return ResponseEntity.ok(resultMap);
	}
	
	/**
	 * 登入系統
	 * 帶入帳號與密碼登入回傳用戶基本資訊
	 * 登入失敗回傳錯誤訊息
	 * @return
	 */
	@PostMapping(value = "/login",produces="application/json")
	public @ResponseBody ResponseEntity<?> login(@RequestBody Map<String, Object> obj) {
		if(obj.get("username")!=null && obj.get("password")!=null) {
			Optional<Map<String,Object>> optionResult = this.userService.login(obj);
			if(optionResult.isPresent()) {
				return ResponseEntity.ok(optionResult.get());
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("status", "fail");
		resultMap.put("reason", "param error");
		return ResponseEntity.ok(resultMap);
	}
	
	/**
	 * 忘記密碼  對手機發送新密碼
	 * 輸入手機號碼
	 * @param obj
	 * @return
	 */
	@PostMapping(value = "/forget",produces="application/json")
	public @ResponseBody ResponseEntity<?> forget(@RequestBody Map<String, Object> obj) {
		if(obj.get("phone") != null) {
			Optional<Map<String,Object>> resultMap = this.userService.sendResetPasswordByPhone(obj);
			if(resultMap.isPresent()) {
				return ResponseEntity.ok(resultMap);
			}
		}
		if(obj.get("email") != null) {
			Optional<Map<String,Object>> resultMap = this.userService.sendResetPasswordByPhone(obj);
			if(resultMap.isPresent()) {
				return ResponseEntity.ok(resultMap);
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("status", "fail");
		resultMap.put("reason", "param error");
		return ResponseEntity.ok(resultMap);
	}
	
}
