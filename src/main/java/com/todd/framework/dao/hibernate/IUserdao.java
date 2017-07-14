package com.todd.framework.dao.hibernate;

import java.util.List;

import com.todd.framework.dao.hibernate.currency.IBaseDao;
import com.todd.framework.po.Permission;
import com.todd.framework.po.Role;
import com.todd.framework.po.User;

public interface IUserdao extends IBaseDao<User> {
	public List<Role> getRoleWithName(String username);

	public List<Permission> getPermissionWithName(String username);
}
