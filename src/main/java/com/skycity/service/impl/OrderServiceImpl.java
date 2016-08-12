package com.skycity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skycity.dao.CustomerDao;
import com.skycity.dao.OrderDao;
import com.skycity.dao.OrderDetailDao;
import com.skycity.entity.Customer;
import com.skycity.entity.Goods;
import com.skycity.entity.Order;
import com.skycity.service.OrderService;
import com.skycity.util.PageInfo;

/**
 * @author YingBo.Dai
 * @E-mail:lyyb2001@163.com
 * @qq:20880488
 * @version 创建时间：2016年8月10日 上午8:53:22 程序的简单说明
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderDetailDao orderDetailDao;

	@Transactional
	public int addOrder(Order order) {
		Customer customer = customerDao.getCustomerByMobile(order.getCustomer().getMobile());
		if(customer==null){
			customerDao.add(order.getCustomer());
		}else{
			order.setCustomer(customer);
		}
		orderDao.add(order);
		order.getOrderDetail().forEach(s -> {
			s.setOrderId(order.getId());
			orderDetailDao.add(s);
		});
		return 1;
	}
	
	public PageInfo<Order> query(PageInfo<Order> pageInfo, Order entity) {
		return orderDao.queryPageInfo(pageInfo,entity);
	}

	@Override
	public Order getOrder(int id) {
		return orderDao.getOrderById(id);
	}
}
