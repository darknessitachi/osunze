package com.skycity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skycity.dao.GoodsDao;
import com.skycity.entity.Goods;
import com.skycity.service.GoodsService;
import com.skycity.util.PageInfo;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2015年9月25日 下午3:47:04 
 * 程序的简单说明 
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public PageInfo<Goods> query(PageInfo<Goods> pageInfo, Goods entity) {
		return goodsDao.queryPageInfo(pageInfo,entity);
	}

	public int add(Goods entity) {
		return goodsDao.add(entity);
	}

	public void delete(String ids) {
		goodsDao.delete(ids);
	}

	public Goods getById(String id) {
		return goodsDao.getById(id);
	}

	public int update(Goods entity) {
		return goodsDao.modify(entity);
	}

	public List<Goods> listModelBySupplierId(int supplierId) {
		Goods goods = new Goods();
		goods.setSupplierId(supplierId);
		return goodsDao.queryByGoods(goods);
	}

	public Goods getByModel(int supplierId, String model) {
		Goods goods = new Goods();
		goods.setSupplierId(supplierId);
		goods.setModel(model);
		return goodsDao.getGoodsByT(goods);
	}
}
