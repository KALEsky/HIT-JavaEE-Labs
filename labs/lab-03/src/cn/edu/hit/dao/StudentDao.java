package cn.edu.hit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.hit.entity.Student;
import cn.edu.hit.utils.DbUtils;

public class StudentDao 
{
	//增
	public void add(Student s)
	{
		String sid = s.getSid();
		String sname = s.getSname();
		String gender = s.getGender();
		int age = s.getAge();
		String birthday = s.getBirthday();
		String sql = "insert into student values('" + sid + "','" + sname + "'," + age + ",'" + birthday + "','" + gender + "')";
		DbUtils du = new DbUtils();
		du.executeUpdate(sql);
		du.close();
	}
	//删
	public void remove(String sid)
	{
		String sql = "delete from student where sid = '" + sid + "'";
		DbUtils du = new DbUtils();
		du.executeUpdate(sql);
		du.close();
	}
	//改
	public void modify(Student s)
	{
		String sid = s.getSid();
		String sname = s.getSname();
		String gender = s.getGender();
		int age = s.getAge();
		String birthday = s.getBirthday();
		String sql = "update student set sname = '" + sname + "',"
				+ " age = " + age + ", birthday = '" + birthday + "',"
				+ " gender = '" + gender + "' where sid = '" + sid + "'";
		DbUtils du = new DbUtils();
		du.executeUpdate(sql);
		du.close();
	}
	//全部查询
	public List<Student> getStudents(String sql)
	{
		DbUtils du = new DbUtils();
		ResultSet rs = du.executeQuery(sql);
		List<Student> stuList = new ArrayList<>();
		String sid = null;
		String sname = null;
		String gender = null;
		int age = 0;
		String birthday = null;
		try 
		{
			while(rs.next())
			{
				sid = rs.getString(1);
				sname = rs.getString(2);
				age = rs.getInt(3);
				birthday = rs.getString(4);
				gender = rs.getString(5);
				stuList.add(new Student(sid, sname, age, birthday, gender));
			}
			DbUtils.close();
			return stuList;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//按学号查询
	public Student getBySid(String sid)
	{
		DbUtils du = new DbUtils();
		String sql = "select * from student where sid = '" + sid + "'";
		ResultSet rs = du.executeQuery(sql);
		String sname = null;
		String gender = null;
		int age = 0;
		String birthday = null;
		try 
		{
			if(rs.next())
			{
				sname = rs.getString(2);
				age = rs.getInt(3);
				birthday = rs.getString(4);
				gender = rs.getString(5);
			}
			du.close();
			return new Student(sid, sname, age, birthday, gender);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
