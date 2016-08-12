package com.skycity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skycity.dao.UserDao;
import com.skycity.entity.User;
import com.skycity.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void changePwd(User user) {
		userDao.changePwd(user);
	}

	public User querySingleUser(String userName) {
		return userDao.querySingleUser(userName);
	}

	@Override
	public User querySingleUserByEmail(String email) {
		return userDao.querySingleUserByEmail(email);
	}

	@Override
	public User querySingleUserByPhone(String mobile) {
		return userDao.querySingleUserByPhone(mobile);
	}

}
