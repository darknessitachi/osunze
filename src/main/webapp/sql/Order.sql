	/*订单表*/
DROP TABLE IF EXISTS orderInfo;

	
create table orderInfo(
	id int(10) NOT NULL primary key AUTO_INCREMENT,
	orderDate date,
	orderTotalPrice float,
	orderStatus varchar(2),		
	orderDescrption varchar(60),
	lastUpdateTime datetime on update current_timestamp,
	orderCharge varchar(20),
	floorMaterial varchar(20),
	shutterMaterial varchar(20),
	cabinetCabinetMaterial varchar(20),
	seePlateMaterial varchar(20),
	mesaColor varchar(20),
	doorColor varchar(20),
	floorCabinetMaterial varchar(20),
	topSealingMaterial varchar(20),
	mesaBlock varchar(20),
	otherMaterial varchar(20),
	otherMaterial2 varchar(20),
	handle varchar(20),
	customerId int(10)
)
