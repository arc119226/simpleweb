package com.hoyetec.api.po;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the app_user_log database table.
 * 
 */
@Entity
@Table(name="app_user_log")
@NamedQuery(name="AppUserLog.findAll", query="SELECT a FROM AppUserLog a")
public class AppUserLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer uid;

	@Column(name="auth_time")
	private Timestamp authTime;

	private String country;

	@Column(name="create_time")
	private Timestamp createTime;

	private String email;

	private String ip;

	@Column(name="is_email_auth")
	private Integer isEmailAuth=0;

	@Column(name="is_phone_auth")
	private Integer isPhoneAuth=0;

	@Column(name="is_validate_email")
	private Integer isValidateEmail=0;

	@Column(name="is_validate_phone")
	private Integer isValidatePhone=0;

	@Column(name="last_login_time")
	private Timestamp lastLoginTime;

	@Column(name="last_logout_time")
	private Timestamp lastLogoutTime;

	private String location;

	private String nation;

	private String phone;

	private String token;

	private String uname;

	@Column(name="update_time")
	private Timestamp updateTime;

	private String uuid;

	public AppUserLog() {
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Timestamp getAuthTime() {
		return this.authTime;
	}

	public void setAuthTime(Timestamp authTime) {
		this.authTime = authTime;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getIsEmailAuth() {
		return this.isEmailAuth;
	}

	public void setIsEmailAuth(Integer isEmailAuth) {
		this.isEmailAuth = isEmailAuth;
	}

	public Integer getIsPhoneAuth() {
		return this.isPhoneAuth;
	}

	public void setIsPhoneAuth(Integer isPhoneAuth) {
		this.isPhoneAuth = isPhoneAuth;
	}

	public Integer getIsValidateEmail() {
		return this.isValidateEmail;
	}

	public void setIsValidateEmail(Integer isValidateEmail) {
		this.isValidateEmail = isValidateEmail;
	}

	public Integer getIsValidatePhone() {
		return this.isValidatePhone;
	}

	public void setIsValidatePhone(Integer isValidatePhone) {
		this.isValidatePhone = isValidatePhone;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Timestamp getLastLogoutTime() {
		return this.lastLogoutTime;
	}

	public void setLastLogoutTime(Timestamp lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}