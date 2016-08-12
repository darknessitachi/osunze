package com.skycity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skycity.entity.Permission;
import com.skycity.entity.Roles;
import com.skycity.framework.UIFacade;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.framework.utility.RightsHelper;
import com.skycity.service.PermissionService;
import com.skycity.service.RolesService;
import com.skycity.util.PageInfo;


@Controller
@RequestMapping(value = "/roles/")
public class RolesController extends UIFacade<Roles>{
	@Autowired
	private RolesService rolesService;
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
		Roles res = new Roles();
		res.setName($V("name"));
		PageInfo<Roles> pageInfo = rolesService.queryPageInfo(page,res);
		String jsonStr = JsonUtil.bean2json(pageInfo);
		response.getWriter().write(jsonStr);
	}
	
	@RequestMapping("auth")
	public ModelAndView auth(@RequestParam int roleId)throws Exception{
		ModelAndView mv = new ModelAndView();
		List<Permission> allMenuList = permissionService.getAllMenus();
		List<Integer> auths = permissionService.queryMenusByRoleId(roleId);
		allMenuList.forEach(s->{
			s.setHasMenu(RightsHelper.testRights(auths, s.getId()));;
		});
		String json = objectMapper.writeValueAsString(allMenuList);
		mv.addObject("zTreeNodes", json);
		mv.addObject("roleId",roleId);
		
		List<String> authString = new ArrayList<String>();
		CollectionUtils.collect(auths, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return String.valueOf(arg0);
			}
		}, authString);
		mv.addObject("menuIds",authString.stream().collect(Collectors.joining(",")));
		mv.setViewName("Platform/authorization");
		return mv;
	}
	
	/**
	 * 保存角色菜单权限
	 */
	@RequestMapping("auth/save")
	public void saveAuth(HttpServletRequest request,HttpServletResponse response)throws Exception{
		initRequest(request);
		int roleId = data.getInt("roleId");
		String menuIds = data.getString("menuIds");
		if(null != menuIds && !"".equals(menuIds.trim())){
			rolesService.updateRoleRights(roleId,menuIds);
		}else{
//			Roles role = new Roles();
//			role.setRights("");
//			role.setRoleId(roleId);
//			roleService.updateRoleRights(role);
		}
		Response.setSuccessMessage("权限设置成功");
		response.getWriter().write(Response.toJson().replace("'", ""));
	}
	
//	@RequestMapping("delResource")
//	public void delResource(HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		this.initRequest(request);
////		resourcesService.delete($V("IDs"));
//		this.Response.setSuccessMessage(LangUtil.get("Resources")+LangUtil.get("Common.DeleteSuccess"));
//		response.getWriter().write(this.Response.toJson());
//	}
//	
//	@RequestMapping("resourceSelectDialog")
//	public String resourceSelectDialog(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		initRequest(request);
//		String id= $V("id");
//		request.setAttribute("id", id);
//		return "/Platform/resourceSelectDialog";
//	}
//	
//	@RequestMapping("save")
//	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		initRequest(request);
//		try {
//			Permission permission = objectMapper.readValue($V("_SEND_DATA"), Permission.class);
//			if(permission.getId()==0){
//				permissionService.add(permission);
//				Response.setSuccessMessage(LangUtil.get("Resources")+LangUtil.get("Common.AddSuccess"));
//			}else{
//				permissionService.update(permission);
//				Response.setSuccessMessage(LangUtil.get("Resources")+LangUtil.get("Common.ModifySuccess"));
//			}
//		}catch (Exception e) {
//			Response.setFailedMessage(LangUtil.get("Resources")+LangUtil.get("Common.AddOrUpdateFailed")+",cause["+e.getMessage()+"]");
//		}
//		response.getWriter().write(Response.toJson());
//	}
//	
//	@RequestMapping("update")
//	public String update(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		initRequest(request);
//		try {
//			String id = $V("id");
//			Permission resource = permissionService.getById(id);
//			request.setAttribute("resource", resource);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "Platform/ResourcesDialog";
//	}
}
