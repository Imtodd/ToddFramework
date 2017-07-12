package com.todd.framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todd.framework.dao.hibernate.IRoleDao;
import com.todd.framework.dao.hibernate.currency.IBaseDao;
import com.todd.framework.po.Role;
import com.todd.framework.service.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService {

	private IBaseDao<Role> roledao;

	@Resource(name = "roledao")
	public void setRoledao(IBaseDao<Role> roledao) {
		this.roledao = roledao;
	}

	@Cacheable(value = "toddCache")
	public List<Role> getRoles() {
		return roledao.findByHQL("from Role", null);
	}

	@Cacheable(value = "toddCache", key = "#name")
	public Role getRoleWithName(String name) {
		List<Role> roles = roledao.findByHQL("from Role where role = ?", name);
		if (roles.size() != 0) {
			return roles.get(0);
		}
		return null;
	}

	@Cacheable(value = "toddCache", key = "#id")
	public Role getRoleWithID(int id) {
		List<Role> roles = roledao.findByHQL("from Role where id = ?", id);
		if (roles.size() != 0) {
			return roles.get(0);
		}
		return null;
	}

	public void addRole(Role role) {
		roledao.insert(role);
	}

	public void updateRole(Role role) {
		roledao.update(role);
	}

	public void deleteRole(Role role) {
		roledao.delete(role.getId());
	}

}
