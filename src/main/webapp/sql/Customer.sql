create table customer(
	id int(10) not null primary key auto_increment,	
	name varchar(20),
	sex varchar(4),
	mobile varchar(13),
	email varchar(30),
	qq varchar(14),
	webChat varchar(15),
	job varchar(14),
	address varchar(100),
	comefrom varchar(20),
	description varchar(100)
)