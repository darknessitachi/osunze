package com.skycity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.skycity.dao.GoodsDao;
import com.skycity.entity.Goods;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2015年9月25日 下午3:44:50 
 * 程序的简单说明 
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {

	public List<Goods> queryByGoods(Goods goods) {
		return getSqlSession().selectList(this.getClassName()+".queryByCondition",goods);
	}

	public Goods getGoodsByT(Goods goods) {
		return getSqlSession().selectOne(this.getClassName()+".queryByCondition",goods);
	}

}
