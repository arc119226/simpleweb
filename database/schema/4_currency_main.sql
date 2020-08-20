CREATE TABLE `currency_main` (
	`currency_id` INT(11) NOT NULL AUTO_INCREMENT,
	`currency_name` VARCHAR(60) NOT NULL COMMENT '幣種名稱',
	`blockchain_id` INT(11) NOT NULL COMMENT '對應tb_blockchain_main.blockchain_id',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`last_update` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最後更新時間',
	`last_update_uid` INT(11) NOT NULL COMMENT '最後修改者admin_id',
	`transaction_fee` DOUBLE NOT NULL DEFAULT '0.001' COMMENT '手續費',
	`erc20_address` VARCHAR(100) NULL DEFAULT NULL COMMENT 'erc 20 Address - contract address',
	`initial_price` DOUBLE NOT NULL DEFAULT '0' COMMENT '初始價格',
	`descript` VARCHAR(200) NULL DEFAULT NULL COMMENT '描述',
	`status` INT(2) NOT NULL DEFAULT '0' COMMENT '狀態\\n0:下線\\n1:上線\\n2:鎖住_不能交易',
	`alias_name` VARCHAR(45) NULL DEFAULT NULL COMMENT '英文名稱',
	`charge_fee` DOUBLE NOT NULL DEFAULT '0' COMMENT '充值手續費_百分比',
	`withdraw_fee` DOUBLE NOT NULL DEFAULT '0' COMMENT '提現手續費',
	`buying_fee` DOUBLE NOT NULL DEFAULT '0' COMMENT '買入手續費',
	`selling_fee` DOUBLE NOT NULL DEFAULT '0' COMMENT '賣出手續費',
	PRIMARY KEY (`currency_id`)
)
COMMENT='幣種'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;
