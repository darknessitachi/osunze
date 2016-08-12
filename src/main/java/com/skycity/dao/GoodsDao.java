package com.skycity.dao;

import java.util.List;

import com.skycity.entity.Goods;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2015年9月25日 下午3:43:50 
 * 程序的简单说明 
 */
public interface GoodsDao extends BaseDao<Goods>{
	public List<Goods> queryByGoods(Goods goods);
	
	public Goods getGoodsByT(Goods goods);
}
