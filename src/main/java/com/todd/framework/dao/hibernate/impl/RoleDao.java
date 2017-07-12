package com.todd.framework.dao.hibernate.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.todd.framework.dao.hibernate.IRoleDao;
import com.todd.framework.dao.hibernate.currency.BaseDao;
import com.todd.framework.po.Role;

@Repository(value="roledao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

}
