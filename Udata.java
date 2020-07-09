package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Udata {
	public static final String DB_NAME = "user.sqlite";
	public static final String CONNECTION_STRING ="jdbc:sqlite:D:\\Softwares\\Databases\\"+ DB_NAME;
	public static final String TABLE_USER = "qwerty";
	private Connection c;
	public boolean open()
	{
		try {
			c = DriverManager.getConnection(CONNECTION_STRING);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}	
	}
	public void close()
	{
		try
		{
			if(c!= null)
			{
				c.close();
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public List<User> queryUser()
	{
		StringBuilder s = new StringBuilder("SELECT * FROM ");
		s.append(TABLE_USER);
		s.append(" WHERE ");
		s.append(" Count >"+ 500);
		System.out.println(s.toString());
		try(Statement st = c.createStatement();
			ResultSet resu = st.executeQuery(s.toString()))
		{
			List<User> use = new ArrayList<>();
			while(resu.next())
			{
				User us = new User();
				us.setUs(resu.getString("User"));
				us.setUC(resu.getInt("Count"));
				use.add(us);
			}
			return use;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}

}
