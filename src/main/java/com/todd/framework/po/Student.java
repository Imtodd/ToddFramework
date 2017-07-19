package com.todd.framework.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "toddFramework_Student")
public class Student implements Serializable {
	private int id;
	private String name;
	private int age;
	private String student_id;
	private String specialty;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, int age, String student_id, String specialty) {
		super();
		this.name = name;
		this.age = age;
		this.student_id = student_id;
		this.specialty= specialty;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getSpecialty() {
		return specialty;
	}


}
