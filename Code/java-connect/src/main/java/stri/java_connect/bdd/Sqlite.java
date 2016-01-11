package stri.java_connect.bdd;

import java.sql.*;
import org.sqlite.*;

public class Sqlite
{
  public static void main( String args[] )
  {
	  SQLiteConnection connexion = null;
	  try
	  {
		connexion = new SQLiteConnection("./url-bdd", "nom-bdd");
		System.out.println( "SQLite database open with " + connexion.getDriverVersion() + " driver." );
		connexion.close();
	  }
	  catch (SQLException e)
	  {
		e.printStackTrace();
		System.exit(0);
	  }
	  System.out.println("Success !");
  }
}
