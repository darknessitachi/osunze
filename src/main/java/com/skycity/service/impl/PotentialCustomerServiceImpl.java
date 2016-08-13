package com.skycity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skycity.dao.PotentialCustomerDao;
import com.skycity.entity.PotentialCustomer;
import com.skycity.service.PotentialCustomerService;
import com.skycity.util.PageInfo;

@Service("potentialCustomerService")
public class PotentialCustomerServiceImpl implements PotentialCustomerService {
	@Autowired
	private PotentialCustomerDao potentialCustomerDao;
	
	public PageInfo<PotentialCustomer> query(PageInfo<PotentialCustomer> pageInfo, PotentialCustomer entity) {
		return potentialCustomerDao.queryPageInfo(pageInfo, entity);
	}

	@Override
	public int save(PotentialCustomer entity) {
		if(0==entity.getId()){
			return potentialCustomerDao.add(entity);
		}else{
			return potentialCustomerDao.modify(entity);
		}
	}

	@Override
	public PotentialCustomer getById(String id) {
		return potentialCustomerDao.getById(id);
	}
}
