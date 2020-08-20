CREATE TABLE `app_user_log` (
	`uid` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用戶id',
	`uuid` VARCHAR(36) NOT NULL,
	`uname` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_bin',
	`email` VARCHAR(100) NULL DEFAULT NULL COMMENT '信箱',
	`phone` VARCHAR(50) NULL DEFAULT NULL COMMENT '手機號碼',
	`token` VARCHAR(2048) NULL DEFAULT NULL COMMENT 'keycloak token',
	`ip` VARCHAR(200) NULL DEFAULT NULL COMMENT 'IP',
	`country` VARCHAR(64) NULL DEFAULT '',
	`nation` VARCHAR(20) NULL DEFAULT NULL,
	`location` VARCHAR(128) NULL DEFAULT '',
	`is_validate_phone` SMALLINT(2) NULL DEFAULT '0' COMMENT '是否手機已認證/綁定',
	`is_validate_email` SMALLINT(2) NULL DEFAULT '0' COMMENT '是否邮箱已認證/綁定',
	`last_login_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登入時間',
	`last_logout_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登出時間',
	`create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
	`update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新時間',
	`is_phone_auth` INT(11) NULL DEFAULT '0',
	`is_email_auth` INT(11) NULL DEFAULT '0',
	`auth_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`uid`)
)
COMMENT='前台user登入登出log'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
ROW_FORMAT=COMPACT
AUTO_INCREMENT=106
;
