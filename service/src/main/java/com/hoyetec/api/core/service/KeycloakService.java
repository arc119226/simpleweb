package com.hoyetec.api.core.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.hoyetec.api.util.RestTemplateUtil;
import com.hoyetec.api.vo.KeycloakTokenInfo;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: KeycloakService.java
*              Title: Keycloak通信類
*        Description:
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月25日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月25日
*             Author: Arc Liu  
*
*****************************************************************************
 */
@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class KeycloakService {
	
	private final Logger logger = LoggerFactory.getLogger(KeycloakService.class);
	
	@Value("${keycloak.auth-server-url}")
	private String apiHost;
	/**
	 * admin user 帳號
	 */
	@Value("${service.keycloak.admin.accouunt}")
	private String adminAccount;
	/**
	 * admin user password
	 */
	@Value("${service.keycloak.admin.password}")
	private String adminPassword;
	/**
	 * keycloak client id
	 */
	@Value("${service.keycloak.client.id}")
	private String clientId;
	/**
	 * keycloak client secret
	 */
	@Value("${service.keycloak.client.secret}")
	private String clientSecret;
	/**
	 *  授權模式 目前都用 password
	 */
	@Value("${service.keycloak.client.grant.type}")
	private String grantType;
	/**
	 *token endpoint
	 */
	@Value("${service.keycloak.token.endpoint}")
	private String tokenEndpoint;//done
	/**
	 * auth endpoint
	 */
	@Value("${service.keycloak.auth.endpoint}")
	private String authEndpoint;
	/**
	 * introspection endpoint
	 */
	@Value("${service.keycloak.introspection.endpoint}")
	private String introspectionEndpoint;
	/**
	 * logout endpoint
	 */
	@Value("${service.keycloak.logout.endpoint}")
	private String logoutEndpoint;//done
	/**
	 * users endpoint
	 */
	@Value("${service.keycloak.users.endpoint}")
	private String usersEndpoint;
	/**
	 * roles endpoint
	 */
	@Value("${service.keycloak.roles.endpoint}")
	private String rolesEndpoint;//done
	/**
	 * timeout
	 */
	@Value("${service.keycloak.timeout}")
	private Integer timeout;

	@Value("${service.keycloak.createUser.json.template}")
	private String createUserJsonTemplate;

	@Value("${service.keycloak.asignUserRole.json.template}")
	private String asignUserRoleJsonTemplate;
	
	@Value("${service.keycloak.resetPassword.json.template}")
	private String resetPasswordJsonTempalte;
	
	@Value("${service.keycloak.userEnable.json.template}")
	private String userEnableTemplate;
	
	private KeycloakTokenInfo adminTokenInfo;
	
	@PostConstruct
	private void init() {		
		Optional<KeycloakTokenInfo> result = this.token(this.adminAccount,this.adminPassword);
		result.ifPresent(obj->this.adminTokenInfo=obj);
//test code
//		Optional<Map<String,Object>> user=this.findUserByUserName("arc");
//		if(!user.isPresent()) {
//			this.createUser("arc", UUID.randomUUID().toString(), "pwd123");
//			user=this.findUserByUserName("arc");
//			Optional<Map<String,Object>> role = this.findRoleByRoleName("user");
//			if(role.isPresent()) {
//				boolean asiginRoleResult=this.asignUserRole(user.get().get("id").toString(),role.get().get("id").toString(), role.get().get("name").toString());
//				logger.info("resetResult "+asiginRoleResult);
//			}
//			this.refreshToken(adminTokenInfo.getRefresh_token()).ifPresent(obj->this.adminTokenInfo=obj);
//		}
//		
//		if(user.isPresent()) {			
//			user.ifPresent(_user->{
//				boolean resetResult = this.resetPassword(_user.get("id").toString(), "zzzz");
//				logger.info("resetResult "+resetResult);
//				Optional<KeycloakTokenInfo> userToken = this.token("arc", "zzzz");
//				if(userToken.isPresent()) {
//					logger.info("get user token success");
//				}else {
//					logger.info("get user token fail");
//				}
//				this.enableUser(_user.get("id").toString(), "false");
//				userToken = this.token("arc", "zzzz");
//				if(userToken.isPresent()) {
//					logger.info("get user token success");
//				}else {
//					logger.info("get user token fail");
//				}
//			});
//		}
//	
	}
	
	/**
	 * request結束時註銷token
	 */
	@PreDestroy
    public void preDestroy()  {
		this.logout(this.adminTokenInfo.getAccess_token(), this.adminTokenInfo.getRefresh_token());
    }

	
	
	/**
	 * get token by password mode
	 * @author arc
	 * @param username
	 * @param password
	 * @return
	 */
	public Optional<KeycloakTokenInfo> token(String username,String password) {
		logger.info("start");
		LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
		postParam.add("client_id",this.clientId);
		postParam.add("client_secret",this.clientSecret);
		postParam.add("grant_type", this.grantType);
		postParam.add("username",username.toLowerCase());
		postParam.add("password", password);
		Optional<String> result = this.doPostPublicAPI(this.apiHost, this.tokenEndpoint, postParam);
		if(result.isPresent()) {
			KeycloakTokenInfo staff = new Gson().fromJson(result.get(), KeycloakTokenInfo.class);
			return Optional.of(staff);
		}else {
			return Optional.empty();
		}
	}
	
	/**
	 * refresh_token
	 * @author arc
	 * @param username
	 * @param password
	 * @return
	 */
	public Optional<KeycloakTokenInfo> refreshToken(String refresh_token) {
		logger.info("start");
		LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
		postParam.add("client_id",this.clientId);
		postParam.add("client_secret",this.clientSecret);
		postParam.add("grant_type", "refresh_token");
		postParam.add("refresh_token",refresh_token);
		Optional<String> result = this.doPostPublicAPI(this.apiHost, this.tokenEndpoint, postParam);
		if(result.isPresent()) {
			KeycloakTokenInfo staff = new Gson().fromJson(result.get(), KeycloakTokenInfo.class);
			return Optional.of(staff);
		}else {
			return Optional.empty();
		}
	}

	/**
	 * logoutEndpoint
	 * @author arc
	 * @param accessToken
	 * @param refreshToken
	 * @return
	 */
	public boolean logout(String accessToken,String refreshToken) {
		logger.info("start");
		LinkedMultiValueMap<String, String> postParam = new LinkedMultiValueMap<>();
		postParam.add("client_id",this.clientId);
		postParam.add("client_secret",this.clientSecret);
		postParam.add("refresh_token", refreshToken);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", "Bearer "+accessToken);
		Optional<String> result=this.doPostProtectedAPI(this.apiHost, this.logoutEndpoint, headers, postParam);
		if(result.isPresent()) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * get role object fron keycloak
	 * @author arc
	 * @param roleName
	 * @return
	 */
	public Optional<Map<String,Object>> findRoleByRoleName(String roleName) {
		logger.info("start");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", "Bearer "+this.adminTokenInfo.getAccess_token());
		Optional<String> result = this.doGetProtectedAPI(this.apiHost,this.rolesEndpoint,headers);
		if(result.isPresent()) {
			@SuppressWarnings("unchecked")//可以忽略警告
			List<Map<String,Object>> roleList= new Gson().fromJson(result.get(), List.class);
			if(!roleList.isEmpty()) {
				return roleList.stream().filter(it->it.get("name").toString().equals(roleName)).findFirst();
			}
		}
		logger.info("role not exist. "+roleName);
		return Optional.empty();
	}
	
	/**
	 * find keycloak user by username
	 * @author arc
	 * @param username
	 * @return
	 */
	public Optional<Map<String,Object>> findUserByUserName(String username) {		
		logger.info("start");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", "Bearer "+this.adminTokenInfo.getAccess_token());
		Optional<String> result=this.doGetProtectedAPI(this.apiHost, this.usersEndpoint+"?username="+username, headers);
		if(result.isPresent()) {
			@SuppressWarnings("unchecked")//可以忽略警告
			List<Map<String,Object>> userList= new Gson().fromJson(result.get(), List.class);
			if(!userList.isEmpty()) {
				return Optional.of(userList.stream().findFirst().get());
			}
		}
		logger.info("user not exist. "+username);
		return Optional.empty();
	}

	
	/**
	 * asign user role
	 * @author arc
	 * @param userId
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	public boolean asignUserRole(String userId,String roleId,String roleName) {
		logger.info("start");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+this.adminTokenInfo.getAccess_token());
    	String json = this.asignUserRoleJsonTemplate
    			.replaceAll("TBR-roleId", roleId)
    			.replaceAll("TBR-roleName", roleName);
    	Optional<String> result = this.doPostProtectedAPI(this.apiHost, this.usersEndpoint+ "/" + userId +"/role-mappings/realm", headers, json);
    	if(result.isPresent()) {
    		return true;
    	}else {
    		return false;
    	}
	}

	/**
	 * 建立帳號
	 * @author arc
	 * @param userAccount
	 * @param userId
	 * @param password
	 * @param role
	 * @return
	 */
    public boolean createUser(String userAccount, String userId, String password) {    	
    	logger.info("start");
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+this.adminTokenInfo.getAccess_token());
    	String json = this.createUserJsonTemplate
    			.replaceAll("TBR-username", userAccount)
    			.replaceAll("TBR-password", password)
    			.replaceAll("TBR-userid", userId);
    	Optional<String> result = this.doPostProtectedAPI(this.apiHost, this.usersEndpoint, headers, json);
    	if(result.isPresent()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * 重新設定密碼
     * @author arc
     * @param userid
     * @param newPassword
     * @return
     */
    public boolean resetPassword(String userid,String newPassword) {
    	logger.info("start");
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+this.adminTokenInfo.getAccess_token());
    	String json = this.resetPasswordJsonTempalte.replaceAll("TBR-password", newPassword);
    	Optional<String> result = this.doPutProtectedAPI(this.apiHost, this.usersEndpoint+"/"+userid+"/reset-password", headers, json);
    	if(result.isPresent()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * 用戶生效/失效
     * @author arc
     * @param userid
     * @param enable (true/false)
     * @return
     */
    public boolean enableUser(String userid,String enable) {
    	logger.info("start");
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+this.adminTokenInfo.getAccess_token());
    	String json = this.userEnableTemplate.replaceAll("TBR-enabled", enable);
    	Optional<String> result = this.doPutProtectedAPI(this.apiHost, this.usersEndpoint+"/"+userid, headers, json);
    	if(result.isPresent()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * PUT呼叫受保護的API
     * @param apiHost
     * @param apiName
     * @param headers
     * @param json
     * @return
     */
    private Optional<String> doPutProtectedAPI(String apiHost,String apiName,HttpHeaders headers,String json) {
		logger.debug(String.format("API {%s} BEGIN", apiHost+apiName));
		long startTime = System.currentTimeMillis();
    	try {
			RestTemplate restTemplate = RestTemplateUtil.createRestTemplate(apiHost);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			ResponseEntity<String> response = restTemplate.exchange(apiHost+apiName,HttpMethod.PUT,entity,String.class);
			logger.debug(String.format("API {%s} STATUS: %s", apiHost+apiName, response.getStatusCode()));
			if(response.getStatusCode().value() >= 200 || response.getStatusCode().value() < 400) {
				String result=response.getBody();
				logger.debug(String.format("API {%s} OUTPUT: %s", apiHost+apiName, result));
				if(null == result) {
					result="";
				}
				return Optional.ofNullable(result);
			}else {
				return Optional.empty();
			}
    	} catch (Exception e) {
			logger.error(String.format("API {%s} EXCEPTION: %s", apiHost+apiName, e.getMessage()), e);
			return Optional.empty();
		}finally {
			logger.debug(String.format("API {%s} END (%s ms)", apiHost+apiName, (System.currentTimeMillis() - startTime)));
		}
    }
    
    /**
     * POST呼叫受保護的API
     * @author arc
     * @param apiHost
     * @param apiName
     * @param headers
     * @param json
     * @return
     */
    private Optional<String> doPostProtectedAPI(String apiHost,String apiName,HttpHeaders headers,String json) {
		logger.debug(String.format("API {%s} BEGIN", apiHost+apiName));
		long startTime = System.currentTimeMillis();
    	try {
			RestTemplate restTemplate = RestTemplateUtil.createRestTemplate(apiHost);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			ResponseEntity<String> response = restTemplate.exchange(apiHost+apiName,HttpMethod.POST,entity,String.class);
			logger.debug(String.format("API {%s} STATUS: %s", apiHost+apiName, response.getStatusCode()));
			if(response.getStatusCode().value() >= 200 || response.getStatusCode().value() < 400) {
				String result=response.getBody();
				logger.debug(String.format("API {%s} OUTPUT: %s", apiHost+apiName, result));
				if(null == result) {
					result="";
				}
				return Optional.ofNullable(result);
			}else {
				return Optional.empty();
			}
    	} catch (Exception e) {
			logger.error(String.format("API {%s} EXCEPTION: %s", apiHost+apiName, e.getMessage()), e);
			return Optional.empty();
		}finally {
			logger.debug(String.format("API {%s} END (%s ms)", apiHost+apiName, (System.currentTimeMillis() - startTime)));
		}
    }

	/**
	 * 以POST呼叫受保護的 API
	 * @author arc
	 * @param apiHost
	 * @param apiName
	 * @param headers
	 * @param postParam
	 * @return
	 */
	private Optional<String> doPostProtectedAPI(
			String apiHost,
			String apiName,
			HttpHeaders headers,
			MultiValueMap<String, String> postParam) {
		logger.debug(String.format("API {%s} BEGIN", apiHost+apiName));
		long startTime = System.currentTimeMillis();
		try {
			RestTemplate restTemplate = RestTemplateUtil.createRestTemplate(apiHost);
			HttpEntity<MultiValueMap<String, String>> entity =new HttpEntity<MultiValueMap<String, String>>(postParam, headers);
			ResponseEntity<String> response = restTemplate.exchange(apiHost+apiName,HttpMethod.POST,entity,String.class);
			logger.debug(String.format("API {%s} STATUS: %s", apiHost+apiName, response.getStatusCode()));
			if(response.getStatusCode().value() >= 200 || response.getStatusCode().value() < 400) {
				String result=response.getBody();
				logger.debug(String.format("API {%s} OUTPUT: %s", apiHost+apiName, result));
				if(null == result) {
					result="";
				}
				return Optional.ofNullable(result);
			}else {
				return Optional.empty();
			}
		} catch (Exception e) {
			logger.error(String.format("API {%s} EXCEPTION: %s", apiHost+apiName, e.getMessage()), e);
			return Optional.empty();
		}finally {
			logger.debug(String.format("API {%s} END (%s ms)", apiHost+apiName, (System.currentTimeMillis() - startTime)));
		}
	}
	/**
	 * 以POST呼叫公開的 API
	 * @author arc
	 * @param apiHost api網域
	 * @param apiName api名稱
	 * @param postParam post參數
	 * @return
	 */
	private Optional<String> doPostPublicAPI(String apiHost,String apiName,MultiValueMap<String, String> postParam) {
		logger.debug(String.format("API {%s} BEGIN", apiHost+apiName));
		long startTime = System.currentTimeMillis();
		try {
			RestTemplate restTemplate = RestTemplateUtil.createRestTemplate(apiHost);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<MultiValueMap<String, String>> entity =new HttpEntity<MultiValueMap<String, String>>(postParam, headers);
			ResponseEntity<String> response = restTemplate.exchange(apiHost+apiName,HttpMethod.POST,entity,String.class);
			logger.debug(String.format("API {%s} STATUS: %s", apiHost+apiName, response.getStatusCode()));
			if(response.getStatusCode().value() >= 200 || response.getStatusCode().value() < 400) {
				String result=response.getBody();
				logger.debug(String.format("API {%s} OUTPUT: %s", apiHost+apiName, result));
				if(null == result) {
					result="";
				}
				return Optional.ofNullable(result);
			}else {
				return Optional.empty();
			}
		} catch (Exception e) {
			logger.error(String.format("API {%s} EXCEPTION: %s", apiHost+apiName, e.getMessage()), e);
			return Optional.empty();
		}finally {
			logger.debug(String.format("API {%s} END (%s ms)", apiHost+apiName, (System.currentTimeMillis() - startTime)));
		}
	}
	/**	
	 * 以GET呼叫受保護的API
	 * @author arc
	 * @param apiHost
	 * @param apiName
	 * @param headers
	 * @param param
	 * @return
	 */
	private Optional<String> doGetProtectedAPI(String apiHost,String apiName,HttpHeaders headers) {
		logger.debug(String.format("API {%s} BEGIN", apiHost+apiName));
		long startTime = System.currentTimeMillis();
		try {
			RestTemplate restTemplate = RestTemplateUtil.createRestTemplate(apiHost);
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
			ResponseEntity<String> response = restTemplate.exchange(apiHost+apiName, HttpMethod.GET, entity, String.class);
			if(response.getStatusCode().value() >= 200 || response.getStatusCode().value() < 400) {
				String result = response.getBody();
				logger.debug(String.format("API {%s} OUTPUT: %s", apiHost+apiName, result));
				if(null == result) {
					result="";
				}
				return Optional.ofNullable(result);
			}else {
				return Optional.empty();
			}
		} catch (Exception e) {
			logger.error(String.format("API {%s} EXCEPTION: %s", apiHost+apiName, e.getMessage()), e);
			return Optional.empty();
		}finally {
			logger.debug(String.format("API {%s} END (%s ms)", apiHost+apiName, (System.currentTimeMillis() - startTime)));
		}
	}
}
