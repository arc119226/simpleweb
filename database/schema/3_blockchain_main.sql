CREATE TABLE `blockchain_main` (
	`blockchain_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '區塊鏈資料主鍵',
	`blockchain_name` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '區塊鏈名稱',
	`blockchain_node_address` VARCHAR(300) NULL DEFAULT NULL COMMENT '大錢包Node 地址',
	`create_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '區塊鏈資料建立時間',
	`last_update` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '區塊鏈資料最後更改時間',
	`last_update_admin_id` INT(11) NOT NULL COMMENT '最後更新者admin_id',
	PRIMARY KEY (`blockchain_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;
