package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnectionUtil {
	
	static String url = "jdbc:mysql://localhost:3306/ShopSmart";
	static String username= "root";
	static String password = "1234";
	
	static Connection con = null;
	
	public static void main(String[] args) throws  ClassNotFoundException,SQLException
	{
		
		if(con==null)
		{
		con =DriverManager.getConnection(url,username,password);
		System.out.println("Connection Established");
		}
		else
		{
		System.out.println("Connection is already Established");
		}
	}

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
}