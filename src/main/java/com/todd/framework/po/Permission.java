package com.todd.framework.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "toddFramework_permission")
public class Permission {
	private int id;
	private String permission;
	private String description;
	private String available;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "int COMMENT '许可序号'")
	public int getId() {
		return id;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '许可名称'")
	public String getPermission() {
		return permission;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '许可描述'")
	public String getDescription() {
		return description;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '是否可用'")
	public String getAvailable() {
		return available;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

}
