package com.skycity.dao.impl;

import org.springframework.stereotype.Repository;

import com.skycity.dao.OrderDetailDao;
import com.skycity.entity.OrderDetail;

@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail> implements OrderDetailDao {

}
