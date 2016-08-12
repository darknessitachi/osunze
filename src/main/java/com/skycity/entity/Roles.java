package com.skycity.entity;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Roles implements java.io.Serializable {

	private int roleId;
	private int enable;// 是否禁用角色　1　表示禁用　2　表示不禁用
	private String name;
	private String roleKey;
	private String description;
	private List<Permission> permissions = new ArrayList<Permission>();

	public Roles() {
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

}