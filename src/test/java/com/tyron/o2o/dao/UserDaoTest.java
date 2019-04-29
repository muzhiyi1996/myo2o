package com.tyron.o2o.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tyron.o2o.BaseTest;
import com.tyron.o2o.entity.User;

public class UserDaoTest extends BaseTest{
	@Autowired
	private UserDao userDao;

	@Test
	@Ignore
	public void testQueryArea() {
		System.out.println(userDao);
		User user =userDao.selectUser("yang");
		System.out.println(user.toString());
	}

	@Test
	@Ignore
	public void testInsertUser() {
		User user = new User();
		user.setAddr("地址");
		user.setAge(12);
		user.setGender("男");
		user.setId(4);
		user.setIdentity(0);
		user.setPassword("123456");
		user.setPhone(123456);
		user.setUserName("测试");
		userDao.insertUser(user);
	}
	@Test
	@Ignore
	public void testUpdateUser() {
		User user = new User();
		user.setUserName("zaiyici");
		user.setAddr("地址测试");
		user.setAge(12);
		user.setGender("男");
		userDao.updateUser(user);
	}
	@Test
	@Ignore
	public void testGetUserList() {
		List<User> list = userDao.getUserList();
		System.out.println(list);
	}
	@Test
	public void TestSelectUserById() {
		int id = 13;
		User user = userDao.selectUserById(id);
		System.out.println(user);
	}
}
