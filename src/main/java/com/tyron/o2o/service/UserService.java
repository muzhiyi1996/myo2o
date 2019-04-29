package com.tyron.o2o.service;

import java.util.List;

import com.tyron.o2o.entity.User;

public interface UserService {

	int insertUser(User user);

	int updateUser(User user);

	User selectUser(String name);
	
	List<User> getUserList();
	
	User selectUserById(int id);

}
