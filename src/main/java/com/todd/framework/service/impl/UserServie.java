package com.todd.framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todd.framework.dao.hibernate.currency.IBaseDao;
import com.todd.framework.dao.mybatis.UserDaoForMyBatis;
import com.todd.framework.po.User;
import com.todd.framework.service.IUserService;

@Service
@Transactional
public class UserServie implements IUserService {

	private IBaseDao<User> userdao;

	@Resource(name = "userdao")
	public void setUserdao(IBaseDao<User> userdao) {
		this.userdao = userdao;
	}

	@Cacheable(value = "toddCache")
	public List<User> getUsers() {
		return userdao.findByHQL("from User", null);
	}

	@Cacheable(value = "toddCache", key = "#name")
	public User getUserWithName(String name) {
		List<User> users = userdao.findByHQL("from User where name=?", name);
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
		String newpassword = new Md5Hash(user.getPassword(), user.getSale(), 5).toHex();
		user.setPassword(newpassword);
		userdao.insert(user);
	}

	public void deleteUser(User user) {
		userdao.delete(user.getId());
	}

}
