
create database tracking_gps;
use tracking_gps;

CREATE TABLE USER(
	id int not null auto_increment,
	email varchar(100) not null,
	password varchar(100) not null,
	name varchar(150) null,
	last_name varchar(150) null,
	PRIMARY KEY(id)
);

CREATE TABLE DESTINY (
	id int not null auto_increment,
	observation varchar(100) not null,
	user_id int not null,
	external_id int null,
	FOREIGN KEY (user_id) REFERENCES USER(id),
	PRIMARY KEY(id),
	INDEX external_index (external_id)
);

CREATE TABLE ROUTE_PATH (
	id bigint NOT NULL AUTO_INCREMENT,
    type enum('AUTOMATIC', 'ON_DEMAND') not null,
	destiny_id bigint null,
	external_id bigint null,
	user_id int not null,
	longitude decimal(20,10) null,
	latitude decimal(20,10) null,
	speed varchar(50) null,
	temperature varchar(50) null,
	humidity varchar(50) null,
    time_stamp varchar(50) null,
	INDEX destiny_index (destiny_id),
	INDEX external_destiny_index (external_id),
	INDEX user_index (user_id),
	PRIMARY KEY(id)
);
