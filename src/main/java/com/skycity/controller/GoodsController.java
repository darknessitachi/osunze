package com.skycity.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skycity.entity.Code;
import com.skycity.entity.Goods;
import com.skycity.entity.Supplier;
import com.skycity.framework.Constant;
import com.skycity.framework.UIFacade;
import com.skycity.framework.collection.Mapx;
import com.skycity.framework.i18n.LangUtil;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.framework.utility.StringUtil;
import com.skycity.service.CodeService;
import com.skycity.service.GoodsService;
import com.skycity.service.SupplierService;
import com.skycity.util.PageInfo;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2015年9月25日 下午3:41:27 
 * 商品信息管理
 */
@Controller
@RequestMapping("/Goods/")
public class GoodsController extends UIFacade<Goods> {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String pageIndex = $V("page");
		String pageSize = $V("rows");
		page.setPageSize(Integer.parseInt(pageSize));
		page.setPageNo(Integer.parseInt(pageIndex));
		Goods entity = new Goods();
//		qrcode.setQrCode($V("qrCode"));
//		qrcode.setParentCode($V("parentCode"));
		PageInfo<Goods> pageInfo = goodsService.query(page, entity);
		String jsonStr = JsonUtil.bean2json(pageInfo);
		response.getWriter().write(jsonStr);
	}
	
	@RequestMapping("editUI")
	public String editUI(HttpServletRequest request,HttpServletResponse response){
		initRequest(request);
		String id=$V("id");
		Goods goods = new Goods();
		if(StringUtil.isNotNull(id)){
			goods = goodsService.getById(id);
			goods.setColorList(Arrays.asList(goods.getColor().split(",")));
		}
		List<Supplier> supplierList = supplierService.getList();
		List<Code> brandList = codeService.getList(Constant.CODE_BRAND);
		List<Code> goodsTypeList = codeService.getList(Constant.CODE_GOODSTYPE);
		List<Code> levelList = codeService.getList(Constant.CODE_LEVEL);
		List<Code> unitList = codeService.getList(Constant.CODE_UNIT);
		List<Code> colorList = codeService.getList(Constant.CODE_COLOR);
		request.setAttribute("goods", goods);
		request.setAttribute("supplierList",supplierList);		//供应商列表
		request.setAttribute("brandList", brandList);			//品牌
		request.setAttribute("goodsTypeList",goodsTypeList);	//商品类别
		request.setAttribute("unitList", unitList); 			//单位
		request.setAttribute("colorList", colorList); 			//颜色
		request.setAttribute("levelList", levelList); 			//颜色
		
		return "Platform/GoodsDialog";
	}
	
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initRequest(request);
		try {
			@SuppressWarnings("unchecked")
			Mapx<String,Object> mapx = objectMapper.readValue($V("_SEND_DATA"), Mapx.class);
			Goods entity = new Goods();
			entity.setId(mapx.getString("id"));
			entity.setSupplierId(mapx.getInt("supplierId"));
			entity.setBrand(mapx.getString("brand"));
			entity.setGoodType(mapx.getString("goodType"));
			entity.setModel(mapx.getString("model"));
			entity.setUnit(mapx.getString("unit"));
			entity.setOrigin(mapx.getString("origin"));
			entity.setColor(mapx.getString("colorList"));
			entity.setBarcode(mapx.getString("barcode"));
			entity.setWeight(mapx.getString("weight"));
			entity.setSizeLong(mapx.getString("sizeLong"));
			entity.setSizeWidth(mapx.getString("sizeWidth"));
			entity.setSizeHeight(mapx.getString("sizeHeight"));
			entity.setMaterial(mapx.getString("material"));
			entity.setLevel(mapx.getString("level"));
			entity.setRetailPrice(mapx.getString("retailPrice"));
			entity.setDescription(mapx.getString("description"));
			if(StringUtil.isNull(entity.getId())){
				goodsService.add(entity);
				Response.setSuccessMessage(LangUtil.get("Goods.Title")+LangUtil.get("Common.AddSuccess"));
			}else{
//				entity.setColor(entity.getColorList().stream().collect(Collectors.joining("|")));
				goodsService.update(entity);
				Response.setSuccessMessage(LangUtil.get("Goods.Title")+LangUtil.get("Common.ModifySuccess"));
			}
			response.getWriter().write(Response.toJson());
		}catch (Exception e) {
			Response.setFailedMessage(LangUtil.get("Goods.Title")+LangUtil.get("Common.AddOrUpdateFailed")+",cause["+e.getMessage()+"]");
			response.getWriter().write(Response.toJson());
		}
	}
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		this.initRequest(request);
		goodsService.delete($V("IDs"));
		this.Response.setSuccessMessage(LangUtil.get("Goods.Title")+LangUtil.get("Common.DeleteSuccess"));
		response.getWriter().write(this.Response.toJson());
	}
	
	/**
	 * 列出供应商下的所有商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("listModelBySupplierId")
	public void listModelBySupplierId(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.initRequest(request);
		int supplierId = Integer.parseInt($V("supplierId"));
		List<Goods> goodsList = goodsService.listModelBySupplierId(supplierId);
		String jsonStr = JsonUtil.list2json(goodsList);
		response.getWriter().write(jsonStr);
	}
	
	/**
	 * 根据型号得到商品信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getGoodsByModel")
	public void getGoodsByModel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		this.initRequest(request);
		Goods goods = goodsService.getByModel(0, $V("model"));
		String jsonStr = JsonUtil.bean2json(goods);
		response.getWriter().write(jsonStr);
	}
}
