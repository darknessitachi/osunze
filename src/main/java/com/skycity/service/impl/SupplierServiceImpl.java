package com.skycity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skycity.dao.SupplierDao;
import com.skycity.entity.Supplier;
import com.skycity.service.SupplierService;
import com.skycity.util.PageInfo;


@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDao supplierDao;
	
	public PageInfo<Supplier> query(PageInfo<Supplier> pageInfo, Supplier entity) {
		return supplierDao.queryPageInfo(pageInfo, entity);
	}

	public int add(Supplier entity) {
		return supplierDao.add(entity);
	}

	public int update(Supplier entity) {
		return supplierDao.modify(entity);
	}

	public Supplier getById(String id) {
		return supplierDao.getById(id);
	}
	
	public void delete(String id) {
		supplierDao.delete(id);
	}

	public List<Supplier> getList() {
		Supplier t  = new Supplier();
		return supplierDao.queryAll(t);
	}
}
