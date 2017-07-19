package com.todd.framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todd.framework.dao.hibernate.IStudentDao;
import com.todd.framework.dao.hibernate.currency.IBaseDao;
import com.todd.framework.po.Student;
import com.todd.framework.service.IStudentService;

@Service
@Transactional
public class StudentService implements IStudentService {

	@Resource(name = "studentdao")
	private IStudentDao studentdao;

	public void setStudentdao(IBaseDao<Student> studentdao) {
		this.studentdao = (IStudentDao) studentdao;
	}

	@Cacheable(value = "toddCache", key = "#rows+#page")
	public List<Student> getStudentList(int rows, int page) {
		return studentdao.findByHQLWithPagination("from Student", rows, page, null);
	}

	public Student getStudentWithId(int id) {
		List<Student> list = studentdao.findByHQL("from Student where id=?", id);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	public Student getStudentWithName(String name) {
		List<Student> list = studentdao.findByHQL("from Student where name=?", name);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	public void updateStudent(Student stu) {
		studentdao.update(stu);
	}

	public void deleteStudent(int id) {
		studentdao.delete(id);
	}

	public void saveStudent(Student stu) {
		studentdao.insert(stu);
	}

	public int totleStudent() {
		return studentdao.findByHQL("from Student", null).size();
	}

}
