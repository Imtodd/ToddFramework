package com.todd.framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todd.framework.dao.hibernate.currency.IBaseDao;
import com.todd.framework.po.Permission;
import com.todd.framework.service.IPermissionService;

@Service
@Transactional
public class PermissionService implements IPermissionService {

	private IBaseDao<Permission> permissiondao;

	@Resource(name = "permissiondao")
	public void setPermissiondao(IBaseDao<Permission> permissiondao) {
		this.permissiondao = permissiondao;
	}

	@Cacheable(value = "toddCache")
	public List<Permission> getPermissions() {
		return permissiondao.findByHQL("from Permission", null);
	}

	@Cacheable(value = "toddCache", key = "#name")
	public Permission getPermissionWithName(String name) {
		List<Permission> permissions = permissiondao.findByHQL("from Permission where permission = ?", name);
		if (permissions.size() != 0) {
			return permissions.get(0);
		}
		return null;
	}

	@Cacheable(value = "toddCache", key = "#id")
	public Permission getPermissionWithID(int id) {
		List<Permission> permissions = permissiondao.findByHQL("from Permission where id = ?", id);
		if (permissions.size() != 0) {
			return permissions.get(0);
		}
		return null;
	}

	public void addPermission(Permission permission) {
		permissiondao.insert(permission);
	}

	public void updatePermission(Permission permission) {
		permissiondao.update(permission);
	}

	public void deletePermission(Permission permission) {
		permissiondao.delete(permission.getId());
	}

}
