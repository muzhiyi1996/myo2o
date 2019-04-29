package com.tyron.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyron.o2o.dao.UserDao;
import com.tyron.o2o.entity.User;
import com.tyron.o2o.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User selectUser(String name) {
		return userDao.selectUser(name);
	}
	
	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
		
	}

	
	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
		
	}

	@Override
	public List<User> getUserList() {
		List<User> list = new ArrayList();
		list = userDao.getUserList();
		return list;
	}

	@Override
	public User selectUserById(int id) {
		return userDao.selectUserById(id);
	}

	

}
