package com.skycity.service;

import com.skycity.entity.Order;
import com.skycity.util.PageInfo;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2016年8月10日 上午8:53:08 
 * 程序的简单说明 
 */
public interface OrderService {
	/**
	 * 新增订单
	 * @param order
	 * @return
	 */
	public int addOrder(Order order);
	
	public PageInfo<Order> query(PageInfo<Order> pageInfo, Order entity);
	
	public Order getOrder(int id);
}
