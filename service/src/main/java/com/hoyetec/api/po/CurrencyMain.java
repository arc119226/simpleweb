package com.hoyetec.api.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the currency_main database table.
 * 
 */
@Entity
@Table(name="currency_main")
@NamedQuery(name="CurrencyMain.findAll", query="SELECT c FROM CurrencyMain c")
public class CurrencyMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="currency_id")
	private Integer currencyId;

	@Column(name="alias_name")
	private String aliasName;

	@Column(name="blockchain_id")
	private Integer blockchainId;

	@Column(name="buying_fee")
	private Double buyingFee;

	@Column(name="charge_fee")
	private Double chargeFee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="currency_name")
	private String currencyName;

	private String descript;

	@Column(name="erc20_address")
	private String erc20Address;

	@Column(name="initial_price")
	private Double initialPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;

	@Column(name="last_update_uid")
	private Integer lastUpdateUid;

	@Column(name="selling_fee")
	private Double sellingFee;

	private Integer status;

	@Column(name="transaction_fee")
	private Double transactionFee;

	@Column(name="withdraw_fee")
	private Double withdrawFee;

	public CurrencyMain() {
	}

	public Integer getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getAliasName() {
		return this.aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Integer getBlockchainId() {
		return this.blockchainId;
	}

	public void setBlockchainId(Integer blockchainId) {
		this.blockchainId = blockchainId;
	}

	public Double getBuyingFee() {
		return this.buyingFee;
	}

	public void setBuyingFee(Double buyingFee) {
		this.buyingFee = buyingFee;
	}

	public Double getChargeFee() {
		return this.chargeFee;
	}

	public void setChargeFee(Double chargeFee) {
		this.chargeFee = chargeFee;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getErc20Address() {
		return this.erc20Address;
	}

	public void setErc20Address(String erc20Address) {
		this.erc20Address = erc20Address;
	}

	public Double getInitialPrice() {
		return this.initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getLastUpdateUid() {
		return this.lastUpdateUid;
	}

	public void setLastUpdateUid(Integer lastUpdateUid) {
		this.lastUpdateUid = lastUpdateUid;
	}

	public Double getSellingFee() {
		return this.sellingFee;
	}

	public void setSellingFee(Double sellingFee) {
		this.sellingFee = sellingFee;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getTransactionFee() {
		return this.transactionFee;
	}

	public void setTransactionFee(Double transactionFee) {
		this.transactionFee = transactionFee;
	}

	public Double getWithdrawFee() {
		return this.withdrawFee;
	}

	public void setWithdrawFee(double withdrawFee) {
		this.withdrawFee = withdrawFee;
	}

}