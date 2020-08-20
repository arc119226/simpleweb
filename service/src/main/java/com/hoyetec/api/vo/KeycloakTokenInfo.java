package com.hoyetec.api.vo;

import java.io.Serializable;

/**
 * 
*****************************************************************************
*
*    Page/Class Name: KeycloakTokenInfo.java
*              Title:
*        Description:
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月23日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月23日
*             Author: Arc Liu  
*
*****************************************************************************
 */
public class KeycloakTokenInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String access_token;
	private int expires_in;
	private int refresh_expires_in;
	private String refresh_token;
	private String token_type;
	private int not_before_policy;
	private String session_state;
	private String scope;

	public KeycloakTokenInfo() {
		access_token = "";
		expires_in = 0;
		refresh_expires_in = 0;
		refresh_token = "";
		token_type = "";
		not_before_policy = 0;
		session_state = "";
		scope = "";
	}

	// Getter Methods

	public String getAccess_token() {
		return access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public int getRefresh_expires_in() {
		return refresh_expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public int getNot_before_policy() {
		return not_before_policy;
	}

	public String getSession_state() {
		return session_state;
	}

	public String getScope() {
		return scope;
	}

	// Setter Methods

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public void setRefresh_expires_in(int refresh_expires_in) {
		this.refresh_expires_in = refresh_expires_in;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public void setNot_before_policy(int not_before_policy) {
		this.not_before_policy = not_before_policy;
	}

	public void setSession_state(String session_state) {
		this.session_state = session_state;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "KeycloakTokenInfo [access_token=" + access_token + ", expires_in=" + expires_in
				+ ", refresh_expires_in=" + refresh_expires_in + ", refresh_token=" + refresh_token + ", token_type="
				+ token_type + ", not_before_policy=" + not_before_policy + ", session_state=" + session_state
				+ ", scope=" + scope + "]";
	}

}
