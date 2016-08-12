package com.skycity.service;

import com.skycity.entity.User;

public interface UserService {
	public void changePwd(User user);
	
	public User querySingleUser(String userName);
	
	public User querySingleUserByEmail(String email);
	
	public User querySingleUserByPhone(String mobile);
}
