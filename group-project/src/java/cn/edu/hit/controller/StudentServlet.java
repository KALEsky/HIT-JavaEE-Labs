package cn.edu.hit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.dao.impl.StudentDaoImpl;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String from = request.getParameter("from");
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		if(userid == null)
		{
			response.sendRedirect("login.jsp");
			return;
		}
		if(from.equals("remove"))
		{
		String sid = request.getParameter("sid");
		StudentDao dao = new StudentDaoImpl();
		dao.remove(sid);
		response.sendRedirect("stulist.jsp");
		}
		else if(from.equals("modify"))
		{
			request.setCharacterEncoding("UTF-8");
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			StudentDao dao = new StudentDaoImpl();
			Student stu = new Student(sid,sname);
			dao.modify(stu);
			response.sendRedirect("stulist.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		StudentDao dao = new StudentDaoImpl();
		dao.add(new Student(sid,sname));
		response.sendRedirect("stulist.jsp");
	}

}
