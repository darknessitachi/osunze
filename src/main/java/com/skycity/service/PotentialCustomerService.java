package com.skycity.service;

import com.skycity.entity.PotentialCustomer;
import com.skycity.util.PageInfo;

public interface PotentialCustomerService {
	public PageInfo<PotentialCustomer> query(PageInfo<PotentialCustomer> pageInfo, PotentialCustomer entity);
	
	public int save(PotentialCustomer entity);
	
	public PotentialCustomer getById(String id);
}
