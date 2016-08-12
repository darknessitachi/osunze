package com.skycity.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skycity.dao.RoleDao;
import com.skycity.entity.Roles;
import com.skycity.service.RolesService;
import com.skycity.util.PageInfo;

/**
 * @author YingBo.Dai
 * @E-mail:lyyb2001@163.com
 * @qq:20880488
 * @version 创建时间：2016年7月20日 下午4:59:53 程序的简单说明
 */
@Service("rolesService")
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Roles> queryRolesByUserName(String userName) {
		return roleDao.queryRolesByUserName(userName);
	}

	@Override
	public List<String> getRolesName(String userName) {
		return roleDao.getRolesName(userName);
	}

	@Override
	public PageInfo<Roles> queryPageInfo(PageInfo<Roles> pageInfo, Roles roles) {
		return roleDao.queryPageInfo(pageInfo, roles);
	}

	@Override
	public void updateRoleRights(int roleId, String roleRights) {
		roleDao.deletePermissionByRoleId(roleId);
		String[] permissionIds = roleRights.split(",");
		List<String> auths = Arrays.asList(permissionIds);
		auths.forEach(s->{
			Map<String,Integer> mapx = new HashMap<String,Integer>();
			mapx.put("roleId", roleId);
			mapx.put("permissionId", Integer.parseInt(s));
			roleDao.addRoleAuth(mapx);
		});
	}

}
