


--账户总数
drop table if exists db_accounts;
create table db_accounts
(
   id                   bigint not null auto_increment,
   number               varbinary(200) comment '账户数量',
   create_time          timestamp,
   primary key (id)
);

alter table db_accounts comment '账户数量';








CREATE TABLE "transactions_index" (
"address"  CHAR NOT NULL,
"tableIndex"  INT,
"offsets"  INT,
PRIMARY KEY ("address" ASC)
);

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
CREATE TABLE "transactions" (
"id"  CHAR NOT NULL,
"creation_date"  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
"amount"  BIGINT NOT NULL,
"fee"  BIGINT NOT NULL DEFAULT 0,
"addressFrom"  CHAR,
"addressTo"  CHAR,
"result"  TEXT NOT NULL DEFAULT 'pending',
"remark"  TEXT,
"type"  INT NOT NULL DEFAULT 0,
"sType"  INT DEFAULT 0,
"eType"  INT DEFAULT 0,
"percent"  double,
"sHash"  TEXT,
"eHash"  TEXT,
"sConfirm"  smallint,
"eConfirm"  smallint,
"sStatu"  smallint,
"eStatu"  smallint,
"sInfo"  TEXT,
"eInfo"  TEXT,
"amount_point"  BIGINT DEFAULT 0,
"fee_point"  BIGINT DEFAULT 0,
"multiHash"  TEXT,
PRIMARY KEY ("id" ASC),
CHECK (result IN ( 'good' , 'pending' , 'final-bad' ))
);