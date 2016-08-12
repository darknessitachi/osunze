package com.skycity.dao.impl;

import org.springframework.stereotype.Repository;

import com.skycity.dao.CustomerDao;
import com.skycity.entity.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public Customer getCustomerByMobile(String mobile) {
		return getSqlSession().selectOne("customer.selectByMobile", mobile);
	}
}
