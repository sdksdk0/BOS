--mysql

CREATE DATABASE bos;
USE bos;


/* 创建user数据表 */
create table user (
	id varchar(32) primary key,
	username varchar(20),
	password varchar(32), /* md5加密密码 */
	salary double ,
	birthday date ,
	gender varchar(10),
	station varchar(40),
	telephone varchar(11),
	remark varchar(255)
);

/* 初始化一条记录  */
insert into user(id,username,password) values('abcdefghijklmn','admin',md5('admin'));