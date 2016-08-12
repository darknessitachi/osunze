package com.skycity.dao.impl;

import org.springframework.stereotype.Repository;

import com.skycity.dao.UserDao;
import com.skycity.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	
	public User querySingleUser(String userName) {
		return (User)getSqlSession().selectOne("user.queryUserInfo",userName);
	}

	public User querySingleUserByEmail(String email) {
		return (User)getSqlSession().selectOne("user.queryUserInfoByEmail",email);
	}
	
	public void changePwd(User user) {
//		getSqlSession().update("user.changePwd", user);
	}

	@Override
	public User querySingleUserByPhone(String mobile) {
		return getSqlSession().selectOne("user.queryUserInfoByPhone",mobile);
	}
}
