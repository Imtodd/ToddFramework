package com.todd.framework.service;

import java.util.List;

import com.todd.framework.po.Role;

public interface IRoleService {

	public List<Role> getRoles();

	public Role getRoleWithName(String name);

	public Role getRoleWithID(int id);

	public void addRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(Role role);
}
