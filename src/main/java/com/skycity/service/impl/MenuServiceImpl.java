package com.skycity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skycity.dao.MenuDao;
import com.skycity.entity.Menu;
import com.skycity.framework.collection.Mapx;
import com.skycity.framework.ui.controls.TreeNode;
import com.skycity.service.MenuService;

@Transactional
@Service("menuService")
public class MenuServiceImpl  implements MenuService{
	
	@Autowired
	private MenuDao menuDao;
	
	public Menu getMenu(String menuId) {
		return menuDao.getMenu(menuId);
	}

	public Menu getParentMenu(String menuId){
		return menuDao.getParentMenu(menuId);
	}
	
	public List<TreeNode> getMenuTree(String id) {
		List<Menu> menus = menuDao.findChildMenus(id);//用于取出所有的部门对象的list集合
		return convertTreeNodeList(menus);
	}
	
	private List<TreeNode> convertTreeNodeList(List<Menu> menus){
		List<TreeNode> nodes= null;
		if(menus!=null){
			nodes = new ArrayList<TreeNode>();
			for(Menu menu:menus){
				TreeNode treeNode = convertTreeNode(menu);
				if(treeNode!=null){
					nodes.add(treeNode);
				}
			}
		}
		return nodes;
	}
	
	private TreeNode convertTreeNode(Menu menu){
		TreeNode node = null;
		if(menu!=null){
			node = new TreeNode();
			node.setId(menu.getId());
			node.setText(menu.getName());
			node.setPid(menu.getParentId());
			boolean hasChild = menuDao.hasChild(menu.getId());
			if(hasChild){
				node.setState("closed");
			}else{
				node.setState("open");
			}
			node.setAttributes(new Mapx<String,Object>());
		}
		return node;
	}
	
	public int addMenu(Menu menu){
		return menuDao.addMenu(menu);
	}
	
	public int updateMenu(Menu menu){
		return menuDao.updateMenu(menu);
	}
	
	public int delMenu(String id){
		return menuDao.delMenu(id);
	}
}
