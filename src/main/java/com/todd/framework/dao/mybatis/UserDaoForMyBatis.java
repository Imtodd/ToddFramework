package com.todd.framework.dao.mybatis;

import com.todd.framework.po.User;

public interface UserDaoForMyBatis {
	/**
	 * 根据id获得用户
	 * @param id
	 * @return
	 */
	public User getUser(int id);
}
