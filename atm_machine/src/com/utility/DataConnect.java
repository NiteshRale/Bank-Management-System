package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect 
{
	public static Connection getConnect() 
	{
		Connection con = null;
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","Nitesh@123");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return con;
		
	}
}
