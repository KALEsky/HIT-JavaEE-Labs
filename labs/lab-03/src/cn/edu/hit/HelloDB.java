package cn.edu.hit;

import java.util.List;
import java.util.Scanner;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.entity.Student;
import cn.edu.hit.utils.DbUtils;

public class HelloDB 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
        int flag = 0;
        
        int age;
        String sid, sname, birthday, gender;
        
        lab:
        while (true)
        {
            System.out.println("1:  查询全部学生信息	Search student");
            System.out.println("2:  按学号查询信息	Get one student");
            System.out.println("3:  增加一个学生信息 	Add student");
            System.out.println("4:  修改一个学生信息	Modify student");
            System.out.println("5:  删除一个学生信息	Remove student");
            System.out.println("6:  退出			Exit System");
            flag = input.nextInt();
            StudentDao dao = new StudentDao();
            switch(flag)
            {
                case 1://查询全部学生信息
                    List<Student> stuList = dao.getStudents("select * from student");
                    for(int i=0; i<stuList.size(); i++)
                    {
                        sid = stuList.get(i).getSid();
                        sname = stuList.get(i).getSname();
                        age = stuList.get(i).getAge();
                        birthday = stuList.get(i).getBirthday();
                        gender = stuList.get(i).getGender();
                        System.out.println(sid + "\t" + sname + "\t" + age + "\t" + birthday + "\t" + gender);
                     }
                    break;
                
                case 2://按学号查询学生
                    System.out.println("请输入所要查询学生的学号");
                    sid = input.next();
                    StudentDao dao2 = new StudentDao();
                    Student s2 = new Student();
                    s2 = dao.getBySid(sid);
                    if(s2.getAge() == 0)
                    {
                    	System.out.println("未找到学生");
                    }
                    else
                    {
                    	sname = s2.getSname();
                    	gender = s2.getGender();
                    	age = s2.getAge();
                    	birthday = s2.getBirthday();
                    	System.out.println(sid + "\t" + sname + "\t" + age + "\t" + birthday + "\t" + gender + "\t");
                    }
                    break;
                 
                case 3://增加学生
                	sid = null;
                    sname = null;
                    gender = null;
                    age = 0;
                    birthday = null;
                    Scanner input3 = new Scanner(System.in);
                    System.out.print("请输入学号：");
                    sid = input3.next();
                    System.out.print("请输入姓名：");            
                    sname = input3.next();
                    System.out.print("请输入年龄：");
                    age = input3.nextInt();
                    System.out.print("请输入生日：");
                    birthday = input3.next();
                    System.out.print("请输入性别：");
                    gender = input3.next();
                    dao.add(new Student(sid, sname, age, birthday, gender));
                    System.out.println("增加成功");
                    break;
                    
                case 4://修改学生信息
                	String sage;
                	System.out.println("请输入所要修改学生的学号");
                	Scanner input4 = new Scanner(System.in);
                    sid = input4.next();
                    StudentDao dao4 = new StudentDao();
                    Student s4 = new Student();
                    s4 = dao.getBySid(sid);
                    if(s4.getAge() == 0)
                    {
                    	System.out.println("未找到该学生");
                    }
                    else
                    {
                    	System.out.println("请修改该学生除学号外的其他信息");
                    	System.out.print("姓名：");
                    	input4.nextLine();
                    	sname = input4.nextLine();
                    	if(sname.length() == 0)
                    	{
                    		sname = s4.getSname();
                    	}
                    	System.out.print("年龄：");
                    	sage = input4.nextLine();
                    	if(sage.length() == 0)
                    	{
                    		age = s4.getAge();
                    	}
                    	else
                    	{
                    		age = Integer.parseInt(sage);
                    	}
                    	System.out.print("生日：");
                    	birthday = input4.nextLine();
                    	if(birthday.length() == 0)
                    	{
                    		birthday = s4.getBirthday();
                    	}
                    	System.out.print("性别：");
                    	gender = input4.nextLine();
                    	if(gender.length() == 0)
                    	{
                    		gender = s4.getGender();
                    	}
                    	dao.modify(new Student(sid, sname, age, birthday, gender));
                    	System.out.println("修改成功，修改后信息如下：");
                    	System.out.println(sid + "\t" + sname + "\t" + age + "\t" + birthday + "\t" + gender + "\t");
                    }
                    break;
                    
                case 5://删除学生信息
                	System.out.println("请输入所要删除学生的学号");
                	Scanner input5 = new Scanner(System.in);
                	sid = input5.next();
                    StudentDao dao5 = new StudentDao();
                	Student s5 = new Student();
                    s5 = dao.getBySid(sid);
                    if(s5.getAge() == 0)
                    {
                    	System.out.println("未找到该学生");
                    }
                    else
                    {
                    	System.out.println("确认删除该学生？（确定请按1，撤销请按0）");
                    	if(input5.nextInt() == 1)
                    	{
                    		dao.remove(sid);
                    	}
                    	else break;
                    }
                    break;

                    
                default://退出
                    DbUtils.close();
                    System.out.println("系统已关闭");
                    break lab;
            }
        }
    }
}