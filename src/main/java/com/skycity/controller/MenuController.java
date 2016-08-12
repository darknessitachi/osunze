package com.skycity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skycity.entity.Menu;
import com.skycity.framework.UIFacade;
import com.skycity.framework.ui.controls.TreeNode;
import com.skycity.framework.utility.JsonUtil;
import com.skycity.framework.utility.StringUtil;
import com.skycity.service.MenuService;

@Controller("Menu")
@RequestMapping(value = "/Menu/")
public class MenuController extends UIFacade<Menu> {
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping("showMenu")
	public void showMenu(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		initRequest(request);
		String currentId = $V("id");
		List<TreeNode> treeList = null;
		if (StringUtil.isNotNull(currentId)) {
			treeList = menuService.getMenuTree(currentId);
		} else {
			treeList = menuService.getMenuTree("0");
		}
		String jsonStr = JsonUtil.list2json(treeList);
		response.getWriter().write(jsonStr);
		clearData();
	}

	@RequestMapping("delMenu")
	public void delMenu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		String id = $V("id");
		if (StringUtil.isNotNull(id)) {
			int result = menuService.delMenu(id);
			if(result>0){
				Response.setSuccessMessage("删除成功");
			}else{
				Response.setFailedMessage("删除失败");
			}
		}
		response.getWriter().write(Response.toJson());
	}
	
	@RequestMapping("addUI")
	public String addUI(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		Menu menu = new Menu();
		Menu parentMenu = menuService.getMenu($V("parentId"));
		menu.setParentId($V("parentId"));
		menu.setParent(parentMenu);
		request.setAttribute("menu", menu);
		return "/Platform/MenuDialog";
	}
	
	@RequestMapping("addMenu")
	public void addMenu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
		try {
//			Menu menu = JsonUtil.toPOJO($V("_SEND_DATA"), Menu.class);
//			if(StringUtil.isNull(menu.getId())){
//				menuService.addMenu(menu);
//			}else{
//				menuService.updateMenu(menu);
//			}
		} catch (Exception e) {
			Response.setFailedMessage("添加菜单失败,cause["+e.getMessage()+"]");
			response.getWriter().write(Response.toJson());
		}
	}
	
	@RequestMapping("updateUI")
	public String updateUI(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		initRequest(request);
//		Menu menu = menuService.getMenu($V("id"));
//		Menu parentMenu= menuService.getMenu(menu.getParentId());
//		menu.setParent(parentMenu);
//		if(StringUtil.isNotNull(menu.getRefRes())){
//			String refName="";
//			String[] rs= StringUtil.splitEx(menu.getRefRes(),",");
//			for(String id:rs){
//				refName += resourcesService.getById(id).getName()+",";
//			}
//			refName = refName.substring(0,refName.length()-1);
//			request.setAttribute("refResName", refName);
//		}
//		request.setAttribute("menu", menu);
		return "/Platform/MenuDialog";
	}
	
	@RequestMapping("queryRefResList")
	public void queryRefResList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		initRequest(request);
//		String menuId = $V("menuId");
//		Menu menu = menuService.getMenu(menuId);
//		if(StringUtil.isNotNull(menu.getRefRes())){
//			PageInfo<Resources> page = new PageInfo<Resources>(10);
//			String rsfId= StringUtil.singleQuote(menu.getRefRes(),",");
//			String pageIndex = $V("page");
//			String pageSize = $V("rows");
//			page.setPageSize(Integer.parseInt(pageSize));
//			page.setPageNo(Integer.parseInt(pageIndex));
//			Resources resource = new Resources();
//			resource.setId(rsfId);
//			PageInfo<Resources> pageInfo = resourcesService.queryPageInfo(page,resource);
//			String jsonStr = JsonUtil.bean2json(pageInfo);
//			response.getWriter().write(jsonStr);
//		}
	}
}
