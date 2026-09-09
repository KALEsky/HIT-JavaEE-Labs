package cn.edu.hit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import cn.edu.hit.utils.DBUtils;
public class LoginDao {
	public boolean login(String userid,String pwd)
	{
		int count = 0;
		DBUtils du = new DBUtils();
		ResultSet rs = du.executeQuery("select count(*) from sysuser where userid = '" + userid +"'and pwd = '" + pwd +"'");
		try {
			rs.next();
			count = rs.getInt(1);			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count > 0 ?true:false;
	}
}
