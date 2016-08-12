package com.skycity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skycity.entity.Code;
import com.skycity.entity.Order;
import com.skycity.framework.Constant;
import com.skycity.framework.UIFacade;
import com.skycity.framework.utility.StringUtil;
import com.skycity.service.CodeService;
import com.skycity.service.OrderService;
import com.skycity.util.PageInfo;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2015年10月4日 下午2:21:52 
 * 销售管理
 * 当添加订单信息时，先根据手机号码找是否存在相同的客户。
 * 如果找到相同的手机号码，则列出手机号码关联的用户信息
 * 如果没有找到相同的手机号码，则先新增客户信息，然后得到客户的ID，用于关联订单
 */
@Controller
@RequestMapping("/Order")
public class OrderController extends UIFacade<Order>{

	@Autowired
	private OrderService orderService;
	@Autowired
	private CodeService codeService;
	@RequestMapping
	public void list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String pageIndex = $V("page");
		String pageSize = $V("rows");
		page.setPageSize(Integer.parseInt(pageSize));
		page.setPageNo(Integer.parseInt(pageIndex));
		Order entity = new Order();
		PageInfo<Order> pageInfo = orderService.query(page, entity);
		pageInfo.getRows().forEach(s->{
			s.setOrderStatus(Constant.ORDERSTATUS.getValue(s.getOrderStatus()));
		});
		response.getWriter().write(objectMapper.writeValueAsString(pageInfo));
	}
	@RequestMapping("/editUI")
	public ModelAndView editUI(HttpServletRequest request,HttpServletResponse response){
		initRequest(request);
		String id=$V("id");
		Order order = new Order();
		List<Code> orderChargeList = codeService.getList("OrderCharge");
		if(StringUtil.isNotNull(id)){
//			goods = goodsService.getById(id);
//			goods.setColorList(Arrays.asList(goods.getColor().split("\\|")));
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("order", order);
		mv.addObject("orderChargeList", orderChargeList);
		mv.setViewName("Platform/SaleDialog");
		return mv;
	}
	
	/**
	 * 保存订单的时候同时要保存顾客信息，订单信息，订单明细信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initRequest(request);
		try {
			Order order = objectMapper.readValue($V("_SEND_DATA"), Order.class);
			int result = orderService.addOrder(order);
			if(result==1){
				Response.setSuccessMessage("订单添加成功");
			}
		}catch (Exception e) {
			Response.setSuccessMessage("订单添加失败");
		}
		response.getWriter().write(Response.toJson());
	}
	
	@RequestMapping("showOrderInfo")
	public ModelAndView showOrderInfo(int id){
		Order order = orderService.getOrder(id);
		List<Code> productList = codeService.getList("OrderProduct");
		List<Code> unitList = codeService.getList("Unit");
		order.getOrderDetail().forEach(s->{
			s.setGoodsName(codeService.getCodeName(productList, s.getGoodsName()));
			s.setUnit(codeService.getCodeName(unitList, s.getUnit()));
		});
		ModelAndView mv = new ModelAndView();
		mv.addObject("order", order);
		mv.setViewName("Platform/ShowOrderInfo");
		return mv;
	}
}
