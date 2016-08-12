create table resources(
	resourcesId int(10) NOT NULL primary key AUTO_INCREMENT,
	name varchar(20) not null,
	resurl varchar(40),
	type varchar(10),
	description varchar(40)
	);