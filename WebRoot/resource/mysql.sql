create database ebookstore;
use ebookstore;
create table categories(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(100)
);