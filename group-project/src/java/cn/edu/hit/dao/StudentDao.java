package cn.edu.hit.dao;

import java.util.List;

import cn.edu.hit.entity.Student;

public interface StudentDao {
	List<Student> getAll(String sql);
	Student getBySid(String sid);
	int add(Student s);
	int modify(Student s);
	int remove(String sid);
}
