package com.skycity.dao;

import com.skycity.entity.Customer;

public interface CustomerDao extends BaseDao<Customer>{
	/**
	 * 根据电话号码客户信息
	 * @param mobile
	 * @return
	 */
	public Customer getCustomerByMobile(String mobile);
}
