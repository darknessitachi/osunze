DROP TABLE IF EXISTS goods;

create table goods(
	id int(10) not null auto_increment,		
	supplierId int(10) not null,		/**供应商Id*/
	brand varchar(20),		/**品牌*/
	goodType varchar(10),	/**商品类别*/
	model varchar(50),		/**型号*/
	unit varchar(10),		/**商品单位*/
	origin varchar(20),		/**产地*/
	color varchar(10),		/**颜色(列表)*/
	barcode varchar(20),	/**条码信息*/
	weight float,			/**净重*/
	sizeLong float,			/**长*/
	sizeWidth float,		/**宽*/
	sizeHeight float,		/**高*/
	material varchar(20),	/**材质*/
	level varchar(20),		/**等级*/
	description varchar(100), /**描述信息*/
	 PRIMARY KEY (id)
);