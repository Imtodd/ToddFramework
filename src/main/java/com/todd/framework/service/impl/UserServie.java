package com.todd.framework.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todd.framework.dao.hibernate.IUserdao;
import com.todd.framework.dao.hibernate.currency.IBaseDao;
import com.todd.framework.dao.hibernate.impl.UserDao;
import com.todd.framework.dao.mybatis.UserDaoForMyBatis;
import com.todd.framework.po.Permission;
import com.todd.framework.po.Role;
import com.todd.framework.po.User;
import com.todd.framework.service.IUserService;
import com.todd.framework.tools.PasswordHelper;

@Service
@Transactional
public class UserServie implements IUserService {

	private IUserdao userdao;

	@Resource(name = "userdao")
	public void setUserdao(IBaseDao<User> userdao) {
		this.userdao =  (IUserdao) userdao;
	}

	@Cacheable(value = "toddCache")
	public List<User> getUsers() {
		return userdao.findByHQL("from User", null);
	}

	@Cacheable(value="findUserByName",key="#name")
	public User getUserWithName(String name) {
		List<User> users = userdao.findByHQL("from User where userName=?", name);
		if (users.size() != 0) {
			return users.get(0);
		}
		return null;
	}

	@Cacheable(value = "toddCache", key = "#id")
	public User getUserWithID(int id) {
		List<User> users = userdao.findByHQL("from User where id=?", id);
		if (users.size() != 0) {
			return users.get(0);
		}
		return null;
	}

	public void updateUser(User user) {
		userdao.update(user);
	}

	public void addUser(User user) {
		PasswordHelper.encryptPassword(user);
		userdao.insert(user);
	}

	public void deleteUser(User user) {
		userdao.delete(user.getId());
	}

	@Cacheable(value = "toddCacheWithRole", key = "#userNameWithRole")
	public Set<String> findRoles(String userNameWithRole) {
		List<Role> roles = userdao.getRoleWithName(userNameWithRole);
		Set<String> set = new HashSet<String>();
		for (Role role : roles) {
			set.add(role.getRole());
		}
		return set;
	}

	@Cacheable(value = "toddCacheWithPermission", key = "#userNameWithPermissions")
	public Set<String> findPermissions(String userNameWithPermissions) {
		Set<String> set = new HashSet<String>();
		List<Permission> permissions = userdao.getPermissionWithName(userNameWithPermissions);
		for (Permission permission : permissions) {
			set.add(permission.getPermission());
		}
		return set;
	}

}
