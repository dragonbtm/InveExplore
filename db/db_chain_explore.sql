

drop table if exists db_accounts;
/*==============================================================*/
/* Table: db_accounts  --账户总数                                      */
/*==============================================================*/
CREATE TABLE `db_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '账户数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='账户数量';


alter table db_accounts comment '账户数量';



drop table if exists db_messages;
/*==============================================================*/
/* Table: db_messages      --节点信息表                                     */
/*==============================================================*/
CREATE TABLE `db_messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `runTime` varchar(40) DEFAULT NULL,
  `shardNumber` varchar(10) DEFAULT NULL,
  `tps` varchar(30) DEFAULT NULL,
  `userCount` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='节点数据信息';
alter table db_messages comment '节点数据信息';




drop table if exists db_transactions_index;
/*==============================================================*/
/* Table: db_transactions_index         --交易历史索引                         */
/*==============================================================*/
CREATE TABLE `db_transactions_index` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tableIndex` int(11) DEFAULT NULL,
  `offsets` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='交易记录索引';



drop table if exists db_transaction_0;

/*==============================================================*/
/* Table: db_transactions         交易历史数据                               */
/*==============================================================*/
CREATE TABLE `db_transaction_0` (
  `id` bigint(255) NOT NULL,
  `fromAddress` varchar(600) DEFAULT NULL,
  `toAddress` varchar(600) DEFAULT NULL,
  `hash` varchar(600) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `eHash` varchar(600) DEFAULT NULL,
  `isValid` bigint(20) DEFAULT NULL,
  `updateTime` bigint(20) DEFAULT NULL,
  `snapshot` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史记录';





--未使用
CREATE TABLE "transactions_device_address" (
"id"  CHAR NOT NULL,
"device"  CHAR NOT NULL,
"result"  TEXT,
"status"  TEXT,
PRIMARY KEY ("id", "device")
);
CREATE TABLE "coin_type" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"coinType"  TEXT NOT NULL,
"confirmations"  smallint DEFAULT -1
);
INSERT INTO "coin_type" VALUES(1,'INVE',-1);
INSERT INTO "coin_type" VALUES(2,'BTC',6);
INSERT INTO "coin_type" VALUES(3,'ETH',12);