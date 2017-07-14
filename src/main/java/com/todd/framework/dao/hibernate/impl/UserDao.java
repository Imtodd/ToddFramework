package com.todd.framework.dao.hibernate.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.todd.framework.dao.hibernate.IUserdao;
import com.todd.framework.dao.hibernate.currency.BaseDao;
import com.todd.framework.po.Permission;
import com.todd.framework.po.Role;
import com.todd.framework.po.User;

@Repository(value = "userdao")
public class UserDao extends BaseDao<User> implements IUserdao {

	public List<Role> getRoleWithName(String username) {
		Query query = this.getsession().createQuery("from User where userName=:username");
		query.setParameter("username", username);
		List list = query.getResultList();
		if (list.size() != 0) {
			User user = (User) list.get(0);
			return user.getRoles();
		}
		return null;
	}

	public List<Permission> getPermissionWithName(String username) {
		List<Role> roles = this.getRoleWithName(username);
		List<Permission> permissionsum = new ArrayList<Permission>();
		for (Role role : roles) {
			Query query = this.getsession().createQuery("from Role where id=:id");
			query.setParameter("id", role.getId());
			List list = query.getResultList();
			if (list.size() != 0) {
				Role temp_role = (Role) list.get(0);
				permissionsum.addAll(role.getPermissions() );
			}
		}
		return permissionsum;
	}

}
