CREATE TABLE `app_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uuid` varchar(36) NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gender` int(11) DEFAULT '1',
  `profession` varchar(64) DEFAULT '1' COMMENT '1 受雇人士 2 自雇人士 3 学生 4 退休 5 其他',
  `profession_other` varchar(42) DEFAULT NULL COMMENT '职业其他',
  `country` varchar(64) DEFAULT '',
  `nation` varchar(20) DEFAULT NULL,
  `location` varchar(128) DEFAULT '',
  `birth_day` datetime DEFAULT NULL,
  `grade` int(11) DEFAULT '1',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `fd_password` varchar(45) DEFAULT NULL COMMENT '账户密码',
  `fd_pwd_order_enabled` varchar(45) DEFAULT '1',
  `is_validate_pass` int(11) DEFAULT '0',
  `fd_password_time` int(11) DEFAULT '0',
  `fd_password_update_time` timestamp NULL DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL COMMENT '验证码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `is_validate_email` int(11) DEFAULT '0',
  `is_auth` smallint(2) DEFAULT '0' COMMENT '是否认证',
  `phone` varchar(50) DEFAULT NULL,
  `is_validate_phone` int(11) DEFAULT '0',
  `is_auth_primary` int(11) DEFAULT '0',
  `is_auth_senior` int(11) DEFAULT '0',
  `auth_fail_reason` varchar(255) DEFAULT NULL,
  `is_auth_video` int(11) DEFAULT '0',
  `auth_level` int(11) DEFAULT '0',
  `secure_level` int(11) DEFAULT '0',
  `status` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否鎖定：1=鎖定 0=正常',
  `invited_id` varchar(16) DEFAULT NULL COMMENT '邀请码',
  `invited_uid` bigint(20) NOT NULL DEFAULT '0' COMMENT '邀请人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_type` int(11) DEFAULT '0',
  `is_phone_auth` int(11) DEFAULT '0',
  `auth_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `auth_person` varchar(45) DEFAULT NULL,
  `auth_submit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `auth_status` int(1) DEFAULT '0' COMMENT '審查三級認證 2通過 0未認證 1 待審核 -1 不通過',
  `auth_level_pre` int(1) DEFAULT '0',
  `read_fail_reason` int(11) DEFAULT '0',
  `refuse_reason_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '拒绝理由ID',
  `risk_auditor` varchar(45) DEFAULT NULL,
  `risk_audit_time` timestamp NULL DEFAULT NULL COMMENT '风控时间',
  `risk_level` tinyint(1) DEFAULT '0' COMMENT '风控级别',
  `risk_reason` varchar(255) DEFAULT NULL,
  `real_location` tinyint(2) NOT NULL DEFAULT '0' COMMENT '所在区域',
  `register_source` tinyint(2) DEFAULT '0' COMMENT '注册终端类型 1-web 2-ios 3-android 4-wapp',
  `open_auth_video` tinyint(1) DEFAULT '0' COMMENT '是否开启Kyc3入口',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uuid_index` (`uuid`) USING HASH,
  UNIQUE KEY `uid_UNIQUE` (`uid`),
  UNIQUE KEY `phone` (`phone`) USING HASH,
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='业务表user';

CREATE TABLE `action` (
  `OBJECT_ID` varchar(64) NOT NULL,
  `USER_TYPE` varchar(1) NOT NULL,
  `MENU_ID` int(11) NOT NULL,
  `SUBMENU_ID` int(11) NOT NULL,
  `ACTION_ID` int(11) NOT NULL,
  `ACTION_DESC` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `ORDINAL` int(11) NOT NULL,
  `ENABLED` tinyint(4) NOT NULL,
  PRIMARY KEY (`USER_TYPE`,`MENU_ID`,`SUBMENU_ID`,`ACTION_ID`),
  KEY `ACTION_INDEX2` (`OBJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `menu` (
  `OBJECT_ID` varchar(64) NOT NULL,
  `USER_TYPE` varchar(1) NOT NULL,
  `MENU_ID` int(11) NOT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `STATE` varchar(255) NOT NULL,
  `ORDINAL` int(11) NOT NULL,
  `ENABLED` tinyint(4) NOT NULL,
  PRIMARY KEY (`USER_TYPE`,`MENU_ID`),
  UNIQUE KEY `MENU_INDEX1` (`OBJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `submenu` (
  `OBJECT_ID` varchar(64) NOT NULL,
  `USER_TYPE` varchar(1) NOT NULL,
  `MENU_ID` int(11) NOT NULL,
  `SUBMENU_ID` int(11) NOT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `STATE` varchar(255) NOT NULL,
  `ORDINAL` int(11) NOT NULL,
  `ENABLED` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`USER_TYPE`,`MENU_ID`,`SUBMENU_ID`),
  UNIQUE KEY `SUBMENU_INDEX1` (`OBJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sysuser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user_id\nUI 顯示用',
  `user_uuid` varchar(64) NOT NULL COMMENT 'uuid\n系統key',
  `user_account` varchar(100) NOT NULL,
  `user_type` varchar(1) NOT NULL COMMENT '使用者型態描述\n1:平台使用者\n2:商家使用者\n',
  `user_pwd` varchar(100) NOT NULL COMMENT '使用者密碼',
  `user_name` varchar(100) NOT NULL COMMENT '使用者名稱',
  `lock_status` varchar(1) NOT NULL DEFAULT 'N' COMMENT '鎖定狀態\nY鎖定,N未鎖定，預設值N',
  `lock_limit` tinyint(2) NOT NULL DEFAULT '6' COMMENT '登入失敗次數上限\n預設值：6,(-1表無上限)',
  `lock_count` tinyint(2) NOT NULL DEFAULT '0' COMMENT '己登入失敗次數',
  `enabled` varchar(1) NOT NULL DEFAULT '1' COMMENT '使用者致能\n0 : disabled\n1 : enabled\n預設:1',
  `actor_id` int(11) DEFAULT NULL COMMENT '角色id\n對應 sys_submenu.actor_id',
  `dep_id` int(11) DEFAULT NULL COMMENT '所屬部門id',
  `email` varchar(100) DEFAULT NULL COMMENT '郵箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '電話',
  PRIMARY KEY (`user_uuid`),
  UNIQUE KEY `user_account_UNIQUE` (`user_account`),
  UNIQUE KEY `user_uuid_UNIQUE` (`user_uuid`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='後台管理者 table';

CREATE TABLE `sysuser_actor` (
  `actor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '角色名稱',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `enabled` varchar(1) DEFAULT '0' COMMENT '0 : disabled\n1 : enabled\n預設:1',
  PRIMARY KEY (`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='使用者角色檔';

CREATE TABLE `sysuser_actor_auth` (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `ua_actor_id` int(11) NOT NULL COMMENT '管理者角色代碼\nuser mode：sys_user_actor.actor_id',
  `action_submenu_id` int(11) NOT NULL COMMENT '授權 sys_submenu.submenu_id',
  `default_page` varchar(10) DEFAULT NULL COMMENT '預設頁',
  `create_uid` int(11) NOT NULL COMMENT '建立者',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立日期',
  `check_uid` int(11) DEFAULT NULL COMMENT '複核者',
  `check_date` datetime DEFAULT NULL COMMENT '覆核日期',
  `modify_uid` int(11) DEFAULT NULL COMMENT '修改者',
  `modify_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ua_actor_id`,`action_submenu_id`),
  UNIQUE KEY `object_id_UNIQUE` (`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色權限檔';

CREATE TABLE `sysuser_dep` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '部門名稱',
  `create_uid` int(11) NOT NULL COMMENT '建立者user_id\n對應 sys_user.user_id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '部門建立日期',
  `parent_dep_id` int(11) NOT NULL DEFAULT '0' COMMENT '上層部門id，預設0=第一層',
  `enabled` varchar(1) NOT NULL DEFAULT '1' COMMENT '0 : disabled\n1 : enabled\n預設:1',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='使用者部門檔';

CREATE TABLE `usertype` (
  `USER_TYPE` varchar(1) NOT NULL,
  `DESCRIPTION` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`USER_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_authority` (
  `OBJECT_ID` varchar(64) NOT NULL,
  `UA_ID` varchar(40) NOT NULL,
  `ACTION_OBJECT_ID` varchar(64) NOT NULL,
  `DEFAULT_PAGE` tinyint(4) DEFAULT NULL,
  `INSERT_USER` varchar(20) DEFAULT NULL,
  `INSERT_DATE` datetime DEFAULT NULL,
  `CHECK_USER` varchar(20) DEFAULT NULL,
  `CHECK_DATE` datetime DEFAULT NULL,
  `MODIFY_USER` varchar(20) DEFAULT NULL,
  `MODIFY_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`UA_ID`,`ACTION_OBJECT_ID`),
  UNIQUE KEY `USER_AUTHORITY_INDEX1` (`OBJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `currency_main` (
  `currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `currency_name` varchar(60) NOT NULL COMMENT '幣種名稱',
  `blockchain_id` int(11) NOT NULL COMMENT '對應tb_blockchain_main.blockchain_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最後更新時間',
  `last_update_uid` int(11) NOT NULL COMMENT '最後修改者admin_id',
  `transaction_fee` double NOT NULL DEFAULT '0.001' COMMENT '手續費',
  `erc20_address` varchar(100) DEFAULT NULL COMMENT 'erc 20 Address - contract address',
  `initial_price` double NOT NULL DEFAULT '0' COMMENT '初始價格',
  `descript` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '狀態\n0:下線\n1:上線\n2:鎖住_不能交易',
  `alias_name` varchar(45) DEFAULT NULL COMMENT '英文名稱',
  `charge_fee` double NOT NULL DEFAULT '0' COMMENT '充值手續費_百分比',
  `withdraw_fee` double NOT NULL DEFAULT '0' COMMENT '提現手續費',
  `buying_fee` double NOT NULL DEFAULT '0' COMMENT '買入手續費',
  `selling_fee` double NOT NULL DEFAULT '0' COMMENT '賣出手續費',
  PRIMARY KEY (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='幣種';

CREATE TABLE `currency_behavior` (
  `behavior_id` int(11) NOT NULL,
  `currency_id` int(11) NOT NULL,
  `user_kyc_id` int(11) NOT NULL COMMENT '用戶等級設定',
  `user_behavior_id` int(11) NOT NULL COMMENT '用戶行為ID',
  `trans_quota_min` int(11) NOT NULL DEFAULT '0' COMMENT '限額低',
  `trans_quota_max` int(11) NOT NULL DEFAULT '0' COMMENT '限額高',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '狀態\n0:無效\n1:有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
  `last_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最後修改時間',
  `last_update_uid` int(11) NOT NULL COMMENT '最後修改admin id',
  PRIMARY KEY (`behavior_id`,`user_kyc_id`),
  UNIQUE KEY `behavior_id_UNIQUE` (`behavior_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='等級行為幣種限制';

CREATE TABLE `transaction_pair` (
  `pair_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `base_currency_id` int(11) NOT NULL COMMENT '基礎幣種id',
  `target_currency_id` int(11) NOT NULL COMMENT '目標幣種id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
  `last_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新時間',
  `last_update_uid` int(11) NOT NULL COMMENT '最近更新者 admin id',
  `initial_price` double NOT NULL DEFAULT '0' COMMENT '行情初始價',
  `sell_price_min` double NOT NULL DEFAULT '0' COMMENT '最低掛單價',
  `sell_price_max` double NOT NULL DEFAULT '0' COMMENT '最高掛單價',
  `price_decimal_point_digit` tinyint(2) NOT NULL DEFAULT '0' COMMENT '價格小數位數\n限制此交易對的價格可以輸入到小數以下第幾位',
  `quantiity_decimal_point_digit` tinyint(2) NOT NULL DEFAULT '0' COMMENT '數量小數位數\n限制此交易對的數量可以輸入到小數以下第幾位',
  `order_index` smallint(5) NOT NULL DEFAULT '0' COMMENT '排序\n此為登入後的首頁行情以及交易盤面中的交易對的排序',
  `sell_depth` int(11) NOT NULL DEFAULT '0' COMMENT '委託深度\n掛單多少換一條',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '狀態\n0:無效,預設\n1:有效',
  `permission_buy` varchar(1) NOT NULL DEFAULT '0' COMMENT '買入權限\n0:啟用,預設\n1:禁用',
  `permission_sell` varchar(1) NOT NULL DEFAULT '0' COMMENT '賣出權限\n0:啟用,預設\n1:禁用',
  `permission_market_price` varchar(1) NOT NULL DEFAULT '0' COMMENT '市價交易權限\n0:啟用,預設\n1:禁用',
  `maker_fee` double NOT NULL DEFAULT '0' COMMENT 'Maker手續費',
  `taker_fee` double NOT NULL DEFAULT '0' COMMENT 'taker手續費',
  PRIMARY KEY (`pair_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

  create table `batman_schedule` 
   (	`task_id` varchar(64), 
	`task_name` varchar(40), 
	`command` varchar(100), 
	`enable_flag` varchar(50), 
	`run_ip` varchar(50), 
	`cron` varchar(30), 
	`holiday_yn` varchar(50), 
	`run_status` varchar(50), 
	`need_ftp` varchar(50), 
	`need_sftp` varchar(50)
   ) 
  ;

CREATE TABLE `exchange_order` (
  `order_id` bigint(20) NOT NULL COMMENT '訂單的主鍵',
  `order_source_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '此筆訂單的來源 id',
  `uid` bigint(20) NOT NULL COMMENT '關聯 user 資料表的 uid',
  `pair_id` int(10) NOT NULL COMMENT '關聯 transaction_pair 的 pair_id',
  `order_type` char(1) NOT NULL DEFAULT 'S' COMMENT '訂單種類，賣是S，買是B',
  `total_price` double NOT NULL COMMENT '此筆訂單的總價格',
  `amount` double NOT NULL COMMENT '此筆訂單的單位數',
  `unit_price` double NOT NULL COMMENT '此訂單計算出來的單價',
  `completed_amount` double NOT NULL DEFAULT 0 COMMENT '已完成交易的數量',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '訂單狀態\n0:暫存\n1:進行中\n2:分單\nQ:媒合交易完成，結算處理中(queue)\n3:完成交易(結算完成)\n4:抽單\n',
  `create_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '此筆訂單的建立時間',
  `last_update` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '此筆訂單的最後更新時間',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transaction_queue` (
  `transaction_id` bigint(20) NOT NULL COMMENT 'PK,Queue的unique id',
  `pair_id` int(11) NOT NULL COMMENT '交易對的 id',
  `amount` double NOT NULL COMMENT '單位數',
  `final_price` double DEFAULT NULL COMMENT '成交價',
  `sell_order_id` bigint(20) NOT NULL COMMENT '賣單的 order_id',
  `sell_uid` int(10) NOT NULL COMMENT '賣單的 uid',
  `sell_price` double NOT NULL COMMENT '賣單的賣價',
  `sell_fee` double NOT NULL COMMENT '賣單手續費',
  `buy_order_id` bigint(20) NOT NULL COMMENT '買單的 order_id',
  `buy_uid` int(10) NOT NULL COMMENT '買單的 uid',
  `buy_price` double NOT NULL COMMENT '買單的買價',
  `buy_fee` double NOT NULL COMMENT '買單的手續費',
  `transaction_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '交易狀態：0 剛建立、1 處理中、2 已完成、-1 交易失敗',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '交易建立時間',
  `last_update` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '交易更新時間',
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `blockchain_main` (
  `blockchain_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '區塊鏈資料主鍵',
  `blockchain_name` varchar(30) NOT NULL DEFAULT '' COMMENT '區塊鏈名稱',
  `blockchain_node_address` varchar(300) DEFAULT NULL COMMENT '大錢包Node 地址',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '區塊鏈資料建立時間',
  `last_update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '區塊鏈資料最後更改時間',
  `last_update_admin_id` int(11) NOT NULL COMMENT '最後更新者admin_id',
  PRIMARY KEY (`blockchain_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT '區塊鏈Node(大錢包)資料檔';

CREATE TABLE `user_wallet` (
  `wallet_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_uid` int(11) NOT NULL COMMENT 'Owner uid',
  `currency_id` int(11) NOT NULL COMMENT '幣別id',
  `currency_address` varchar(255) DEFAULT NULL COMMENT '會員幣種錢包地址',
  `address_passwd` varchar(255) DEFAULT NULL COMMENT '錢包密碼',
  `amount_avalible` double NOT NULL DEFAULT '0' COMMENT '可用餘額',
  `amount_lock` double NOT NULL DEFAULT '0' COMMENT '處理中資金',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '狀態：\\n0:正常,1:鎖定',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立日期',
  `last_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新日期',
  PRIMARY KEY (`wallet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='使用者資產紀錄';

CREATE TABLE `user_wallet_logs_blockchain` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `blockchain_txn_id` int(11) NOT NULL COMMENT '區塊練交易id',
  `currency_id` int(11) NOT NULL COMMENT '幣種id',
  `blockchain_node_address` varchar(300) DEFAULT NULL COMMENT '大錢包Node 地址',
  `from_address` varchar(300) DEFAULT NULL COMMENT '轉帳者錢包地址',
  `to_address` varchar(300) DEFAULT NULL COMMENT '收款者錢包地址',
  `amount` double NOT NULL COMMENT '交易貨幣數量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
  `status` varchar(1) DEFAULT NULL COMMENT '1：成功\n0：失敗',
  `error_code` varchar(3) NOT NULL DEFAULT '000' COMMENT '錯誤碼，預設000',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='錢包入提幣紀錄檔';

CREATE TABLE `user_wallet_logs_transaction` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `currency_id` int(11) NOT NULL COMMENT '幣種id',
  `from_uid` int(11) DEFAULT NULL COMMENT '轉帳者 user id',
  `to_uid` int(11) DEFAULT NULL COMMENT '收款者 user id',
  `amount` double NOT NULL COMMENT '交易貨幣數量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
  `status` varchar(20) DEFAULT NULL COMMENT 'SUCC：成功\nFAIL：失敗',
  `error_code` varchar(3) NOT NULL DEFAULT '000' COMMENT '錯誤碼，預設000',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='錢包買賣單紀錄檔';


CREATE TABLE `app_user_log` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uuid` varchar(36) NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '手機號碼',
  `token` varchar(2048) DEFAULT NULL COMMENT 'keycloak token',
  `ip` varchar(200) DEFAULT NULL COMMENT 'IP',
  `country` varchar(64) DEFAULT '',
  `nation` varchar(20) DEFAULT NULL,
  `location` varchar(128) DEFAULT '',
  `is_validate_phone` smallint(2) DEFAULT '0' COMMENT '是否手機已认证/綁定',
  `is_validate_email` smallint(2) DEFAULT '0' COMMENT '是否邮箱已认证/綁定',
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登入时间',
  `last_logout_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登出时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_phone_auth` int(11) DEFAULT '0',
  `is_email_auth` int(11) DEFAULT '0',
  `auth_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='前台user登入登出log';

CREATE TABLE `sms_verifycode` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_account` varchar(255) NOT NULL COMMENT '使用者帳號',
  `verifycode` varchar(12) NOT NULL COMMENT '驗證碼',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立日期',
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='註冊簡訊發送驗證碼';