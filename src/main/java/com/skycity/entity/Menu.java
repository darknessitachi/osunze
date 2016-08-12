package com.skycity.entity;

import org.apache.ibatis.type.Alias;

/**
 * 菜单表
 * @author YingBo
 *
 */
@Alias("Menu")
public class Menu {
	private String id;
	private String name;
	private String menuId;
	private String status;
	private String menuSeq;
	private String refRes;
	private String description;
	
	private String parentId;
	
	private Menu parent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq;
	}

	public String getRefRes() {
		return refRes;
	}

	public void setRefRes(String refRes) {
		this.refRes = refRes;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString(){
		return "id:"+this.getId()+",name:"+this.getName()+",parentId:"+this.getParentId()+",description:"+this.getDescription();
	}
}
