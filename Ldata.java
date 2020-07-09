package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ldata {
	public static final String DB_NAME = "loc.sqlite";
	public static final String CONNECTION_STRING ="jdbc:sqlite:D:\\Softwares\\Databases\\"+ DB_NAME;
	public static final String TABLE_LOC = "qwerty";
	private Connection co;
	public boolean open()
	{
		try {
			co = DriverManager.getConnection(CONNECTION_STRING);
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
			if(co!= null)
			{
				co.close();
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public List<Loca> queryLoc()
	{
		StringBuilder s = new StringBuilder("SELECT * FROM ");
		s.append(TABLE_LOC);
		s.append(" WHERE ");
		s.append(" Count >"+ 500);
		System.out.println(s.toString());
		try(Statement sta = co.createStatement();
			ResultSet resul = sta.executeQuery(s.toString()))
		{
			List<Loca> loc = new ArrayList<>();
			while(resul.next())
			{
				Loca locat = new Loca();
				locat.setL(resul.getString("Location"));
				locat.setLC(resul.getInt("Count"));
				loc.add(locat);
			}
			return loc;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}

}
