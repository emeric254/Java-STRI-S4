package stri.java_connect.server;

import static org.junit.Assert.*;

import org.junit.Test;

import stri.java_connect.protocol.ControlleurProtocole;

public class ControlleurProtocoleTest
{

	@Test
	public void testRequeteMethode()
	{
		String requete = "METHOD /URI/URI/URI/ID\n{DATA:TEST,DATA:TEST}";
		assertEquals("METHOD", ControlleurProtocole.requeteMethode(requete));
	}

	@Test
	public void testRequeteURI()
	{
		String requete = "METHOD /URI/URI/URI/ID\n{DATA:TEST,DATA:TEST}";
		assertEquals("/URI/URI/URI/ID", ControlleurProtocole.requeteURI(requete));
	}

	@Test
	public void testRequeteCorps()
	{
		String requete = "METHOD /URI/URI/URI/ID\n{DATA:TEST,DATA:TEST}";
		assertEquals("{DATA:TEST,DATA:TEST}", ControlleurProtocole.requeteCorps(requete));
	}

}
