package com.skycity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.skycity.dao.MenuDao;
import com.skycity.entity.Menu;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

	public Menu getMenu(String menuId) {
		return getById(menuId);
	}

	public Menu getParentMenu(String menuId){
		return getSqlSession().selectOne("menu.getParentMenu", menuId);
	}
	
	public boolean hasChild(String parentId) {
		int totalCount =  getSqlSession().selectOne("menu.countChildMenu", parentId);
		return totalCount>0?true:false;
	}
	
	public List<Menu> findChildMenus(String parentId){
		return getSqlSession().selectList("menu.findChildMenu", parentId);
	}
	
	public int addMenu(Menu menu){
		return getSqlSession().insert("menu.addMenu", menu);
	}
	
	public int updateMenu(Menu menu){
		return getSqlSession().update("menu.updateMenu",menu);
	}
	
	public int delMenu(String id){
		return getSqlSession().delete("menu.delMenu", id);
	}
}
