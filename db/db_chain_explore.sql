

drop table if exists db_accounts;
/*==============================================================*/
/* Table: db_accounts  --账户总数                                      */
/*==============================================================*/
create table db_accounts
(
   id                   bigint not null auto_increment,
   number               varbinary(200) comment '账户数量',
   create_time          timestamp,
   update_time          timestamp,
   primary key (id)
);

alter table db_accounts comment '账户数量';





drop table if exists db_messages;
/*==============================================================*/
/* Table: db_messages      --节点信息表                                     */
/*==============================================================*/
create table db_messages
(
   id                   bigint not null auto_increment,
   runTime              varchar(40),
   shardNumber          varchar(10),
   tps                  varchar(30),
   userCount            bigint,
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);
alter table db_messages comment '节点数据信息';




drop table if exists db_transactions_index;
/*==============================================================*/
/* Table: db_transactions_index         --交易历史索引                         */
/*==============================================================*/
create table db_transactions_index
(
   id                   bigint not null,
   tableIndex           int,
   offsets              int,
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);
alter table db_transactions_index comment '交易记录索引';



drop table if exists db_transactions;

/*==============================================================*/
/* Table: db_transactions         交易历史数据                               */
/*==============================================================*/
create table db_transactions
(
   id                   CHAR not null,
   creation_date        timestamp not null default CURRENT_TIMESTAMP,
   amount               bigint not null,
   fee                  bigint not null default 0,
   amount_point         bigint default 0,
   fee_point            bigint default 0,
   addressFrom          CHAR,
   addressTo            CHAR,
   result               text not null ,
   remark               text,
   type                 INT not null default 0,
   sType                int default 0,
   eType                int default 0,
   percent              double,
   sHash                text,
   eHash                text,
   sConfirm             smallint,
   eConfirm             smallint,
   sStatu               smallint,
   eStatu               smallint,
   sInfo                text,
   eInfo                text,
   multiHash            text,
   primary key (id)
);

alter table db_transactions comment '交易历史记录';




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