package com.skycity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skycity.entity.Permission;
import com.skycity.framework.UIFacade;
import com.skycity.framework.i18n.LangUtil;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.service.PermissionService;
import com.skycity.util.PageInfo;


@Controller
@RequestMapping(value = "/resources/")
public class ResourcesController extends UIFacade<Permission>{
	@Autowired
	private PermissionService permissionService;

	/**
	 * list
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initRequest(request);
		String pageIndex = $V("page");
		String pageSize = $V("rows");
		page.setPageSize(Integer.parseInt(pageSize));
		page.setPageNo(Integer.parseInt(pageIndex));
		Permission res = new Permission();
		res.setName($V("name"));
		PageInfo<Permission> pageInfo = permissionService.queryPageInfo(page,res);
		String jsonStr = JsonUtil.bean2json(pageInfo);
		response.getWriter().write(jsonStr);
	}
	
	@RequestMapping("delResource")
	public void delResource(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		this.initRequest(request);
		permissionService.delete($V("IDs"));
		this.Response.setSuccessMessage(LangUtil.get("Resources")+LangUtil.get("Common.DeleteSuccess"));
		response.getWriter().write(this.Response.toJson());
	}
	
	@RequestMapping("toAdd")
	public ModelAndView toAdd(){
		ModelAndView mv = new ModelAndView();
		List<Permission> rootMenus = permissionService.getRootMenus();
		mv.addObject("rootMenus", rootMenus);
		mv.setViewName("Platform/ResourcesDialog");
		return mv;
	}
	
	@RequestMapping("resourceSelectDialog")
	public String resourceSelectDialog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initRequest(request);
		String id= $V("id");
		request.setAttribute("id", id);
		return "/Platform/resourceSelectDialog";
	}
	
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initRequest(request);
		try {
			Permission permission = objectMapper.readValue($V("_SEND_DATA"), Permission.class);
			permission.setType((0==permission.getParentId())?"0":"1");	//如果父节点是0，表示当前资源为目录
			if(permission.getId()==0){
				permissionService.add(permission);
				Response.setSuccessMessage(LangUtil.get("Resources")+LangUtil.get("Common.AddSuccess"));
			}else{
				permissionService.update(permission);
				Response.setSuccessMessage(LangUtil.get("Resources")+LangUtil.get("Common.ModifySuccess"));
			}
		}catch (Exception e) {
			Response.setFailedMessage(LangUtil.get("Resources")+LangUtil.get("Common.AddOrUpdateFailed")+",cause["+e.getMessage()+"]");
		}
		response.getWriter().write(Response.toJson());
	}
	
	@RequestMapping("update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		try {		
			initRequest(request);
			String id = $V("id");
			Permission resource = permissionService.getById(id);
			List<Permission> rootMenus = permissionService.getRootMenus();
			mv.addObject("rootMenus", rootMenus);
			mv.addObject("resource", resource);
			mv.setViewName("Platform/ResourcesDialog");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
