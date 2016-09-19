--mysql

CREATE DATABASE bos;
USE bos;



drop table if exists bc_decidedzone;

drop table if exists bc_region;

drop table if exists bc_staff;

drop table if exists bc_standard;

drop table if exists bc_subarea;

drop table if exists user;

/*==============================================================*/
/* Table: bc_decidedzone                                        */
/*==============================================================*/
create table bc_decidedzone
(
   id                   varchar(32) not null,
   name                 varchar(30),
   staff_id             varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: bc_region                                             */
/*==============================================================*/
create table bc_region
(
   id                   varchar(32) not null,
   province             varchar(50),
   city                 varchar(50),
   district             varchar(50),
   postcode             varchar(50),
   shortcode            varchar(30),
   citycode             varchar(30),
   primary key (id)
);

/*==============================================================*/
/* Table: bc_staff                                              */
/*==============================================================*/
create table bc_staff
(
   id                   varchar(32) not null,
   name                 varchar(20),
   telephone            varchar(20),
   haspda               char(1),
   deltag               char(1),
   station              varchar(40),
   standard_id          varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: bc_standard                                           */
/*==============================================================*/
create table bc_standard
(
   id                   varchar(32) not null,
   name                 varchar(30),
   minweight            double,
   maxweight            double,
   deltag               char(1),
   user_id              varchar(32),
   updatetime           timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: bc_subarea                                            */
/*==============================================================*/
create table bc_subarea
(
   id                   varchar(32) not null,
   decidedzone_id       varchar(32),
   region_id            varchar(32),
   addresskey           varchar(100),
   startnum             varchar(30),
   endnum               varchar(30),
   single               char(1),
   position             varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   varchar(32) not null,
   username             varchar(20),
   password             varchar(32),
   gender               char(1),
   birthday             date,
   salary               double,
   station              varchar(40),
   telephone            varchar(40),
   remark               varchar(255),
   primary key (id)
);

alter table bc_decidedzone add constraint FK_decidedzone_staff foreign key (staff_id)
      references bc_staff (id) on delete restrict on update restrict;

alter table bc_staff add constraint FK_staff_standard foreign key (standard_id)
      references bc_standard (id) on delete restrict on update restrict;

alter table bc_standard add constraint FK_stardard_user foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table bc_subarea add constraint FK_area_decidedzone foreign key (decidedzone_id)
      references bc_decidedzone (id) on delete restrict on update restrict;

alter table bc_subarea add constraint FK_area_region foreign key (region_id)
      references bc_region (id) on delete restrict on update restrict;



/* 初始化一条记录  */
insert into user(id,username,password) values('abcdefghijklmn','admin',md5('admin'));



--取派模块
drop table if exists qp_noticebill;

drop table if exists qp_workbill;

drop table if exists qp_workordermanage;


create table qp_noticebill
(
   id                   varchar(32) not null,
   customer_id          varchar(32),
   customer_name        varchar(20),
   delegater            varchar(20),
   telephone            varchar(20),
   pickaddress          varchar(200),
   arrivecity           varchar(20),
   product              varchar(20),
   pickdate             date,
   num                  int,
   weight               double,
   volume               varchar(20),
   remark               varchar(255),
   ordertype            varchar(20),
   user_id              varchar(32),
   staff_id             varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: qp_workbill                                           */
/*==============================================================*/
create table qp_workbill
(
   id                   varchar(32) not null,
   noticebill_id        varchar(32),
   type                 varchar(20),
   pickstate            varchar(20),
   buildtime            timestamp,
   attachbilltimes      int,
   remark               varchar(255),
   staff_id             varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: qp_workordermanage                                    */
/*==============================================================*/
create table qp_workordermanage
(
   id                   varchar(32) not null,
   arrivecity           varchar(20),
   product              varchar(20),
   num                  int,
   weight               double,
   floadreqr            varchar(255),
   prodtimelimit        varchar(40),
   prodtype             varchar(40),
   sendername           varchar(20),
   senderphone          varchar(20),
   senderaddr           varchar(200),
   receivername         varchar(20),
   receiverphone        varchar(20),
   receiveraddr         varchar(200),
   feeitemnum           int,
   actlweit             double,
   vol                  varchar(20),
   managerCheck         varchar(1),
   updatetime           date,
   primary key (id)
);

alter table qp_noticebill add constraint FK_Reference_2 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table qp_noticebill add constraint FK_Reference_3 foreign key (staff_id)
      references bc_staff (id) on delete restrict on update restrict;

alter table qp_workbill add constraint FK_Reference_4 foreign key (staff_id)
      references bc_staff (id) on delete restrict on update restrict;

alter table qp_workbill add constraint FK_workbill_noticebill_fk foreign key (noticebill_id)
      references qp_noticebill (id) on delete restrict on update restrict;




