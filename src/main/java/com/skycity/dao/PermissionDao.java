package com.skycity.dao;

import java.util.List;

import com.skycity.entity.Permission;

public interface PermissionDao extends BaseDao<Permission>{
	
	public List<Permission> getAllDirectory();
	
	public List<Permission> getAllMenus();
	
	public List<Permission> getRootMenus();
	/**
	 * 根据角色ID查询该角色绑定了哪些菜单
	 * @param roleId
	 * 		角色ID
	 * @return
	 * 		只返回菜单信息，不返回按钮信息
	 */
	public List<Integer> queryMenusByRoleId(int roleId);
	
	public List<Permission> getMenusByDirectoryId(int directoryId);
//	//<!-- 根据用户Id获取该用户的权限-->
//	public List<Resources> getUserResources(String userId);
//	//<!-- 根据角色Id获取该角色的权限-->
//	public List<Resources> getRoleResources(String roleId);
//	//<!-- 根据用户名获取该用户的权限-->
//	public List<Resources> getResourcesByUserName(String username);
////	public void saveRoleRescours(ResourceRoles resourceRoles);
//	public void deleteRoleRescours(String roleId);
}
