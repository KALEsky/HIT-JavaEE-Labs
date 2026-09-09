<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,cn.edu.hit.entity.Student,cn.edu.hit.dao.StudentDao" %>
<%
/*
	String username = (String)session.getAttribute("username");
	if(username == null)
	{
		response.sendRedirect("login.jsp");
		return;
	}
*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息情况</title>
</head>
<body>
<a href="add.html">增加学生</a>
<table border="1">
  <tr>
    <td width="92">学号</td>
    <td width="124">姓名</td>
    <td width="66">年龄</td>
    <td width="92">生日</td>
    <td width="61">性别</td>
    <td width="130">专业</td>
    <td width="48">修改</td>
    <td width="48">删除</td>
  </tr>
  <%
  	StudentDao dao = new StudentDao();
	List<Student> stuList = dao.getStudents("select * from student");
	String sid, sname, birthday, gender, major;
	int age;
	for(int i=0;i<stuList.size();i++)
	{
		sid = stuList.get(i).getSid();
		sname = stuList.get(i).getSname();
		age = stuList.get(i).getAge();
		birthday = stuList.get(i).getBirthday();
		gender = stuList.get(i).getGender();
		major = stuList.get(i).getMajor();
  %>
  <tr>
    <td><%=sid%></td>
    <td><%=sname%></td>
    <td><%=age%></td>
    <td><%=birthday%></td>
    <td><%=gender%></td>
    <td><%=major%></td>
    <td><a href="modify.jsp?sid=<%=sid%>">修改</a></td>
    <td><a href="StudentServlet?from=remove&sid=<%=sid%>" onclick="return confirm('确定要删除吗？')">删除</a></td>
  </tr>
  <%
  	}
  %>
</table>
</body>
</html>