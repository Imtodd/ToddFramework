package com.todd.framework.service;

import java.util.List;
import java.util.Set;

import com.todd.framework.po.Role;
import com.todd.framework.po.User;

public interface IUserService {

	public List<User> getUsers();

	public User getUserWithName(String name);

	public User getUserWithID(int id);

	public void updateUser(User user);

	public void addUser(User user);

	public void deleteUser(User user);

	public Set<String> findRoles(String username);// 根据用户名查找其角色

	public Set<String> findPermissions(String username); // 根据用户名查找其权限

}
