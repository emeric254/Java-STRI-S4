package stri.java_connect.protocol;

import static org.junit.Assert.*;

import org.junit.Test;

public class ControlleurProtocoleTest
{

	@Test
	public void testTraiterRequete()
	{
		try
		{
			ControlleurProtocole.class.newInstance();
			fail("abstract method !!!");
		}
		catch (Exception e)
		{
			
		}
	}

	@Test
	public void testRequeteMethode()
	{
		String requete = "METHOD /URI/1/2/3\ncorps=test";
		assertEquals("METHOD", ControlleurProtocole.requeteMethode(requete));
		// TODO ajouter d'autres cas !
	}

	@Test
	public void testRequeteURI()
	{
		String requete = "METHOD /URI/1/2/3\ncorps=test";
		assertEquals("/URI/1/2/3", ControlleurProtocole.requeteURI(requete));
		// TODO ajouter d'autres cas !
	}

	@Test
	public void testRequeteCorps()
	{
		String requete = "METHOD /URI/1/2/3\ncorps=test";
		assertEquals("corps=test", ControlleurProtocole.requeteCorps(requete));
		// TODO ajouter d'autres cas !
	}

	@Test
	public void testTraiterReponse()
	{
		try
		{
			ControlleurProtocole.class.newInstance();
			fail("abstract method !!!");
		}
		catch (Exception e)
		{
			
		}
	}

	@Test
	public void testReponseCode()
	{
		String reponse = "{\"code\" : 123 , \"data\" : \"test\" }";
		assertEquals(123, ControlleurProtocole.reponseCode(reponse));
		// TODO ajouter d'autres cas !
	}

	@Test
	public void testReponseDonnees()
	{
		String reponse = "{\"code\" : 0 , \"data\" : \"test\" }";
		assertEquals("test", ControlleurProtocole.reponseDonnees(reponse));
		// TODO ajouter d'autres cas !
	}

}
