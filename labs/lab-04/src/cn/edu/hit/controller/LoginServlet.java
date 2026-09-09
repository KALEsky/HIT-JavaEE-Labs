package cn.edu.hit.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hit.dao.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//把用户名和密码保存在Cookie对象中
        String username = URLEncoder.encode(request.getParameter("username"), "utf-8");
        //使用URLEncoder解决中文报错问题，在net包中
        String password = URLEncoder.encode(request.getParameter("password"), "utf-8");
        
		//String username = request.getParameter("username");
		//String pwd = request.getParameter("pwd");
        
		LoginDao dao = new LoginDao();
		boolean flag = dao.login(username, password);
		//String check = request.getParameter("checkbox");
		String[] isUseCookie = request.getParameterValues("isUseCookie");
		
		if(flag)
		{
			if (isUseCookie != null && isUseCookie.length > 0) 
			{
		        Cookie usernameCookie = new Cookie("usernameC", username);
		        Cookie passwordCookie = new Cookie("passwordC", password);
		        //保存上面两个对象
		        usernameCookie.setMaxAge(864000);
		        passwordCookie.setMaxAge(864000);//该Cookie对象保存多少秒（在这里是设置生存期限为十天）
		        response.addCookie(usernameCookie);
		        response.addCookie(passwordCookie);
		        response.sendRedirect("stulist.jsp");
		    } 
			else 
			{	//未选择记住，将Cookie对象置为失效
		        //以下表示找到已存在的Cookie对象
		        Cookie[] cookies = request.getCookies();
		        if (cookies != null && cookies.length > 0) 
		        {
		            for (Cookie c : cookies) 
		            {
		                if (c.getName().equals("usernameC") || c.getName().equals("passwordC")) 
		                {
		                    c.setMaxAge(0);//设置Cookie失效
		                    response.addCookie(c);//重新保存
		                }
		            }
		        }
		        response.sendRedirect("stulist.jsp");
		    }
		}
		else
		{
			response.getWriter().print("Error<br/>");
		}
		/*
		if(flag)
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			String isUseCookie = request.getParameter("isUseCookie");
			if((check!=null)&&(check.equals("yes")))
			{
				Cookie usernameCookie = new Cookie("username", username);
				usernameCookie.setMaxAge(60*60*24*7);
				response.addCookie(usernameCookie);
			}
			response.sendRedirect("stulist.jsp");
			
		}
		
		else
		{
			response.getWriter().print("Error<br/>");
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
