package com.hoyetec.api.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the blockchain_main database table.
 * 
 */
@Entity
@Table(name="blockchain_main")
@NamedQuery(name="BlockchainMain.findAll", query="SELECT b FROM BlockchainMain b")
public class BlockchainMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="blockchain_id")
	private Integer blockchainId;

	@Column(name="blockchain_name")
	private String blockchainName;

	@Column(name="blockchain_node_address")
	private String blockchainNodeAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;

	@Column(name="last_update_admin_id")
	private int lastUpdateAdminId;

	public BlockchainMain() {
	}

	public int getBlockchainId() {
		return this.blockchainId;
	}

	public void setBlockchainId(int blockchainId) {
		this.blockchainId = blockchainId;
	}

	public String getBlockchainName() {
		return this.blockchainName;
	}

	public void setBlockchainName(String blockchainName) {
		this.blockchainName = blockchainName;
	}

	public String getBlockchainNodeAddress() {
		return this.blockchainNodeAddress;
	}

	public void setBlockchainNodeAddress(String blockchainNodeAddress) {
		this.blockchainNodeAddress = blockchainNodeAddress;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLastUpdateAdminId() {
		return this.lastUpdateAdminId;
	}

	public void setLastUpdateAdminId(int lastUpdateAdminId) {
		this.lastUpdateAdminId = lastUpdateAdminId;
	}

}