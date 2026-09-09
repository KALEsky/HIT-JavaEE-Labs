package cn.edu.hit.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.entity.Student;
import cn.edu.hit.utils.DBUtils;

public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> getAll(String sql) {
		ResultSet rs = DBUtils.executeQuery(sql);
		List<Student> stuList = new ArrayList<>();
		String sid, sname;
		try {
			while(rs.next()) {
				sid = rs.getString(1);
				sname = rs.getString(2);
				Student stu  = new Student(sid, sname);
				stuList.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stuList;
	}

	@Override
	public Student getBySid(String sid) {
		ResultSet rs = DBUtils.executeQuery("select * from student where sid = '" + sid + "'");
		String sname;
		Student s = null;
		try {
			if(rs.next()) {
				sid = rs.getString(1);
				sname = rs.getString(2);
				s = new Student(sid, sname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public int add(Student s) {
		String sid = s.getSid();
		String sname = s.getSname();
		String sql = "insert into student(sid, sname, age, birthday, gender, major) values('" + sid + "','" + sname + "')";
		return DBUtils.executeUpdate(sql);	
	}

	@Override
	public int modify(Student s) {
		String sid = s.getSid();
		String sname = s.getSname();
		String sql = "update student set sname = '" + sname 
				+ "' where sid = '" + sid + "'";
		return DBUtils.executeUpdate(sql);
	}

	@Override
	public int remove(String sid) {
		String sql = "delete from student where sid = '" + sid + "'";
		return DBUtils.executeUpdate(sql);
	}

}
