package com.todd.framework.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "toddFramework_user")
public class User {

	private int id;
	private String userName;
	private String password;
	private String sale;
	private boolean locked;
	private List<Role> roles;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "int COMMENT '用户序号'")
	public int getId() {
		return id;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '用户名'")
	public String getUserName() {
		return userName;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '用户密码'")
	public String getPassword() {
		return password;
	}

	@Column(columnDefinition = "varchar(255) COMMENT '盐'")
	public String getSale() {
		return sale;
	}

	@Column(columnDefinition = "boolean COMMENT '是否锁定'")
	public boolean isLocked() {
		return locked;
	}

	@ManyToMany
	@JoinColumn(referencedColumnName="id",name="role_id")
	public List<Role> getRoles() {
		return roles;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
