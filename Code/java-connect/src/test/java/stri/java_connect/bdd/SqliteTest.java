package stri.java_connect.bdd;


import java.sql.*;

import org.junit.*;
import static org.junit.Assert.*;

public class SqliteTest
{
	private static Sqlite testeur;
	
	@BeforeClass 
    public static void setup()
    {
    	System.out.println("\ndebut test");
        testeur = new Sqlite("JUnitTest.db");
    	testeur.reset();
    } 
    
    @AfterClass
    public static void reset()
    {
    	testeur.reset();
    	testeur.close();
    	System.out.println("fin test\n");
    }
    
	@Test
	public void testEntier()
	{

		if(testeur.executerMaj("drop table if exists person") && testeur.executerMaj("create table person (id integer, name string)"))
			System.out.println("partie Tables reussie");

		if(testeur.executerMaj("insert into person values(1, 'leo')") && testeur.executerMaj("insert into person values(2, 'yui')"))
			System.out.println("partie Insertion reussie");
		try
		{
			ResultSet rs = testeur.executerRequete("select * from person");
			
			assertNotNull(rs);
			
			int count = 0;
			while(rs.next())
			{
				// read the result set
//				System.out.println("name = " + rs.getString("name"));
//				System.out.println("id = " + rs.getInt("id"));
				if(rs.getString("name").equals("yui"))
					assertTrue(rs.getInt("id") == 2);
				else if(rs.getString("name").equals("leo"))
					assertTrue(rs.getInt("id") == 1);
				else
					fail("normalement il n'y a personne d'autre");
				count ++;
			}
			assertEquals(2, count);
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void creationTable()
	{
		assertTrue("on supprime la table avant de la creer", testeur.executerMaj("drop table if exists person") && testeur.executerMaj("create table person (id integer, name string)"));
	}
	
	@Test
	public void creationTable5Fois()
	{
		for(int i=0; i<5; i++)
		{
			System.out.println( " > " + (i+1) );
			assertTrue("on supprime la table avant de la creer", testeur.executerMaj("drop table if exists person") && testeur.executerMaj("create table person (id integer, name string)"));
		}
	}

	@Test
	public void creationTable5FoisSansSuppresion()
	{
		creationTable();
		for(int i=0; i<5; i++)
		{
			System.out.println( " > " + (i+1) );
			assertFalse("la table existe deja", testeur.executerMaj("create table person (id integer, name string)"));
		}
	}
}
