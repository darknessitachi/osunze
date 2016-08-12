package com.skycity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.skycity.dao.PermissionDao;
import com.skycity.entity.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements
		PermissionDao {

	@Override
	public List<Permission> getAllDirectory() {
		return getSqlSession().selectList("permission.queryDirectory");
	}

	@Override
	public List<Integer> queryMenusByRoleId(int roleId) {
		return getSqlSession().selectList("permission.queryAuthsByRoleId",roleId);
	}

	@Override
	public List<Permission> getAllMenus() {
		return getSqlSession().selectList("permission.queryAllMenus");
	}

	public List<Permission> getMenusByDirectoryId(int directoryId) {
		return getSqlSession().selectList("permission.queryMenusByDirectoryId",directoryId);
	}

	@Override
	public List<Permission> getRootMenus() {
		return getSqlSession().selectList("permission.queryAllRootMenus");
	}


	// public List<Resources> findAll() {
	// return getSqlSession().selectList("resources.findAll");
	// }
	//
	// //根据用户Id获取该用户的权限
	// public List<Resources> getUserResources(String userId) {
	// return getSqlSession().selectList("resources.getUserResources", userId);
	// }
	//
	// //根据用户名获取该用户的权限
	// public List<Resources> getResourcesByUserName(String username) {
	// return getSqlSession().selectList("resources.getResourcesByUserName",
	// username);
	// }
	//
	// //根据用户Id获取该用户的权限
	// public List<Resources> getRoleResources(String roleId) {
	// return getSqlSession().selectList("resources.getRoleResources", roleId);
	// }
	//
	// // public void saveRoleRescours(ResourceRoles resourceRoles) {
	// // getSqlSession().insert("resources.saveRoleRescours", resourceRoles);
	// // }
	//
	// public void deleteRoleRescours(String roleId) {
	// getSqlSession().delete("resources.deleteRoleRescours", roleId);
	// }
}
