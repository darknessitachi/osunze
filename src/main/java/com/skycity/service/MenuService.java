package com.skycity.service;

import java.util.List;

import com.skycity.entity.Menu;
import com.skycity.framework.ui.controls.TreeNode;

public interface MenuService {
	public Menu getMenu(String menuId);
	
	public Menu getParentMenu(String menuId);
	
	public List<TreeNode> getMenuTree(String id);
	
	public int addMenu(Menu menu);
	
	public int updateMenu(Menu menu);
	
	public int delMenu(String id);
}
