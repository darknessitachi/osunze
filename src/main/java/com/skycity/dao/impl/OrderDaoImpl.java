package com.skycity.dao.impl;

import org.springframework.stereotype.Repository;

import com.skycity.dao.OrderDao;
import com.skycity.entity.Order;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

	@Override
	public Order getOrderById(int id) {
		return getSqlSession().selectOne("order.getOrderById", id);
	}

}
