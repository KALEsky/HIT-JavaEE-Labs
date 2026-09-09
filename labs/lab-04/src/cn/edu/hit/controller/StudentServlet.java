package cn.edu.hit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.entity.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(username == null)
		{
			response.sendRedirect("login.jsp");
			return;
		}
		String from = request.getParameter("from");
		if(from.equals("add"))
		{
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			int age = Integer.valueOf(request.getParameter("age"));
			String birthday = request.getParameter("birthday");
			String gender = request.getParameter("gender");
			String major = request.getParameter("major");
			StudentDao dao = new StudentDao();
			dao.add(new Student(sid, sname, age, birthday, gender, major));
			response.sendRedirect("stulist.jsp");
		}
		else if(from.equals("modify"))
		{
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			int age = Integer.parseInt(request.getParameter("age"));
			String birthday = request.getParameter("birthday");
			String gender = request.getParameter("gender");
			String major = request.getParameter("major");
			StudentDao dao = new StudentDao();
			dao.modify(new Student(sid, sname, age, birthday, gender, major));
			response.sendRedirect("stulist.jsp");
		}
		else if(from.equals("remove"))
		{
			String sid = request.getParameter("sid");
			StudentDao dao = new StudentDao();
			dao.remove(sid);
			response.sendRedirect("stulist.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
