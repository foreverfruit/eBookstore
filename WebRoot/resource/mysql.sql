create database ebookstore;
use ebookstore;
create table categories(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(100)
);
create table books(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	author varchar(100),
	price float(8,2),
	`path` varchar(100),
	filename varchar(100),
	description varchar(100),
	categoryid varchar(100),
	constraint category_id_fk foreign key(categoryid) references categories(id)
);