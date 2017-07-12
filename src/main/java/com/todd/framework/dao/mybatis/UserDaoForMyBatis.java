package com.todd.framework.dao.mybatis;

import java.util.List;

import com.todd.framework.po.User;

public interface UserDaoForMyBatis {
	/**
	 * 根据id获得用户
	 * @param id
	 * @return
	 */
	public User getUserWithID(int id);
	/**
	 * 获得全部用户
	 * @return
	 */
	public List<User> getUsers();
	/**
	 * 根据用户名获得用户
	 * @param name
	 * @return
	 */
	public User getUserWithName(String name);
	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(User user);
	/**
	 * 删除用户
	 * @param user
	 */
	public void deletaUser(User user);
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
}
