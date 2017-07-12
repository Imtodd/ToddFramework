package com.todd.framework.service;

import java.util.List;

import com.todd.framework.po.Permission;

public interface IPermissionService {

	public List<Permission> getPermissions();

	public Permission getPermissionWithName(String name);

	public Permission getPermissionWithID(int id);

	public void addPermission(Permission permission);

	public void updatePermission(Permission permission);

	public void deletePermission(Permission permission);
}
