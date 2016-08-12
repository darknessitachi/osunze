package com.skycity.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.skycity.framework.collection.Mapx;
import com.skycity.util.PageInfo;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDaoImpl<T> extends SqlSessionDaoSupport{

	@Resource  
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
    	super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	public String getClassName(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class) pt.getActualTypeArguments()[0];
		return clazz.getSimpleName().toString().toLowerCase();
	}
	
	
	public int add(T t) {
		return getSqlSession().insert(this.getClassName()+".add",t);
	}
	
	public void delete(String id) {
		String[] ids = id.split(",");
		for(String id2:ids){
			getSqlSession().delete(this.getClassName()+".deleteById",id2);
		}
	}
	
	public T getById(String id) {
		return (T)getSqlSession().selectOne(this.getClassName()+".getById",id);
	}
	public int modify(T t) {
		return getSqlSession().update(this.getClassName()+".update",t);
	}
	
	public List<T> query(PageInfo<T> pageInfo,T t) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageInfo);
		map.put("t", t);
		return getSqlSession().selectList(this.getClassName()+".query",map);
	}
	
	public PageInfo<T> queryPageInfo(PageInfo<T> pageInfo,T t) {
		Mapx<String, Object> map = new Mapx<String, Object>();
		map.put("paging", pageInfo);
		map.put("t", t);
		List<T> list = getSqlSession().selectList(this.getClassName()+".query",map);
		pageInfo.setRows(list);
		int totalCount = getSqlSession().selectOne(this.getClassName()+".count",t);
		pageInfo.setTotal(totalCount);
		return pageInfo;
	}
	
	public List<T> queryAll(T t) {
		return getSqlSession().selectList(this.getClassName()+".queryAll",t);
	}
	
	public int getCount(T t){
		return getSqlSession().selectOne(this.getClassName()+".count",t);
	}
}
