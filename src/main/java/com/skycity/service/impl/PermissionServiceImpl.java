package com.skycity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skycity.dao.PermissionDao;
import com.skycity.entity.Permission;
import com.skycity.service.PermissionService;
import com.skycity.util.PageInfo;

@Transactional
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;

	public PageInfo<Permission> queryPageInfo(PageInfo<Permission> pageInfo, Permission resources) {
		PageInfo<Permission> resultPage =  permissionDao.queryPageInfo(pageInfo, resources);
		List<Permission> topMenuList = resultPage.getRows();
		topMenuList.forEach(s->{
			s.setChildren(this.getMenusByDirectoryId(s.getId()));
			s.setState("closed");
		});
		return resultPage;
	}

	public void add(Permission resources) {
		permissionDao.add(resources);
	}

	public void delete(String id) {
		permissionDao.delete(id);
	}

	public Permission getById(String id) {
		return permissionDao.getById(id);
	}

	public void update(Permission resources) {
		permissionDao.modify(resources);
	}

	public List<Integer> queryMenusByRoleId(int roleId) {
		return permissionDao.queryMenusByRoleId(roleId);
	}

	@Override
	public List<Permission> getAllMenus() {
		return permissionDao.getAllMenus();
	}

	
	public List<Permission> getRootMenus(){
		return permissionDao.getRootMenus();
	}
	
	@Override
	public List<Permission> getMenusByDirectoryId(int directoryId) {
		return permissionDao.getMenusByDirectoryId(directoryId);
	}

//	public List<Resources> findAll() {
//		return resourcesDao.findAll();
//	}
//
//	public List<Resources> getUserResources(String userId) {
//		
//		return resourcesDao.getUserResources(userId);
//	}
//	//<!-- 根据用户Id获取该用户的权限-->
//	public List<Resources> getRoleResources(String roleId){
//		return resourcesDao.getRoleResources(roleId);
//	}
//	public void saveRoleRescours(String roleId,List<String> list) {
//			resourcesDao.deleteRoleRescours(roleId);
////			for (String rId : list) {
////				if(!Common.isEmpty(rId)){
////					ResourceRoles resourceRoles = new ResourceRoles(); 
////					resourceRoles.setRescId(Integer.parseInt(rId));
////					resourceRoles.setRoleId(Integer.parseInt(roleId));
////					resourcesDao.saveRoleRescours(resourceRoles);
////				}
////			}
//	}
//
//	public List<Resources> getResourcesByUserName(String username) {
//		return resourcesDao.getResourcesByUserName(username);
//	}

}
