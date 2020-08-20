package com.hoyetec.api.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_wallet database table.
 * 
 */
@Entity
@Table(name="user_wallet")
@NamedQuery(name="UserWallet.findAll", query="SELECT u FROM UserWallet u")
public class UserWallet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="wallet_id")
	private Integer walletId;

	@Column(name="address_passwd")
	private String addressPasswd;

	@Column(name="amount_avalible")
	private Double amountAvalible=0d;

	@Column(name="amount_lock")
	private Double amountLock=0d;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime= new Date();;

	@Column(name="currency_address")
	private String currencyAddress;

	@Column(name="currency_id")
	private Integer currencyId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate = new Date();;

	private Integer status= 0;

	@Column(name="user_uid")
	private Integer userUid;

	public UserWallet() {
	}

	public Integer getWalletId() {
		return this.walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public String getAddressPasswd() {
		return this.addressPasswd;
	}

	public void setAddressPasswd(String addressPasswd) {
		this.addressPasswd = addressPasswd;
	}

	public double getAmountAvalible() {
		return this.amountAvalible;
	}

	public void setAmountAvalible(Double amountAvalible) {
		this.amountAvalible = amountAvalible;
	}

	public Double getAmountLock() {
		return this.amountLock;
	}

	public void setAmountLock(Double amountLock) {
		this.amountLock = amountLock;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCurrencyAddress() {
		return this.currencyAddress;
	}

	public void setCurrencyAddress(String currencyAddress) {
		this.currencyAddress = currencyAddress;
	}

	public Integer getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserUid() {
		return this.userUid;
	}

	public void setUserUid(Integer userUid) {
		this.userUid = userUid;
	}

}