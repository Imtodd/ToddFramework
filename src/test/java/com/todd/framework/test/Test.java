package com.todd.framework.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.todd.framework.dao.hibernate.IPermissionDao;
import com.todd.framework.dao.hibernate.IRoleDao;
import com.todd.framework.dao.hibernate.IStudentDao;
import com.todd.framework.dao.hibernate.IUserdao;
import com.todd.framework.dao.hibernate.currency.IBaseDao;
import com.todd.framework.po.Permission;
import com.todd.framework.po.Role;
import com.todd.framework.po.Student;
import com.todd.framework.po.User;

public class Test extends TToddFramework {

	@Resource(name = "userdao")
	private IUserdao userdao;
	@Resource(name = "roledao")
	private IRoleDao roledao;
	@Resource(name = "permissiondao")
	private IPermissionDao permissiondao;
	@Resource(name="studentdao")
	private IStudentDao studentdao;

	@org.junit.Test
	@Transactional
	@Rollback(false)
	public void test() {
		Permission add = new Permission("add", "添加", true);
		Permission delete = new Permission("delete", "删除", true);
		Permission update = new Permission("update", "修改", true);
		Permission select = new Permission("select", "查询", true);
		permissiondao.insert(add);
		permissiondao.insert(delete);
		permissiondao.insert(update);
		permissiondao.insert(select);
		List<Permission> adminPermission = new ArrayList<Permission>();
		adminPermission.add(add);
		adminPermission.add(delete);
		adminPermission.add(update);
		List<Permission> guestPermission = new ArrayList<Permission>();
		guestPermission.add(select);
		Role admin = new Role("admin", "管理员", true, adminPermission);
		Role guest = new Role("guest", "访客", true, guestPermission);
		roledao.insert(admin);
		roledao.insert(guest);
		System.out.println("初始化完成");
	}

	@org.junit.Test
	@Transactional
	@Rollback(false)
	public void tests() {
		Student s1 = new Student("孙立人", 22, "1515135135", "焦土战策");
		Student s2 = new Student("林彪", 22, "1515135155", "游击野战");
		Student s3 = new Student("布琼尼", 48, "1515134325", "骑兵作战");
		Student s4 = new Student("隆美尔", 33, "1515134555", "闪击战");
		studentdao.insert(s1);
		studentdao.insert(s2);
		studentdao.insert(s3);
		studentdao.insert(s4);
		System.out.println("数据插入完成");
	}

	public void setUserdao(IBaseDao<User> userdao) {
		this.userdao = (IUserdao) userdao;
	}

	public void setRoledao(IBaseDao<Role> roledao) {
		this.roledao = (IRoleDao) roledao;
	}

	public void setPermissiondao(IBaseDao<Permission> permissiondao) {
		this.permissiondao = (IPermissionDao) permissiondao;
	}

	public void setStudentdao(IBaseDao<Student> studentdao) {
		this.studentdao = (IStudentDao) studentdao;
	}
	

}
