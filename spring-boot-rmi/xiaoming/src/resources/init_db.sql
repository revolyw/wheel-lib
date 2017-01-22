create database sampledb default charset utf8;
use sampledb;

create table user
(
	id int unsigned auto_increment,
	username varchar(50) unique not null,
	passwd varchar(50) not null,

	primary key (id)
);