<%@ page language="java" import = "cn.edu.hit.entity.*,cn.edu.hit.dao.*,cn.edu.hit.dao.impl.*,cn.edu.hit.utils.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = (String)session.getAttribute("userid");
	if(userid == null)
	{
		response.sendRedirect("login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="addStu.html">修改密码</a>
<br>
<br>
<a href="addStu.html">增加学生</a>
<table width="758" border="1">
  <tr>
    <td width="80">学号</td>
    <td width="78">姓名</td>
  </tr>
  <%
  StudentDao dao = new StudentDaoImpl();
  List<Student> stuList = dao.getAll("select * from student");
  String sid,sname;
  for(int i = 0;i< stuList.size();i++)
  {
	sid = stuList.get(i).getSid();
	sname = stuList.get(i).getSname();
  %>
  <tr>
    <td><%=sid%></td>
    <td><%=sname %></td>
    <td><a href="modifyStu.jsp?sid=<%=sid%>">修改</a></td>
    <td><a href="StudentServlet?sid=<%=sid%>&from=remove" onclick="return confirm('确定要删除吗')">删除</a></td>
  </tr>
  <%
  }
  %>
</table>
<br>
<a href="addTea.html">增加老师</a>
<table width="758" border="1">
  <tr>
    <td width="80">教师编号</td>
    <td width="78">姓名</td>
  </tr>
  <%
  StudentDao d = new StudentDaoImpl();
  List<Student> teaList = dao.getAll("select * from teacher");
  String tid,tname;
  for(int i = 0;i< teaList.size();i++)
  {
	tid = teaList.get(i).getSid();
	tname = teaList.get(i).getSname();
  %>
  <tr>
    <td><%=tid%></td>
    <td><%=tname %></td>
    <td><a href="modifyStu.jsp?sid=<%=tid%>">修改</a></td>
    <td><a href="StudentServlet?sid=<%=tid%>&from=remove" onclick="return confirm('确定要删除吗')">删除</a></td>
  </tr>
  <%
  }
  %>
</table>
<br>
<table width="758" border="1">
  <tr>
    <td width="80">课程</td>
    <td width="78">授课教师</td>
  </tr>
  <%
  StudentDao da = new StudentDaoImpl();
  List<Student> gList = dao.getAll("select * from grade");
  String gid;
  for(int i = 0;i< gList.size();i++)
  {
	gid = gList.get(i).getSid();
	tname = teaList.get(i).getSname();
  %>
  <tr>
    <td><%=gid%></td>
    <td><%=tname %></td>
    <td><a href="modifyStu.jsp?sid=<%=gid%>">修改</a></td>
  </tr>
  <%
  }
  %>
</table>
<br>
<table width="758" border="1">
  <tr>
    <td width="80">课程</td>
    <% 
  for(int i = 0;i< stuList.size();i++)
  {
	sname = stuList.get(i).getSname();
	%>
    <td width="20"><%=sname %></td>
  <%
  }
  %>
  </tr>
  <%
  for(int i = 0;i< gList.size();i++)
  {
	gid = gList.get(i).getSid();
  %>
  <tr>
    <td><%=gid%></td>
    <% 
  for(int j = 0;j< stuList.size();j++)
  {
		Random a = new Random();
	%>
    <td width="20"><%=a.nextInt(100) %></td>
  <%
  }
  %>
  </tr>
  <%
  }
  %>
</table>
<br>
</body>
</html>