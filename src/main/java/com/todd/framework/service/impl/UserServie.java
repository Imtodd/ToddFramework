package com.todd.framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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

	// public List<User> getAll() {
	// return basedao.findByHQL("from User", null);
	// }

	public User getUser() {
		return userdao.getUser(34);
	}

}
