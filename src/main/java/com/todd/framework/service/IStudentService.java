package com.todd.framework.service;

import java.util.List;

import com.todd.framework.po.Student;

public interface IStudentService {
	List<Student> getStudentList(int rows,int page);

	Student getStudentWithId(int id);

	Student getStudentWithName(String name);

	void updateStudent(Student stu);

	void deleteStudent(int id);

	void saveStudent(Student stu);
	
	int totleStudent();
}
