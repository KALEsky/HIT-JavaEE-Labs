package cn.edu.hit.utils;
import java.sql.*;

public class DBUtils {
	private static Connection con;
	private static Statement stmt;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(System.getenv().getOrDefault("JAVAEE_DB_URL", "jdbc:mysql://127.0.0.1:3306/hit"), System.getenv().getOrDefault("JAVAEE_DB_USER", "root"), System.getenv().getOrDefault("JAVAEE_DB_PASSWORD", "change-me"));
			stmt = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet executeQuery(String sql) {
		try {			
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int executeUpdate(String sql) {
		try {
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public static void close() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
