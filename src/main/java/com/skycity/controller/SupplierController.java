package com.skycity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skycity.entity.Supplier;
import com.skycity.framework.UIFacade;
import com.skycity.framework.i18n.LangUtil;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.framework.utility.StringUtil;
import com.skycity.service.SupplierService;
import com.skycity.util.PageInfo;

@Controller("Supplier")
@RequestMapping("/Supplier/")
public class SupplierController extends UIFacade<Supplier> {
	@Autowired
	private SupplierService supplierService;

	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String pageIndex = $V("page");
		String pageSize = $V("rows");
		page.setPageSize(Integer.parseInt(pageSize));
		page.setPageNo(Integer.parseInt(pageIndex));
		Supplier entity = new Supplier();
		entity.setQrText($V("qrText"));
		PageInfo<Supplier> pageInfo = supplierService.query(page, entity);
		String jsonStr = JsonUtil.bean2json(pageInfo);
		response.getWriter().write(jsonStr);
	}
	
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initRequest(request);
		try {
			Supplier entity = objectMapper.readValue($V("_SEND_DATA"), Supplier.class);
			if(StringUtil.isNull(entity.getId())){
				supplierService.add(entity);
				Response.setSuccessMessage(LangUtil.get("Supplier")+LangUtil.get("Common.AddSuccess"));
			}else{
				supplierService.update(entity);
				Response.setSuccessMessage(LangUtil.get("Supplier")+LangUtil.get("Common.ModifySuccess"));
			}
			response.getWriter().write(Response.toJson());
		}catch (Exception e) {
			Response.setFailedMessage(LangUtil.get("Supplier")+LangUtil.get("Common.AddOrUpdateFailed")+",cause["+e.getMessage()+"]");
			response.getWriter().write(Response.toJson());
		}
	}
	
	@RequestMapping("update")
	public String update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		try {
			String id = $V("id");
			Supplier supplier = supplierService.getById(id);
			request.setAttribute("entity", supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Platform/SupplierDialog";
	}
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		this.initRequest(request);
		supplierService.delete($V("IDs"));
		this.Response.setSuccessMessage(LangUtil.get("Supplier")+LangUtil.get("Common.DeleteSuccess"));
		response.getWriter().write(this.Response.toJson());
	}
	
	@RequestMapping("getAllList")
	public void getAllList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		List<Supplier> supplierList = supplierService.getList();
		String jsonStr = JsonUtil.list2json(supplierList);
		response.getWriter().write(jsonStr);
	}
}
