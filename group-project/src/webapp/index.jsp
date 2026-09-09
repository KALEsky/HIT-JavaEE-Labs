<%@ page language="java" import = "cn.edu.hit.entity.*,cn.edu.hit.dao.*,cn.edu.hit.dao.impl.*,cn.edu.hit.utils.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
StudentDao dao = new StudentDaoImpl();
List<Student> stuList = dao.getAll("select * from student");
out.print("<table border = \"1\">");
for(int i = 0;i< stuList.size();i++)
{
	out.print("<tr>");
	out.print("<td>"+stuList.get(i).getSid()+"</td>");
	out.print("<td>"+stuList.get(i).getSname()+"</td>");
	out.print("<td>"+stuList.get(i).getGender()+"</td>");
	out.print("<td>"+stuList.get(i).getAge()+"</td>");
	out.print("<td>"+stuList.get(i).getBirthday()+"</td>");
	out.print("<td>"+stuList.get(i).getMajor()+"</td>");
	out.print("<tr/>");
}
out.print("</table>");
%>
</body>
</html>