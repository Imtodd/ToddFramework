package com.todd.framework.dao.hibernate.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.todd.framework.dao.hibernate.IPermissionDao;
import com.todd.framework.dao.hibernate.currency.BaseDao;
import com.todd.framework.po.Permission;

@Repository(value="permissiondao")
public class PermissionDao extends BaseDao<Permission> implements IPermissionDao {

}
