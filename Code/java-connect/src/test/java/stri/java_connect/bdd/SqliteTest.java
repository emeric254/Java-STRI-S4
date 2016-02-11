package stri.java_connect.bdd;


import java.sql.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqliteTest
{
	private static Sqlite testeur;
	
	@Before
    public void setUp()
    {
        try
        {
			testeur = new Sqlite("JUnitTest.db");
		} catch (ClassNotFoundException e)
        {
			fail("can't load sqlite !");
		}
    	testeur.reset();
    } 
    
    @After
    public void tearDown()
    {
    	testeur.reset();
    	testeur.close();
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
			assertTrue("on supprime la table avant de la creer", testeur.executerMaj("drop table if exists person") && testeur.executerMaj("create table person (id integer, name string)"));
		}
	}

	@Test
	public void creationTable3FoisSansSuppresion()
	{
		creationTable();
		for(int i=0; i<3; i++)
		{
			assertFalse("la table existe deja", testeur.executerMaj("create table person (id integer, name string)"));
			try
			{
				ResultSet rs = testeur.executerRequete("select * from person where name='test'");
				assertNotNull(rs);
				while(rs.next())
				{
					fail("la personne qui se nomme test ne devrait pas exister !");
				}
			}
			catch(SQLException e)
			{
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void memeInsertion5Fois()
	{
		testeur.executerMaj("create table person (id integer, name string)");
		testeur.executerMaj("insert into person values(1, 'leo')");
		for(int i=0; i<5; i++)
		{
			assertTrue("renvoie normalement ok meme si l'id existe deja", testeur.executerMaj("insert into person values(1, 'test')"));
		}
	}

	@Test
	public void memeSuppression5Fois()
	{
		testeur.executerMaj("create table person (id integer, name string)");
		testeur.executerMaj("insert into person values(1, 'leo')");
		for(int i=0; i<5; i++)
		{
			assertTrue("renvoie normalement ok meme si l'id n'y est plus", testeur.executerMaj("delete from person where id=1"));
		}
	}
	
	@Test
	public void testEntier()
	{

		assertTrue(testeur.executerMaj("drop table if exists person"));
		assertTrue(testeur.executerMaj("create table person (id integer, name string)"));
		assertTrue(testeur.executerMaj("insert into person values(1, 'leo')"));
		assertTrue(testeur.executerMaj("insert into person values(2, 'yui')"));
		
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

			rs = testeur.executerRequete("select * from person where name='leo'");
			assertNotNull(rs);
			count = 0;
			while(rs.next())
			{
				if(rs.getString("name").equals("leo"))
					assertTrue(rs.getInt("id") == 1);
				else
					fail("normalement il n'y a personne d'autre");
				count ++;
			}
			assertEquals(1, count);

			rs = testeur.executerRequete("select * from person where name='yui'");
			assertNotNull(rs);
			count = 0;
			while(rs.next())
			{
				if(rs.getString("name").equals("yui"))
					assertTrue(rs.getInt("id") == 2);
				else
					fail("normalement il n'y a personne d'autre");
				count ++;
			}
			assertEquals(1, count);
		}
		catch(SQLException e)
		{
			fail(e.getMessage());
		}
	}
}
