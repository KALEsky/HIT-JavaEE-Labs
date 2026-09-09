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
		<%
		String sid = request.getParameter("sid");
		StudentDao dao = new StudentDaoImpl();
		Student stu = dao.getBySid(sid);
		String sname =stu.getSname();
		%>
		<form action = "StudentServlet" method = "Get">
			<table border="1">
				<tr>
					<td>学号</td>
					<td><input type="text" name="sid" value = "<%=sid%>" readonly/></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="sname"value = "<%=sname%>"/></td>
				</tr>
				<tr>
					<td><input type = "submit" value = "提交"/></td>
				</tr>
			</table>
			<input type = "hidden" name = "from" value = "modify">
		</form>
	</body>
</html>