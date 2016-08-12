package com.skycity.dao;

import com.skycity.entity.Order;

public interface OrderDao extends BaseDao<Order>{
	
	public Order getOrderById(int id);
	
}
