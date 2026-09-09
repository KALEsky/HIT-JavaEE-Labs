<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>修改学生信息</title>
	</head>
	<body>
		<%
		String sid = request.getParameter("sid");
		StudentDao dao = new StudentDao();
		Student stu = dao.getBySid(sid);
		String sname = stu.getSname();
		int age = stu.getAge();
		String birthday = stu.getBirthday();
		String gender = stu.getGender();
		String major = stu.getMajor();
		%>
		<form name="form1" method="post" action="StudentServlet">
			<input type = "hidden" name="from" value= "modify"/>
			<table border="1">
				<tr>
					<td>学号</td>
					<td><input type="text" name="sid" value="<%=sid%>" readonly/></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="sname" value="<%=sname%>"/></td>
				</tr>
				<tr>
					<td>年龄</td>
					<td><input type="text" name="age" value="<%=age%>"/></td>
				</tr>
				<tr>
					<td>生日</td>
					<td><input type="text" name="birthday" value="<%=birthday%>"/></td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
					<input name="gender" type="radio" value="男"
					<%
					if(gender.equals("男"))
							out.print("checked=\"checked\""); 
					%>/>男
					<input name="gender" type="radio" value="女"
					<%
					if(gender.equals("女"))
							out.print("checked=\"checked\""); 
					%>
					/>女
					</td>
				</tr>
				<tr>
					<td>专业</td>
					<td>
						<select name="major">
							<option>电子与通信工程</option>
							<option>计算机科学与技术</option>
							<option>软件工程</option>
							<option>网络空间安全</option>
							<option>化学工程与工艺</option>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="提交"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>