	/*订单详情表*/
DROP TABLE IF EXISTS orderDetail;

	
create table orderDetail(
	id int(10) NOT NULL primary key AUTO_INCREMENT,
	orderId int,
	goodsName varchar(20),
	unit varchar(20),		
	count float,
	price float,
	amountOfPrice float
)
