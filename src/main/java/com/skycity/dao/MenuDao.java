package com.skycity.dao;

import java.util.List;

import com.skycity.entity.Menu;

public interface MenuDao  extends BaseDao<Menu>{
	public Menu getMenu(String menuId);
	
	public Menu getParentMenu(String menuId);
	
	public boolean hasChild(String parentId);
	
	public List<Menu> findChildMenus(String parentId);
	
	public int addMenu(Menu menu);
	
	public int updateMenu(Menu menu);
	
	public int delMenu(String id);
}
