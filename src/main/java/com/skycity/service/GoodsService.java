package com.skycity.service;

import java.util.List;

import com.skycity.entity.Goods;
import com.skycity.util.PageInfo;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2015年9月25日 下午3:46:02 
 * 程序的简单说明 
 */
public interface GoodsService {

	public PageInfo<Goods> query(PageInfo<Goods> pageInfo,Goods entity);
	
	public int add(Goods entity);

	public void delete(String ids);
	
	public int update(Goods entity);
	
	public Goods getById(String id);
	
	public List<Goods> listModelBySupplierId(int supplierId);
	
	public Goods getByModel(int supplierId,String model);
}
