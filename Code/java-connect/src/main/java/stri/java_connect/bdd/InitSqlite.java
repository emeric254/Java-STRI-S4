package stri.java_connect.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.sql.*;
import stri.java_connect.bdd.Sqlite;

public class InitSqlite {
	
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
}
