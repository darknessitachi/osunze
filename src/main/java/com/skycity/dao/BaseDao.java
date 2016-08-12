package com.skycity.dao;

import java.util.List;

import com.skycity.util.PageInfo;

public interface BaseDao<T> {
	
	public List<T> query(PageInfo<T> pageInfo,T t);
	
	public PageInfo<T> queryPageInfo(PageInfo<T> pageInfo,T t);

	public List<T> queryAll(T t);
	
	public void delete(String id);
	
	public int modify(T t);
	
	public T getById(String id);
	
	public int add(T t);
	
	public int getCount(T t);
}
