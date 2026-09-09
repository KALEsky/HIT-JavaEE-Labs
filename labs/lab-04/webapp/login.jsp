<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,cn.edu.hit.entity.Student,cn.edu.hit.dao.StudentDao,java.net.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>

<body>
<%
String username = "";
String password = "";
Cookie[] cookies = request.getCookies();
if(cookies == null) 
{
	return;
}
if (cookies != null && cookies.length > 0) 
{
    for (Cookie c : cookies) 
    {
        if (c.getName().equals("usernameC")) 
        {
            username = URLDecoder.decode(c.getValue(), "utf-8");//使用URLDecoder解码
        }
        if (c.getName().equals("passwordC")) {
            password = URLDecoder.decode(c.getValue(), "utf-8");
        }
    }
}


%>
<form name="form1" method="post" action="LoginServlet">
  <table width="292" border="1" align="center">
    <tr>
      <td width="83">用户ID</td>
      <td width="193"><label>
        <input name="username" type="text" id="username" value="<%=username%>">
      </label></td>
    </tr>
    <tr>
      <td>密码</td>
      <td><label>
        <input name="password" type="password" id="password" value="<%=password%>">
      </label></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><label>
        <input type="submit" name="Submit" value="提交">
        <input name="isUseCookie" type="checkbox" checked="checked" value="yes">
		记住我</label></td>
    </tr>
  </table>
</form>

</body>
</html>