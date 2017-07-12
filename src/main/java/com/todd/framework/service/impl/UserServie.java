package com.todd.framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todd.framework.dao.mybatis.UserDaoForMyBatis;
import com.todd.framework.po.User;
import com.todd.framework.service.IUserService;

@Service
// @Transactional
public class UserServie implements IUserService {

	// private IDataBase<User> basedao;

	// @Resource(name = "userdao")
	// public void setBasedao(IDataBase<User> basedao) {
	// this.basedao = basedao;
	// }

	@Autowired
	private UserDaoForMyBatis userdao;

	@Cacheable(value = "toddCache")
	public List<User> getUsers() {
		return userdao.getUsers();
	}

	@Cacheable(value = "toddCache", key = "#name")
	public User getUserWithName(String name) {
		return userdao.getUserWithName(name);
	}

	@Cacheable(value = "toddCache", key = "#id")
	public User getUserWithID(Integer id) {
		return userdao.getUserWithID(id);
	}

	public void updateUser(User user) {
		userdao.updateUser(user);
	}

	public void addUser(User user) {
		String password = user.getPassword();
	}

	public void deleteUser(User user) {
		userdao.deletaUser(user);
	}

}
