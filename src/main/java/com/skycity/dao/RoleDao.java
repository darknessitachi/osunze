package com.skycity.dao;

import java.util.List;
import java.util.Map;

import com.skycity.entity.Roles;

public interface RoleDao extends BaseDao<Roles> {
	
	public List<Roles> queryRolesByUserName(String userName);
	
	public List<String> getRolesName(String userName);
	
	public void deletePermissionByRoleId(int roleId);
	
	public int addRoleAuth(Map<String,Integer> authMap);
}
