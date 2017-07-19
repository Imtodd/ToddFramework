package com.todd.framework.dao.hibernate.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.todd.framework.dao.hibernate.IStudentDao;
import com.todd.framework.dao.hibernate.currency.BaseDao;
import com.todd.framework.po.Student;

@Repository(value="studentdao")
public class StudentDao extends BaseDao<Student> implements IStudentDao {

}
