package cn.edu.hit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils 
{
	private static Connection con;
	public DbUtils()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(System.getenv().getOrDefault("JAVAEE_DB_URL", "jdbc:mysql://127.0.0.1:3306/hit"), System.getenv().getOrDefault("JAVAEE_DB_USER", "root"), System.getenv().getOrDefault("JAVAEE_DB_PASSWORD", "change-me"));
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close()
	{
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String sql)
	{
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeUpdate(String sql)
	{
		try 
		{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
