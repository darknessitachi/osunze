package com.skycity.service;


import java.util.List;

import com.skycity.entity.Roles;
import com.skycity.util.PageInfo;

public interface RolesService{
	
	public PageInfo<Roles> queryPageInfo(PageInfo<Roles> pageInfo,Roles code);
	
	public List<Roles> queryRolesByUserName(String userName);
	
	public List<String> getRolesName(String userName);
	
	public void updateRoleRights(int roleId,String roleRights);
}
