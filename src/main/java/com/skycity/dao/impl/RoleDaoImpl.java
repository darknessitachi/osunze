package com.skycity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.skycity.dao.RoleDao;
import com.skycity.entity.Roles;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Roles> implements RoleDao{
	
	@Deprecated
	public List<Roles> queryRolesByUserName(String userName) {
		return getSqlSession().selectList("roles.queryUserRoles",userName);
	}
	
	public List<String> getRolesName(String userName){
		return getSqlSession().selectList("roles.queryRolesName",userName);
	}

	@Override
	public void deletePermissionByRoleId(int roleId) {
		getSqlSession().delete("roles.deletePermissioByRoleId", roleId);
	}

	@Override
	public int addRoleAuth(Map<String, Integer> authMap) {
		getSqlSession().insert("roles.addAuth", authMap);
		return 0;
	}

}
