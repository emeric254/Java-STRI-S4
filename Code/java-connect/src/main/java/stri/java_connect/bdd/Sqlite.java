package stri.java_connect.bdd;

import java.io.File;
import java.sql.*;

public class Sqlite
{
	private String nomBDD;
	private Connection connexion;
	private Statement statement;
	
	/**
	 * @param dbFileName
	 */
	public Sqlite(String dbFileName) throws ClassNotFoundException
	{
		nomBDD = dbFileName;
		Class.forName("org.sqlite.JDBC");
		dbConnect();
	}
	
	/**
	 * 
	 */
	public void dbConnect()
	{
		connexion = null;
		try
		{
			connexion = DriverManager.getConnection("jdbc:sqlite:./" + nomBDD);
			statement = connexion.createStatement();
			statement.setQueryTimeout(20);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void init()
	{
		if(executerMaj("drop table if exists person") && executerMaj("create table person (id integer, name string)"))
			System.out.println("partie Tables reussie");

		if(executerMaj("insert into person values(1, 'leo')") && executerMaj("insert into person values(2, 'yui')"))
			System.out.println("partie Insertion reussie");
		try
		{
			ResultSet rs = executerRequete("select * from person");
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
	}
	
	/**
	 * 
	 */
	public void close()
	{
		if(connexion != null)
		{
			try
			{
				connexion.close();
			}
			catch(SQLException e)
			{
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * @param sql
	 * @return
	 */
	public ResultSet executerRequete(String sql)
	{
		ResultSet rs = null;
		try
		{
			rs = statement.executeQuery(sql);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return rs;
	}
	
	/**
	 * @param sql
	 * @return
	 */
	public boolean executerMaj(String sql)
	{
		boolean state = true;
		try
		{
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			state = false;
		}
		return state;
	}
	
	/**
	 * 
	 */
	public void reset()
	{
		close();
		File temp = new File("./" + nomBDD);
		temp.delete();
		dbConnect();
	}
}
