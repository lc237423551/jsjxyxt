package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.UserDao;
import com.java.entity.User;
import com.java.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	@Override
	public List<User> findUser(User user) {
		// TODO Auto-generated method stub
		return userDao.findUser(user);
	}
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
	@Override
	public int deleteUser(String users) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(users);
	}
	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}
	@Override
	public List<User> findUser1(String users) {
		// TODO Auto-generated method stub
		return userDao.findUser1(users);
	}
	@Override
	public User findUser2(User user) {
		// TODO Auto-generated method stub
		return userDao.findUser2(user);
	}

}
