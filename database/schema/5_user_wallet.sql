CREATE TABLE `user_wallet` (
	`wallet_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
	`user_uid` INT(11) NOT NULL COMMENT 'Owner uid',
	`currency_id` INT(11) NOT NULL COMMENT '幣別id',
	`currency_address` VARCHAR(255) NULL DEFAULT NULL COMMENT '會員幣種錢包地址',
	`address_passwd` VARCHAR(255) NULL DEFAULT NULL COMMENT '錢包密碼',
	`amount_avalible` DOUBLE NOT NULL DEFAULT '0' COMMENT '可用餘額',
	`amount_lock` DOUBLE NOT NULL DEFAULT '0' COMMENT '處理中資金',
	`status` INT(2) NOT NULL DEFAULT '0' COMMENT '狀態：\\\\n0:正常,1:鎖定',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立日期',
	`last_update` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新日期',
	PRIMARY KEY (`wallet_id`)
)
COMMENT='使用者資產紀錄'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=86
;
