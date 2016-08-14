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
import com.skycity.entity.PotentialCustomer;
import com.skycity.framework.Constant;
import com.skycity.framework.UIFacade;
import com.skycity.framework.i18n.LangUtil;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.framework.utility.StringUtil;
import com.skycity.service.CodeService;
import com.skycity.service.PotentialCustomerService;
import com.skycity.util.PageInfo;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2015年9月25日 下午3:41:27 
 * 商品信息管理
 */
@Controller
@RequestMapping("/potentialCustomer")
public class PotentialCustomerController extends UIFacade<PotentialCustomer> {
	@Autowired
	private PotentialCustomerService potentialCustomerService;
	@Autowired
	private CodeService codeService;
	@RequestMapping
	public String list(){
		return "Platform/PotentialCustomer";
		
	}
	
	@RequestMapping("/queryList")
	public void queryList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String pageIndex = $V("page");
		String pageSize = $V("rows");
		page.setPageSize(Integer.parseInt(pageSize));
		page.setPageNo(Integer.parseInt(pageIndex));
		PotentialCustomer entity = new PotentialCustomer();
		PageInfo<PotentialCustomer> pageInfo = potentialCustomerService.query(page, entity);
		String jsonStr = JsonUtil.bean2json(pageInfo);
		response.getWriter().write(jsonStr);
	}
	
	
	@RequestMapping("editUI")
	public ModelAndView editUI(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		initRequest(request);
		String id=$V("id");
		PotentialCustomer potentialCustomer = new PotentialCustomer();
		if(StringUtil.isNotNull(id)){
			potentialCustomer = potentialCustomerService.getById(id);
		}
		List<Code> sexList = codeService.getList(Constant.CODE_SEX);
		view.addObject("potentialCustomer",potentialCustomer);
		view.addObject("sexList", sexList);
		view.setViewName("Platform/PotentialCustomerDialog");
		return view;
	}
	
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		try {
			PotentialCustomer entity = objectMapper.readValue($V("_SEND_DATA"), PotentialCustomer.class);
			potentialCustomerService.save(entity);
			Response.setSuccessMessage(LangUtil.get("Code")+LangUtil.get("Common.ModifySuccess"));
		}catch (Exception e) {
			Response.setFailedMessage(LangUtil.get("Code")+LangUtil.get("Common.AddOrUpdateFailed")+",cause["+e.getMessage()+"]");
		}
		response.getWriter().write(Response.toJson());
	}
}
