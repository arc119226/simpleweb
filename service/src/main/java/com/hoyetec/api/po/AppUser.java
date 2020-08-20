package com.hoyetec.api.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the app_user database table.
 * 
 */
@Entity
@Table(name="app_user")
@NamedQuery(name="AppUser.findAll", query="SELECT a FROM AppUser a")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	@Column(name="auth_fail_reason")
	private String authFailReason;

	@Column(name="auth_level")
	private Integer authLevel=0;

	@Column(name="auth_level_pre")
	private Integer authLevelPre=0;

	@Column(name="auth_person")
	private String authPerson;

	@Column(name="auth_status")
	private Integer authStatus=0;

	@Column(name="auth_submit_time")
	private Timestamp authSubmitTime;

	@Column(name="auth_time")
	private Timestamp authTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birth_day")
	private Date birthDay;

	private String code;

	private String country;

	@Column(name="create_time")
	private Timestamp createTime;

	private String email;

	@Column(name="fd_password")
	private String fdPassword;

	@Column(name="fd_password_time")
	private Integer fdPasswordTime=0;

	@Column(name="fd_password_update_time")
	private Timestamp fdPasswordUpdateTime;

	@Column(name="fd_pwd_order_enabled")
	private String fdPwdOrderEnabled="1";

	private Integer gender=1;

	private Integer grade;

	@Column(name="invited_id")
	private String invitedId;

	@Column(name="invited_uid")
	private Integer invitedUid=0;

	@Column(name="is_auth")
	private Integer isAuth=0;

	@Column(name="is_auth_primary")
	private Integer isAuthPrimary=0;

	@Column(name="is_auth_senior")
	private Integer isAuthSenior=0;

	@Column(name="is_auth_video")
	private Integer isAuthVideo=0;

	@Column(name="is_phone_auth")
	private Integer isPhoneAuth=0;

	@Column(name="is_validate_email")
	private Integer isValidateEmail=0;

	@Column(name="is_validate_pass")
	private Integer isValidatePass=0;

	@Column(name="is_validate_phone")
	private Integer isValidatePhone=0;

	private String location;

	private String nation;

	@Column(name="open_auth_video")
	private Integer openAuthVideo=0;

	private String password;

	private String phone;

	private String profession="1";

	@Column(name="profession_other")
	private String professionOther;

	@Column(name="read_fail_reason")
	private Integer readFailReason=0;

	@Column(name="real_location")
	private Integer realLocation=0;

	@Column(name="refuse_reason_id")
	private Integer refuseReasonId=0;

	@Column(name="register_source")
	private Integer registerSource=0;

	@Column(name="risk_audit_time")
	private Timestamp riskAuditTime;

	@Column(name="risk_auditor")
	private String riskAuditor;

	@Column(name="risk_level")
	private Integer riskLevel=0;

	@Column(name="risk_reason")
	private String riskReason;

	@Column(name="secure_level")
	private Integer secureLevel=0;

	private Integer status;

	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer uid;

	private String uname;

	@Column(name="update_time")
	private Timestamp updateTime;

	@Column(name="user_type")
	private Integer userType=0;

	public AppUser() {
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAuthFailReason() {
		return this.authFailReason;
	}

	public void setAuthFailReason(String authFailReason) {
		this.authFailReason = authFailReason;
	}

	public Integer getAuthLevel() {
		return this.authLevel;
	}

	public void setAuthLevel(Integer authLevel) {
		this.authLevel = authLevel;
	}

	public Integer getAuthLevelPre() {
		return this.authLevelPre;
	}

	public void setAuthLevelPre(Integer authLevelPre) {
		this.authLevelPre = authLevelPre;
	}

	public String getAuthPerson() {
		return this.authPerson;
	}

	public void setAuthPerson(String authPerson) {
		this.authPerson = authPerson;
	}

	public Integer getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public Timestamp getAuthSubmitTime() {
		return this.authSubmitTime;
	}

	public void setAuthSubmitTime(Timestamp authSubmitTime) {
		this.authSubmitTime = authSubmitTime;
	}

	public Timestamp getAuthTime() {
		return this.authTime;
	}

	public void setAuthTime(Timestamp authTime) {
		this.authTime = authTime;
	}

	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getFdPassword() {
		return this.fdPassword;
	}

	public void setFdPassword(String fdPassword) {
		this.fdPassword = fdPassword;
	}

	public Integer getFdPasswordTime() {
		return this.fdPasswordTime;
	}

	public void setFdPasswordTime(Integer fdPasswordTime) {
		this.fdPasswordTime = fdPasswordTime;
	}

	public Timestamp getFdPasswordUpdateTime() {
		return this.fdPasswordUpdateTime;
	}

	public void setFdPasswordUpdateTime(Timestamp fdPasswordUpdateTime) {
		this.fdPasswordUpdateTime = fdPasswordUpdateTime;
	}

	public String getFdPwdOrderEnabled() {
		return this.fdPwdOrderEnabled;
	}

	public void setFdPwdOrderEnabled(String fdPwdOrderEnabled) {
		this.fdPwdOrderEnabled = fdPwdOrderEnabled;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getInvitedId() {
		return this.invitedId;
	}

	public void setInvitedId(String invitedId) {
		this.invitedId = invitedId;
	}

	public Integer getInvitedUid() {
		return this.invitedUid;
	}

	public void setInvitedUid(Integer invitedUid) {
		this.invitedUid = invitedUid;
	}

	public Integer getIsAuth() {
		return this.isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public Integer getIsAuthPrimary() {
		return this.isAuthPrimary;
	}

	public void setIsAuthPrimary(Integer isAuthPrimary) {
		this.isAuthPrimary = isAuthPrimary;
	}

	public Integer getIsAuthSenior() {
		return this.isAuthSenior;
	}

	public void setIsAuthSenior(Integer isAuthSenior) {
		this.isAuthSenior = isAuthSenior;
	}

	public Integer getIsAuthVideo() {
		return this.isAuthVideo;
	}

	public void setIsAuthVideo(Integer isAuthVideo) {
		this.isAuthVideo = isAuthVideo;
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

	public Integer getIsValidatePass() {
		return this.isValidatePass;
	}

	public void setIsValidatePass(Integer isValidatePass) {
		this.isValidatePass = isValidatePass;
	}

	public Integer getIsValidatePhone() {
		return this.isValidatePhone;
	}

	public void setIsValidatePhone(Integer isValidatePhone) {
		this.isValidatePhone = isValidatePhone;
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

	public Integer getOpenAuthVideo() {
		return this.openAuthVideo;
	}

	public void setOpenAuthVideo(Integer openAuthVideo) {
		this.openAuthVideo = openAuthVideo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProfessionOther() {
		return this.professionOther;
	}

	public void setProfessionOther(String professionOther) {
		this.professionOther = professionOther;
	}

	public Integer getReadFailReason() {
		return this.readFailReason;
	}

	public void setReadFailReason(Integer readFailReason) {
		this.readFailReason = readFailReason;
	}

	public Integer getRealLocation() {
		return this.realLocation;
	}

	public void setRealLocation(Integer realLocation) {
		this.realLocation = realLocation;
	}

	public Integer getRefuseReasonId() {
		return this.refuseReasonId;
	}

	public void setRefuseReasonId(Integer refuseReasonId) {
		this.refuseReasonId = refuseReasonId;
	}

	public Integer getRegisterSource() {
		return this.registerSource;
	}

	public void setRegisterSource(Integer registerSource) {
		this.registerSource = registerSource;
	}

	public Timestamp getRiskAuditTime() {
		return this.riskAuditTime;
	}

	public void setRiskAuditTime(Timestamp riskAuditTime) {
		this.riskAuditTime = riskAuditTime;
	}

	public String getRiskAuditor() {
		return this.riskAuditor;
	}

	public void setRiskAuditor(String riskAuditor) {
		this.riskAuditor = riskAuditor;
	}

	public Integer getRiskLevel() {
		return this.riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRiskReason() {
		return this.riskReason;
	}

	public void setRiskReason(String riskReason) {
		this.riskReason = riskReason;
	}

	public Integer getSecureLevel() {
		return this.secureLevel;
	}

	public void setSecureLevel(Integer secureLevel) {
		this.secureLevel = secureLevel;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}