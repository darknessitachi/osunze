package com.skycity.dao;

import com.skycity.entity.User;

public interface UserDao extends BaseDao<User>{
	public User querySingleUser(String userName);
	public User querySingleUserByEmail(String userName);
	public User querySingleUserByPhone(String mobile);
	
	public void changePwd(User user);
}
