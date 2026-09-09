<%@ page language="java" import = "cn.edu.hit.entity.*,cn.edu.hit.dao.*,cn.edu.hit.dao.impl.*,cn.edu.hit.utils.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>登录</title>
	    <style type="text/css">
<!--
.STYLE1 {font-size: 12px}
-->
        </style>
</head>
	<body>
	<%
	Cookie[] cookies = request.getCookies();
	String userid = "";
	for(int i = 0;i< cookies.length ;i++)
	{
		if(cookies!=null)
		{
			Cookie c = cookies[i];
			if(c.getName().equals("userid"))
			{
				userid = c.getValue();
			}
		}
	}
	%>
		<form name="form1" method="post" action="LoginServlet">
  			<table width="293" border="1">
			    <tr>
			      <td width="85">用户名</td>
			      <td width="192"><label>
			        <input name="userid" type="text" id="userid" value = "<%=userid%>">
			      </label></td>
			    </tr>
			    <tr>
			      <td>密码</td>
			      <td><label>
			        <input name="pwd" type="password" id="pwd">
			      </label></td>
			    </tr>
			    <tr>
			      <td>&nbsp;</td>
			      <td><label>
			        <input type="submit" name="Submit" value="提交">
			        <input type="checkbox" name="checkbox" id = "checkbox" value="checked">
			        <span class="STYLE1">			        记住用户名			        </span></label></td>
			    </tr>
			  </table>
		</form>

	</body>
</html>
