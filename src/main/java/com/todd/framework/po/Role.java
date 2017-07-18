package com.todd.framework.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "toddFramework_role")
public class Role implements Serializable {
	private int id;
	private String role;
	private String description;
	private boolean available;
	private List<Permission> permissions;

	public Role() {

	}

	public Role(String role, String description, boolean available, List<Permission> permissions) {
		this.role = role;
		this.description = description;
		this.available = available;
		this.permissions = permissions;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "int COMMENT '角色序号'")
	public int getId() {
		return id;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '角色名称'")
	public String getRole() {
		return role;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '角色描述'")
	public String getDescription() {
		return description;
	}

	@Column(columnDefinition = "boolean COMMENT '是否可用'")
	public boolean getAvailable() {
		return available;
	}

	@ManyToMany
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
