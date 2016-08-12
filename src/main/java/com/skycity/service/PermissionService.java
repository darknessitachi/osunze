package com.skycity.service;

import java.util.List;

import com.skycity.entity.Permission;
import com.skycity.util.PageInfo;



public interface PermissionService{
	public PageInfo<Permission> queryPageInfo(PageInfo<Permission> pageInfo,Permission resources);

	public void add(Permission resources);
	
	public void update(Permission resources);
	
	public void delete(String ids);
	
	public Permission getById(String id);
//	
//	public List<Resources> findAll();
//	
//	//<!-- 根据用户Id获取该用户的权限-->
//	public List<Resources> getUserResources(String userId);
//	//<!-- 根据用户Id获取该用户的权限-->
//	public List<Resources> getRoleResources(String roleId);
//	//<!-- 根据用户名获取该用户的权限-->
//	public List<Resources> getResourcesByUserName(String username);
//	
//	public void saveRoleRescours(String roleId,List<String> list);
	
	/**
	 * 得到所有的目录和菜单
	 * for zTree使用
	 * @return
	 */
	public List<Permission> getAllMenus();
	
	/**
	 * 得到所有parentId=0的目录
	 * @return
	 */
	public List<Permission> getRootMenus();
	/**
	 * 根据目录得到菜单
	 * @param directoryId
	 * @return
	 */
	public List<Permission> getMenusByDirectoryId(int directoryId);
	/**
	 * 根据角色ID查询该角色绑定了哪些菜单
	 * @param roleId
	 * @return
	 * 		只返回菜单信息，不返回按钮信息
	 */
	public List<Integer> queryMenusByRoleId(int roleId);
}
