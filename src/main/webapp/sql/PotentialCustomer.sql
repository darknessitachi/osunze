create table PotentialCustomer(
	id int(10) not null primary key auto_increment,	
	name varchar(20),
	sex varchar(4),
	mobile varchar(13),
	email varchar(30),
	qq varchar(14),
	webChat varchar(15),
	job varchar(14),
	comefrom varchar(14),
	visitDate varchar(10),
	address varchar(100),
	description varchar(100)
)