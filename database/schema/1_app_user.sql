CREATE TABLE `app_user` (
	`uid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '前台用户id',
	`uuid` VARCHAR(36) NOT NULL,
	`uname` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_bin',
	`gender` INT(11) NULL DEFAULT '1',
	`profession` VARCHAR(64) NULL DEFAULT '1' COMMENT '1 受雇人士 2 自雇人士 3 學生 4 退休 5 其他',
	`profession_other` VARCHAR(42) NULL DEFAULT NULL COMMENT '其他職業',
	`country` VARCHAR(64) NULL DEFAULT '',
	`nation` VARCHAR(20) NULL DEFAULT NULL,
	`location` VARCHAR(128) NULL DEFAULT '',
	`birth_day` DATETIME NULL DEFAULT NULL,
	`grade` INT(11) NULL DEFAULT '1',
	`password` VARCHAR(255) NULL DEFAULT NULL COMMENT '密碼',
	`fd_password` VARCHAR(255) NULL DEFAULT NULL COMMENT '帳戶密碼',
	`fd_pwd_order_enabled` VARCHAR(45) NULL DEFAULT '1',
	`is_validate_pass` INT(11) NULL DEFAULT '0',
	`fd_password_time` INT(11) NULL DEFAULT '0',
	`fd_password_update_time` TIMESTAMP NULL DEFAULT NULL,
	`code` VARCHAR(45) NULL DEFAULT NULL COMMENT '驗證碼',
	`email` VARCHAR(100) NULL DEFAULT NULL COMMENT 'email',
	`is_validate_email` INT(11) NULL DEFAULT '0',
	`is_auth` SMALLINT(2) NULL DEFAULT '0' COMMENT '是否認證',
	`phone` VARCHAR(50) NULL DEFAULT NULL,
	`is_validate_phone` INT(11) NULL DEFAULT '0',
	`is_auth_primary` INT(11) NULL DEFAULT '0',
	`is_auth_senior` INT(11) NULL DEFAULT '0',
	`auth_fail_reason` VARCHAR(255) NULL DEFAULT NULL,
	`is_auth_video` INT(11) NULL DEFAULT '0',
	`auth_level` INT(11) NULL DEFAULT '0',
	`secure_level` INT(11) NULL DEFAULT '0',
	`status` SMALLINT(2) NOT NULL DEFAULT '0' COMMENT '是否鎖定：1=鎖定 0=正常',
	`invited_id` VARCHAR(16) NULL DEFAULT NULL COMMENT '邀請碼',
	`invited_uid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '邀請人',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
	`user_type` INT(11) NULL DEFAULT '0',
	`is_phone_auth` INT(11) NULL DEFAULT '0',
	`auth_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`auth_person` VARCHAR(45) NULL DEFAULT NULL,
	`auth_submit_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`auth_status` INT(1) NULL DEFAULT '0' COMMENT '審查三級認證 2通過 0未認證 1 待審核 -1 不通過',
	`auth_level_pre` INT(1) NULL DEFAULT '0',
	`read_fail_reason` INT(11) NULL DEFAULT '0',
	`refuse_reason_id` BIGINT(20) NULL DEFAULT '0' COMMENT '拒絕理由ID',
	`risk_auditor` VARCHAR(45) NULL DEFAULT NULL,
	`risk_audit_time` TIMESTAMP NULL DEFAULT NULL COMMENT '風空時間',
	`risk_level` TINYINT(1) NULL DEFAULT '0' COMMENT '風控級別',
	`risk_reason` VARCHAR(255) NULL DEFAULT NULL,
	`real_location` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '所在區域',
	`register_source` TINYINT(2) NULL DEFAULT '0' COMMENT '註冊終端類型 1-web 2-ios 3-android 4-wapp',
	`open_auth_video` TINYINT(1) NULL DEFAULT '0' COMMENT '是否開啟Kyc3入口',
	PRIMARY KEY (`uuid`),
	UNIQUE INDEX `uuid_index` (`uuid`) USING HASH,
	UNIQUE INDEX `uid_UNIQUE` (`uid`),
	UNIQUE INDEX `phone` (`phone`) USING HASH,
	UNIQUE INDEX `email` (`email`),
	UNIQUE INDEX `idx_code` (`code`)
)
COMMENT='前台user 表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
ROW_FORMAT=COMPACT
AUTO_INCREMENT=40
;
