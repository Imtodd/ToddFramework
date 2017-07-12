package com.todd.framework.service;

import java.util.List;

import com.todd.framework.po.User;

public interface IUserService {
	
	public List<User> getUsers();
	
	public User getUserWithName(String name);
	
	public User getUserWithID(Integer id);
	
	public void updateUser(User user);
	
	public void addUser(User user);
	
	public void deleteUser(User user);
	
}
