package com.skycity.service;

import java.util.List;

import com.skycity.entity.Supplier;
import com.skycity.util.PageInfo;

public interface SupplierService {
	
	public PageInfo<Supplier> query(PageInfo<Supplier> pageInfo,Supplier entity);
	
	public List<Supplier> getList();
	
	public int add(Supplier entity);
	
	public int update(Supplier entity);
	
	public void delete(String ids);
	
	public Supplier getById(String id);
//	
//	public int getCodeCount(Code code);
}
