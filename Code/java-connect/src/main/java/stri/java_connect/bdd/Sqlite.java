package stri.java_connect.bdd;

import java.sql.*;
import org.sqlite.*;

public class Sqlite
{
	private Connection connexion;
	private Statement statement;
	
	public Sqlite(String dbFileName)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
			System.exit(0);
		}
		
		connexion = null;
		
		try
		{
			connexion = DriverManager.getConnection("jdbc:sqlite:./" + dbFileName);
			statement = connexion.createStatement();
			statement.setQueryTimeout(20);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		System.out.println("SQLite init done !");
	}
	
	public void Init()
	{
		try
		{
			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate("create table person (id integer, name string)");
			statement.executeUpdate("insert into person values(1, 'leo')");
			statement.executeUpdate("insert into person values(2, 'yui')");
			ResultSet rs = statement.executeQuery("select * from person");
			while(rs.next())
			{
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		System.out.println("BDD init Success !");
	}
	
	public void close()
	{
		try
		{
			if(connexion != null)
			{
				connexion.close();
			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		System.out.println("Bdd closed !");
	}
}
