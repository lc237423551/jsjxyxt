package com.java.dao;

import java.util.List;

import com.java.entity.User;

public interface UserDao {
	public List<User> findUser(User user);
	//得到辅导员和管理员的所有账号
	public List<User> findUser1(String users);
	
	public int updateUser(User user);
	
	public int  deleteUser(String users);
	
	public int insertUser(User user);
	//匹配密码
	public User findUser2(User user);

}
